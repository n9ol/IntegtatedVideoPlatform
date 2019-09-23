package com.zzrenfeng.zhsx.controller.oldtonew;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.ExcelUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 老平台数据同步到新平台
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/oldToNew")
public class OldToNewController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private SysClassroomService sysClassroomService;

	/**
	 * 进入同步页面
	 * 
	 * @return
	 */
	@RequestMapping("/oldToNew")
	public String oldToNew() {
		return "/oldToNew/oldToNew";
	}

	/**
	 * 批量添加用户
	 * 
	 * @param response
	 * @param request
	 * @param filePath
	 * @param userType
	 * @param schoolId
	 */
	@RequestMapping("/batchInsertUser")
	public void batchInsertUser(HttpServletResponse response, HttpServletRequest request, String filePathUser) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		filePathUser = uploadPath + filePathUser;
		Map<String, Object> hm = null;
		try {
			hm = ExcelUtil.getDataFromExcel(filePathUser);
		} catch (Exception e1) {
			WriterUtils.toHtml(response, "execl导入失败");
		}

		if (hm == null) {
			WriterUtils.toHtml(response, "execl导入失败");
			return;
		}
		Sheet sheet = (Sheet) hm.get("sheet");
		Row rowHead = (Row) hm.get("rowHead");
		Integer totalRowNum = (Integer) hm.get("totalRowNum");
		// 判断表头是否正确
		if (rowHead.getPhysicalNumberOfCells() != 10) {
			WriterUtils.toHtml(response, "表头的数量不对,请下载导入模板");
		} else {
			String j = "";
			Date date = new Date();
			String userCode = null;
			String pwd = null;
			String nickName = null;
			String currName = null;
			String gender = null;
			String age = null;
			String schoolId = null;
			String userType = null;
			String bak1 = null;
			String bak2 = null;

			List<User> userList = new ArrayList<User>();
			for (int i = 1; i <= totalRowNum; i++) {
				User user1 = new User();
				user1.setBak("Y");
				user1.setCreateTime(date);
				user1.setModiyTime(date);
				user1.setEXP(50);

				// 获得第i行对象
				Row row = sheet.getRow(i);

				// 获得获得第i行第0列的 String类型对象
				Cell cell = row.getCell((short) 0);
				userCode = ExcelUtil.formatCell(cell);
				if (userCode != null && userCode != "") {
					user1.setUserCode(userCode.trim());
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 1);
				pwd = ExcelUtil.formatCell(cell);
				if (pwd != null && pwd != "") {
					user1.setPassword(pwd.trim());
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 2);
				nickName = ExcelUtil.formatCell(cell);
				if (nickName != null && nickName != "") {
					user1.setNickName(nickName);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 3);
				currName = ExcelUtil.formatCell(cell);
				if (currName != null && currName != "") {
					user1.setCurrName(currName);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 4);
				gender = ExcelUtil.formatCell(cell);
				user1.setGender(gender);

				cell = row.getCell((short) 5);
				age = ExcelUtil.formatCell(cell);
				if (age != null && age != "") {
					user1.setAge(Integer.valueOf(age));
				}

				cell = row.getCell((short) 6);
				schoolId = ExcelUtil.formatCell(cell);
				if (schoolId.equals("0")) {
					schoolId = null;
				}
				user1.setSchoolId(schoolId);

				cell = row.getCell((short) 7);
				userType = ExcelUtil.formatCell(cell);
				if (userType != null && !userType.equals("")) {
					user1.setUserType(userType);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 8);
				bak1 = ExcelUtil.formatCell(cell);
				if (bak1 != null && !bak1.equals("")) {
					user1.setBak1(bak1);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 9);
				bak2 = ExcelUtil.formatCell(cell);
				if (bak2 != null && !bak2.equals("")) {
					user1.setBak2(bak2);
				} else {
					j += i + ",";
					continue;
				}

				userList.add(user1);
			}

			try {
				if (userList != null && userList.size() > 0) {
					userService.insertBatchFromOld(userList);
					if (j.equals(""))
						WriterUtils.toHtml(response, MessageUtils.SUCCESS);
					else
						WriterUtils.toHtml(response, "第" + j + "行因账号已存在或不符合要求,未能添加！");
				} else {
					WriterUtils.toHtml(response, MessageUtils.FAilURE + ",可能原因账号已存在或不符合要求！");
				}
				File f = new File(filePathUser);
				if (f.exists())
					f.delete();
			} catch (Exception e) {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量添加学校到数据库
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 * @param school
	 */
	@RequestMapping("/batchInsertSchool")
	public void batchInsertSchool(HttpServletRequest request, HttpServletResponse response, String filePathSchool) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		filePathSchool = uploadPath + filePathSchool;
		Map<String, Object> hm = null;
		try {
			hm = ExcelUtil.getDataFromExcel(filePathSchool);
		} catch (Exception e1) {
			WriterUtils.toHtml(response, "execl导入失败");
		}

		if (hm == null) {
			WriterUtils.toHtml(response, "execl导入失败");
			return;
		}
		Sheet sheet = (Sheet) hm.get("sheet");
		Row rowHead = (Row) hm.get("rowHead");
		Integer totalRowNum = (Integer) hm.get("totalRowNum");
		// 判断表头是否正确
		if (rowHead.getPhysicalNumberOfCells() != 7) {
			WriterUtils.toHtml(response, "表头的数量不对,请下载导入模板");
		} else {
			String id = null;
			String provinceId = null;
			String cityId = null;
			String countyId = null;
			Date date = new Date();
			String schoolName = null;
			String address = null;
			String isaf = null;

			String j = "";
			List<SysSchool> sList = new ArrayList<SysSchool>();
			for (int i = 1; i <= totalRowNum; i++) {
				SysSchool sysSchool = new SysSchool();
				sysSchool.setCreateTime(date);
				sysSchool.setModiyTime(date);

				// 获得第i行对象
				Row row = sheet.getRow(i);

				// 获得获得第i行第0列的 String类型对象
				Cell cell = row.getCell((short) 0);
				id = ExcelUtil.formatCell(cell);
				if (id != null && !id.equals("")) {
					sysSchool.setId(id);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 1);
				schoolName = ExcelUtil.formatCell(cell);
				if (schoolName != null && !schoolName.equals("")) {
					sysSchool.setSchoolName(schoolName);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 2);
				provinceId = ExcelUtil.formatCell(cell);
				if (provinceId != null && !provinceId.equals("")) {
					sysSchool.setProvinceId(provinceId);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 3);
				cityId = ExcelUtil.formatCell(cell);
				if (cityId != null && !cityId.equals("")) {
					sysSchool.setCityId(cityId);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 4);
				countyId = ExcelUtil.formatCell(cell);
				if (countyId != null && !countyId.equals("")) {
					sysSchool.setCountyId(countyId);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 5);
				address = ExcelUtil.formatCell(cell);
				sysSchool.setAddress(address);

				cell = row.getCell((short) 6);
				isaf = ExcelUtil.formatCell(cell);
				if (isaf != null && !isaf.equals("")) {
					sysSchool.setIsaf(isaf);
				} else {
					sysSchool.setIsaf("N");
				}

				sList.add(sysSchool);

			}
			try {
				if (sList != null && sList.size() > 0) {
					sysSchoolService.insertBatchFromOld(sList);
					if (j.equals(""))
						WriterUtils.toHtml(response, MessageUtils.SUCCESS);
					else
						WriterUtils.toHtml(response, "第" + j + "行的学校名称已存在,未能添加！");
				} else {
					WriterUtils.toHtml(response, MessageUtils.FAilURE + ",可能原因所有学校名称都已存在！");
				}
				File f = new File(filePathSchool);
				if (f.exists())
					f.delete();
			} catch (Exception e) {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量添加教室到数据库
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 * @param classroom
	 */
	@RequestMapping("/batchInsertClassRoom")
	public void batchInsertClassRoom(HttpServletRequest request, HttpServletResponse response, String filePathClass) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		filePathClass = uploadPath + filePathClass;
		Map<String, Object> hm = null;
		try {
			hm = ExcelUtil.getDataFromExcel(filePathClass);
		} catch (Exception e1) {
			WriterUtils.toHtml(response, "execl导入失败");
		}
		if (hm == null) {
			WriterUtils.toHtml(response, "execl导入失败");
			return;
		}
		Sheet sheet = (Sheet) hm.get("sheet");
		Row rowHead = (Row) hm.get("rowHead");
		Integer totalRowNum = (Integer) hm.get("totalRowNum");

		// 判断表头是否正确
		if (rowHead.getPhysicalNumberOfCells() != 8) {
			WriterUtils.toHtml(response, "表头的数量不对,请下载导入模板");
		} else {
			String className = "";
			String classCode = "";
			String serviceIp = "";
			String webPort = "";
			String roomId = "";
			String uid = "";
			String clientIp = "";
			String schoolId = null;

			List<SysClassroom> sList = new ArrayList<SysClassroom>();
			String j = "";
			for (int i = 1; i <= totalRowNum; i++) {
				SysClassroom classroom = new SysClassroom();
				classroom.setBak("Y");
				// 获得第i行对象
				Row row = sheet.getRow(i);

				// 获得获得第i行第0列的 String类型对象
				Cell cell = row.getCell((short) 0);
				classCode = ExcelUtil.formatCell(cell);
				if (classCode != null && !classCode.equals("")) {
					classroom.setClassCode(classCode);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 1);
				className = ExcelUtil.formatCell(cell);
				if (className != null && !className.equals("")) {
					classroom.setClassName(className);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 2);
				schoolId = ExcelUtil.formatCell(cell);
				if (schoolId != null && !schoolId.equals("")) {
					classroom.setSchoolId(schoolId);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 3);
				serviceIp = ExcelUtil.formatCell(cell);
				if (serviceIp != null && !serviceIp.equals("")) {
					classroom.setServiceIp(serviceIp);
				} else {
					j += i + ",";
					continue;
				}

				cell = row.getCell((short) 4);
				webPort = ExcelUtil.formatCell(cell);
				classroom.setWebPort(webPort);

				cell = row.getCell((short) 5);
				roomId = ExcelUtil.formatCell(cell);
				classroom.setRoomId(roomId);

				cell = row.getCell((short) 6);
				uid = ExcelUtil.formatCell(cell);
				classroom.setUid(uid);

				cell = row.getCell((short) 7);
				clientIp = ExcelUtil.formatCell(cell);
				classroom.setClientIp(clientIp);

				sList.add(classroom);
			}
			try {
				if (sList != null && sList.size() > 0) {
					sysClassroomService.insertBatch(sList);
					if (j.equals(""))
						WriterUtils.toHtml(response, MessageUtils.SUCCESS);
					else
						WriterUtils.toHtml(response, "第" + j + "行的数据不符合条件,未能添加！");
				} else {
					WriterUtils.toHtml(response, MessageUtils.FAilURE + ",可能原因数据不符合条件！");
				}
				File f = new File(filePathClass);
				if (f.exists())
					f.delete();
			} catch (Exception e) {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
				e.printStackTrace();
			}
		}
	}

}
