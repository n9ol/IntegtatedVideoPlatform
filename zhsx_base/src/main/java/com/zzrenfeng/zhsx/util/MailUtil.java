package com.zzrenfeng.zhsx.util;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * 邮件工具类
 */
public class MailUtil {

	// 服务器地址
	public static final String HOST = "smtp.163.com";

	// 协议
	public static final String PROTOCOL = "smtp";

	// 发送端口
	public static final int PORT = 25;

	// 发件人的email
	public static final String FROM = "renfeng_email@163.com";

	// 发件人密码
	public static final String PWD = "renfeng5188";

	/**
	 * 发送邮件
	 * 
	 * @param toEmail
	 *            给谁发
	 * @param userCode
	 *            用户账号
	 * @param text
	 *            发送内容
	 * @param title
	 *            发送主体
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void send_mail(String toEmail, String userCode, String text, String title) {

		// 创建连接对象 连接到邮件服务器
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.store.protocol", PROTOCOL);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", "true");

		// 设置发送邮件的账号和密码
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}

		};
		Session session = Session.getDefaultInstance(props, authenticator);

		// 创建邮件对象
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress(FROM));
			// 设置收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			// 设置主题
			message.setSubject(MimeUtility.encodeText(title, "UTF-8", "B"));
			// 设置时间
			message.setSentDate(new Date());
			// 设置邮件正文 第二个参数是邮件发送的类型
			text = emailSendText(userCode, text);
			message.setContent(text, "text/html;charset=UTF-8");
			// 发送一封邮件
			Transport.send(message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 编写邮箱发送内容
	 * 
	 * @param userCode
	 *            发送的用户账号
	 * @param text
	 *            发送的动态内容
	 * @return
	 */
	public static String emailSendText(String userCode, String textCode) {
		Calendar now = Calendar.getInstance();

		String text = "<div style=\"margin-left:2em;\">尊敬的" + userCode + "用户：</div>";
		text += "<div style=\"margin-left:4em;\">您好！ </div>";
		text += "<div style=\"margin-left:4em;\">您于" + now.get(Calendar.YEAR) + "年" + (now.get(Calendar.MONTH) + 1)
				+ "月" + now.get(Calendar.DAY_OF_MONTH) + "日在淇县教育综合视讯管理平台通过邮箱验证重置密码，验证码信息如下：</div>";
		text += "<div style=\"margin:16px 0 32px 8em;\"><a href=\"javascript:;\" style=\"text-decoration:none;\">"
				+ textCode + "</a></div>";
		text += "<div style=\"margin-left:2em;color:#FF5722;font-size:20px;\">温馨提示：</div>";
		text += "<div style=\"margin-left:4em;padding-top:10px;\">我们平台不会以任何形式向您索要验证码，请确保您的验证码安全。</div>";
		text += "<div style=\"text-align:right;margin-right:5em;\">郑州仁峰软件开发有限公司 </div>";
		text += "<div style=\"text-align:right;margin-right:10em;\">" + now.get(Calendar.YEAR) + "年"
				+ (now.get(Calendar.MONTH) + 1) + "月" + now.get(Calendar.DAY_OF_MONTH) + "日</div>";

		return text;
	}

}