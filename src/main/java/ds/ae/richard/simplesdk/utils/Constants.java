package ds.ae.richard.simplesdk.utils;

/**
 * @author Richard Yan
 * @date 2025/2/20 22:49
 */
public class Constants {

    public static final String SIGN_METHOD_SHA256 = "sha256";
    public static final String SIGN_METHOD_HMAC_SHA256 = "HmacSHA256";

    public static final String BASE_URL_FOR_SYSTEM = "https://api-sg.aliexpress.com/rest";
    public static final String BASE_URL_FOR_BUSINESS = "https://api-sg.aliexpress.com/sync";

    public static final String TOKEN_CREATE_API = "/auth/token/create";
    public static final String TOKEN_REFRESH_API = "/auth/token/refresh";
}
