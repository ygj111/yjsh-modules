
package com.hhh.fund.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.hhh.opsservice.wsdl.WriteLogException;
import com.hhh.opsservice.wsdl.WriteLogLogin;
import com.hhh.opsservice.wsdl.WriteLogOperation;
import com.hhh.opsservice.wsdl.WriteLogPerformance;
import com.hhh.security.util.ShiroUtils;

public class LogUploadClient extends WebServiceGatewaySupport {
	//private final static String OPS_SERVICE = "http://192.168.2.42:10003/opsService/opsService?wsdl";
	private final static String NAMESPACE = "http://webservice.ops.platform.hhh.com/";
	private String ops_service = "";

	/**
	 * 传异常日志
	 * @param dpcode 部署点
	 * @param module
	 * @param content
	 */
	public void uploadLogException(final String dp_code, final String module, final String content) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String username = ShiroUtils.getFullname();
				writeLogException(dp_code, username, module, content);
			}
		}).start();;
	}
	
	/**
	 * 上传性能日志
	 * @param module
	 * @param time
	 * @param content
	 */
	public void uploadLogPerformance(final String dp_code, final String module, final long time, final String content) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String username = ShiroUtils.getFullname();
				writeLogPerformance(dp_code, username, module, String.valueOf(time), content);
			}
		}).start();
	}
	
	/**
	 * 上传登录日志
	 * @param dp_code
	 * @param customerId
	 * @param req
	 */
	public void uploadLogLogLogin(final String dp_code, final String customerId,  final HttpServletRequest req) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String username = ShiroUtils.getFullname();
				String account = ShiroUtils.getUsername();
				String agent = req.getHeader("user-agent");
				String os = getClientOS(agent);
				String browserInfo = getBrowserInfo(agent);
				String ip = StringUtil.getIpAddress(req);
				boolean isMobile = HttpRequestDeviceUtils.isMobileDevice(req);
				String os_type = isMobile ? "M" : "P";
				
				writeLogLogin(dp_code, username, account, customerId, os, browserInfo, ip, os_type, new Date());
			}
		}).start();
	}
	
	/**
	 * 上传操作日志
	 * @param dp_code
	 * @param customerId
	 * @param appname
	 * @param operation
	 */
	public void uploadLogLogOperation(final String dp_code, final String customerId, final String appname, final String operation) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String username = ShiroUtils.getFullname();
				String account = ShiroUtils.getUsername();
				
				writeLogOperation(dp_code, username, account, customerId, appname, operation, new Date());
			}
		}).start();
	}
	
	/**
	 * 获取用户使用的浏览器
	 * @param agent
	 * @return
	 */
	private String getBrowserInfo(String agent) {
		String browserInfo = "";
		try {
			String[] userAgentInfo = agent.split(" ");
			for (int index = 0; index < userAgentInfo.length; index++) {
				String info = userAgentInfo[index];
				if (info.toUpperCase().contains("MSIE")) {
					String version = userAgentInfo[index + 1].substring(0, userAgentInfo[index + 1].length() - 1);
					browserInfo = "IE" + " " + version;
					break;
				} else if (info.toUpperCase().contains("FIREFOX")) {
					String version = userAgentInfo[index].substring(1 + "FIREFOX".length());
					browserInfo = "FireFox" + " " + version;
					break;
				} else if (info.toUpperCase().contains("CHROME")) {
					String version = userAgentInfo[index].substring(1 + "CHROME".length());
					browserInfo = "Chrome" + " " + version;
					break;
				} else if (info.toUpperCase().contains("OPERA")) {
					String version = userAgentInfo[index].substring(1 + "OPERA".length());
					browserInfo = "Opera" + " " + version;
					break;
				} else if (info.toUpperCase().contains("SAFARI")) {
					String version = userAgentInfo[index].substring(1 + "SAFARI".length());
					browserInfo = "Safari" + " " + version;
					break;
				}
			}
			if (browserInfo.isEmpty()) {
				if (agent.toUpperCase().contains("LIKE GECKO") && agent.toUpperCase().contains("TRIDENT/7.0")) {
					browserInfo = "IE 11.0";
				}
			}
			if (agent.toUpperCase().contains("EDGE")) {
				String version = agent.substring(1 + "EDGE".length());
				browserInfo = "Edge" + " " + version;
			}
		} catch (Exception e) {
		}
		return browserInfo;
	}

	/**
	 * 获取客户端的操作系统
	 * @param userAgent
	 * @return
	 */
	private String getClientOS(String userAgent) {
		String cos = "unknow os";

		Pattern p = Pattern.compile(".*(Windows NT 6\\.1).*");
		Matcher m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win 7";
			return cos;
		}

		p = Pattern.compile(".*(Windows NT 6\\.3).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win8";
			return cos;
		}

		p = Pattern.compile(".*(Windows NT 6\\.4|Windows NT 10\\.0).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win10";
			return cos;
		}

		p = Pattern.compile(".*(Windows NT 5\\.1|Windows XP).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "WinXP";
			return cos;
		}

		p = Pattern.compile(".*(Windows NT 5\\.2).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win2003";
			return cos;
		}

		p = Pattern.compile(".*(Win2000|Windows 2000|Windows NT 5\\.0).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win2000";
			return cos;
		}

		p = Pattern.compile(".*(Mac|apple|MacOS8).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "MAC";
			return cos;
		}

		p = Pattern.compile(".*(WinNT|Windows NT).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "WinNT";
			return cos;
		}

		p = Pattern.compile(".*Linux.*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Linux";
			return cos;
		}

		p = Pattern.compile(".*(68k|68000).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Mac68k";
			return cos;
		}

		p = Pattern.compile(".*(9x 4.90|Win9(5|8)|Windows 9(5|8)|95/NT|Win32|32bit).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win9x";
			return cos;
		}

		return cos;
	}

	/**
	 * 上传异常日志
	 * 
	 * @param dp_code
	 * @param login_user
	 * @param module
	 * @param content
	 */
	private void writeLogException(String dp_code, String login_user, String module, String content) {
		WriteLogException request = new WriteLogException();
		request.setDpCode(dp_code);
		request.setLoginUser(login_user);
		request.setModule(module);
		request.setContent(content);
		getWebServiceTemplate().marshalSendAndReceive(ops_service, request,
				new SoapActionCallback(NAMESPACE + "writeLogException"));
	}

	private void writeLogPerformance(String dp_code, String login_user, String module, String cost_time,
			String content) {
		WriteLogPerformance request = new WriteLogPerformance();
		request.setDpCode(dp_code);
		request.setLoginUser(login_user);
		request.setModule(module);
		request.setCostTime(cost_time);
		request.setContent(content);
		getWebServiceTemplate().marshalSendAndReceive(ops_service, request,
				new SoapActionCallback(NAMESPACE + "writeLogPerformance"));
	}

	private void writeLogLogin(String dp_code, String userName, String userAccount, String customerId, String os,
			String browserInfo, String ip, String os_type, Date time) {
		WriteLogLogin request = new WriteLogLogin();
		request.setDpCode(dp_code);
		request.setUserName(userName);
		request.setUserAccount(userAccount);
		request.setCustomerId(customerId);
		request.setOs(os);
		request.setBrowserType(browserInfo);
		request.setIp(ip);
		request.setOsType(os_type);
		String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
		request.setTime(timeStr);
		getWebServiceTemplate().marshalSendAndReceive(ops_service, request,
				new SoapActionCallback(NAMESPACE + "writeLogLogin"));

	}

	private void writeLogOperation(String dp_code, String userName, String userAccount, String customerId,
			String appName, String operation, Date time) {
		WriteLogOperation request = new WriteLogOperation();
		request.setDpCode(dp_code);
		request.setUserName(userName);
		request.setUserAccount(userAccount);
		request.setCustomerId(customerId);
		request.setAppName(appName);
		request.setOperation(operation);
		String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
		request.setTime(timeStr);
		getWebServiceTemplate().marshalSendAndReceive(ops_service, request,
				new SoapActionCallback(NAMESPACE + "writeLogOperation"));
	}

	public void setOpsService(String ops_service) {
		this.ops_service = ops_service;
	}
}
