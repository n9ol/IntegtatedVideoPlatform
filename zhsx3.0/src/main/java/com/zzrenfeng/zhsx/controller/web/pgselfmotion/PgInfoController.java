package com.zzrenfeng.zhsx.controller.web.pgselfmotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.mapper.WebPjMapper;
import com.zzrenfeng.zhsx.model.LoPgCour;
import com.zzrenfeng.zhsx.model.PgPjinfo;
import com.zzrenfeng.zhsx.model.WebPj;
import com.zzrenfeng.zhsx.model.WebPjInfoExt;
import com.zzrenfeng.zhsx.model.WebPjdetail;
import com.zzrenfeng.zhsx.model.WebPjinfo;
import com.zzrenfeng.zhsx.service.AnswerQuestionsCollectedService;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoPgCourService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.WebPjService;
import com.zzrenfeng.zhsx.service.WebPjdetailService;
import com.zzrenfeng.zhsx.service.WebPjinfoService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 评估控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/pgInfo")
public class PgInfoController extends BaseController {

	@Resource
	private WebPjService webPjService;
	@Resource
	private WebPjMapper webPjMapper;
	@Resource
	private WebPjinfoService webPjinfoService;
	@Resource
	private WebPjdetailService webPjdetailService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private LoFscheduleService loFscheduleService;
	@Resource
	private AnswerQuestionsCollectedService answerQuestionsCollectedService;
	@Resource
	private LoPgCourService loPgCourService;

	/**
	 * 获得课中评估项
	 * 
	 * @param model
	 * @param pgPjinfo
	 * @return
	 */
	@RequestMapping("/getPjInfo")
	public String getPjInfo(Model model, PgPjinfo pgPjinfo, String pgId) {
		Map<String, Object> hm = webPjService.getPjInfo(getUserId(), pgId, pgPjinfo.getOnOff(), pgPjinfo.getType());
		model.addAttribute("webpj", hm.get("webpj"));
		model.addAttribute("webPjInfoList", hm.get("webPjInfoList"));
		model.addAttribute("onOff", pgPjinfo.getOnOff());
		return "/web/pg/pjInfo";
	}

	/**
	 * 获得课后评课
	 * 
	 * @param model
	 * @param pgPjinfo
	 * @return
	 */
	@RequestMapping("/getPjInfoDuke")
	public String getPjInfoDuke(Model model, PgPjinfo pgPjinfo, String pgId) {
		WebPj webpj = new WebPj();
		webpj.setUserId(getUserId());
		webpj.setPgId(pgId);
		webpj.setOnOff(pgPjinfo.getOnOff());
		List<WebPj> webpjs = webPjService.findSelective(webpj);
		if (webpjs == null || webpjs.size() == 0) {
			webPjService.insterPgMessage(getUserId(), pgId, pgPjinfo.getOnOff(), pgPjinfo.getType());
			webpj = webPjService.findSelective(webpj).get(0);
		} else {
			webpj = webpjs.get(0);
		}

		// 获得总评
		model.addAttribute("webpj", webpj);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pgId", pgId);
		List<Map<String, Object>> dekelist = webPjMapper.findDukeList(paramMap);
		model.addAttribute("dekelist", dekelist);
		return "/web/pg/pjDuke";
	}

	/**
	 * 获得评估内容
	 * 
	 * @param webPjdetail
	 * @return
	 */
	@RequestMapping("/getPjDetail")
	public String getPjDetail(Model model, WebPjdetail webPjdetail, String webPjInfoId) {
		model.addAttribute("webPjInfoId", webPjInfoId);
		WebPjinfo webPjInfo = webPjinfoService.findByKey(webPjInfoId);
		model.addAttribute("webPjInfo", webPjInfo);
		webPjdetail.setUserId(getUserId());
		List<WebPjdetail> webPjdetailList = webPjdetailService.findSelective(webPjdetail);
		model.addAttribute("webPjdetailList", webPjdetailList);
		return "/web/pg/pjDetail";
	}

