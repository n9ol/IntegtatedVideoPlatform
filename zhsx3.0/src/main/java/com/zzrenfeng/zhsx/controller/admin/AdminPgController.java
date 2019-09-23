package com.zzrenfeng.zhsx.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.PgPjdetail;
import com.zzrenfeng.zhsx.model.PgPjinfo;
import com.zzrenfeng.zhsx.service.PgPjdetailService;
import com.zzrenfeng.zhsx.service.PgPjinfoService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 评估管理
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/pg")
public class AdminPgController extends BaseController {

	@Resource
	private PgPjinfoService pgPjinfoService;
	@Resource
	private PgPjdetailService pgPjdetailService;

	/**
	 * 进入评估项管理
	 * 
	 * @param modle
	 * @param p
	 * @param pgPjinfo
	 * @return
	 */
	@RequestMapping("/pg_pjinfo")
	public String pgPjinfo(Model model, Integer p, PgPjinfo pgPjinfo) {
		if (p == null) {
			p = 1;
		}
		Page<PgPjinfo> pageInfo = pgPjinfoService.findPageSelective(pgPjinfo, p, 12);
		List<PgPjinfo> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lists", lists);
		model.addAttribute("type", pgPjinfo.getType());
		return "/admin/pg/pgPjInfo";
	}

	/**
	 * 进入添加评估项页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addPjinfo")
	public String addPjinfo(HttpServletResponse response, HttpServletRequest request, Model model) {
		return "/admin/pg/addPjinfo";
	}

	/**
	 * 添加评估项到数据库
	 * 
	 * @param response
	 * @param pgPjinfo
	 */
	@RequestMapping("/insertPjinfo")
	public void insertPjinfo(HttpServletResponse response, @Validated PgPjinfo pgPjinfo) {
		try {
			pgPjinfoService.insert(pgPjinfo);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 删除评估项
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delPjInfo")
	public void delPjInfo(HttpServletResponse response, String id) {
		try {
			pgPjinfoService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入评估项编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPjInfo")
	public String editPjInfo(String id, Model model) {
		PgPjinfo pjinfo = pgPjinfoService.findByKey(id);
		model.addAttribute("pjinfo", pjinfo);
		return "/admin/pg/editPjInfo";
	}

	/**
	 * 更新数据库中评估项内容
	 * 
	 * @param response
	 * @param pgPjinfo
	 */
	@RequestMapping("/updatePjinfo")
	public void updatePjinfo(HttpServletResponse response, @Validated PgPjinfo pgPjinfo) {
		try {
			pgPjinfoService.updateByKeySelective(pgPjinfo);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入评估项内容页面
	 * 
	 * @param model
	 * @param p
	 * @param pgPjdetail
	 * @return
	 */
	@RequestMapping("/pg_pjdetail")
	public String pgPjdetail(Model model, PgPjdetail pgPjdetail, Integer p, String type) {
		if (p == null) {
			p = 1;
		}
		type = "I";
		pgPjdetail.setType(type);
		Page<PgPjdetail> pageInfo = pgPjdetailService.findPageSelective(pgPjdetail, p, 12);
		List<PgPjdetail> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		if (type != null) {
			PgPjinfo pgPjinfo = new PgPjinfo();
			pgPjinfo.setType(type);
			List<PgPjinfo> pgPjinfos = pgPjinfoService.findSelective(pgPjinfo);
			model.addAttribute("pgPjinfos", pgPjinfos);
		}

		model.addAttribute("pjinfoId", pgPjdetail.getPjinfoId());
		model.addAttribute("type", type);
		model.addAttribute("lists", lists);
		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", p);
		return "/admin/pg/pgPjDetail";
	}

	/**
	 * 进入添加评估项页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addPjDetail")
	public String addPjDetail(HttpServletResponse response, HttpServletRequest request, Model model) {
		return "/admin/pg/addPjDetail";
	}

	/**
	 * 添加评估内容
	 * 
	 * @param response
	 * @param pgPjdetail
	 */
	@RequestMapping("/insertPjDetail")
	public void insertPjDetail(HttpServletResponse response, @Validated PgPjdetail pgPjdetail) {
		try {
			pgPjdetailService.insert(pgPjdetail);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 删除评估内容
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delPjDetail")
	public void delPjDetail(HttpServletResponse response, String id) {
		try {
			pgPjdetailService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入编辑评估内容页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPjDetail")
	public String editPjDetail(Model model, String id, String type) {
		PgPjdetail pgPjdetail = pgPjdetailService.findByKey(id);
		model.addAttribute("pgPjdetail", pgPjdetail);
		model.addAttribute("type", type);
		if (type != null) {
			PgPjinfo pgPjinfo = new PgPjinfo();
			pgPjinfo.setType(type);
			List<PgPjinfo> pgPjinfos = pgPjinfoService.findSelective(pgPjinfo);
			model.addAttribute("pgPjinfos", pgPjinfos);
		}
		return "/admin/pg/editPjDetail";
	}

	/**
	 * 更新评估内容数据库
	 * 
	 * @param response
	 * @param pgPjdetail
	 */
	@RequestMapping("/updatePjDetail")
	public void updatePjDetail(HttpServletResponse response, @Validated PgPjdetail pgPjdetail) {
		try {
			pgPjdetailService.updateByKeySelective(pgPjdetail);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}

	}

}
