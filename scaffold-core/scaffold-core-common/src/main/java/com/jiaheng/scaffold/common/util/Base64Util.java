package com.jiaheng.scaffold.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * 加密工具类
 */
public final class Base64Util {

    /**
     * 私钥加密
     *
     * @param privateKeyStr 私钥
     * @return str 需要加密的字符串
     * @since p2p_cloud_v1.0
     */
    public static String encrypt(String str, String privateKeyStr) {
        PrivateKey privateKey = RSAUtil.generatePrivateKey(privateKeyStr);
        if (privateKey == null) {
            throw new RuntimeException("加密私钥为空,请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(str.getBytes("UTF-8"));
            Base64 base64 = new Base64();
            return base64.encodeAsString(base64.encodeAsString(output).getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("明文长度非法");
        } catch (BadPaddingException e) {
            throw new RuntimeException("明文数据已损坏");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码格式不支持");
        }
    }

    public static void main(String[] args) {
        Base64 base64 = new Base64();
        String s = "abcde";
        System.out.println(base64.encodeAsString(s.getBytes()));
        System.out.println(new String(base64.decode("YWJjZGU=")));
    }

    /**
     * 公钥解密
     *
     * @param publicKeyStr 公钥字符串
     * @param encryptStr 密文
     */
    public static String decrypt(String encryptStr, String publicKeyStr) {
        if (StringUtils.isEmpty(encryptStr)) {
            return encryptStr;
        }
        PublicKey publicKey = RSAUtil.generatePublicKeyFromDer(publicKeyStr);
        if (publicKey == null) {
            throw new RuntimeException("解密公钥为空,请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            Base64 base64 = new Base64();
            encryptStr = new String(base64.decode(encryptStr), "UTF-8");
            byte[] output = cipher.doFinal(base64.decode(encryptStr));
            return new String(output, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new RuntimeException("解密公钥非法,请检查", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("密文长度非法", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("密文数据已损坏", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码格式不支持", e);
        }
    }

    /**
     * 生成签名的方法,必须为utf-8格式
     *
     * @param privateKey 私钥
     * @param str 签名明文字符串
     */
    public static String generateSign(String str, String privateKey) {
        String signature = null;
        try {
            byte[] prikeybytes = RSAUtil.hexString2ByteArr(privateKey);
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(prikeybytes);
            // 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 取私钥匙对象
            PrivateKey privatekey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initSign(privatekey);
            byte[] digest = str.getBytes("UTF-8");
            instance.update(digest);
            byte[] sign = instance.sign();
            signature = RSAUtil.byteArr2HexString(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    /**
     * 校验签名参数,必须为utf-8格式
     *
     * @param str 签名明文
     * @param signInfo 签名参数
     * @param pubKey 公钥
     * @return 返回true和false，true代表验签通过，false代表验签失败
     */
    public static boolean checkSign(String str, String signInfo, String pubKey) {
        boolean flag = false;
        try {
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(RSAUtil.generatePublicKeyFromDer(pubKey));
            byte[] digest = str.getBytes("UTF-8");
            instance.update(digest);
            flag = instance.verify(RSAUtil.hexString2ByteArr(signInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    public static String encodeReqExt(Object object) {
        String str = JSON.toJSONString(object);
        Base64 base64 = new Base64();
        byte[] bytes = str.getBytes();
        return base64.encodeAsString(bytes);
    }

    public static <T> T decodeReqExt(String str, Class<T> clazz) {
        Base64 base64 = new Base64();
        byte[] bytes = str.getBytes();
        try {
            return JSON.parseObject(new String(base64.decode(bytes), "utf-8"), clazz);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
