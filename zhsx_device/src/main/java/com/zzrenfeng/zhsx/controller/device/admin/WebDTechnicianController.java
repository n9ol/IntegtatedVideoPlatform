package com.zzrenfeng.zhsx.controller.device.admin;

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
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.WebDeviceManage;
import com.zzrenfeng.zhsx.model.WebDeviceTechnician;
import com.zzrenfeng.zhsx.service.WebDeviceTechnicianService;




/**
 * TODO 后台管理
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-08-02 11:52:51
 * @see com.zzrenfeng.zhsx.controller.WebDeviceTechnician
 */
@Controller
@RequestMapping(value="/webdevicetechnician/admin/")
public class WebDTechnicianController extends BaseController{


	@Resource
	private WebDeviceTechnicianService webDeviceTechnicianService;

	@RequestMapping("/listDTBycontion")
	public String listDTBycontion(HttpServletRequest request, Model model, Integer p) {
		if (p == null){
			p = 1;
		}
		
		WebDeviceTechnician dt = new WebDeviceTechnician();
		
		Page<WebDeviceTechnician> pageInfo = webDeviceTechnicianService.findPageSelective(dt, p, 10);
		int pages = pageInfo.getPages(); // 总页数
		List<WebDeviceTechnician> dtList = pageInfo.getResult();
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("dtList", dtList);

		return "/admin/device/device_technician";
	}


	@RequestMapping("/listDTData")
	public String listDTData(HttpServletRequest request, Model model, Integer p) {

		return "/admin/device/device_technician_data";
	}
	
	@RequestMapping("findDTById")
	public String findDTById(HttpServletRequest request, Model model, String id, String flag) {
		if (flag == null) {
			return "/web/error";
		}else {
			if(flag.equalsIgnoreCase("2")){//编辑
				if(id == null){
					return "/web/error";	
				}else{
					WebDeviceTechnician dt = webDeviceTechnicianService.findByKey(id);
					model.addAttribute("dt", dt);
				}
			}
			//无论编辑和添加都是返回同一个页面
			return "/admin/device/device_technician_edit";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deviceupdateDT")
	public Map<String,Object> deviceupdateDT(WebDeviceTechnician dt, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String id = dt.getId();
		int state = 0;
		if(id!=null){//更新
			state = webDeviceTechnicianService.updateByKeySelective(dt);
		}else{//添加
			state = webDeviceTechnicianService.insert(dt);
		}
		
		if(state!=0){//成功
			resultMap.put("errorCode", 0);
		}else{//失败
			resultMap.put("errorCode", 1);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/deviceDeleteDT")
	public String deviceDeleteDT(String ids, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		webDeviceTechnicianService.deleteBatchByKeys(ids);
		return "redirect:/webdevicetechnician/admin/listDTBycontion";
	}

}
