package com.cms.scaffold.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 字符串伪装处理工具类
 */
public final class StringMaskingUtil {

    private StringMaskingUtil() {
    }

    /**
     * 身份证号
     */
    public static String idCard2Mask(String idCard) {
        if (idCard == null) {
            return "";
        }
        int length = idCard.length();
        if (length == 18) {
            return idCard.replaceAll("(\\d{3})\\d{14}(\\d{1})", "$1**************$2");
        } else if (length == 15) {
            return idCard.replaceAll("(\\d{3})\\d{11}(\\d{1})", "$1***********$2");
        } else {
            return idCard;
        }

    }

    /**
     * 卡号
     */
    public static String cardNo2Mask(String cardNo) {
        if (StringUtils.isNotEmpty(cardNo) && cardNo.length() > 10) {
            int maskLength = cardNo.length() - 8;
            String mask = StringUtils.repeat('*', maskLength);
            cardNo = cardNo.substring(0, 4) + mask + cardNo.substring(cardNo.length() - 4);
        }
        return cardNo;
    }

    /**
     * 手机号码
     */
    public static String mobileNo2Mask(String mobileNo) {
        if (StringUtils.isEmpty(mobileNo)) {
            return "";
        }
        return mobileNo.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

    }

    /**
     * 公民真实姓名伪装 隐藏姓
     */
    public static String citizenRealName(final String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        return name.substring(0).replaceFirst(".", "*");
    }

    /**
     * 公民真实姓名伪装 隐藏名
     */
    public static String citizenName(final String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        return name.substring(0, 1) + "**";
    }


    /**
     * username的隐藏
     *
     * @return String
     * @since p2p_cloud_v1.0
     */
    public static String username2Mask(final String username) {
        if (StringUtils.isEmpty(username)) {
            return "";
        }
        return username.charAt(0) + "******" + username.charAt(username.length() - 1);
    }

    public static String email2Mask(final String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }
        String[] strArr = StringUtils.split(email, '@');
        String prefix = strArr[0];
        return prefix.charAt(0) + "***" + prefix.charAt(prefix.length() - 1) + '@' + strArr[1];
    }

    public static String businessLicenceCodeMask(final String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        return value.substring(0, 6) + "****";
    }

    public static String corpNameMask(final String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        return value.substring(0, 2) + "****" + value.substring(value.length() - 2, value.length());
    }

    public static String corpAddressMask(final String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        return value.substring(0, 4) + "****";
    }

    /**
     * 是否包含中文字符
     */
    public static boolean isContainChinese(String chinese) {
        if (StringUtils.isEmpty(chinese)) {
            return false;
        }
        final String regex = "^([\\u4e00-\u9fa5]*|([a-zA-Z]+\\s?)+)$";
        return Pattern.compile(regex).matcher(chinese).matches();
    }

}
