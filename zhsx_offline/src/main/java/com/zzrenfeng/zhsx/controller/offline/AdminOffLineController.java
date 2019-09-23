package com.zzrenfeng.zhsx.controller.offline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WuFile;
import com.zzrenfeng.zhsx.service.CourResourceService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.Utils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 点播视频后台管理控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/adminOffLine")
public class AdminOffLineController extends BaseController {

	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;
	@Resource
	private CourResourceService courResourceService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private UserService userService;
	@Resource
	private SysSchoolService sysSchoolService;

	/**
	 * 是否是自动录制的视频
	 */
	private final String IS_RECORD_Y = "Y";

	/**
	 * 进入视频资源管理页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/videoRes")
	public String videoRes(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("isRecord", null);
		return "/admin/offLine/videoRes";
	}

	/**
	 * 评估录制资源页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/recordVideoRes")
	public String recordVideoRes(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("isRecord", IS_RECORD_Y);
		return "/admin/offLine/videoRes";
	}

	/**
	 * 获得视频资源
	 * 
	 * @param request
	 * @param videoResources
	 * @param model
	 * @param p
	 * @param pageType
	 * @return
	 */
	@RequestMapping("/getVideoRes")
	public String getVideoRes(HttpServletRequest request, OffLineVideoResources videoResources, Model model, Integer p,
			String isRecord) {
		if (p == null) {
			p = 1;
		}
		if (IS_RECORD_Y.equals(isRecord)) {
			videoResources.setIsRecord(IS_RECORD_Y);
			videoResources.setSchoolId(getUserSchoolId());
		} else {
			videoResources.setUserId(getUserId());
		}
		Page<OffLineVideoResources> pageInfo = offLineVideoResourcesService.findPageSelective(videoResources, p, 14);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		List<OffLineVideoResources> lists = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);
		model.addAttribute("search", videoResources.getSearch());
		model.addAttribute("sortord", videoResources.getSortord());

