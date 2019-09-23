package com.zzrenfeng.zhsx.controller.web.personalCenter;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.CourResource;
import com.zzrenfeng.zhsx.model.LoPgCour;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.PgPjinfo;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.WebPj;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleBigTime;
import com.zzrenfeng.zhsx.service.CourResourceService;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoPgCourService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.PgPjinfoService;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.WebPjService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleBigTimeService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 个人中心控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/personalCenter")
public class PersonalCenterController extends BaseController {

	@Resource
	private LoTermTimeService loTermTimeService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private LoClassTimeService loClassTimeService;
	@Resource
	private PgPjinfoService pgPjinfoService;
	@Resource
	private CourResourceService courResourceService;
	@Resource
	private WebPjService webPjService;
	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;
	@Resource
	private LoPgCourService loPgCourService;
	@Resource
	private SysHistoryService sysHistoryService;
	@Resource
	private CourseScheduleService courseScheduleService;
	@Resource
	private CourseScheduleBigTimeService courseScheduleBigTimeService;
	@Resource
	private Environment env;

	/**
	 * 进入个人中心页面
	 * 
	 * @param model
	 * @param rapidTAG
	 *            进入个人中心 指定菜单 标识符
	 * @return
	 */
	@RequestMapping("/mstd_geren")
	public String mstd_geren(Model model, String rapidTAG) {
		String isAutomaticRecording = env.getProperty("is.automatic.recording");
		model.addAttribute("isAutomaticRecording", isAutomaticRecording);
		model.addAttribute("rapidTAG", rapidTAG);
		return "/personalCenter/mstd_geren";
	}

	/**
	 * 进入个人中心评估记录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/geren_pingkejilu")
	public String geren_pingkejilu(Model model, Integer p) {
		return "/personalCenter/geren_pingkejilu";
	}

	@RequestMapping("/getRecord")
	public String getRecord(Model model, Integer p) {
		if (p == null) {
			p = 1;
		}
		String userId = getUserId();
		Page<WebPj> pageInfo = webPjService.listPersonalWebPjRecord(userId, p, 6);
		List<WebPj> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);
		return "/personalCenter/geren_pingkejilu_data";
	}

	/**
	 * 进入个人中心观看记录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/geren_guankanjilu")
	public String geren_guankanjilu(Model model, Integer p) {
		return "/personalCenter/geren_guankanjilu";
	}

	@RequestMapping("/getWatchRecord")
	public String getWatchRecord(Model model, Integer p) {
		if (p == null)
			p = 1;

		String userId = getShiroUser().getId();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);

		Page<Map<String, String>> pageInfo = sysHistoryService.findWatchRecord(m, p, 6);

		int pages = pageInfo.getPages(); // 总页数
		List<Map<String, String>> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);

		return "/personalCenter/geren_guankanjilu_data";
	}

	/**
	 * 进入个人中心收藏记录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/geren_shoucangjilu")
	public String geren_shoucangjilu(HttpServletRequest req, Model model, Integer p) {
		String searchType = req.getParameter("searchType");
		model.addAttribute("searchType", searchType);
		return "/personalCenter/geren_shoucangjilu";
	}

	@RequestMapping("/getCollectRecord")
	public String getCollectRecord(HttpServletRequest req, Model model, Integer p) {
		if (p == null)
			p = 1;
		String userId = getShiroUser().getId();

		String searchType = req.getParameter("searchType");
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);
		if ("R".equals(searchType)) {
			m.put("searchType", 0);
		} else if ("T".equals(searchType)) {
			m.put("searchType", 1);
		} else if ("V".equals(searchType)) {
			m.put("searchType", 2);
		} else if ("E".equals(searchType)) {
			m.put("searchType", 3);
		} else if ("Q".equals(searchType)) {
			m.put("searchType", 4);
		}
		m.put("pubType", searchType);

		Page<Map<String, String>> pageInfo = sysHistoryService.findCollectRecord(m, p, 6);

		int pages = pageInfo.getPages(); // 总页数
		List<Map<String, String>> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);

		return "/personalCenter/geren_shoucangjilu_data";
	}

	/**
	 * 课后督课
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/geren_kehou")
	public String geren_kehou(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		model.addAttribute("id", id);
		return "/personalCenter/geren_kehou";
	}

	@RequestMapping("/duke")
	public void duke(HttpServletResponse response, @Validated WebPj webPj) {
		try {
			// Date date = new Date();
			WebPj pj = webPjService.findByKey(webPj.getId());
			pj.setBak1(webPj.getBak1());
			pj.setBak2(webPj.getBak2());
			webPjService.updateByKeySelective(pj);

			WriterUtils.toHtml(response, MessageUtils.SUCCESS);

		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入我的课程表页面
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/geren_kechengbiao")
	public String geren_kechengbiao(Model model) throws ParseException {
		CourseSchedule courseSchedule = new CourseSchedule(getUserId());
		List<CourseSchedule> listCourseSchedule = courseScheduleService.listCourseSchedule(courseSchedule);
		List<CourseScheduleBigTime> listCourseScheduleBigTime = courseScheduleBigTimeService.findAll();
		courseScheduleBigTimeService.bigSectionOfDayStringFrombigSectionOfDay(listCourseScheduleBigTime);

		model.addAttribute("listCourseSchedule", listCourseSchedule);
		model.addAttribute("listCourseScheduleBigTime", listCourseScheduleBigTime);
		return "/personalCenter/geren_kechengbiao";
	}

	/**
	 * 设置课件 页面
	 * 
	 * @param model
	 * @param zId
	 * @return
	 */
	@RequestMapping("/setPgCour")
	public String setPgCour(Model model, String zId) {
		model.addAttribute("zId", zId);

		PgPjinfo pjinfo = new PgPjinfo();
		pjinfo.setType("F");
		pjinfo.setzId(zId);
		List<PgPjinfo> pjinfoList = pgPjinfoService.findSelective(pjinfo);
		model.addAttribute("pjinfoList", pjinfoList);

		return "/personalCenter/setPgCour";
	}

