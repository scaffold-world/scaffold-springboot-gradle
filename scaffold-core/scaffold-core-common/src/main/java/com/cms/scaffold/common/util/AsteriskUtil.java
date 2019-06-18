package com.cms.scaffold.common.util;

public class AsteriskUtil {
    public static final String ASTERISK = "*";

    public static String format(String str, int prefixLength, int suffixLength) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int length = str.length();
        if (length == 1) {
            return ASTERISK;
        }

        StringBuffer buf = new StringBuffer();
        buf.append(str.substring(0, prefixLength));
        for (int i = 0; i < (length - suffixLength-1); i++) {
            buf.append(ASTERISK);
        }
        buf.append(str.substring(length - suffixLength, length));
        return buf.toString();
    }


}
