package com.zzrenfeng.zhsx.controller.datacenter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.WebPjService;

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

	/**
	 * 进入评估列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/sjzx_evaluationResult")
	public String sjzxEvaluationResult(Model model, Integer p, String gradeId, String subjectId, String search) {

		// 获得年级
		SysDict dict = new SysDict();
		dict.setKeyname("G");
		List<SysDict> grades = sysDictService.findSelective(dict);
		model.addAttribute("grades", grades);

		// 获得科目 去重复
		List<SysDict> subjects = new ArrayList<SysDict>();
		dict.setKeyname("S");
		List<SysDict> subject = sysDictService.findSelective(dict);
		String tem = "1";
		for (SysDict o : subject) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		model.addAttribute("subjects", subjects);

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
	public String sjzx_evaluationResultData(Model model, Integer p, LoSchedule loSchedule) {

		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);// 当前页

		loSchedule.setTimeSorting("G");
		loSchedule.setIspj("Y");
		loSchedule.setShootingWay("DESC");
		Page<LoSchedule> pageInfo = loScheduleService.findPageSelective(loSchedule, p, 6);
		int pages = pageInfo.getPages();// 总页数
		List<LoSchedule> lists = pageInfo.getResult();
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);

		return "/web/datacenter/pgresult/sjzx_evaluationResultData";
	}

	/**
	 * 查看评估结果
	 * 
	 * @return
	 */
	@RequestMapping("/PgResult")
	public String PgResult(Model model, @RequestParam String id, Integer p, String gradeId, String subjectId,
			String search, String isReturn) {
		model.addAttribute("id", id);
		model.addAttribute("duke", "D");

		model.addAttribute("p", p);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("search", search);
		model.addAttribute("isReturn", isReturn);
		return "/web/datacenter/pgresult/PgResult";
	}

}