		return "/admin/offLine/videoResData";
	}

	/**
	 * 文件上传页面
	 * 
	 * @param request
	 * @param modle
	 * @return
	 */
	@RequestMapping("/fileUpload")
	public String fileUpload(HttpServletRequest request, Model modle, WuFile wuFile) {
		String size = Utils.sizeToString(Long.valueOf(wuFile.getSize()));
		wuFile.setSize(size);
		modle.addAttribute("file", wuFile);
		return "/admin/offLine/fileUpload";
	}

	/**
	 * 添加文件到数据库
	 * 
	 * @param response
	 * @param videoResources
	 */
	@RequestMapping("/insterVideoRes")
	public void insterVideoRes(HttpServletResponse response, HttpServletRequest request,
			OffLineVideoResources videoResources) {
		try {
			videoResources.setUserId(getUserId());
			videoResources.setTranscodingState("U");
			videoResources.setProgress(0);
			videoResources.setReleaseState("N");
			videoResources.setIsShow("Y");
			videoResources.setPageView(0);
			videoResources.setCreateTime(new Date());
			String videoPath = videoResources.getVideoPath().substring(0,
					videoResources.getVideoPath().lastIndexOf(".")) + ".flv";
			String picPath = videoResources.getVideoPath().substring(0, videoResources.getVideoPath().lastIndexOf("."))
					+ ".jpg";
			videoResources.setVideoPath(videoPath);
			videoResources.setPicPath(picPath);

			videoResources.setSchoolId(getUserSchoolId());
			videoResources.setTeacherId(getUserId());
			videoResources.setUploadName("N");
			videoResources.setTitle(videoResources.getTitle().substring(0, videoResources.getTitle().lastIndexOf(".")));

			String bak2 = Utils.sizeToString(Long.valueOf(videoResources.getBak2())); // 原视频大小
			videoResources.setBak2(bak2);

			offLineVideoResourcesService.insert(videoResources);
			String webpath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
					+ request.getRequestURI().split("/")[1] + "/adminOffLine/updateVideoRes";
			String jsonString = "{\"id\":\"" + videoResources.getId() + "\",\"webpath\":\"" + webpath + "\"}";
			WriterUtils.toJson(response, jsonString);
		} catch (Exception e) {
			WriterUtils.toHtml(response, "");
			e.printStackTrace();
		}
	}

	/**
	 * 远程更新数据库
	 * 
	 * @param response
	 * @param videoResources
	 */
	@RequestMapping("/updateVideoRes")
	public void updateVideoRes(HttpServletResponse response, OffLineVideoResources videoResources) {
		offLineVideoResourcesService.renewalData(videoResources);
	}

	/**
	 * 获得视频的转码进度
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getVideoResProgress")
	public @ResponseBody OffLineVideoResources getVideoResProgress(String id) {
		OffLineVideoResources videoResources = offLineVideoResourcesService.findByKey(id);
		return videoResources;
	}

	/**
	 * 删除视频资源
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delVideoRes")
	public void delVideoRes(HttpServletResponse response, String id) {
		try {
			offLineVideoResourcesService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("/batchDelVideoRes")
	public void batchDelVideoRes(HttpServletResponse response, String[] del_id) {
		List<String> ids = Arrays.asList(del_id);
		try {
			offLineVideoResourcesService.batchDelVideoRes(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入视频资源编辑页面
	 * 
	 * @param model
	 * @param id
	 * @param type
	 *            (前台 Q,后台 H)
	 * @return
	 */
	@RequestMapping("/editVideoRes")
	public String editVideoRes(Model model, String id, String type) {
		model.addAttribute("type", type);
		// 获得视频资源信息
		OffLineVideoResources videoResources = offLineVideoResourcesService.findByKey(id);
		model.addAttribute("videoResources", videoResources);

		// 获得学校
		SysSchool sysSchool = new SysSchool();
		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			sysSchool.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (ids != null && ids.size() > 0)
				sysSchool.setIds(ids);
		}
		List<SysSchool> schoolList = sysSchoolService.findSelective(sysSchool);
		model.addAttribute("schoolList", schoolList);

		// 获得教师
		User user = new User();
		user.setSchoolId(videoResources.getSchoolId());
		List<User> userList = userService.findSelective(user);
		model.addAttribute("userList", userList);

		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("gradeList", sysDicts);

		// 获得科目
		sysDict.setValue(videoResources.getGradeName());
		sysDicts = sysDictService.findSelective(sysDict);
		if (sysDicts != null && sysDicts.size() > 0) {
			sysDict.setPid(sysDicts.get(0).getId());
		}
		sysDict.setKeyname("S");
		sysDict.setValue(null);
		sysDicts = sysDictService.findSelective(sysDict);
		// 科目去重复
		List<SysDict> subjects = new ArrayList<SysDict>();
		String tem = "1";
		for (SysDict o : sysDicts) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		model.addAttribute("subjectsList", subjects);

		return "/admin/offLine/editVideoRes";
	}

	/**
	 * 更新数据库信息
	 * 
	 * @param response
	 * @param videoResources
	 */
	@RequestMapping("/updateVideoRes1")
	public void updateVideoRes1(HttpServletResponse response, OffLineVideoResources videoResources) {
		try {
			offLineVideoResourcesService.updateByKeySelective(videoResources);
			if (videoResources.getTeacherId() != null && !videoResources.getTeacherId().equals("")) {
				offLineVideoResourcesService.updateUserExp(videoResources.getTeacherId(), videoResources.getId());
			}
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入视频审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/reviewVideoRes")
	public String reviewVideo() {
		return "/admin/offLine/reviewVideoRes";
	}

	/**
	 * 获得审核视频数据
	 * 
	 * @param request
	 * @param videoResources
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping("/reviewVideoResData")
	public String reviewVideoResData(HttpServletRequest request, OffLineVideoResources videoResources, Model model,
			Integer p) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);

		// videoResources.setUserId(getUserId());
		Page<OffLineVideoResources> pageInfo = offLineVideoResourcesService.findPageSelective(videoResources, p, 10);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		List<OffLineVideoResources> lists = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("total", total);
		model.addAttribute("lists", lists);
		return "/admin/offLine/reviewVideoResData";
	}

	/**
	 * 审核视频播放页面
	 * 
	 * @param mdoel
	 * @param id
	 * @param picPath
	 * @return
	 */
	@RequestMapping("/reviewVideoPlay")
	public String reviewVideoPlay(Model mdoel, String id, String picPath, String bak) {
		mdoel.addAttribute("id", id);
		mdoel.addAttribute("picPath", picPath);
		mdoel.addAttribute("bak", bak);
		return "/admin/offLine/reviewVideoPlay";
	}

	/**
	 * 验证文件是否存在
	 * 
	 * @param fileMd5
	 *            文件md5值
	 * @param size
	 *            文件大小
	 * @return
	 */
	@RequestMapping("/verifyVideoRes")
	public @ResponseBody boolean verifyVideoRes(String fileMd5, String size) {
		boolean isok = true;
		OffLineVideoResources videores = new OffLineVideoResources();
		videores.setUserId(getUserId());
		videores.setBak1(fileMd5);
		size = Utils.sizeToString(Long.valueOf(size));
		videores.setBak2(size);
		List<OffLineVideoResources> videoResList = offLineVideoResourcesService.findSelective(videores);
		if (videoResList != null && videoResList.size() > 0) {
			isok = false;
		}
		return isok;
	}

}
