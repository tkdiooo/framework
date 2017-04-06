package com.qi.common.security.base64;

import com.qi.common.constants.StringConstants;
import com.qi.common.tool.Assert;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Class Base64
 *
 * @author 张麒 2016/4/15.
 * @version Description:
 */
public class Base64 {

    /**
     * Base64 加密
     *
     * @param bytes information
     * @return cipher
     */
    public static String encrypt(byte[] bytes) {
        Assert.notNull(bytes, "加密信息为空");
        String cipher = new BASE64Encoder().encode(bytes);
        return cipher.replaceAll(StringConstants.BACK_SLASH + StringConstants.PLUS, StringConstants.UNDERLINE)
                .replaceAll(StringConstants.FORWARD_SLASH, StringConstants.MINUS)
                .replaceAll(StringConstants.EQUAL, StringConstants.PERIOD).replaceAll("\\s", "");
    }


    /**
     * Base64 解密
     *
     * @param info cipher
     * @return information
     */
    public static byte[] decrypt(String info) {
        Assert.isNotBlank(info, "解密信息为空");
        try {
            info = info.replaceAll(StringConstants.UNDERLINE, StringConstants.BACK_SLASH + StringConstants.PLUS)
                    .replaceAll(StringConstants.MINUS, StringConstants.FORWARD_SLASH)
                    .replaceAll(StringConstants.BACK_SLASH + StringConstants.PERIOD, StringConstants.EQUAL);
            return new BASE64Decoder().decodeBuffer(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
