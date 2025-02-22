package ds.ae.richard.simplesdk.api.business;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import ds.ae.richard.simplesdk.api.ApiClient;
import ds.ae.richard.simplesdk.api.ApiException;
import ds.ae.richard.simplesdk.api.ApiResponse;
import ds.ae.richard.simplesdk.config.ConfigManager;
import ds.ae.richard.simplesdk.utils.Constants;
import ds.ae.richard.simplesdk.utils.SignatureUtil;

import com.google.gson.Gson;

/**
 * @author Richard Yan
 * @date 2025/2/22 15:32
 */
public class BusinessApi {
    private final ApiClient apiClient;
    private final String clientId;
    private final String clientSecret;
    private final Gson gson;

    public BusinessApi() {
        this.apiClient = new ApiClient();
        this.clientId = ConfigManager.getProperty("client_id");
        this.clientSecret = ConfigManager.getProperty("client_secret");
        this.gson = new Gson();
    }

    // 商品详情接口
    public String getProductDetails(String accessToken, String productId, String shipToCountry,
        String targetCurrency, String targetLanguage, boolean removePersonalBenefit) throws ApiException {
        String apiPath = "aliexpress.ds.product.get";
        String timeStamp = String.valueOf(System.currentTimeMillis());

        // Populate parameters
        Map<String, String> params = new HashMap<>();
        params.put("app_key", clientId);
        params.put("timestamp", timeStamp);
        params.put("access_token", accessToken);
        params.put("sign_method", "sha256");
        params.put("ship_to_country", shipToCountry);
        params.put("product_id", productId);
        params.put("target_currency", targetCurrency);
        params.put("target_language", targetLanguage);
        params.put("remove_personal_benefit", String.valueOf(removePersonalBenefit));

        try {
            // Generate signature
            String signature = SignatureUtil.generateSignature(apiPath, params, clientSecret);

            // Add signature to parameters
            params.put("sign", signature);

            // Prepare query string
            String queryParams = params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

            // Form the full URL
            String fullUrl = Constants.BASE_URL_FOR_BUSINESS + "?method=" + apiPath + "&" + queryParams;

            // Send HTTP POST request using ApiClient
            ApiResponse response = apiClient.post(fullUrl, params);
            if (response.getStatusCode() == 200) {
                // Assuming the response is a JSON string, returning it directly
                return response.getBody();
            } else {
                throw new ApiException("Failed to get product details, status code: " + response.getStatusCode(), null);
            }
        } catch (IOException e) {
            throw new ApiException("Error generating product details request", e);
        }
    }
}
