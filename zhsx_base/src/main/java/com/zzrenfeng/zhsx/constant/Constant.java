package com.zzrenfeng.zhsx.constant;

/**
 * 常量类
 * 
 * @author 田杰熠
 *
 */
public class Constant {

	/**
	 * 接口返回的说明信息-获取数据成功
	 */
	public final static String GET_DATA_SUCCESSFULLY = "获取数据成功";

	/**
	 * 接口返回的说明信息-获取数据失败
	 */
	public final static String GET_DATA_FAILURE = "数据不存在、或者获取失败";

	/**
	 * 返回信息 - 同一时间内数据存在
	 */
	public final static String DATA_CURRTIME_HAVE = "同一时间内数据已存在";

	/**
	 * 后台分页统一设置 ,页面大小 = 10
	 */
	public final static int PAGESIZE = 10;

	/**
	 * 字典类型-院系
	 */
	public final static String DICTTYPE_DEPARTMENT = "DE";

	/**
	 * 字典类型-专业
	 */
	public final static String DICTTYPE_SPECIALTY = "SP";

	/**
	 * 字典类型-科目
	 */
	public final static String DICTTYPE_SUBJECT = "SU";

	/**
	 * 字典类型-公共科目的-pid
	 */
	public final static String DICTTYPE_SUBJECT_PUBLIC_PID = "1";

	/**
	 * 字典类型-院系-pid
	 */
	public final static String DICTTYPE_DEPARTMENT_PID = "0";

	/**
	 * 背景图片路径的主键id
	 */
	public final static String IMAGE_BACKGROUNG_PATH_ID = "1";

	/**
	 * 考试名称、日期信息的主键id
	 */
	public final static String EXAM_SCHEDULE_DATE_ID = "1";

	/**
	 * 学校简介主键id
	 */
	public final static String SCHOOL_PROFILE_ID = "1";

	/**
	 * 教室状态 - 上课
	 */
	public final static String CLASSROOM_STATE_COURSE = "C";

	/**
	 * 教室状态 - 考试
	 */
	public final static String CLASSROOM_STATE_EXAM = "E";

	/**
	 * 教室状态 - 社会考试
	 */
	public final static String CLASSROOM_STATE_SOCIAL_EXAM = "S";

	/**
	 * 教室状态 - 紧急公告
	 */
	public final static String CLASSROOM_STATE_ANNOUNCEMENT = "A";

	/**
	 * 公告类型- 普通公告
	 */
	public final static int ANNOUNCEMENT_INFO_STATE_GENERAL = 1;

	/**
	 * 公告类型- 紧急公告
	 */
	public final static int ANNOUNCEMENT_INFO_STATE_URGENT = 2;

	/**
	 * 用户登录异常信息-账号未激活
	 */
	public final static String USER_LOGIN_EXCEPTION_ACTIVATIONACCOUNT = "账号未激活";
	/**
	 * 用户状态 - 未激活
	 */
	public final static int USER_STATE_ACTIVATIONACCOUNT = 0;

	/**
	 * 用户登录异常信息-该账号已被禁用
	 */
	public final static String USER_LOGIN_EXCEPTION_DISABLEDACCOUNT = "该账号已被禁用";
	/**
	 * 用户状态 - 该账号已被禁用
	 */
	public final static int USER_STATE_DISABLEDACCOUNT = 2;

	/**
	 * 用户状态 - 正常的
	 */
	public final static int USER_STATE_NORMAL = 1;

	/**
	 * 一个星期共有7天
	 */
	public final static int DAYS_OF_THE_WEEK = 7;

	/**
	 * 社会考试模板类型码 - 模板一
	 */
	public final static String SOCIAL_EXAM_TEMPLATE_TYPE1 = "1";
	/**
	 * 社会考试模板类型码 - 模板二
	 */
	public final static String SOCIAL_EXAM_TEMPLATE_TYPE2 = "2";
	/**
	 * 社会考试模板类型码 - 模板三
	 */
	public final static String SOCIAL_EXAM_TEMPLATE_TYPE3 = "3";

	/**
	 * Q 表示正在直播和即将直播 (未结束和未开始)
	 */
	public final static String COURSE_SCHEDULE_TIMESORTING_Q = "Q";

	/**
	 * Z 表示正在直播
	 */
	public final static String COURSE_SCHEDULE_TIMESORTING_Z = "Z";

	/**
	 * J 表示即将直播
	 */
	public final static String COURSE_SCHEDULE_TIMESORTING_J = "J";
	/**
	 * Y 表示已经结束的直播
	 */
	public final static String COURSE_SCHEDULE_TIMESORTING_Y = "Y";

	/**
	 * 星级进行评估评分的满分值(如：6为6分制,100为百分制)
	 */
	public final static int PG_RATE_MAX = 6;

	/**
	 * 是否评估-已经评估
	 */
	public final static int ISPJ_YES = 1;

	/**
	 * 评估类型-在线离线之在线
	 */
	public final static String WEBPJ_ONOFF_ON = "ON";

	/**
	 * 直播课程类型-在线评估
	 */
	public final static String COURSESCHEDULE_TYPE_A = "A";
	
	/**
	 * 自动发布用户ID
	 */
	public final static String AUTO_PUBLISH_USERID = "AUTOPUBLISHER";

}
