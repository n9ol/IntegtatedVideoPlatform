package com.zzrenfeng.zhsx.controller.androidios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.LoPgCour;
import com.zzrenfeng.zhsx.model.PgPjinfo;
import com.zzrenfeng.zhsx.model.WebPj;
import com.zzrenfeng.zhsx.model.WebPjInfoExt;
import com.zzrenfeng.zhsx.model.WebPjdetail;
import com.zzrenfeng.zhsx.model.WebPjinfo;
import com.zzrenfeng.zhsx.service.LoPgCourService;
import com.zzrenfeng.zhsx.service.WebPjService;
import com.zzrenfeng.zhsx.service.WebPjdetailService;
import com.zzrenfeng.zhsx.service.WebPjinfoService;
import com.zzrenfeng.zhsx.util.MessageUtils;

/**
 * 移动端接口 - 评估
 * 
 * @author 田杰熠
 */
@Controller
@RequestMapping("/androidiosPg")
public class AndroidiosPgController extends BaseController {

	@Resource
	private WebPjService webPjService;
	@Resource
	private WebPjinfoService webPjinfoService;
	@Resource
	private WebPjdetailService webPjdetailService;
	@Resource
	private LoPgCourService loPgCourService;

	/**
	 * 获得课中评估项 一级列表
	 * 
	 * @param pgPjinfo
	 *            (type:'I',onOff:'ON')
	 * @param pgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getpjInfo")
	public AndroidiosModel getpjInfo(PgPjinfo pgPjinfo, String pgId) {
		// Map<String, Object> hm = new HashMap<>();

		// WebPj webpj = new WebPj();
		// webpj.setUserId(getUserId());
		// webpj.setPgId(pgId);
		// webpj.setOnOff(pgPjinfo.getOnOff());
		// List<WebPj> webpjs = webPjService.findSelective(webpj);
		// if (webpjs == null || webpjs.size() == 0) {
		// webPjService.insterPgMessage(getUserId(), pgId, pgPjinfo.getOnOff(),
		// pgPjinfo.getType());
		// webpj = webPjService.findSelective(webpj).get(0);
		// } else {
		// webpj = webpjs.get(0);
		// }
		//
		// // 获得课中评估项
		// WebPjinfo webPjInfo = new WebPjinfo();
		// webPjInfo.setUserId(getUserId());
		// webPjInfo.setPgId(pgId);
		// webPjInfo.setOnOff(pgPjinfo.getOnOff());
		// webPjInfo.setBak1("I");
		// List<WebPjinfo> webPjInfoList =
		// webPjinfoService.findSelective(webPjInfo);

		// 获得总评
		// hm.put("webpj", webpj);
		// hm.put("webPjInfoList", webPjInfoList);

		Map<String, Object> hm = webPjService.getPjInfo(getUserId(), pgId, pgPjinfo.getOnOff(), pgPjinfo.getType());
		hm.put("onOff", pgPjinfo.getOnOff());

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 获取评估细则
	 * ?pgPjInfoId="+pgPjInfoId+"&pgId="+receive.id+"&onOff=${onOff!''}"+"&
	 * webPjInfoId="+id
	 * 
	 * @param webPjdetail
	 * @param webPjInfoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getpjdetail")
	public AndroidiosModel getpjdetail(WebPjdetail webPjdetail, String webPjInfoId) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("webPjInfoId", webPjInfoId);

		WebPjinfo webPjInfo = webPjinfoService.findByKey(webPjInfoId);
		hm.put("webPjInfo", webPjInfo);

		webPjdetail.setUserId(getUserId());
		List<WebPjdetail> webPjdetailList = webPjdetailService.findSelective(webPjdetail);
		hm.put("webPjdetailList", webPjdetailList);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 更新评估细则 单项评分
	 * 
	 * @param webPjdetail
	 *            (id: id, score: score)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updataPjDetailScore")
	public AndroidiosModel updatetotal(WebPjdetail webPjdetail) {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		Map<String, Object> hm = new HashMap<>();
		try {
			webPjdetailService.updateByKeySelective(webPjdetail);
			hm.put("r", 1);
			hm.put("rs", MessageUtils.SUCCESS);
		} catch (Exception e) {
			hm.put("r", 0);
			hm.put("rs", MessageUtils.FAilURE);
			e.printStackTrace();
		}
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 更新评估项内容
	 * 
	 * @param webPjInfoExt
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updataWebPjInfo")
	public AndroidiosModel updatepfpy(WebPjInfoExt webPjInfoExt) {
		Map<String, Object> hm = new HashMap<>();

		double total = webPjService.calculateTotal(webPjInfoExt);
		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setId(webPjInfoExt.getWebPjInfoId());
		webPjinfo.setContent(webPjInfoExt.getContent());
		webPjinfo.setTotal(new BigDecimal(total));
		try {
			webPjinfoService.updateByKeySelective(webPjinfo);
			hm.put("r", 1);
			hm.put("total", total);
			hm.put("rs", MessageUtils.SUCCESS);
		} catch (Exception e) {
			hm.put("r", 0);
			hm.put("total", 0);
			hm.put("rs", MessageUtils.FAilURE);
		}

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 提交课程总评
	 * 
	 * @param webPjInfoExt
	 *            ( webpj.id )
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updataWebPj")
	public AndroidiosModel updatepj(WebPjInfoExt webPjInfoExt) {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		Map<String, Object> hm = new HashMap<>();

		double total = webPjService.calculateTotal(webPjInfoExt);
		WebPj webPj = new WebPj();
		webPj.setId(webPjInfoExt.getWebPjInfoId());
		webPj.setAllResult(webPjInfoExt.getContent());
		webPj.setScore(new BigDecimal(total));
		webPj.setIspj(1);
		try {
			webPjService.updateByKeySelective(webPj);
			hm.put("r", 1);
			hm.put("rs", MessageUtils.SUCCESS);
		} catch (Exception e) {
			hm.put("r", 0);
			hm.put("rs", MessageUtils.FAilURE);
			e.printStackTrace();
		}
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 得到课前评估
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getKqWebPjInfo")
	public AndroidiosModel getKeQianPg(@RequestParam String pgId) {

		LoPgCour lopgcour = new LoPgCour();
		lopgcour.setLoScheduleId(pgId);
		lopgcour.setUserId(getUserId());
		List<LoPgCour> lopgcourList = loPgCourService.findSelective(lopgcour);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(lopgcourList);
		return androidiosModel;
	}

	/**
	 * 更新课前评估项 - 评估评分
	 * 
	 * @param response
	 * @param id
	 * @param score
	 */
	@ResponseBody
	@RequestMapping("/updataKqWebPjInfoScore")
	public AndroidiosModel updataScore(String pgId, String pgPjInfoId, String score) {

		Map<String, Object> hm = new HashMap<>();
		try {
			webPjinfoService.insertorupdata(getUserId(), pgId, pgPjInfoId, score);
			hm.put("r", 1);
			hm.put("rs", MessageUtils.SUCCESS);
		} catch (Exception e) {
			hm.put("r", 0);
			hm.put("rs", MessageUtils.FAilURE);
			e.printStackTrace();
		}

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 更新课前评估总评得分
	 * 
	 * @param response
	 * @param webPjInfoExt
	 */
	@ResponseBody
	@RequestMapping("/updataWebPjKq")
	public AndroidiosModel updataWebPjKq(HttpServletResponse response, WebPjInfoExt webPjInfoExt) {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		Map<String, Object> hm = new HashMap<>();

		double total = webPjService.calculateTotal(webPjInfoExt);
		WebPj webPj = new WebPj();
		webPj.setId(webPjInfoExt.getWebPjInfoId());
		webPj.setBak(String.valueOf(total));
		webPj.setIspj(1);
		try {
			webPjService.updateByKeySelective(webPj);

			hm.put("r", 1);
			hm.put("rs", MessageUtils.SUCCESS);
		} catch (Exception e) {

			hm.put("r", 1);
			hm.put("rs", MessageUtils.SUCCESS);
			e.printStackTrace();
		}

		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 查看评估结果
	 * 
	 * @param pgId
	 * @param onOff
	 */
	@ResponseBody
	@RequestMapping("/viewResults")
	public AndroidiosModel pgresultshowlist(String pgId, String onOff) {
		Map<String, Object> hm = new HashMap<>();

		WebPj webPj = new WebPj();
		webPj.setPgId(pgId);
		webPj.setIspj(1);
		List<WebPj> webpjList = webPjService.findSelective(webPj);
		hm.put("webpjList", webpjList);
		List<String> userIds = new ArrayList<>();
		if (webpjList != null) {
			for (WebPj webPj2 : webpjList) {
				userIds.add(webPj2.getUserId());
			}
		}
		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setPgId(pgId);
		webPjinfo.setUserIds(userIds);
		List<WebPjinfo> webPjinfoList = webPjinfoService.findSelective(webPjinfo);
		String titles = "1";
		List<String> titleName = new ArrayList<>();
		for (int i = 0; i < webPjinfoList.size(); i++) {
			if (!titles.contains(webPjinfoList.get(i).getTitle())) {
				titleName.add(webPjinfoList.get(i).getTitle());
			}
			titles += webPjinfoList.get(i).getTitle();
		}
		hm.put("titleName", titleName);
		hm.put("webPjinfoList", webPjinfoList);
		hm.put("pgId", pgId);
		hm.put("onOff", onOff);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 查看评估项评分细则 结果
	 * 
	 * @param model
	 * @param webpj
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkPjInfoResult")
	public AndroidiosModel checkPjInfoResult(WebPj webpj) {
		Map<String, Object> hm = new HashMap<>();

		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setPgId(webpj.getPgId());
		webPjinfo.setUserId(webpj.getUserId());
		webPjinfo.setOnOff(webpj.getOnOff());
		List<WebPjinfo> webPjinfoList = webPjinfoService.findSelective(webPjinfo);

		hm.put("webPjinfoList", webPjinfoList);
		hm.put("allResult", webpj.getAllResult());

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

}
