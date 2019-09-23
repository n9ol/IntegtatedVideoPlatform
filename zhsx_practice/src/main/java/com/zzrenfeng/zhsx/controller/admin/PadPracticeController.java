package com.zzrenfeng.zhsx.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.TpPracticeResult;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.service.TpPracticeExecService;
import com.zzrenfeng.zhsx.service.TpPracticeLibraryService;
import com.zzrenfeng.zhsx.service.TpPracticeOptionService;
import com.zzrenfeng.zhsx.service.TpPracticeQuestionService;
import com.zzrenfeng.zhsx.service.TpPracticeResultService;
import com.zzrenfeng.zhsx.service.TpPracticeScheduleService;
import com.zzrenfeng.zhsx.service.TpPracticeService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.shiro.UserNamePasswordUserTypeToken;


@Controller
@RequestMapping(value = "/padPractice")
public class PadPracticeController extends BaseController {
	@Resource
	private TestWebQuestionService testWebQuestionService;
	@Resource
	private TestWebTestService testWebTestService;
	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswer;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private TpPracticeService tpPracticeService;
	@Resource
	private TpPracticeExecService tpPracticeExecService;
	@Resource
	private TpPracticeLibraryService tpPracticeLibraryService;
	@Resource
	private TpPracticeOptionService tpPracticeOptionService;
	@Resource
	private TpPracticeQuestionService tpPracticeQuestionService;
	@Resource
	private UserService userService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private LoTermTimeService loTermTimeService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private LoClassTimeService loClassTimeService;
	@Resource
	private TpPracticeScheduleService tpPracticeScheduleService;
	@Resource
	private TpPracticeResultService tpPracticeResultService;
	
	
	
	
	/**
	 * 解析key
	 * @param key
	 * @return
	 */
	private String analysisKey(String key){
		
		return key;
	}
	
