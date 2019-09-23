package com.zzrenfeng.zhsx.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.base.BaseTeachingBuilding;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.base.BaseTeachingBuildingService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-03-09 17:09:12
 * @see com.zzrenfeng.classbrand.controller.BaseTeachingBuilding
 */
@Controller
@RequestMapping(value = "/baseTeachingBuilding")
public class BaseTeachingBuildingController extends BaseController {

	@Resource
	private BaseTeachingBuildingService baseTeachingBuildingService;
	@Resource
	private SysClassroomService sysClassroomService;

	/**
	 * 进入教学楼管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/buildList")
	public String buildList(Integer p, BaseTeachingBuilding baseTeachingBuilding, Model model) {
		if (p == null) {
			p = 1;
		}

		Page<BaseTeachingBuilding> pageInfo = baseTeachingBuildingService.findPageSelective(baseTeachingBuilding, p,
				Constant.PAGESIZE);
		List<BaseTeachingBuilding> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		int pages = pageInfo.getPages();

		model.addAttribute("lists", lists);
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);

		return "/admin/base/buildList";
	}

	/**
	 * 添加教学楼页面
	 * 
	 * @return
	 */
	@RequestMapping("/addBuild")
	public String addBuild() {
		return "/admin/base/addBuild";
	}

	/**
	 * 添加教学楼信息到数据
	 *
	 * @param response
	 * @param teachingBuilding
	 */
	@RequestMapping("/insterBuild")
	public void insterBuild(HttpServletResponse response, @Validated BaseTeachingBuilding teachingBuilding) {
		Date date = new Date();
		teachingBuilding.setCreateDate(date);
		teachingBuilding.setModiyDate(date);
		try {
			baseTeachingBuildingService.insert(teachingBuilding);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 删除教学楼
	 *
	 * @param response
	 * @param id
	 */
	@RequestMapping("/deleteBuild")
	public void deleteBuild(HttpServletResponse response, @RequestParam String id) {
		try {
			baseTeachingBuildingService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除教学楼
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("/batchDelBuild")
	public void batchDelBuild(HttpServletResponse response, String[] del_id) {
		try {
			baseTeachingBuildingService.deleteBatch(del_id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 编辑教学楼页面
	 *
	 * @param model
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/editBuild")
	public String editBuild(Model model, @RequestParam String id, @RequestParam String buildName)
			throws UnsupportedEncodingException {
		String decodeBuildName = URLDecoder.decode(buildName, "UTF-8");
		model.addAttribute("id", id);
		model.addAttribute("buildName", decodeBuildName);
		return "/admin/base/editBuild";
	}

	/**
	 * 修改教学楼信息
	 *
	 * @param response
	 * @param teachingBuilding
	 */
	@RequestMapping("/updateBuild")
	public void updateBuild(HttpServletResponse response, @Validated BaseTeachingBuilding teachingBuilding) {
		try {
			teachingBuilding.setModiyDate(new Date());
			baseTeachingBuildingService.updateByKeySelective(teachingBuilding);
			sysClassroomService.updateClassroomTeachingBuildingName(teachingBuilding.getId(),
					teachingBuilding.getBuildName());
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
