package com.zzrenfeng.zhsx.controller.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AfDh;
import com.zzrenfeng.zhsx.model.AfManager;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.service.AfDhService;
import com.zzrenfeng.zhsx.service.AfManagerService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-06-22 17:02:47
 * @see com.zzrenfeng.zhsx.controller.AfManager
 */
@Controller
@RequestMapping(value = "/afmanager")
public class AdminAfManagerController extends BaseController {

	@Resource
	private AfManagerService afManagerService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private AfDhService afDhService;
	@Resource
	private Environment environment;

	/**
	 * 进入安防管理列表
	 */
	@RequestMapping("/afmanager")
	public String afmanager(HttpServletRequest request, HttpServletResponse response, Model model, AfManager af,
			Integer p) {
		if (p == null) {
			p = 1;
		}
		Page<AfManager> pageInfo = afManagerService.findPageSelective(af, p, 10);
		List<AfManager> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		int pages = pageInfo.getPages();// 总页数
		String schoolLevel = environment.getProperty("school.level");

		model.addAttribute("search", af.getSearch());
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("schoolLevel", schoolLevel);
		return "/admin/af/afmanager";

	}

	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response, String id) {
		try {
			afManagerService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	@RequestMapping("/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response, AfManager af) {
		try {
			afManagerService.updateByKeySelective(af);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	/**
	 * 批量删除
	 */
	@RequestMapping("deleteBatch")
	public void deleteBatch(HttpServletResponse response, String[] del_id) {
		try {
			List<String> ids = Arrays.asList(del_id);
			afManagerService.deleteBatch(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入添加页面
	 * 
	 * @param response
	 * @param af
	 */
	@RequestMapping("/addAf")
	public String addAf(Model model, AfDh afd) {
		SysSchool school = new SysSchool();
		school.setIsaf("Y");
		List<SysSchool> list = sysSchoolService.findSelective(school);
		List<AfDh> afdhs = afDhService.findSelective(afd);
		String schoolLevel = environment.getProperty("school.level");
		String schoolId = environment.getProperty("school.id");

		model.addAttribute("schools", list);
		model.addAttribute("afdh", afdhs);
		model.addAttribute("schoolLevel", schoolLevel);
		model.addAttribute("schoolId", schoolId);
		return "/admin/af/addAf";
	}

	/**
	 * 添加
	 * 
	 * @param response
	 * @param af
	 */
	@RequestMapping("/insertAf")
	public void insertAf(HttpServletResponse response, AfManager af) {
		try {
			af.setStatus("Y");
			af.setStarttime(new Date());
			afManagerService.insert(af);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("/updateAf")
	public void updateAf(HttpServletResponse response, AfManager af) {
		try {
			afManagerService.updateByKeySelective(af);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("/editAf")
	public String editAf(Model model, String id) {
		AfManager af = afManagerService.findByKey(id);

		// 获得学校
		SysSchool school = new SysSchool();
		List<SysSchool> list = sysSchoolService.findSelective(school);
		model.addAttribute("schools", list);

		// 选择区域
		AfDh afdh = new AfDh();
		List<AfDh> afdhs = afDhService.findSelective(afdh);
		model.addAttribute("afdh", afdhs);

		model.addAttribute("af", af);
		model.addAttribute("ids", id);
		return "/admin/af/editAf";
	}

	/**
	 * 进入安防导航查询管理界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param af
	 * @param p
	 * @return
	 */
	@RequestMapping("/afdh")
	public String afdh(HttpServletRequest request, HttpServletResponse response, Model model, AfDh dh, Integer p) {

		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);// 当前页
		Page<AfDh> pageInfo = afDhService.findPageSelective(dh, p, 12);
		int pages = pageInfo.getPages();// 总页数
		List<AfDh> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		return "/admin/af/afdh";

	}

	/**
	 * 删除安防导航
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 */
	@RequestMapping("/afdhdel")
	public void del(HttpServletRequest request, HttpServletResponse response, Model model, String id) {

		try {
			afDhService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("/updateAfdh")
	public void updateAfdh(HttpServletRequest request, HttpServletResponse response, Model model, AfDh afdh) {

		try {
			afDhService.updateByKeySelective(afdh);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	/**
	 * 进入编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/afdhedit")
	public String afdhedit(HttpServletRequest request, HttpServletResponse response, Model model, String id) {

		AfDh afdh = afDhService.findByKey(id);
		model.addAttribute("afdh", afdh);
		return "/admin/af/afdhedit";
	}

	@RequestMapping("/addAfDh")
	public String addAfDh(HttpServletRequest request, HttpServletResponse response, Model model, String id) {

		AfDh afdh = afDhService.findByKey(id);
		model.addAttribute("afdh", afdh);
		return "/admin/af/addAfdh";
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/afDhAdd")
	public void afDhAdd(HttpServletResponse response, AfDh afdh) {
		try {
			afDhService.insert(afdh);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

}
