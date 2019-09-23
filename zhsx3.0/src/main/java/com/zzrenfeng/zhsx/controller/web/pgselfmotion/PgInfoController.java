package com.zzrenfeng.zhsx.controller.web.pgselfmotion;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
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
import com.zzrenfeng.zhsx.util.Utils;
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
		String onOff = pgPjinfo.getOnOff();
		String type = pgPjinfo.getType();
		String userId = getUserId();

		// 获得评估记录信息(总评)
		WebPj webPj = webPjService.getWebPj(userId, pgId, onOff);
		// 获得评估项
		String webPjId = webPj.getId();
		List<WebPjinfo> listWebPjinfo = webPjinfoService.listWebPjinfo(userId, pgId, onOff, type, webPjId);
		setSixPointSystemScore(listWebPjinfo);
		int weightSum = getWeightSum(listWebPjinfo);
		model.addAttribute("webpj", webPj);
		model.addAttribute("webPjInfoList", listWebPjinfo);
		model.addAttribute("onOff", onOff);
		model.addAttribute("weightSum", weightSum);
		return "/web/pg/pjInfo";
	}

	/**
	 * 将 webPjinfo的得分score设置成六分制分值
	 * 
	 * @param listWebPjinfo
	 */
	private void setSixPointSystemScore(List<WebPjinfo> listWebPjinfo) {
		for (WebPjinfo webPjinfo : listWebPjinfo) {
			BigDecimal sixPointSystemScore = Utils.percentageSystemConvertedIntoSixPointSystem(webPjinfo.getTotal());
			BigDecimal score = Utils.getScore(sixPointSystemScore.doubleValue());
			webPjinfo.setTotal(score);
		}
	}

	/**
	 * 进入评估规则说明页面
	 * 
	 * @param webPjId
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewPgRule")
	public String viewPgRule(@RequestParam String webPjId, Model model) {
		StringBuffer legendData = new StringBuffer();
		StringBuffer seriesData = new StringBuffer();
		List<WebPjinfo> listWebPjinfo = webPjinfoService.listWebPjinfo(webPjId);
		int weightSum = getWeightSum(listWebPjinfo);
		for (WebPjinfo webPjinfo : listWebPjinfo) {
			legendData.append("'" + webPjinfo.getTitle() + "'" + ",");
			BigDecimal weight = webPjinfo.getWeight();
			BigDecimal multiply = getWeightPercent(weightSum, weight);
			seriesData.append("{ value :" + multiply + ",name : '" + webPjinfo.getTitle() + "'},");
		}
		model.addAttribute("legendData", legendData.toString());
		model.addAttribute("seriesData", seriesData.toString());
		return "/web/pg/viewPgRule";
	}

	/**
	 * 获得权重百分比
	 * 
	 * @param weightSum
	 * @param weight
	 * @return
	 */
	private BigDecimal getWeightPercent(int weightSum, BigDecimal weight) {
		BigDecimal multiply = weight.divide(new BigDecimal(weightSum)).multiply(new BigDecimal(100));
		return multiply;
	}

	/**
	 * 得到权重总和
	 * 
	 * @param listWebPjinfo
	 * @return
	 */
	private int getWeightSum(List<WebPjinfo> listWebPjinfo) {
		int weightSum = 0;
		for (WebPjinfo webPjinfo : listWebPjinfo) {
			weightSum = weightSum + webPjinfo.getWeight().intValue();
		}
		return weightSum;
	}

	/**
	 * 获得评估内容
	 * 
	 * @param webPjdetail
	 * @return
	 */
	@RequestMapping("/getPjDetail")
	public String getPjDetail(Model model, WebPjdetail webPjdetail, String webPjInfoId) {
		WebPjinfo webPjInfo = webPjinfoService.findByKey(webPjInfoId);
		webPjdetail.setUserId(getUserId());
		webPjdetail.setBak2(webPjInfo.getBak2());
		List<WebPjdetail> webPjdetailList = webPjdetailService.listWebPjdetail(webPjdetail);
		setSixPointSystemScoreWebPjdetailList(webPjdetailList);
		model.addAttribute("webPjInfoId", webPjInfoId);
		model.addAttribute("webPjInfo", webPjInfo);
		model.addAttribute("webPjdetailList", webPjdetailList);
		return "/web/pg/pjDetail";
	}

	/**
	 * 将 webPjdetail的得分score设置成六分值
	 * 
	 * @param webPjdetailList
	 */
	private void setSixPointSystemScoreWebPjdetailList(List<WebPjdetail> webPjdetailList) {
		for (WebPjdetail webPjdetail2 : webPjdetailList) {
			BigDecimal score = webPjdetail2.getScore();
			BigDecimal sixPointSystemScore = Utils.percentageSystemConvertedIntoSixPointSystem(score);
			BigDecimal score2 = Utils.getScore(sixPointSystemScore.doubleValue());
			webPjdetail2.setScore(score2);
		}
	}

	/**
	 * 更新评估内容评分
	 * 
	 * @param webPjdetail
	 */
	@RequestMapping("/updataPjDetailScore")
	public void updataPjDetailScore(HttpServletResponse response, WebPjdetail webPjdetail) {
		BigDecimal score = webPjdetail.getScore();
		BigDecimal percentageSystemScore = Utils.sixPointSystemConvertedIntoPercentageSystem(score);
		webPjdetail.setScore(percentageSystemScore);
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
		double total = webPjService.calculateTotalNotWeight(webPjInfoExt);
		BigDecimal percentageSystemTotal = Utils.sixPointSystemConvertedIntoPercentageSystem(new BigDecimal(total));
		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setId(webPjInfoExt.getWebPjInfoId());
		webPjinfo.setContent(webPjInfoExt.getContent());
		webPjinfo.setTotal(percentageSystemTotal);
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
		BigDecimal percentageSystemTotal = Utils.sixPointSystemConvertedIntoPercentageSystem(new BigDecimal(total));
		WebPj webPj = new WebPj();
		webPj.setId(webPjInfoExt.getWebPjInfoId());
		webPj.setAllResult(webPjInfoExt.getContent());
		webPj.setScore(percentageSystemTotal);
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
	public String viewResults(Model model, String pgId, String onOff, Date addTime) {
		WebPj webPj = new WebPj();
		webPj.setIspj(1);

		// 将 onOff = ON ,addTime 为同一天,pgId 相同,作为同一节直播评估课
		webPj.setPgId(pgId);
		webPj.setAddTime(addTime);
		webPj.setOnOff(onOff);
		List<WebPj> webpjList = webPjService.findSelective(webPj);
		for (WebPj webPj2 : webpjList) {
			BigDecimal score = webPj2.getScore();
			BigDecimal sixPointSystemScore = Utils.percentageSystemConvertedIntoSixPointSystem(score);
			BigDecimal score2 = Utils.getScore(sixPointSystemScore.doubleValue());
			webPj2.setScore(score2);
		}
		model.addAttribute("webpjList", webpjList);
		List<String> userIds = new ArrayList<>();
		List<String> webPjIds = new ArrayList<>();
		if (webpjList != null) {
			for (WebPj webPj2 : webpjList) {
				userIds.add(webPj2.getUserId());
				webPjIds.add(webPj2.getId());
			}
		}

		/**
		 * 因为同一个用户对同一节直播评估课的评估记录 webPj是唯一的(即webPjId 唯一),
		 * 所以以pgId,userId,webPjId为条件查询保证了查询结果为本节直播评估课的评估记录
		 */
		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setPgId(pgId);
		webPjinfo.setUserIds(userIds);
		webPjinfo.setWebPjIds(webPjIds);
		List<WebPjinfo> listWebPjinfo = webPjinfoService.findSelective(webPjinfo);
		setSixPointSystemScore(listWebPjinfo);
		String titles = "1";
		List<String> titleName = new ArrayList<>();
		for (int i = 0; i < listWebPjinfo.size(); i++) {
			if (!titles.contains(listWebPjinfo.get(i).getTitle())) {
				titleName.add(listWebPjinfo.get(i).getTitle());
			}
			titles += listWebPjinfo.get(i).getTitle();
		}
		model.addAttribute("titleName", titleName);
		model.addAttribute("webPjinfoList", listWebPjinfo);
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
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/checkPjInfoResult")
	public String checkPjInfoResult(Model model, WebPj webpj) throws UnsupportedEncodingException {
		WebPjinfo webPjinfo = new WebPjinfo();
		webPjinfo.setPgId(webpj.getPgId());
		webPjinfo.setUserId(webpj.getUserId());
		webPjinfo.setOnOff(webpj.getOnOff());
		List<WebPjinfo> webPjinfoList = webPjinfoService.findSelective(webPjinfo);
		setSixPointSystemScore(webPjinfoList);
		int weightSum = getWeightSum(webPjinfoList);
		model.addAttribute("weightSum", weightSum);
		model.addAttribute("webPjinfoList", webPjinfoList);
		model.addAttribute("allResult", URLDecoder.decode(webpj.getAllResult(), "utf-8"));
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
			double pjinfoavg = webPjinfo.getPjinfoavg();
			seriesData += pjinfoavg + ",";
		}
		Map<String, Object> avg = webPjService.getPgAverageDraw(pgId);
		BigDecimal bigDecimal = (BigDecimal) avg.get("pjavg");
		double pjavg = bigDecimal.doubleValue();
		if (onOff.equals("OFF")) {
			xData += "总分";
			seriesData += pjavg;
		} else {
			xData += "课前总分,";
			double kqpjavg = (double) avg.get("kqpjavg");
			seriesData += kqpjavg + ",";
			xData += "课中总分";
			seriesData += pjavg;
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
