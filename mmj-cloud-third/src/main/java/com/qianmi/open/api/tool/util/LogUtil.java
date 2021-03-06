package com.qianmi.open.api.tool.util;

import com.qianmi.open.api.Constants;
import com.qianmi.open.api.QianmiResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 客户端日志
 * 通讯错误格式：time api app ip os sdk url responseCode
 * 业务错误格式：time response
 */
public class LogUtil {

	private static final Log clog = LogFactory.getLog("sdk.comm.err");
	private static final Log blog = LogFactory.getLog("sdk.biz.err");

	private static String osName = System.getProperties().getProperty("os.name");
	private static String ip = null;
	private static boolean needEnableLogger = true;

	public static void setNeedEnableLogger(boolean needEnableLogger) {
		LogUtil.needEnableLogger = needEnableLogger;
	}

	public static String getIp() {
		if (ip == null) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ip;
	}

	public static void setIp(String ip) {
		LogUtil.ip = ip;
	}

	/**
	 * 通讯错误日志
	 */
	public static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, byte[] content) {
		if (!needEnableLogger) {
			return;
		}
		String contentString = null;
		try {
			contentString = new String(content, "UTF-8");
			logCommError(e, conn, appKey, method, contentString);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 通讯错误日志
	 */
	public static void logCommError(Exception e, String url, String appKey, String method, byte[] content) {
		if (!needEnableLogger) {
			return;
		}
		String contentString = null;
		try {
			contentString = new String(content, "UTF-8");
			logCommError(e, url, appKey, method, contentString);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 通讯错误日志
	 */
	public static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, Map<String, String> params) {
		if (!needEnableLogger) {
			return;
		}
		_logCommError(e, conn, null, appKey, method, params);
	}

	public static void logCommError(Exception e, String url, String appKey, String method, Map<String, String> params) {
		if (!needEnableLogger) {
			return;
		}
		_logCommError(e, null, url, appKey, method, params);
	}

	/**
	 * 通讯错误日志
	 */
	private static void logCommError(Exception e, HttpURLConnection conn, String appKey, String method, String content) {
		Map<String, String> params = parseParam(content);
		_logCommError(e, conn, null, appKey, method, params);
	}

	/**
	 * 通讯错误日志
	 */
	private static void logCommError(Exception e, String url, String appKey, String method, String content) {
		Map<String, String> params = parseParam(content);
		_logCommError(e, null, url, appKey, method, params);
	}

	/**
	 * 通讯错误日志
	 */
	private static void _logCommError(Exception e, HttpURLConnection conn, String url, String appKey, String method, Map<String, String> params) {
		DateFormat df = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		df.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
		String sdkName = Constants.SDK_VERSION;
		String urlStr = null;
		String rspCode = "";
		if (conn != null) {
			try {
				urlStr = conn.getURL().toString();
				rspCode = "HTTP_ERROR_" + conn.getResponseCode();
			} catch (IOException ioe) {
			}
		} else {
			urlStr = url;
			rspCode = "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(df.format(new Date()));// 时间
		sb.append(" ");
		sb.append(method);// API
		sb.append(" ");
		sb.append(appKey);// APP
		sb.append(" ");
		sb.append(getIp());// IP地址
		sb.append(" ");
		sb.append(osName);// 操作系统
		sb.append(" ");
		sb.append(sdkName);// SDK名字,这是例子，请换成其他名字
		sb.append(" ");
		sb.append(urlStr);// 请求URL
		sb.append(" ");
		sb.append(rspCode);
		sb.append(" ");
		sb.append((e.getMessage() + "").replaceAll("\r\n", " "));
		clog.error(sb.toString());
	}

	private static Map<String, String> parseParam(String contentString) {
		Map<String, String> params = new HashMap<String, String>();
		if (contentString == null || contentString.trim().equals("")) {
			return params;
		}
		String[] paramsArray = contentString.split("\\&");
		if (paramsArray != null) {
			for (String param : paramsArray) {
				String[] keyValue = param.split("=");
				if (keyValue != null && keyValue.length == 2) {
					params.put(keyValue[0], keyValue[1]);
				}
			}
		}
		return params;
	}

	/**
	 * 业务/系统错误日志
	 */
	public static void logBizError(String rsp) {
		if (!needEnableLogger) {
			return;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
		StringBuilder sb = new StringBuilder();
		sb.append(df.format(new Date()));
		sb.append(" ");
		sb.append(rsp);
		blog.error(sb.toString());
	}

	/**
	 * 发生特别错误时记录完整错误现场
	 */
	public static void logErrorScene(RequestParametersHolder context, QianmiResponse tRsp, String appSecret) {
		if (!needEnableLogger) {
			return;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
		StringBuilder sb = new StringBuilder();
		sb.append("ErrorScene");
		sb.append(" ");
		sb.append(tRsp.getErrorCode());
		sb.append(" ");
		sb.append(tRsp.getSubCode());
		sb.append(" ");
		sb.append("appSecret***");// 出于安全考虑，不再打印appSecret
		sb.append(" ");
		sb.append(ip);
		sb.append(" ");
		sb.append(osName);
		sb.append(" ");
		sb.append(df.format(new Date()));
		sb.append(" ");
		sb.append("ProtocalMustParams:");
		appendLog(context.getProtocalMustParams(), sb);
		sb.append(" ");
		sb.append("ProtocalOptParams:");
		appendLog(context.getProtocalOptParams(), sb);
		sb.append(" ");
		sb.append("ApplicationParams:");
		appendLog(context.getApplicationParams(), sb);
		sb.append(" ");
		sb.append("Body:");
		sb.append(context.getResponseBody());
		blog.error(sb.toString());
	}

	private static void appendLog(QianmiHashMap map, StringBuilder sb) {
		boolean first = true;
		Set<Map.Entry<String, String>> set = map.entrySet();
		for (Map.Entry<String, String> entry : set) {
			if (!first) {
				sb.append("&");
			} else {
				first = false;
			}
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}
	}
}
