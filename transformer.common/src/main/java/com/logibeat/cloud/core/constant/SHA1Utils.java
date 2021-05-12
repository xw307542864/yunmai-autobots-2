/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.logibeat.cloud.core.constant;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * SHA1Utils class
 * <p/>
 * 签名
 */
public class SHA1Utils {

    /**
     * facex和unicron验证签名(用SHA1算法生成安全签名（固定4参数）)
     *
     * @param token
     * @param timestamp 时间戳
     * @param encrypt   密文
     * @return 安全签名
     */
    public static String encyptSignWithToken(String token,String timestamp, String encrypt) throws Exception {
//        long timestamp = System.currentTimeMillis() / 1000;
        String sha1 = MessageDigestUtils.calc(token, timestamp, encrypt);
        return sha1;
    }

    public static String getMD5(String txt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(txt.getBytes("UTF-8"));
            byte[] bPwd = md.digest();
            String pwd = new BigInteger(1, bPwd).toString(16);
            if (pwd.length() % 2 == 1) {
                pwd = "0" + pwd;
            }
            int length = pwd.length();
            StringBuffer sb = new StringBuffer(length + length / 2 - 1);
            for (int i = 0; i < length; i += 2) {
                sb.append(pwd.substring(i, i + 2));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return txt;
    }
}
