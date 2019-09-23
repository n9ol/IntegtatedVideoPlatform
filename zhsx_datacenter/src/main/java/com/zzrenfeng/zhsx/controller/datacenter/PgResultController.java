package com.zzrenfeng.zhsx.controller.datacenter;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.WebPj;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.WebPjService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;

/**
 * 数据中心 - 直播课程评估结果查看 控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/pgResult")
public class PgResultController extends BaseController {

	@Resource
	private WebPjService webPjService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private CourseScheduleService courseScheduleService;

	/**
	 * 进入评估列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/sjzx_evaluationResult")
	public String sjzxEvaluationResult(Model model, Integer p, String gradeId, String subjectId, String search) {

		// 获得年级
		List<SysDict> grades = sysDictService.listSpecialty();
		model.addAttribute("grades", grades);

		// 获得科目 去重复
		List<SysDict> listSubject = sysDictService.listSubject();
		model.addAttribute("subjects", listSubject);

		model.addAttribute("p", p);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("search", search);
		return "/web/datacenter/pgresult/sjzx_evaluationResult";
	}

	/**
	 * 获得评估列表数据
	 * 
	 * @param model
	 * @param p
	 * @param loSchedule
	 * @return
	 */
	@RequestMapping("/sjzx_evaluationResultData")
	public String sjzx_evaluationResultData(Model model, Integer p, WebPj webPj, String gradeId, String subjectId) {
		if (p == null) {
			p = 1;
		}
		webPj.setCourScheduleSpecialtyName(gradeId);
		webPj.setCourScheduleSubjectName(subjectId);
		Page<WebPj> pageInfo = webPjService.listWebPjResult(webPj, p, 6);
		List<WebPj> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();
		model.addAttribute("pageNum", p);
		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lists", lists);
		return "/web/datacenter/pgresult/sjzx_evaluationResultData";
	}

	/**
	 * 查看评估结果
	 * 
	 * @param model
	 * @param id
	 * @param p,gradeId,subjectId,search
	 *            用于从数据中心查看评估结果时,点击返回按钮查询信息一致
	 * @param addTime
	 * @param isNotGoBack
	 *            当从个人中心进入评估结果查看页面时不显示返回键
	 * @return
	 */
	@RequestMapping("/PgResult")
	public String PgResult(Model model, @RequestParam String id, Integer p, String gradeId, String subjectId,
			String search, Date addTime, String isNotGoBack) {
		model.addAttribute("id", id);
		model.addAttribute("p", p);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("search", search);
		model.addAttribute("addTime", addTime);
		model.addAttribute("isNotGoBack", isNotGoBack);
		return "/web/datacenter/pgresult/PgResult";
	}

}
