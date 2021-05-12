/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.logibeat.cloud.common.cache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Objects;

/**
 * SHA1Utils class
 * <p/>
 * 计算FaceX、Simiao、微信公众平台等的消息签名接口.
 */
public class MessageDigestUtils {

    private static final Logger log = LoggerFactory.getLogger(MessageDigestUtils.class);

    public static Boolean verify(String signature, String... array) throws Exception {
        return Objects.equals(signature,calc(array));
    }
    /**
     * 用SHA1算法生成安全签名（不固定参数个数）
     * 所有传入的字符串将会排序后拼接进行计算
     *
     * @param array
     * @return
     */
    public static String calc(String... array) throws Exception {

        try {
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                String a = array[i];
                sb.append(a);
                if (i != array.length - 1) {
                    sb.append('&');
                }
            }
            String str = sb.toString();
            // SHA1签名生成
             MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            log.error("Compute Signature Error: {}", e);
            throw new Exception("AES Compute Signature Error.");
        }
    }


}
