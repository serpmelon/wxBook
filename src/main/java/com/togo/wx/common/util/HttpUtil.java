package com.togo.wx.common.util;

import com.togo.wx.common.entity.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年08月26日 21:30
 * @since 1.0
 */
@Component
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final char QP_SEP_A = '&';
    private static final String NAME_VALUE_SEPARATOR = "=";

    private final static String STRING_UTF_8 = "UTF-8";
    private final static Charset CHARSET_UTF_8 = Charset.forName(STRING_UTF_8);

    private final static int READ_TIMEOUT = 30 * 1000; // 即  socketTimeout
    private final static int CONNECTION_REQUEST_TIMOUT = 30 * 1000;
    private final static int CONNETION_TIMEOUT = 30 * 1000;
    private final static int HTTP_CONNECT_MAX_TOTAL = 500;
    private final static int HTTP_CONNECT_DEFAULT_MAXP_ERROUTE = 100;
    ;

    public void test(){

        System.out.println("test" + STRING_UTF_8);
    }
    // 设置默认的连接配置：几种超时时间
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMOUT).setConnectTimeout(CONNETION_TIMEOUT).setSocketTimeout(READ_TIMEOUT).build();
    private static PoolingHttpClientConnectionManager poolConnManager = null;
    private static CloseableHttpClient httpClient = null;

    static {
        try {
            // 初始化http连接池
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf).build();
            // 初始化连接管理器
            poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolConnManager.setMaxTotal(HTTP_CONNECT_MAX_TOTAL); // 池中最大的连接数目
            poolConnManager.setDefaultMaxPerRoute(HTTP_CONNECT_DEFAULT_MAXP_ERROUTE); // 每路由 最大并发数量，   maxTotal 是所有的最大数量

            // 构建http client
            httpClient = HttpClients.custom()
                    .setConnectionManager(poolConnManager)
                    .setDefaultRequestConfig(requestConfig)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)) // 禁用重试
                    .build();

        } catch (Exception e) {
            logger.error("初始化http 连接池失败", e);
        }
    }

    private static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public ResponseResult post(String url, Map<String, Object> params) {

        return null;
    }

    public ResponseResult get(String url, Map<String, Object> params) {

        return get(url, params, 500, 500);
    }

    public ResponseResult get(String url, Map<String, Object> params, int connectTimeout, int readTimeout) {

        if (StringUtils.isBlank(url)) {
            logger.error("url为空，不进行请求，被过滤");
            return null;
        }

        String paramStr = mapConvertToString(params);

        String reqUrl = StringUtils.isNotBlank(paramStr) ? url + "?" + paramStr : url;
        System.out.println("requrl" + reqUrl);
        HttpGet request = new HttpGet(reqUrl);
        return execute(request, getHttpClientContext(connectTimeout, readTimeout));
    }

    private String mapConvertToString(Map<String, Object> map) {

        if (CollectionUtils.isEmpty(map))
            return null;

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {

            if (result.length() != 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());

        }

        return result.toString();
    }

    private ResponseResult execute(HttpUriRequest request, HttpContext... context) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = getHttpClient();
        int statusCode = 200;
        String resultStr = null;
        try {
            response = httpClient.execute(request, context != null && context.length > 0 && context[0] != null ? context[0] : null);
            statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, CHARSET_UTF_8);
        } catch (IOException e) {
            logger.error("http调用失败", e);
        } finally {
            try {
                if (null != request && !request.isAborted()) {
                    request.abort();
                }
                HttpClientUtils.closeQuietly(response);
            } catch (UnsupportedOperationException e) {
                logger.error("error:", e);
            }
        }

        return new ResponseResult(statusCode, resultStr);
    }

    private HttpClientContext getHttpClientContext(Integer connectTimeout, Integer readTimeout) {
        HttpClientContext context = null;
        if (connectTimeout != null || readTimeout != null) {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout == null ? CONNETION_TIMEOUT : connectTimeout).setSocketTimeout(readTimeout == null ? READ_TIMEOUT : readTimeout).build();
            context = HttpClientContext.create();
            context.setRequestConfig(config);
        }
        return context;
    }
}