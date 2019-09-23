package com.zzrenfeng.zhsx.controller.courres;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.CourResource;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WuFile;
import com.zzrenfeng.zhsx.service.CourResourceService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.DownloadUtils;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.Utils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 课件资源控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/adminCourRes")
public class AdminCourResController extends BaseController {

	@Resource
	private CourResourceService courResourceService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private UserService userService;
	@Resource
	private String fileWebPath;

	/**
	 * 进入课件资源管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/courRes")
	public String courRes(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/admin/courRes/courRes";
	}

	/**
	 * 获得课件资源
	 * 
	 * @param request
	 * @param response
	 * @param courResource
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping("/getCourRes")
	public String getCourRes(HttpServletRequest request, CourResource courResource, Model model, Integer p,
			String pageType) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);

		model.addAttribute("search", courResource.getSearch());
		model.addAttribute("sortord", courResource.getSortord());

		courResource.setUploadPersonId(getUserId());
		Page<CourResource> pageInfo = courResourceService.findPageSelective(courResource, p, 14);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		List<CourResource> lists = pageInfo.getResult();

		model.addAttribute("pages", pages);
		model.addAttribute("total", total);
		model.addAttribute("lists", lists);

		return "/admin/courRes/courResData";
	}

	/**
	 * 删除课件资源
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delCourRes")
	public void delCourRes(HttpServletResponse response, String id) {
		try {
			courResourceService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入编辑资源页面
	 * 
	 * @param model
	 * @param id
	 * @param type
	 *            (前台 Q, 后台 H)
	 * @return
	 */
	@RequestMapping("/editCourRes")
	public String editCourRes(Model model, String id, String type) {
		model.addAttribute("type", type);

		CourResource courResource = courResourceService.findByKey(id);
		model.addAttribute("courResource", courResource);

		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("gradeList", sysDicts);

		// 获得科目
		sysDict.setValue(courResource.getGradeName());
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

		// 获得版本
		sysDict.setPid(null);
		sysDict.setValue(null);
		sysDict.setKeyname("V");
		sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("versionList", sysDicts);

		return "/admin/courRes/editCourRes";
	}

	/**
	 * 修改数据库页面
	 * 
	 * @param response
	 * @param courResource
	 */
	@RequestMapping("/updataCourRes")
	public void updataCourRes(HttpServletResponse response, CourResource courResource) {
		try {
			courResourceService.updateByKeySelective(courResource);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
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
		return "/admin/courRes/fileUpload";
	}

	/**
	 * 添加数据到数据库
	 * 
	 * @param request
	 * @param courResource
	 */
	@RequestMapping("/insterCourRes")
	public void insterCourRes(HttpServletResponse response, CourResource courResource) {
		String size = Utils.sizeToString(Long.valueOf(courResource.getSize()));
		courResource.setSize(size);
		courResource.setState("N");
		courResource.setUploadPersonId(getUserId());
		courResource.setAuthorId(getUserId());
		courResource.setCreateTime(new Date());
		courResource.setBak2("Y");
		courResource.setType(courResource.getType().toLowerCase());
		String pdfPath = courResource.getDownloadPath().substring(0, courResource.getDownloadPath().lastIndexOf("."));
		courResource.setPdfPath(pdfPath + ".pdf");
		try {
			courResourceService.insert(courResource);
			if (getUserType().equals(User.userType_teachers)) {
				courResourceService.addUserExp(getUserId(), courResource.getId());
			}
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 下载资源
	 * 
	 * @param id
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/downloadCourRes")
	public void downloadCourRes(HttpServletResponse response, HttpServletRequest request, String id)
			throws UnsupportedEncodingException {
		CourResource courRes = courResourceService.findByKey(id);
		String filepath = fileWebPath + "/courRes" + courRes.getDownloadPath();
		DownloadUtils.downloadInternet(response, request, filepath, courRes.getName());
	}

	/**
	 * 批量下载资源
	 * 
	 * @param response
	 * @param dow_id
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/batchDownload")
	public void batchDownload(HttpServletResponse response, HttpServletRequest request, String[] dow_id)
			throws UnsupportedEncodingException {
		for (int i = 0; i < dow_id.length; i++) {
			downloadCourRes(response, request, dow_id[i]);
		}
	}

	/**
	 * 批量删除资源
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("/batchDelCourRes")
	public void batchDelCourRes(HttpServletResponse response, String[] del_id) {
		List<String> ids = Arrays.asList(del_id);
		try {
			courResourceService.batchDelCourRes(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入批量编辑页面
	 * 
	 * @param model
	 * @param edit_id
	 * @return
	 */
	@RequestMapping("/batchEdit")
	public String batchEdit(Model model, String[] edit_id) {
		model.addAttribute("edit_id", edit_id);
		List<String> ids = Arrays.asList(edit_id);
		model.addAttribute("ids", ids);
		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("gradeList", sysDicts);

		// 获得版本
		sysDict.setKeyname("V");
		sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("versionList", sysDicts);

		return "/admin/courRes/batchEdit";
	}

	/**
	 * 批量更新课件资源 数据库基本信息
	 * 
	 * @param response
	 * @param courResource
	 * @param edit_id
	 */
	@RequestMapping("/batchUpdataCourRes")
	public void batchUpdataCourRes(HttpServletResponse response, CourResource courResource, String[] del_id) {
		try {
			courResourceService.batchUpdataCourRes(courResource, del_id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入课件资源审核页面
	 * 
	 * @param request
	 * @param courResource
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping("/checkCourRes")
	public String checkCourRes(HttpServletRequest request, CourResource courResource, Model model, Integer p) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);

		model.addAttribute("search", courResource.getSearch());
		model.addAttribute("sortord", courResource.getSortord());

		Page<CourResource> pageInfo = courResourceService.findPageSelective(courResource, p, 12);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		List<CourResource> lists = pageInfo.getResult();

		model.addAttribute("pages", pages);
		model.addAttribute("total", total);
		model.addAttribute("lists", lists);

		return "/admin/courRes/checkCourRes";
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
	@RequestMapping("/verifyCourRes")
	public @ResponseBody boolean verifyCourRes(String fileMd5, String size) {
		boolean isok = true;
		CourResource courRes = new CourResource();
		courRes.setUploadPersonId(getUserId());
		courRes.setFileMd5(fileMd5);
		size = Utils.sizeToString(Long.valueOf(size));
		courRes.setSize(size);
		List<CourResource> crList = courResourceService.findSelective(courRes);
		if (crList != null && crList.size() > 0) {
			isok = false;
		}
		return isok;
	}

	/**
	 * 课件审核页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/auditCourRes")
	public String auditCourRes(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/admin/courRes/auditCourRes";
	}

}
