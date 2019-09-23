package com.zzrenfeng.zhsx.controller.web.pgselfmotion;

import java.text.ParseException;
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

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AnswerQuestionsCollected;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.service.AnswerQuestionsCollectedService;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;

/**
 * 自动评估控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/pgSelfMotion")
public class PgSelfMotionController extends BaseController {

	@Resource
	private AnswerQuestionsCollectedService answerQuestionsCollectedService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private LoFscheduleService loFscheduleService;
	@Resource
	private CourseScheduleService courseScheduleService;

	/**
	 * 添加数据到数据库
	 * 
	 * @param request
	 * @param response
	 * @throws ParseException
	 */
	@RequestMapping("/insteraqc")
	public void insteraqc(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		System.out.println("--进来了--");
		String classCode = request.getParameter("classCode");
		String timelength = request.getParameter("timelength");
		String type = request.getParameter("type");

		if (classCode != null && !classCode.equals("") && timelength != null && !timelength.equals("") && type != null
				&& !type.equals("")) {
			Date date = new Date();

			AnswerQuestionsCollected answerQuestionsCollected = new AnswerQuestionsCollected();
			answerQuestionsCollected.setTimelength(timelength);
			answerQuestionsCollected.setCreatetime(date);
			answerQuestionsCollected.setCreatedate(date);
			answerQuestionsCollected.setType(Integer.valueOf(type));
			LoSchedule sz = loScheduleService.findByCDtime(classCode);
			if (sz != null) {
				answerQuestionsCollected.setClassid(sz.getClassId());
				if (sz.getSubjectId() != null) {
					answerQuestionsCollected.setSubjectname(sz.getSubjectId());
				}
				int i = answerQuestionsCollectedService.getPeriodTime(sz.getStartDate());
				answerQuestionsCollected.setIsz(i);
				answerQuestionsCollected.setZid(sz.getId());
				answerQuestionsCollectedService.recordAnswerQuestionsCollected(answerQuestionsCollected);
			}
			LoSchedule sf = loScheduleService.findFByCDtime(classCode);
			if (sf != null) {
				answerQuestionsCollected.setClassid(sf.getClassId());
				if (sf.getSubjectId() != null) {
					answerQuestionsCollected.setSubjectname(sf.getSubjectId());
				}
				int i = answerQuestionsCollectedService.getPeriodTime(sf.getStartDate());
				answerQuestionsCollected.setIsz(i);
				answerQuestionsCollected.setZid(sf.getId());
				answerQuestionsCollectedService.recordAnswerQuestionsCollected(answerQuestionsCollected);
			}
		}
	}

	/**
	 * 获得自动评估统计数据
	 * 
	 * @param pgId
	 * @return
	 */
	@RequestMapping("/zidongpg")
	public String zidongpg(Model model, String pgId) {
		// 课堂活跃度统计获取
		if (pgId != null) {

			// LoSchedule los = loScheduleService.findByKey(pgId);
			CourseSchedule los = courseScheduleService.getCourseSchedule(pgId);

			AnswerQuestionsCollected answerQuestionsCollected = new AnswerQuestionsCollected();
			answerQuestionsCollected.setZid(pgId);
			answerQuestionsCollected.setClassid(los.getClassroomId());

			String legendData = "'本地教室'";
			String seriesdata = "{ name:'本地教室',type:'line', data:[";
			List<AnswerQuestionsCollected> zList = answerQuestionsCollectedService
					.findSelective(answerQuestionsCollected);
			for (AnswerQuestionsCollected z : zList) {
				seriesdata += z.getTimelength() + ",";
			}
			seriesdata += "]}";

			String seriesdatanum = "{ name:'本地教室',type:'bar', data:[";
			List<AnswerQuestionsCollected> zListnum = answerQuestionsCollectedService.findNumByzid(pgId,
					los.getClassroomId());
			for (int i = 1; i <= 4; i++) {
				int j = 0;
				for (AnswerQuestionsCollected anum : zListnum) {
					if (anum.getIsz() == i) {
						j = anum.getNum();
					}
				}
				seriesdatanum += j + ",";
			}
			seriesdatanum += "]}";

			List<AnswerQuestionsCollected> z1List = new ArrayList<>();
			List<AnswerQuestionsCollected> z2List = new ArrayList<>();
			List<AnswerQuestionsCollected> z3List = new ArrayList<>();
			LoFschedule lof = new LoFschedule();
			lof.setZId(pgId);
			List<LoFschedule> lofList = loFscheduleService.findSelective(lof);
			if (lofList != null && lofList.size() > 0) {
				legendData += ",'远程教室1'";
				answerQuestionsCollected.setClassid(lofList.get(0).getClassId());
				z1List = answerQuestionsCollectedService.findSelective(answerQuestionsCollected);
				seriesdata += ",{name:'远程教室1',type:'line',stack: '远程教室',data:[";
				for (AnswerQuestionsCollected z1 : z1List) {
					seriesdata += z1.getTimelength() + ",";
				}
				seriesdata += "]}";

				List<AnswerQuestionsCollected> z1Listnum = answerQuestionsCollectedService.findNumByzid(pgId,
						lofList.get(0).getClassId());
				seriesdatanum += ",{name:'远程教室1',type:'bar',stack: '远程教室',data:[";
				for (int i = 1; i <= 4; i++) {
					int j = 0;
					for (AnswerQuestionsCollected anum : z1Listnum) {
						if (anum.getIsz() == i) {
							j = anum.getNum();
						}
					}
					seriesdatanum += j + ",";
				}
				seriesdatanum += "]}";

				if (lofList.size() >= 2) {
					answerQuestionsCollected.setClassid(lofList.get(1).getClassId());
					z2List = answerQuestionsCollectedService.findSelective(answerQuestionsCollected);
					legendData += ",'远程教室2'";
					seriesdata += ",{name:'远程教室2',type:'line',stack: '远程教室',data:[";
					for (AnswerQuestionsCollected z2 : z2List) {
						seriesdata += z2.getTimelength() + ",";
					}
					seriesdata += "]}";

					List<AnswerQuestionsCollected> z2Listnum = answerQuestionsCollectedService.findNumByzid(pgId,
							lofList.get(1).getClassId());
					seriesdatanum += ",{name:'远程教室2',type:'bar',stack: '远程教室',data:[";
					for (int i = 1; i <= 4; i++) {
						int j = 0;
						for (AnswerQuestionsCollected anum : z2Listnum) {
							if (anum.getIsz() == i) {
								j = anum.getNum();
							}
						}
						seriesdatanum += j + ",";
					}
					seriesdatanum += "]}";

				}

				if (lofList.size() >= 3) {
					answerQuestionsCollected.setClassid(lofList.get(2).getClassId());
					z3List = answerQuestionsCollectedService.findSelective(answerQuestionsCollected);
					legendData += ",'远程教室3'";
					seriesdata += ",{name:'远程教室3',type:'line',stack: '远程教室',data:[";
					for (AnswerQuestionsCollected z3 : z3List) {
						seriesdata += z3.getTimelength() + ",";
					}
					seriesdata += "]}";

					List<AnswerQuestionsCollected> z3Listnum = answerQuestionsCollectedService.findNumByzid(pgId,
							lofList.get(2).getClassId());
					seriesdatanum += ",{name:'远程教室3',type:'bar',stack: '远程教室',data:[";
					for (int i = 1; i <= 4; i++) {
						int j = 0;
						for (AnswerQuestionsCollected anum : z3Listnum) {
							if (anum.getIsz() == i) {
								j = anum.getNum();
							}
						}
						seriesdatanum += j + ",";
					}
					seriesdatanum += "]}";

				}
			}

			int[] arrayLength = { zList.size(), z1List.size(), z2List.size(), z3List.size() };
			Arrays.sort(arrayLength);
			int maxNumber = arrayLength[(arrayLength.length - 1)];

			String xdata = "";
			for (int i = 1; i <= maxNumber; i++) {
				xdata += "'" + i + "',";
			}

			if (xdata.equals("")) {
				xdata = "'0'";
				seriesdata = "'0'";
				seriesdatanum = "'0','0','0','0'";
			}

			model.addAttribute("legendData", legendData);
			model.addAttribute("xdata", xdata);
			model.addAttribute("seriesdata", seriesdata);
			model.addAttribute("seriesdatanum", seriesdatanum);
		}
		return "/web/pg/zidongpg";
	}

}
