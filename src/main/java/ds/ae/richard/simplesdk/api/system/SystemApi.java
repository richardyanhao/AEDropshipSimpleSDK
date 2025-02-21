package ds.ae.richard.simplesdk.api.system;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:21
 */
import com.google.gson.Gson;
import ds.ae.richard.simplesdk.api.ApiClient;
import ds.ae.richard.simplesdk.api.ApiException;
import ds.ae.richard.simplesdk.api.ApiResponse;
import ds.ae.richard.simplesdk.config.ConfigManager;
import ds.ae.richard.simplesdk.utils.SignatureUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ds.ae.richard.simplesdk.utils.Constants.BASE_URL_FOR_SYSTEM;

public class SystemApi {

    private final ApiClient apiClient;
    private final String clientId;
    private final String clientSecret;
    private final Gson gson;

    public SystemApi() {
        this.apiClient = new ApiClient();
        this.clientId = ConfigManager.getProperty("client_id");
        this.clientSecret = ConfigManager.getProperty("client_secret");
        this.gson = new Gson();
    }

    public TokenResponse createToken(String code) throws ApiException {
        String apiPath = "/auth/token/create";
        return executeTokenRequest(apiPath, Map.of("code", code));
    }

    public TokenResponse refreshToken(String refreshToken) throws ApiException {
        String apiPath = "/auth/token/refresh";
        return executeTokenRequest(apiPath, Map.of("refresh_token", refreshToken));
    }

    private TokenResponse executeTokenRequest(String apiPath, Map<String, String> additionalParams) throws ApiException {
        String timeStamp = String.valueOf(System.currentTimeMillis());

        // Populate parameters
        Map<String, String> params = new HashMap<>();
        params.put("app_key", clientId);
        params.put("timestamp", timeStamp);
        params.put("sign_method", "sha256");
        params.putAll(additionalParams);

        try {
            // Generate signature
            String signature = SignatureUtil.generateSignature(apiPath, params, clientSecret);

            // Assemble HTTP request URL
            String queryParams = params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
            String fullUrl = BASE_URL_FOR_SYSTEM + apiPath + "?" + queryParams + "&sign=" + signature;

            // Send HTTP request using ApiClient
            ApiResponse response = apiClient.post(fullUrl, new HashMap<>());
            if (response.getStatusCode() == 200) {
                return gson.fromJson(response.getBody(), TokenResponse.class);
            } else {
                throw new ApiException("Failed to refresh token, status code: " + response.getStatusCode(), null);
            }
        } catch (IOException e) {
            throw new ApiException("Error generating token request", e);
        }
    }

    public static void main(String[] args) {
        try {
            SystemApi systemApi = new SystemApi();
            String authorizationCode = "3_509450_NRGb2iY0iB7ju5tEjbuNEXFI869";
            TokenResponse tokenResponse = systemApi.createToken(authorizationCode);
            System.out.println("Create Token Access Token: " + tokenResponse.getAccessToken());
            System.out.println("Create Token Refresh Token: " + tokenResponse.getRefreshToken());
            System.out.println("Create Token expire_time: " + tokenResponse.getExpireTime());
            System.out.println("Create Token refresh_token_valid_time: " + tokenResponse.getRefreshTokenValidTime());

            TokenResponse refreshTokenResponse = systemApi.refreshToken(tokenResponse.getRefreshToken());
            System.out.println("Create Token Access Token: " + refreshTokenResponse.getAccessToken());
            System.out.println("Create Token Refresh Token: " + refreshTokenResponse.getRefreshToken());
            System.out.println("Create Token expire_time: " + refreshTokenResponse.getExpireTime() );
            System.out.println("Create Token refresh_token_valid_time: " + refreshTokenResponse.getRefreshTokenValidTime());

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}