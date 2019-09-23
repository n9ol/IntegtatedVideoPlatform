package com.zzrenfeng.zhsx.controller.androidios;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.WebComments;
import com.zzrenfeng.zhsx.service.WebCommentsService;
import com.zzrenfeng.zhsx.util.MessageUtils;

/**
 * 移动端评论接口.
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/androidiosComments")
public class AndroidiosCommentsController extends BaseController {

	@Resource
	private WebCommentsService webCommentsService;

	/**
	 * 获得评论.
	 */
	@ResponseBody
	@RequestMapping("/commentsList")
	public AndroidiosModel commentsList(WebComments webComments, Integer p) {
		if (p == null) {
			p = 1;
		}
		webComments.setIsShown("Y");
		Page<WebComments> pageInfo = webCommentsService.findPageSelective(webComments, p, 10);
		List<WebComments> lists = pageInfo.getResult();
		int totalPage = pageInfo.getPages();// 总页数
		long totalNum = pageInfo.getTotal();

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(lists);
		androidiosModel.setCurrPage(p);
		androidiosModel.setTotalPage(totalPage);
		androidiosModel.setTotalNum(totalNum);
		return androidiosModel;
	}

	/**
	 * 添加评论信息到数据库.
	 * 
	 * @param response
	 * @param webComments
	 */
	@ResponseBody
	@RequestMapping("/insterComment")
	public AndroidiosModel insterComment(HttpServletResponse response, @Validated WebComments webComments) {
		AndroidiosModel androidiosModel = new AndroidiosModel();

		webComments.setUserId(getUserId());
		webComments.setIsShown("Y");
		webComments.setThumbsUp(0);
		webComments.setThumbsDown(0);
		Date date = new Date();
		webComments.setModiyTime(date);
		webComments.setCreateTime(date);
		try {
			webCommentsService.insert(webComments);
			androidiosModel.setData(MessageUtils.SUCCESS);
		} catch (Exception e) {
			androidiosModel.setData(MessageUtils.FAilURE);
			e.printStackTrace();
		}
		return androidiosModel;
	}

}
