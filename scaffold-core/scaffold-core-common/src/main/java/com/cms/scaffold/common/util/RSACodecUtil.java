package com.cms.scaffold.common.util;


import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 加解密工具类
 */
public final class RSACodecUtil {
    public static final String CHARSET = "UTF-8";
    //密钥算法
    public static final String ALGORITHM_RSA = "RSA";
    public static final String ALGORITHM_RSA_SIGN = "SHA256WithRSA";
    public static final int ALGORITHM_RSA_PRIVATE_KEY_LENGTH = 2048;
    public static final String ALGORITHM_AES = "AES";
    public static final String ALGORITHM_AES_PKCS7 = "AES";
    public static final String ALGORITHM_DES = "DES";
    public static final String ALGORITHM_DESede = "DESede";
    //加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
    //工作模式有ECB--电子密码本模式,CBC--加密分组链接模式,CFB--加密反馈模式,OFB--输出反馈模式,CTR--计数器模式
    //其中ECB过于简单而不安全,已被弃用,相对的CBC模式是最安全的,http://www.moye.me/2015/06/14/cryptography_rsa/
    private static final String ALGORITHM_CIPHER_AES = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHM_CIPHER_AES_PKCS7 = "AES/CBC/PKCS7Padding";
    private static final String ALGORITHM_CIPHER_DES = "DES/ECB/PKCS5Padding";
    private static final String ALGORITHM_CIPHER_DESede = "DESede/ECB/PKCS5Padding";

    private RSACodecUtil() {
    }

