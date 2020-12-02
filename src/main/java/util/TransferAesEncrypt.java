package util;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * jdd的aes加解密
 * 需要特殊jar
 */
public class TransferAesEncrypt {
    private static final String AES_ALG = "AES";
    private static final String AES_CBC_PCK_ALG = "AES/CBC/PKCS5Padding";

    private TransferAesEncrypt() {
    }

    public static String encryptContent(String content, String encryptType, String encryptKey, String charset) throws Exception {
        if (AES_ALG.equalsIgnoreCase(encryptType)) {
            return aesEncrypt(content, encryptKey, charset, null);
        } else {
            throw new Exception("当前不支持该算法类型：encrypeType=" + encryptType);
        }
    }

    public static String decryptContent(String content, String encryptType, String encryptKey, String charset) throws Exception {
        if (AES_ALG.equalsIgnoreCase(encryptType)) {
            return aesDecrypt(content, encryptKey, charset, null);
        } else {
            throw new Exception("当前不支持该算法类型：encrypeType=" + encryptType);
        }
    }

    public static String aesEncrypt(String content, String aesKey, String charset, String ivStr) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec iv;
            if (ivStr == null || ivStr.length() == 0) {
                iv = new IvParameterSpec(initIv());
            } else {
                iv = new IvParameterSpec(ivStr.getBytes());
            }
            cipher.init(1, new SecretKeySpec(aesKey.getBytes(), "AES"), iv);
            byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
            return new String(Base64.getEncoder().encode(encryptBytes));
        } catch (Exception var7) {
            throw new Exception("AES加密失败：Aescontent = " + content + "; charset = " + charset, var7);
        }
    }

    public static String aesDecrypt(String content, String key, String charset, String ivStr) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec iv;
            if (ivStr == null || ivStr.length() == 0) {
                iv = new IvParameterSpec(initIv());
            } else {
                iv = new IvParameterSpec(ivStr.getBytes());
            }
            cipher.init(2, new SecretKeySpec(key.getBytes(), "AES"), iv);
            byte[] cleanBytes = cipher.doFinal(Base64.getDecoder().decode(content.getBytes()));
            return new String(cleanBytes, charset);
        } catch (Exception var7) {
            throw new Exception("AES解密失败：Aescontent = " + content + "; charset = " + charset, var7);
        }
    }

    private static byte[] initIv() {
        String vi = "0000000000000000";
        return vi.getBytes();
    }
}