	/**
	 * 获得上课练习记录（手写板端查看）
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/examRecordPad")
	public String examRecordPad(HttpServletRequest request, HttpServletResponse response,Model model,TpPracticeResult wql) {
		
		 Integer p=null;
		 String qid=null; 
//		 TpPracticeResult wql=new TpPracticeResult();
		if (p == null) {
			p = 1;
		}
		String key =request.getParameter("key");
		String userId = analysisKey(key);
		String classType = request.getParameter("classType");
		
		try {
			if(!userId.equals(getUserId())){
				 User  theUser = userService.findByKey(userId);
					Subject subject = SecurityUtils.getSubject();
					UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(theUser.getUserCode(), theUser.getPassword());
					try {
						subject.login(token);
					} catch (Exception e) {
						token.clear();
					}
			}
		} catch (Exception e1) {
			 User  theUser = userService.findByKey(userId);
				Subject subject = SecurityUtils.getSubject();
				UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(theUser.getUserCode(), theUser.getPassword());
				try {
					subject.login(token);
				} catch (Exception e) {
					token.clear();
				}
		}
		
			
			Map<String, Object> t = new HashMap<String, Object>();
			Page<Map<String, Object>> pageInfo = null;
			if("Z".equals(classType)){
				t.put("userId", userId);
				t.put("classType", classType);
				if (wql.getSearchClass() != null && wql.getSearchClass().length() > 0) {
					t.put("searchClass", wql.getSearchClass());
				}
			 
				pageInfo = tpPracticeResultService.findPageClassRecordPad(t, p, 10);
				for (Map<String, Object> map : pageInfo) {
					String id = map.get("lId")+"";
					Map<String, Object> tt = new HashMap<String, Object>();
					tt.put("zId", id);
					tt.put("classType", "F");
					if (wql.getSearchClass() != null && wql.getSearchClass().length() > 0) {
						tt.put("searchClass", wql.getSearchClass());
					}
				 	Page<Map<String, Object>> pageInfo2 = tpPracticeResultService.findPageClassRecordPad(tt, 1, 10);
				 	map.put("fClass", pageInfo2);
				}
				
			}else if("F".equals(classType)){
				t.put("userId", userId);
				t.put("classType", classType);
				if (wql.getSearchClass() != null && wql.getSearchClass().length() > 0) {
					t.put("searchClass", wql.getSearchClass());
				}
				pageInfo = tpPracticeResultService.findPageClassRecordPad(t, p, 10);
				
			}
			
		 
		int pages = pageInfo.getPages();
		List<Map<String, Object>> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("qid", qid);
		model.addAttribute("wq", wql);
		model.addAttribute("classType", classType);
		model.addAttribute("key", key);
		return "/admin/prac/examRecordPad";
	}
	
	/**
	 * 判断答题是否正确
	 * @param rights 正确答案
	 * @param userAnswer 学生答案
	 * @return
	 */
	private static boolean equelseTheAnswer(String rights,String userAnswer){
		if(StringUtils.isEmpty(rights)||StringUtils.isEmpty(userAnswer))
			return false;
		String r = rights.replaceAll(",+", "");
		if(r.length()!=userAnswer.length())
			return false;
		userAnswer.compareToIgnoreCase(rights);
		char[] r2 = r.toCharArray();
		for (char c : r2) {
			if(!userAnswer.contains(String.valueOf(c))){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 客观题答题统计
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/detailedRecordPad")
	public String detailedRecordPad(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, String classId,String classType,String key, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}
		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);// 课程表Id
		t.put("classId", classId);
		if (!StringUtils.isEmpty(wql.getSearchType())) {
			t.put("searchType", wql.getSearchType());
		}
		if (!StringUtils.isEmpty(wql.getSearchQuestion())) {
			t.put("searchQuestion", wql.getSearchQuestion());
		}

		Page<Map<String, Object>> pageInfo = tpPracticeResultService.findDetailedRecord(t, p, 5);

		int pages = pageInfo.getPages();
		List<Map<String, Object>> lists = pageInfo.getResult();

		for (Map<String, Object> map : lists) {
			String ops = map.get("options") + "";
			String[] arr = ops.split("<br/>");
			List<String> x = new ArrayList<String>();
			List<Integer> y = new ArrayList<Integer>();
			for (int i = 0; ops.length()>0&&i < arr.length; i++) {
				x.add(arr[i].charAt(0) + "");
				y.add(0);
			}

			double rig = 0;// 答对的人数
			double all = 0;// 所有答题人数

			t.put("questionId", map.get("id") + "");
			
			List<Map<String, Object>> li = tpPracticeResultService.findAnswerStatistics(t);
			for (int i = 0; i < li.size(); i++) {
				Map<String, Object> m = li.get(i);
				if (m.get("userAnswer") == null) {
					break;// 没有学生答过该题
				}

				// ----计算正确率
				Object obj = m.get("rights");
				if (obj != null) {
					String rights = obj.toString();
					if (equelseTheAnswer(rights,m.get("userAnswer").toString())) {
						rig = Double.parseDouble(m.get("ansNum") + "");
					}
				}
				if (m.get("ansNum") != null) {
					all += Double.parseDouble(m.get("ansNum") + "");
				}

				// ----统计选项选择人数
				if("B".equals(map.get("type")+"")){//多选题
					String xs = m.get("userAnswer") + "";
					char[] xsch = xs.toCharArray();
					for (int j = 0; j < xsch.length; j++) {
						int inx = x.indexOf(String.valueOf(xsch[j]));
						if (inx != -1) {
							int yy= y.get(inx)+Integer.parseInt(m.get("ansNum") + "");
							y.set(inx, yy);
						}
					}
				}else{
					String xs = m.get("userAnswer") + "";
					int inx = x.indexOf(xs);
					if (inx != -1) {
						y.set(inx, Integer.parseInt(m.get("ansNum") + ""));
					}
				}
			}

			map.put("correctRate", all < 1 ? 0 : ((rig / all) * 100));
			map.put("xAxisData", x.toString());
			map.put("yAxisData", y.toString());
		}
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("lId", lId);
		model.addAttribute("classId", classId);
		model.addAttribute("classType", classType);
		model.addAttribute("key", key);
		model.addAttribute("wq", wql);
		return "/admin/prac/detailedRecordPad";
	}

	/**
	 * 获得学生客观及主观题答题情况
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/findSutdentRecordPad")
	public String findSutdentRecordPad(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, String classId, String classType, String key, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}

		/**
		 * 1.查找学生，（客观题正确率） 详细（主观题答题展示） 2.查找学生答题情况 3. 4.SELECT t.id, t.userAnswer, t.boardId, t.userName, tt.rights, tt.type FROM tp_practice_result t LEFT JOIN tp_practice_question
		 * tt ON tt.id = t.questionId WHERE t.bak1 = '08a86cae-bcba-11e8-8e64-001c25d63ebd'
		 * 
		 */
		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);
		t.put("classId", classId);
		if (wql.getSearchUserName() != null && wql.getSearchUserName().length() > 0) {
			t.put("searchUserName", wql.getSearchUserName());
		}