	/**
	 * 更新评估内容评分
	 * 
	 * @param webPjdetail
	 */
	@RequestMapping("/updataPjDetailScore")
	public void updataPjDetailScore(HttpServletResponse response, WebPjdetail webPjdetail) {
		try {
			webPjdetailService.updateByKeySelective(webPjdetail);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 更新评估项内容
	 * 
	 * @param response
	 * @param webPjInfoExt
	 */
	@RequestMapping("/updataWebPjInfo")
	public void updataWebPjInfo(HttpServletResponse response, WebPjInfoExt webPjInfoExt) {
		double total = webPjService.calculateTotal(webPjInfoExt);
		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setId(webPjInfoExt.getWebPjInfoId());
		webPjinfo.setContent(webPjInfoExt.getContent());
		webPjinfo.setTotal(new BigDecimal(total));
		try {
			webPjinfoService.updateByKeySelective(webPjinfo);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 更新总评信息
	 * 
	 * @param response
	 * @param webPjInfoExt
	 */
	@RequestMapping("/updataWebPj")
	public void updataWebPj(HttpServletResponse response, WebPjInfoExt webPjInfoExt, int ispj) {
		double total = webPjService.calculateTotal(webPjInfoExt);
		WebPj webPj = new WebPj();
		webPj.setId(webPjInfoExt.getWebPjInfoId());
		webPj.setAllResult(webPjInfoExt.getContent());
		webPj.setScore(new BigDecimal(total));
		if (ispj != 0) {
			webPj.setIspj(ispj);
		}
		try {
			webPjService.updateByKeySelective(webPj);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 查看评估结果
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewResults")
	public String viewResults(Model model, String pgId, String onOff) {
		WebPj webPj = new WebPj();
		webPj.setPgId(pgId);
		webPj.setIspj(1);
		List<WebPj> webpjList = webPjService.findSelective(webPj);
		model.addAttribute("webpjList", webpjList);

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
		model.addAttribute("titleName", titleName);
		model.addAttribute("webPjinfoList", webPjinfoList);
		model.addAttribute("pgId", pgId);
		model.addAttribute("onOff", onOff);
		return "/web/pg/viewResults";
	}

	/**
	 * 查看评估项评分结果细则
	 * 
	 * @param model
	 * @param webpj
	 * @return
	 */
	@RequestMapping("/checkPjInfoResult")
	public String checkPjInfoResult(Model model, WebPj webpj) {
		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setPgId(webpj.getPgId());
		webPjinfo.setUserId(webpj.getUserId());
		webPjinfo.setOnOff(webpj.getOnOff());
		List<WebPjinfo> webPjinfoList = webPjinfoService.findSelective(webPjinfo);
		model.addAttribute("webPjinfoList", webPjinfoList);
		model.addAttribute("allResult", webpj.getAllResult());
		return "/web/pg/checkPjInfoResult";
	}

	/**
	 * 得到平均分展示图数据
	 * 
	 * @param pgId
	 * @return
	 */
	@RequestMapping("/getPgAverageDraw")
	public @ResponseBody Map<String, Object> getPgAverageDraw(String pgId, String onOff) {
		Map<String, Object> hm = new HashMap<>();
		String xData = "";
		String seriesData = "";
		List<WebPjinfo> pjInfoavgs = webPjinfoService.getPgAverageDraw(pgId);
		for (WebPjinfo webPjinfo : pjInfoavgs) {
			xData += webPjinfo.getTitle() + ",";
			seriesData += webPjinfo.getPjinfoavg() + ",";
		}
		Map<String, Object> avg = webPjService.getPgAverageDraw(pgId);
		if (onOff.equals("OFF")) {
			xData += "总分";
			seriesData += avg.get("pjavg");
		} else {
			xData += "课前总分,";
			seriesData += avg.get("kqpjavg") + ",";
			xData += "课中总分";
			seriesData += avg.get("pjavg");
		}
		String[] xDataArray = xData.split(",");
		String[] seriesDataArray = seriesData.split(",");
		hm.put("xDataArray", xDataArray);
		hm.put("seriesDataArray", seriesDataArray);
		return hm;
	}

	/**
	 * 进入课前评估
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getKeQianPg")
	public String getKeQianPg(Model model, String pgId) {
		LoPgCour lopgcour = new LoPgCour();
		lopgcour.setLoScheduleId(pgId);
		lopgcour.setUserId(getUserId());
		List<LoPgCour> lopgcourList = loPgCourService.findSelective(lopgcour);
		model.addAttribute("lopgcourList", lopgcourList);

		// 构成图数据
		String legendData = "";
		String seriesData = "";
		for (LoPgCour loPgCour2 : lopgcourList) {
			legendData += "'" + loPgCour2.getPjInfoName() + "',";
			seriesData += "{value:1,name: '" + loPgCour2.getPjInfoName() + "'},";
		}
		model.addAttribute("legendData", legendData);
		model.addAttribute("seriesData", seriesData);
		return "/web/pg/keQianPg";
	}

	/**
	 * 更新课前评估评分
	 * 
	 * @param response
	 * @param id
	 * @param score
	 */
	@RequestMapping("/updataScore")
	public void updataScore(HttpServletResponse response, String pgId, String pgPjInfoId, String score) {
		try {
			webPjinfoService.insertorupdata(getUserId(), pgId, pgPjInfoId, score);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 更新课前评估总评得分
	 * 
	 * @param response
	 * @param webPjInfoExt
	 */
	@RequestMapping("/updataWebPjKq")
	public void updataWebPjKq(HttpServletResponse response, WebPjInfoExt webPjInfoExt) {
		double total = webPjService.calculateTotal(webPjInfoExt);
		WebPj webPj = new WebPj();
		webPj.setId(webPjInfoExt.getWebPjInfoId());
		webPj.setBak(String.valueOf(total));
		webPj.setIspj(1);
		try {
			webPjService.updateByKeySelective(webPj);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入客户端评估页
	 * 
	 * @return
	 */
	@RequestMapping("/clientPg")
	public String clientPg(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/web/online/clientPg";
	}

}
