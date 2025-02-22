package ds.ae.richard.simplesdk.api.system;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:21
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import ds.ae.richard.simplesdk.api.ApiClient;
import ds.ae.richard.simplesdk.api.ApiException;
import ds.ae.richard.simplesdk.api.ApiResponse;
import ds.ae.richard.simplesdk.config.ConfigManager;
import ds.ae.richard.simplesdk.model.TokenResponse;
import ds.ae.richard.simplesdk.utils.SignatureUtil;
import static ds.ae.richard.simplesdk.utils.Constants.BASE_URL_FOR_SYSTEM;

import com.google.gson.Gson;

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
        Map<String, String> param = new HashMap<>(){{
           put("code", code);
        }};
        return executeTokenRequest(apiPath, param);
    }

    public TokenResponse refreshToken(String refreshToken) throws ApiException {
        String apiPath = "/auth/token/refresh";
        Map<String, String> param = new HashMap<>(){{
            put("refresh_token", refreshToken);
        }};
        return executeTokenRequest(apiPath, param);
    }

    private TokenResponse executeTokenRequest(String apiPath, Map<String, String> additionalParams) throws ApiException {
        try {
            // Generate signature
            String signature = SignatureUtil.generateSignature(additionalParams, clientSecret, apiPath, clientId);

            // Assemble HTTP request URL
            String queryParams = additionalParams.entrySet().stream()
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
}