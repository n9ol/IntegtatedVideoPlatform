package com.zzrenfeng.zhsx.controller.offline;

import java.util.ArrayList;
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
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysHistoryService;

/**
 * 点播
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/offLine")
public class OffLineController extends BaseController {

	@Resource
	private OffLineVideoResourcesService videoResourcesService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private String fileWebPath;
	@Resource
	private SysHistoryService sysHistoryService;

	/**
	 * 进入点播首页
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/dianbo")
	public String dianbo(HttpServletResponse response, HttpServletRequest request, Model model, String type) {
		model.addAttribute("type", type);

		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("gradeList", sysDicts);

		// 获得科目
		sysDict.setKeyname("S");
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

		// 获得热门推荐
		OffLineVideoResources videoResources = new OffLineVideoResources();
		videoResources.setReleaseState("Y");
		videoResources.setTranscodingState("O");
		videoResources.setIsShow("Y");
		videoResources.setType(type);
		videoResources.setSortord("view");
		Page<OffLineVideoResources> pageInfo = videoResourcesService.findPageSelective(videoResources, 1, 4);
		List<OffLineVideoResources> lists = pageInfo.getResult();
		model.addAttribute("hotLists", lists);

		return "/web/offline/dianbo";
	}

	/**
	 * 获得视频资源
	 * 
	 * @param request
	 * @param videoResources
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping("/dianboData")
	public String dianboData(HttpServletRequest request, OffLineVideoResources videoResources, Model model, Integer p) {

		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);

		videoResources.setReleaseState("Y");
		videoResources.setTranscodingState("O");
		videoResources.setIsShow("Y");

		Page<OffLineVideoResources> pageInfo = videoResourcesService.findPageSelective(videoResources, p, 9);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		List<OffLineVideoResources> lists = pageInfo.getResult();

		model.addAttribute("pages", pages);
		model.addAttribute("total", total);
		model.addAttribute("lists", lists);

		return "/web/offline/dianboData";
	}

	/**
	 * 进入视频播放页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/videoPlayback")
	public String videoPlayback(Model model, String id, String type) {

		OffLineVideoResources vr = videoResourcesService.findByKey(id);
		model.addAttribute("vr", vr);
		model.addAttribute("type", type);

		// 获得推荐课程
		OffLineVideoResources videoResources = new OffLineVideoResources();
		videoResources.setReleaseState("Y");
		videoResources.setTranscodingState("O");
		videoResources.setIsShow("Y");
		videoResources.setType(type);
		videoResources.setGradeName(vr.getGradeName());
		videoResources.setSubjectName(vr.getSubjectName());
		Page<OffLineVideoResources> pageInfo = videoResourcesService.findPageSelective(videoResources, 1, 8);
		List<OffLineVideoResources> lists = pageInfo.getResult();
		model.addAttribute("hotLists", lists);

		// 添加观看记录
		SysHistory sysh = new SysHistory();
		sysh.setUserId(getUserId());
		sysh.setPubFlag(SysHistory.PUBFLAG_K);
		sysh.setPubType(SysHistory.PUBTYPE_V);
		sysh.setPubId(id);
		List<SysHistory> syshList = sysHistoryService.findSelective(sysh);
		if (syshList == null || syshList.size() == 0) {
			sysHistoryService.insert(sysh);
		}

		// 查看是否收藏
		sysh.setPubFlag(SysHistory.PUBFLAG_C);
		syshList = sysHistoryService.findSelective(sysh);
		if (syshList != null && syshList.size() > 0) {
			model.addAttribute("isCollect", "Y");
		} else {
			model.addAttribute("isCollect", "N");
		}

		// 更新浏览量
		videoResourcesService.updatePageView(id);

		return "/web/offline/db_bofangye";
	}

	/**
	 * 下载视频
	 * 
	 * @param id
	 * @param filename
	 */
	@ResponseBody
	@RequestMapping("/downloadVideoResources")
	public String downloadVideoResources(@RequestParam String id) {
		String filepath = videoResourcesService.findVideoPathById(id);
		return filepath;
	}

}
