package com.zzrenfeng.zhsx.controller.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.CourResource;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WebNews;
import com.zzrenfeng.zhsx.model.WebQuestion;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.service.CourResourceService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.TeacherService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebNewsService;
import com.zzrenfeng.zhsx.service.WebQuestionService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleTimeService;
import com.zzrenfeng.zhsx.service.usersynchronization.UserSynchronizationService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.MailUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

@Controller
public class IndexController extends BaseController {

	private final static String VAR_EMAIL_CODE = "checkcode";
	private final static String VAR_EMAIL = "email";

	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private OffLineVideoResourcesService videoResourcesService;
	@Resource
	private CourResourceService courResourceService;
	@Resource
	private WebNewsService webNewsService;
	@Resource
	private WebQuestionService webQuestionService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private UserService userService;
	@Resource
	private CourseScheduleService courseScheduleService;
	@Resource
	private CourseScheduleTimeService courseScheduleTimeService;
	@Resource
	private UserSynchronizationService userSynchronizationService;

	/**
	 * 进入前台首页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/", "/index" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 直播模块数据
		List<CourseSchedule> listRecommendVideos = courseScheduleService.listRecommendVideos(new CourseSchedule(), 4);
		model.addAttribute("onlineLists", listRecommendVideos);

		// 点播模块数据
		OffLineVideoResources videoResources = new OffLineVideoResources();
		videoResources.setReleaseState("Y");
		videoResources.setTranscodingState("O");
		videoResources.setIsShow("Y");
		videoResources.setSortord("view");
		Page<OffLineVideoResources> pageInfo1 = videoResourcesService.findPageSelective(videoResources, 1, 4);
		List<OffLineVideoResources> lists1 = pageInfo1.getResult();
		model.addAttribute("olVideoLists", lists1);

		// 获得课件资源数据
		CourResource courResource = new CourResource();
		courResource.setState("Y");
		courResource.setBak2("Y");
		courResource.setSortord("downloadNum");
		Page<CourResource> pageInfo2 = courResourceService.findPageSelective(courResource, 1, 7);
		List<CourResource> lists2 = pageInfo2.getResult();
		model.addAttribute("courResLists", lists2);
		// 获得新闻中心数据
		WebNews news = new WebNews();
		news.setStatus(1);
		Page<WebNews> pageInfo3 = webNewsService.findPageSelective(news, 1, 7);
		List<WebNews> lists3 = pageInfo3.getResult();
		model.addAttribute("news", lists3);
		// 获得在线答疑资源
		WebQuestion question = new WebQuestion();
		question.setIfShow(1);
		Page<WebQuestion> pageInfo4 = webQuestionService.findPageSelective(question, 1, 7);
		List<WebQuestion> lists4 = pageInfo4.getResult();
		model.addAttribute("question", lists4);

		// 名师团队信息
		Page<Map<String, Object>> teachers = teacherService.findHotTheacher(1, 10);
		List<Map<String, Object>> t = teachers.getResult();
		for (Map<String, Object> map : t) {

			int a = Integer.parseInt(map.get("EXP").toString());

			double x = map.get("EXP") == null ? 0.0d : a;
			map.put("EXP", getGrade(x) + "");
		}
		model.addAttribute("teachers", t);
		return "/zhsx/index";
	}

	/**
	 * 根据经验值换算等级
	 * 
	 * @param x
	 *            经验值
	 * @return
	 */
	public static int getGrade(double x) {
		double nn = Math.sqrt(x / 15 - 7 / 4) - 0.5;
		int n = (int) Math.floor(nn);
		int ex = (n * n + n + 2) * 15;
		if (ex > x) {
			n -= 1;
		}
		return n;
	}

	/**
	 * 进入忘记密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/forgetPwd")
	public String forgetPwd() {
		return "/zhsx/forgetPwd1";
	}

	/**
	 * 验证账号是否存在
	 * 
	 * @return
	 */
	@RequestMapping("/validationUserCode")
	public @ResponseBody Map<String, Object> validationUserCode(String userCode) {
		Map<String, Object> hm = new HashMap<>();
		boolean isok = true;
		String userEmail = null;
		User user = userService.findByUserCode(userCode);
		if (user == null) {
			isok = false;
		} else if (!user.getEmail().isEmpty()) {
			userEmail = user.getEmail();
		}
		hm.put("isok", isok);

		StringBuffer buffer = null;
		if (userEmail != null) {
			buffer = new StringBuffer(userEmail);
			buffer = buffer.replace(3, userEmail.lastIndexOf("@"), "***");
			buffer = new StringBuffer("请用邮箱" + buffer + "获取验证码");
		}
		hm.put("userEmail", buffer);
		return hm;
	}

	/**
	 * 进入忘记密码页面2
	 * 
	 * @return
	 */
	@RequestMapping("/forgetPwd2")
	public String forgetPwd2(Model model, String userEmail) {
		if (userEmail != null) {
			model.addAttribute("userEmail", userEmail);
			return "/zhsx/forgetPwd2";
		} else {
			return "/zhsx/forgetPwd5";
		}
	}

	/**
	 * 发送邮箱验证码
	 * 
	 * @param response
	 * @param userEmail
	 * @throws MessagingException
	 */
	@RequestMapping("/sendEmailCode")
	public void sendEmailCode(HttpServletResponse response, HttpServletRequest request, String userCode) {
		User user = userService.findByUserCode(userCode);
		String userEmail = user.getEmail();
		Random rad = new Random();
		String value = rad.nextInt(1000000) + "";
		request.getSession().setAttribute(VAR_EMAIL, userEmail);
		request.getSession().setAttribute(VAR_EMAIL_CODE, value);
		try {
			MailUtil.send_mail(userEmail, userCode, value, "教育综合视讯管理平台-重置密码");
			WriterUtils.toHtml(response, MessageUtils.SENDSUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.SENDFAILURE);
			e.printStackTrace();
		}
	}

	/**
	 * 匹配内部存储与用户录入的验证码
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping("/verifyEmailCode")
	public @ResponseBody boolean verifyEmailCode(HttpServletRequest request, String emailCode) {
		boolean res = false;
		final String sessioncode = (String) request.getSession().getAttribute(VAR_EMAIL_CODE);
		if (sessioncode.equals(emailCode)) {
			res = true;
		}
		return res;
	}

	/**
	 * 进入重置密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/forgetPwd3")
	public String forgetPwd3() {
		return "/zhsx/forgetPwd3";
	}

	/**
	 * 重置用户密码
	 * 
	 * @param userCode
	 * @param newPasseord
	 * @return
	 */
	@RequestMapping("/resetPassword")
	public String resetPassword(String userCode, String newPasseord) {
		// userService.recomposeUserPassword(userCode, newPasseord);
		userSynchronizationService.updatePasswordSynchronization(userCode, newPasseord);
		return "/zhsx/forgetPwd4";
	}

}
