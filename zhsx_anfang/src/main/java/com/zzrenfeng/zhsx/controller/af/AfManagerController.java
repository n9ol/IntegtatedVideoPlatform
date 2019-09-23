package com.zzrenfeng.zhsx.controller.af;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.ExceptionMessage;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AfDh;
import com.zzrenfeng.zhsx.model.AfManager;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.AfDhService;
import com.zzrenfeng.zhsx.service.AfManagerService;
import com.zzrenfeng.zhsx.service.SysSchoolService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-06-22 17:02:47
 * @see com.zzrenfeng.zhsx.controller.AfManager
 */
@Controller
@RequestMapping(value = "/af")
public class AfManagerController extends BaseController {

	@Resource
	private AfManagerService afManagerService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private AfDhService afDhService;

	/**
	 * 进入校园安防
	 * 
	 * @throws ExceptionMessage
	 */
	@RequestMapping("/findAfSchool")
	public String findAfSchool(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ExceptionMessage {
		if (getUserType().equals(User.userType_students)) {
			throw new ExceptionMessage("权限不足");
		}
		// 发现安防区域管理
		AfDh afdh = new AfDh();
		List<AfDh> li = afDhService.findSelective(afdh);
		model.addAttribute("li", li);

		return "/af/anfang";
	}

	/**
	 * 获得开通安防学校列表
	 * 
	 * @param request
	 * @param response
	 * @param p
	 * @return
	 */
	@RequestMapping("/getAfSchool")
	public @ResponseBody Map<String, Object> getAfSchool(HttpServletRequest request, HttpServletResponse response,
			Integer p) {
		if (p == null) {
			p = 1;
		}
		SysSchool school = new SysSchool();
		school.setIsaf("Y");
		Page<SysSchool> pageInfo = sysSchoolService.findPageSelective(school, p, 12);
		List<SysSchool> lists = pageInfo.getResult();
		int pages = pageInfo.getPages(); // 总页数
		Map<String, Object> hm = new HashMap<>();
		hm.put("pageNum", p);
		hm.put("pages", pages);
		hm.put("lists", lists);
		return hm;
	}

	/**
	 * 获得学校全部监控
	 * 
	 * @param model
	 * @param af
	 * @return
	 */
	@RequestMapping("/findAll")
	public @ResponseBody Map<String, Object> findAll(String schoolId, String schoolarea, Integer p) {
		if (p == null) {
			p = 1;
		}
		AfManager af = new AfManager();
		af.setSchoolid(schoolId);
		af.setSchoolarea(schoolarea);
		Page<AfManager> pageInfo = afManagerService.findPageSelective(af, p, 4);
		int pages = pageInfo.getPages();
		List<AfManager> lists = pageInfo.getResult();
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("schoolid", af.getSchoolid());
		hm.put("lists", lists);
		hm.put("pageNum", p);
		hm.put("pages", pages);
		return hm;
	}

	@RequestMapping("/findDhBySchool")
	public String findDhBySchool(AfManager af, Model model) {
		af.setStatus("Y");
		List<AfManager> list = afManagerService.findAfSchoolArea(af);
		model.addAttribute("schoolDh", list);
		return "/af/schoolArea";
	}
}
