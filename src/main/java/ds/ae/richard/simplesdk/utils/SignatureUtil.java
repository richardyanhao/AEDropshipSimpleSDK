package ds.ae.richard.simplesdk.utils;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:16
 */
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;

public class SignatureUtil {

    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String SIGN_METHOD_HMAC_SHA256 = "HmacSHA256";

    public static String generateSignature(Map<String, String> params, String appSecret, String apiName, String clientId) throws IOException {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        params.put("sign_method", "sha256");
        params.put("timestamp", timeStamp);
        params.put("app_key", clientId);

        boolean isSystemInterface = apiName.equals(Constants.TOKEN_CREATE_API) || apiName.equals(Constants.TOKEN_REFRESH_API);

        // 如果是业务接口，将apiName作为method参数
        if (!isSystemInterface) {
            params.put("method", apiName);
        }

        // 对所有键进行排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 连接所有键值对
        StringBuilder query = new StringBuilder();
        if (isSystemInterface) {
            // 系统接口在最前面加apiName
            query.append(apiName);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }

        // 签署请求
        byte[] bytes = encryptHMACSHA256(query.toString(), appSecret);
        return byte2hex(bytes).toUpperCase();
    }

    private static byte[] encryptHMACSHA256(String data, String secret) throws IOException {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SIGN_METHOD_HMAC_SHA256);
            Mac mac = Mac.getInstance(SIGN_METHOD_HMAC_SHA256);
            mac.init(secretKey);
            return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException gse) {
            throw new IOException("Error during HMAC-SHA256 encryption", gse);
        }
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    private static boolean areNotEmpty(String key, String value) {
        return key != null && value != null && !key.isEmpty() && !value.isEmpty();
    }
}