	/**
	 * 进入 选择课件页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/chooseCour")
	public String chooseCour(Model model, String pjInfoId, String zId) {
		CourResource courRes = new CourResource();
		courRes.setUploadPersonId(getUserId());
		courRes.setSortord("time");
		courRes.setState("Y");
		List<CourResource> courresList = courResourceService.findSelective(courRes);
		model.addAttribute("courresList", courresList);
		model.addAttribute("pjInfoId", pjInfoId);
		model.addAttribute("zId", zId);
		return "/personalCenter/chooseCour";
	}

	/**
	 * 添加或更新直播课程评估课件
	 */
	@RequestMapping("/insterAndUpdate")
	public void insterAndUpdate(HttpServletResponse response, String pjInfoId, String zId, String courId) {
		LoPgCour lopgc = new LoPgCour();
		lopgc.setPjInfoId(pjInfoId);
		lopgc.setLoScheduleId(zId);
		lopgc.setCourId(courId);
		try {
			loPgCourService.insert(lopgc);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 收藏或关注
	 */
	@RequestMapping("/insterSysHistory")
	public void insterSysHistory(HttpServletResponse response, @Validated SysHistory sysHistory) {
		try {
			Date date = new Date();
			sysHistory.setUserId(getUserId());

			List<SysHistory> h = sysHistoryService.findSelective(sysHistory);
			if (h != null && h.size() > 0) {
				WriterUtils.toHtml(response, "重复操作！");
			} else {
				sysHistory.setCreateTime(date);
				sysHistory.setModiyTime(date);
				sysHistoryService.insert(sysHistory);
				WriterUtils.toHtml(response, MessageUtils.SUCCESS);
			}

		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 我的评估课程通知
	 * 
	 * @return
	 */
	@RequestMapping("/myPgLoSchedule")
	public String myPgLoSchedule(Model model, Integer p) {
		if (p == null) {
			p = 1;
		}
		Page<LoSchedule> pageInfo = loScheduleService.findMyPgLoSchedule(getUserId(), p);
		List<LoSchedule> loSchedules = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		int pages = pageInfo.getPages();

		model.addAttribute("loSchedules", loSchedules);
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);
		return "/personalCenter/myPgLoSchedule";
	}

}