		Page<Map<String, Object>> pageInfo = tpPracticeResultService.findSutdentRecord(t, p, 8);
		int pages = pageInfo.getPages();
		List<Map<String, Object>> lists = pageInfo.getResult();

		for (Map<String, Object> map : lists) {
//			String boardId = map.get("boardId") + "";
//			String scheduleId = map.get("scheduleId") + "";
			// 查客观题正确率
			map.put("searchType", 0);
			List<Map<String, Object>> zql = tpPracticeResultService.findSutdentAnswerInfo(map);
			double a = 0;
			double b = zql.size() > 0 ? zql.size() : 1;
			for (Map<String, Object> map2 : zql) {
				Object obj = map2.get("rights");
				if (obj != null) {
					String rights = obj.toString();
					if (equelseTheAnswer(rights,map2.get("userAnswer").toString())) {
						a++;
					}
				}
			}
			map.put("zql", (a / b) * 100);// 正确率
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("lId", lId);
	    model.addAttribute("classId", classId);
	    model.addAttribute("classType", classType);
	    model.addAttribute("key", key);
		model.addAttribute("wq", wql);
		return "/admin/prac/sutdentRecordPad";
	}
	
	
	/**
	 * 错误分析
	 * 
	 * @return
	 */
	@RequestMapping("/studentAnswerHistoryPad")
	public String studentAnswerHistoryPad(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, String boardId,String classId, String classType,String key, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}

		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);
		t.put("searchType", 0);
		t.put("boardId", boardId);
		t.put("classId", classId);
		List<Map<String, Object>> list = tpPracticeResultService.findSutdentAnswerInfo(t);

		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			model.addAttribute("ansPoint", map.get("rightAnswer"));
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("lists", list);
		model.addAttribute("lId", lId);
		model.addAttribute("wq", wql);
		model.addAttribute("boardId", boardId);
		model.addAttribute("classId", classId);
		model.addAttribute("classType", classType);
		model.addAttribute("key", key);
		return "/admin/prac/studentAnswerHistoryPad";
	}
	
	
	/**
	 * 主观题答题情况
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param lId
	 * @param boardId
	 * @param wql
	 * @return
	 */
	@RequestMapping("/sutdentSubjectivePad")
	public String sutdentSubjectivePad(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, String boardId,String classId, String classType,String key,TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}

		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);
		t.put("searchType", 1);
		t.put("boardId", boardId);
		List<Map<String, Object>> list = tpPracticeResultService.findSutdentAnswerInfo(t);

		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			model.addAttribute("ansPoint", map.get("rightAnswer"));
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("lists", list);
		model.addAttribute("lId", lId);
		model.addAttribute("wq", wql);
		model.addAttribute("boardId", boardId);
		model.addAttribute("classId", classId);
		model.addAttribute("classType", classType);
		model.addAttribute("key", key);
		return "/admin/prac/sutdentSubjectivePad";
	}
}
