package com.zzrenfeng.zhsx.controller.courres;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.CourResource;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.service.CourResourceService;
import com.zzrenfeng.zhsx.service.SysDictService;

/**
 * 课件资源控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/courRes")
public class CourResController extends BaseController {

	@Resource
	private CourResourceService courResourceService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private String fileWebPath;

	/**
	 * 进入课件资源页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping("/ziyuan")
	public String ziyuan(HttpServletRequest request, HttpServletResponse response, Model model) {

		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("gradeList", sysDicts);

		// 获得科目
		sysDict.setKeyname("S");
		sysDicts = sysDictService.findSelective(sysDict);
		// 科目去重复
		List<SysDict> subjects = new ArrayList<SysDict>();
		String tem = "1";
		for (SysDict o : sysDicts) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		model.addAttribute("subjectsList", subjects);

		// 获得版本
		sysDict.setKeyname("V");
		sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("versionList", sysDicts);

		return "/web/courRes/ziyuan";
	}

	/**
	 * 得到资源数据
	 * 
	 * @param request
	 * @param courResource
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping("/getziyuanData")
	public String getziyuanData(HttpServletRequest request, CourResource courResource, Model model, Integer p) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);

		courResource.setState("Y");
		courResource.setBak2("Y");
		if (isLogined()) {
			courResource.setCurrUserId(getUserId());
		}
		Page<CourResource> pageInfo = courResourceService.findPageSelective(courResource, p, 6);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		List<CourResource> lists = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		return "/web/courRes/ziyuanData";
	}

	/**
	 * 进入在线浏览课件资源页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/watchCour")
	public String watchCour(Model model, String id) {
		CourResource courRes = courResourceService.findByKey(id);
		model.addAttribute("courRes", courRes);

		// 获得相关文档
		CourResource courRes1 = new CourResource();
		courRes1.setState("Y");
		courRes1.setBak2("Y");
		courRes1.setGradeName(courRes.getGradeName());
		courRes1.setSubjectsName(courRes.getSubjectsName());
		Page<CourResource> pageInfo = courResourceService.findPageSelective(courRes1, 1, 10);
		List<CourResource> courResList = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("courResList", courResList);
		return "/web/courRes/watchCour";
	}

	/**
	 * 获得_pdf文件流
	 * 
	 * @param filePath
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/receivePdfStream/{courResId}")
	public void receivePdfStream(@PathVariable("courResId") String courResId, HttpServletRequest request,
			HttpServletResponse response) {
		CourResource courRes = courResourceService.findByKey(courResId);
		String path = fileWebPath + "/courRes" + courRes.getPdfPath();
		try {
			InputStream fileInputStream = getYCFile(path);
			response.setHeader("Content-Disposition", "attachment;fileName=test.pdf");
			response.setContentType("multipart/form-data");
			OutputStream outputStream = response.getOutputStream();
			IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 获得流数据
	 * 
	 * @param urlPath
	 * @return
	 */
	public InputStream getYCFile(String urlPath) {
		InputStream inputStream = null;
		try {
			try {
				String strUrl = urlPath.trim();
				URL url = new URL(strUrl);
				// 打开请求连接
				URLConnection connection = url.openConnection();
				HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
				httpURLConnection.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				// 取得输入流，并使用Reader读取
				inputStream = httpURLConnection.getInputStream();
				return inputStream;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				inputStream = null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			inputStream = null;
		}
		return inputStream;
	}

}
