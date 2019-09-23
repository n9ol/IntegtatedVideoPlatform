package com.zzrenfeng.zhsx.aspect;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.SysLog;
import com.zzrenfeng.zhsx.service.SysLogService;
import com.zzrenfeng.zhsx.util.MessageUtils;

/**
 * 日志切面 Test
 */
@Component
@Aspect
public class LogAspect {

	@Resource
	private SysLogService sysLogService;

	@Around(value = "(execution(*  com.zzrenfeng.zhsx.service.impl..save*(..)) || "
			+ "		  execution(*  com.zzrenfeng.zhsx.service.impl..insert*(..)) ||"
			+ "		  execution(*  com.zzrenfeng.zhsx.service.impl..update*(..)) ||"
			+ "		  execution(*  com.zzrenfeng.zhsx.service.impl..delete*(..)) ||"
			+ "		  execution(*  com.zzrenfeng.zhsx.service.impl..add*(..)) ||"
			+ "		  execution(*  com.zzrenfeng.zhsx.service.impl..del*(..))) and !bean(sysLogService)")
	public Object recordLog(ProceedingJoinPoint point) {
		Object obj = null;
		SysLog log = new SysLog();
		try {
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			log.setOperationname(shiroUser.getNickName());
			log.setOperationmehtod(point.getSignature().getName());
			obj = point.proceed();
			log.setIssuccess(MessageUtils.SUCCESS);
			if (obj != null) {
				String mes = Arrays.toString(point.getArgs());
				if (mes.length() > 950) {
					mes = Arrays.toString(point.getArgs()).substring(0, 950);
				}
				log.setContent(mes + obj.getClass().getSimpleName());
			}
		} catch (Exception e) {
			String mes = e.getMessage();
			if (mes.length() > 950) {
				mes = e.getMessage().substring(0, 950);
			}
			log.setReason(MessageUtils.FAilURE + mes);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			log.setOperationdate(new Date());
			sysLogService.recordLog(log);
		}
		return obj;
	}

}
