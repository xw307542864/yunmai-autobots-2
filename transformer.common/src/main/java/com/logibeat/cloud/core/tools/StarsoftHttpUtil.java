package com.logibeat.cloud.core.tools;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class StarsoftHttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(StarsoftHttpUtil.class);
	
	private static final HttpClient httpClient;

	static {
		MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
		manager.getParams().setDefaultMaxConnectionsPerHost(20);
		manager.getParams().setMaxTotalConnections(20);
		httpClient = new HttpClient(manager);
	}
	
	public static String post(String url, String body) {

		// url或者body为空直接返回null
		if (StringUtils.isBlank(url) || StringUtils.isBlank(body)) {
			return null;
		}
		
		System.out.println("url=" + url);
		System.out.println("body=" + body);
		
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();

		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(30000);

		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(120000);

		// post 请求地址
		UngzipPostMethod postMethod = new UngzipPostMethod(url);

		// 表头json
		Header contentType = new Header("Content-type", "application/json");
		postMethod.setRequestHeader(contentType);

		// 支持gzip
		Header acceptEncode = new Header("Accept-Encoding", "gzip");
		postMethod.setRequestHeader(acceptEncode);

		// 入参
		postMethod.setRequestEntity(new StringRequestEntity(body));

		String strResponse = null;
		int statusCode = -1;
		try {
			statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new IllegalStateException("Method failed: " + postMethod.getStatusLine());
			}

			strResponse = postMethod.getResponseBodyAsString();
			return strResponse;
		} catch (Exception ex) {
			logger.error("URL：{} BODY：{} post error.. {}", url, body, ex.toString());
			throw new IllegalStateException(ex.toString());
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}

	public static String postTimeOut(String url, String body, int connectionTimeOut, int readTimeOut) {

		// url或者body为空直接返回null
		if (StringUtils.isBlank(url) || StringUtils.isBlank(body)) {
			return null;
		}

		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();

		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(connectionTimeOut);

		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(readTimeOut);

		// post 请求地址
		UngzipPostMethod postMethod = new UngzipPostMethod(url);

		// 表头json
		Header contentType = new Header("Content-type", "application/json");
		postMethod.setRequestHeader(contentType);

		// 支持gzip
		Header acceptEncode = new Header("Accept-Encoding", "gzip");
		postMethod.setRequestHeader(acceptEncode);

		// 入参
		postMethod.setRequestEntity(new StringRequestEntity(body));

		String strResponse = null;
		int statusCode = -1;
		try {
			statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new IllegalStateException("Method failed: " + postMethod.getStatusLine());
			}

			strResponse = postMethod.getResponseBodyAsString();
			return strResponse;
		} catch (Exception ex) {
			logger.error("URL：{} BODY：{} 返回结果超时.. {}", url, body, ex.toString());
			return null;
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}
}

class UngzipPostMethod extends org.apache.commons.httpclient.methods.PostMethod {
	public UngzipPostMethod(String uri) {
		super(uri);
	}

	@Override
	public String getResponseBodyAsString() throws IOException {
		GZIPInputStream gzin;
		if (getResponseBody() != null || getResponseStream() != null) {
			if (getResponseHeader("Content-Encoding") != null
					&& getResponseHeader("Content-Encoding").getValue().toLowerCase().indexOf("gzip") != -1) {
				InputStream is = getResponseBodyAsStream();
				gzin = new GZIPInputStream(is);

				InputStreamReader isr = new InputStreamReader(gzin, getResponseCharSet());

				BufferedReader br = new BufferedReader(isr);
				StringBuilder sb = new StringBuilder();
				String tmp;
				while ((tmp = br.readLine()) != null) {
					sb.append(tmp);
					sb.append("\r\n");
				}
				br.close();
				isr.close();
				return sb.toString();
			} else {
				// 否则正常返回
				return super.getResponseBodyAsString();
			}
		} else {
			return null;
		}
	}
}
