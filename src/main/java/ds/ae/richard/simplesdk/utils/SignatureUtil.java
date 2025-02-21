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
import java.util.Map;
import java.util.stream.Collectors;

public class SignatureUtil {

    public static String generateSignature(String apiPath, Map<String, String> params, String appSecret) throws IOException {
        // Sort parameters
        String concatenatedString = params.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() + entry.getValue())
            .collect(Collectors.joining());

        // Concatenate API path
        String stringToSign = apiPath + concatenatedString;

        // Sign the whole request
        byte[] bytes = encryptHMACSHA256(stringToSign, appSecret);

        // Convert to upper-case hexadecimal string
        return byte2hex(bytes).toUpperCase();
    }

    private static byte[] encryptHMACSHA256(String data, String secret) throws IOException {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
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
            sign.append(hex);
        }
        return sign.toString();
    }
}