    /**
     * 初始化RSA算法密钥对
     *
     * @param keysize RSA1024已经不安全了,建议2048
     * @return 经过Base64编码后的公私钥Map, 键名分别为publicKey和privateKey
     * @create Feb 20, 2016 7:34:41 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    public static Map<String, String> initRSAKey(int keysize) {
        if (keysize != ALGORITHM_RSA_PRIVATE_KEY_LENGTH) {
            throw new IllegalArgumentException("RSA1024已经不安全了,请使用" + ALGORITHM_RSA_PRIVATE_KEY_LENGTH + "初始化RSA密钥对");
        }
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + ALGORITHM_RSA + "]");
        }
        //初始化KeyPairGenerator对象,不要被initialize()源码表面上欺骗,其实这里声明的size是生效的
        kpg.initialize(ALGORITHM_RSA_PRIVATE_KEY_LENGTH);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encode(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encode(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }


    /**
     * RSA算法分段加解密数据
     *
     * @param cipher 初始化了加解密工作模式后的javax.crypto.Cipher对象
     * @param opmode 加解密模式,值为javax.crypto.Cipher.ENCRYPT_MODE/DECRYPT_MODE
     * @param datas  待分段加解密的数据的字节数组
     * @return 加密或解密后得到的数据的字节数组
     * @create Feb 21, 2016 1:37:21 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8;
        } else {
            maxBlock = ALGORITHM_RSA_PRIVATE_KEY_LENGTH / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultDatas;
    }


    /**
     * RSA算法私钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥加密后的经过Base64编码的密文字符串
     * @create Feb 20, 2016 8:03:25 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    public static String buildRSAEncryptByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            //encrypt
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            //return Base64.encodeBase64URLSafeString(cipher.doFinal(data.getBytes(CHARSET)));
            return Base64.encode(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }


    /**
     * RSA算法公钥加密数据
     *
     * @param data 待加密的明文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥加密后的经过Base64编码的密文字符串
     * @create Feb 20, 2016 8:25:21 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    public static String buildRSAEncryptByPublicKey(String data, String key) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            //encrypt
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //return Base64.encodeBase64URLSafeString(cipher.doFinal(data.getBytes(CHARSET)));
            return Base64.encode(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }


    /**
     * RSA算法公钥解密数据
     *
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA公钥字符串
     * @return RSA公钥解密后的明文字符串
     * @create Feb 20, 2016 8:33:22 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    public static String buildRSADecryptByPublicKey(String data, String key) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            //decrypt
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            //return new String(cipher.doFinal(Base64.decodeBase64(data)), CHARSET);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }


    /**
     * RSA算法私钥解密数据
     *
     * @param data 待解密的经过Base64编码的密文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥解密后的明文字符串
     * @create Feb 20, 2016 8:33:22 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    public static String buildRSADecryptByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            //decrypt
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //return new String(cipher.doFinal(Base64.decodeBase64(data)), CHARSET);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decode(data)), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }


    /**
     * RSA算法使用私钥对数据生成数字签名
     *
     * @param data 待签名的明文字符串
     * @param key  RSA私钥字符串
     * @return RSA私钥签名后的经过Base64编码的字符串
     * @create Feb 20, 2016 8:43:49 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    public static String buildRSASignByPrivateKey(String data, String key) {
        try {
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            //sign
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initSign(privateKey);
            signature.update(data.getBytes(CHARSET));
            return Base64.encode(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException("签名字符串[" + data + "]时遇到异常", e);
        }
    }


    /**
     * RSA算法使用公钥校验数字签名
     *
     * @param data 参与签名的明文字符串
     * @param key  RSA公钥字符串
     * @param sign RSA签名得到的经过Base64编码的字符串
     * @return true--验签通过,false--验签未通过
     * @create Feb 20, 2016 8:51:49 PM
     * @author 玄玉<http://blog.csdn.net/jadyer>
     */
    public static boolean buildRSAverifyByPublicKey(String data, String key, String sign) {
        try {
            //通过X509编码的Key指令获得公钥对象
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            //verify
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initVerify(publicKey);
            signature.update(data.getBytes(CHARSET));
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            throw new RuntimeException("验签字符串[" + data + "]时遇到异常", e);
        }
    }

//    public static void main(String[] args) {
//        Map<String, String> keyMap;
//        try {
//            keyMap = initRSAKey(2048);
//            String publicKey = keyMap.get("publicKey");
//            System.out.println(publicKey);
//            String privateKey = keyMap.get("privateKey");
//            System.out.println(privateKey);
//            //明文
//            String ming = "123456789";
//            String mi = buildRSAEncryptByPrivateKey(ming,privateKey);
//            System.err.println(mi);
//            String ming1= buildRSADecryptByPublicKey(mi,publicKey);
//            System.err.println(ming1);
//            String sign = buildRSASignByPrivateKey(ming, privateKey);
//            System.out.println(sign);
//            boolean check = buildRSAverifyByPublicKey(ming+"1", publicKey,sign);
//            System.out.print(check);
//        } catch (exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        String data = "{\"bankNo\":\"6228480402564890018\",\"cardId\":\"210422199505195334\",\"mobilePhone\":\"15158109820\",\"realName\":\"黎彭泽\",\"redirectUrl\":\"http://localhost:20030/user/userInfo/personalRegisterSyncJump\",\"userDevice\":\"PC\",\"uuid\":\"104182011435137772\"}";


        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyj8PXizWxe9eU7Z/OVW3\n" +
                "gfmXE3l2BwEZ/PxR616bHjzZYQgjZAtjH1PqPtyxYYts9h5nxMxsSKMFumobI16N\n" +
                "0irU9OClTNa7BQOo3n/F+zCS33B69sM7FCX5b4VERSd30jAR4Tr+XVFSNLX4bq3G\n" +
                "Wz0LHyy2b61jCk4tum+AhLHO27IcRPtfliq79VRRHA2oOqk/MJkFQoFUSm7+RYrO\n" +
                "m/l5AqfF/ewKlkN5FFWU8lTCOrqkzorbS3GsezcINAgDBtx9Qcx++PKXhOooEwkA\n" +
                "0t71L5i52LCRAkps8BLHFcbWZFm+wWu5ZffYSBOD5lwLlPNrMbCsXA3bQQXqO7AV\n" +
                "kwIDAQAB";
        String publicKey1 ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyj8PXizWxe9eU7Z/OVW3\n" +
                "gfmXE3l2BwEZ/PxR616bHjzZYQgjZAtjH1PqPtyxYYts9h5nxMxsSKMFumobI16N\n" +
                "0irU9OClTNa7BQOo3n/F+zCS33B69sM7FCX5b4VERSd30jAR4Tr+XVFSNLX4bq3G\n" +
                "Wz0LHyy2b61jCk4tum+AhLHO27IcRPtfliq79VRRHA2oOqk/MJkFQoFUSm7+RYrO\n" +
                "m/l5AqfF/ewKlkN5FFWU8lTCOrqkzorbS3GsezcINAgDBtx9Qcx++PKXhOooEwkA\n" +
                "0t71L5i52LCRAkps8BLHFcbWZFm+wWu5ZffYSBOD5lwLlPNrMbCsXA3bQQXqO7AV\n" +
                "kwIDAQAB";
        String test ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz9gwXDwL2Z2fZGcGqn6h\n" +
                "BhD3/6sWn5iFltZmyviH4DC+68gqldD6PkWWNOALvqikEuOwcYKrPbo3+oA7Bfrl\n" +
                "i3doK7W+rDvzvWCgN5gRjG+8Emrr+08O+AP5GNYgXqf/NBqmDIexdSPJsoL20H6b\n" +
                "C8ssEDRCN5cMBmLhrCkQSVNSXFaszlYR+ddXIR4BppjBYFzcLjIILeLIDVEBMrAV\n" +
                "6S7aE03d43vwAZ6X1Y9f8xJeM1J2JILwAP9rRRDwlijkmnYPD97HPx6SCvFwQ6be\n" +
                "ER4IBbCEz26Ep6lkzUSPnRi5I5K5eu5hUUgnA1Tqz3TwXiPtw6oMy7cXXP7OphqQ\n" +
                "0wIDAQAB";
        String test1="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz9gwXDwL2Z2fZGcGqn6h\n" +
                "BhD3/6sWn5iFltZmyviH4DC+68gqldD6PkWWNOALvqikEuOwcYKrPbo3+oA7Bfrl\n" +
                "i3doK7W+rDvzvWCgN5gRjG+8Emrr+08O+AP5GNYgXqf/NBqmDIexdSPJsoL20H6b\n" +
                "C8ssEDRCN5cMBmLhrCkQSVNSXFaszlYR+ddXIR4BppjBYFzcLjIILeLIDVEBMrAV\n" +
                "6S7aE03d43vwAZ6X1Y9f8xJeM1J2JILwAP9rRRDwlijkmnYPD97HPx6SCvFwQ6be\n" +
                "ER4IBbCEz26Ep6lkzUSPnRi5I5K5eu5hUUgnA1Tqz3TwXiPtw6oMy7cXXP7OphqQ\n" +
                "0wIDAQAB";

        String out1 = buildRSAEncryptByPublicKey(data,publicKey1);
        System.out.println(out1);

        String privateKey = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDKPw9eLNbF715T\n" +
                "tn85VbeB+ZcTeXYHARn8/FHrXpsePNlhCCNkC2MfU+o+3LFhi2z2HmfEzGxIowW6\n" +
                "ahsjXo3SKtT04KVM1rsFA6jef8X7MJLfcHr2wzsUJflvhURFJ3fSMBHhOv5dUVI0\n" +
                "tfhurcZbPQsfLLZvrWMKTi26b4CEsc7bshxE+1+WKrv1VFEcDag6qT8wmQVCgVRK\n" +
                "bv5Fis6b+XkCp8X97AqWQ3kUVZTyVMI6uqTOittLcax7Nwg0CAMG3H1BzH748peE\n" +
                "6igTCQDS3vUvmLnYsJECSmzwEscVxtZkWb7Ba7ll99hIE4PmXAuU82sxsKxcDdtB\n" +
                "Beo7sBWTAgMBAAECggEBAJKM6VTjhum8zww4BK6GJ0g+lLOe4uCmcq94IuA8GIjr\n" +
                "rNT78bEv8y6jPu04PCyjvFwHokffnWNxBdiTxIB3pqqxMMkU85LfUnuKsFYyHkMr\n" +
                "anIQbnna9DdgYQPnfJXjyJZ9EKe1eNYj9D6P0apZr48zh5iabMQ1zruL9OTQoT8E\n" +
                "0xteCxGratOAAoFyrjn71pIdOq2v1URvbxwNWPp4oj/2t4vxgk9i9xlQEdMHQWTs\n" +
                "StgfCQoblF2qxooP+fsyjzz8xH7QKgE5GfVeztzW6ZSYOf9IgSRerFh3KrBSFeqo\n" +
                "iVYQW7ef3u8AuF5lpZHpRp6MIZwKNxJdcfvjIpBM93ECgYEA64ZGu3W4pyYHtJ0G\n" +
                "dYAPLUnwXcYRKkDwdECyqjjhPWdLukVs3LfaJrJYm6xKuDQoyrplTJ/8bcuxTyKd\n" +
                "r37czvCWDga6w2RScE4v/aXhAVKlJmSWNFNMw6pNG6GW2zsn+hEVT1czLBgLkR5v\n" +
                "YpYMoUWLRmo/Fnxug8A1PLsrVMsCgYEA29Qo72axtRXgHnf6oHmVsUCJzfH/LIoS\n" +
                "RshWnbLP2SWqa/CEZArmW4ULkzU+xy3TeVWpO+gg7cfcwwg7OWniaQKat0DHpiZ+\n" +
                "GVim+/n37//0YgQqO5PDNAlFfN3K0DCvtkpdDkpWRf2/pWyYBHGB0jC6xDQP8rWc\n" +
                "GD8lDQdWcVkCgYEA5gXAMvkoHGbyA52QX1VwfSTHUm5M+V1u+bruQ31FzfiCZyvC\n" +
                "xFXzAvQPwgok935JkKU2pf1iRcE4LDd8lZdrA9jIVG6SHJ5wPSq/iBRniikzDioS\n" +
                "4IuyXy6GHERW10oRIxNoIpLhcWESeuqkkj5Fm4QWH/FIKCfir14bIHMUQL0CgYEA\n" +
                "o4D85XuDh8X6iY8BqeRlE46GMNjiy9TyXRVbXzYFZGSgimI9hk+ZIM8nWdmLlQgf\n" +
                "t6qgEj/IZjN2ntV7f2ujDCn0u4KCPLF6rw2CPf6hjC/bLCAe7WstEIIo3gP1er4L\n" +
                "o1FSP3PJEYv3xGmt8/gc8CJwd8ilaAfuTxaAT116zRkCgYEAvbEHYfSna0YgKnBI\n" +
                "5kZdgLtJ3ffMMOtm7d6c8l7cC6bk92NHkut75wQTr7RUR/spwpOIoix9YPcIMb4D\n" +
                "Gw+yS+iqh0OyYVe/mksTjF6IPRvU9++10yq8dxAczrmzNal0tS456NoqXwiTpj+U\n" +
                "iLktFuMBaCkuQZJR3bzE1uWvsCE=";
        String out = buildRSADecryptByPrivateKey(out1, privateKey);
        System.out.println(out);
    }
}
