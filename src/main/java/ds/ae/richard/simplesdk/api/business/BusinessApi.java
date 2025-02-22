package ds.ae.richard.simplesdk.api.business;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    // 获取商品详情
    public String getProductDetails(String accessToken, String productId, String shipToCountry,
        String targetCurrency, String targetLanguage, boolean removePersonalBenefit) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ship_to_country", shipToCountry);
        params.put("product_id", productId);
        params.put("target_currency", targetCurrency);
        params.put("target_language", targetLanguage);
        params.put("remove_personal_benefit", String.valueOf(removePersonalBenefit));

        return executeBusinessRequest("aliexpress.ds.product.get", accessToken, params);
    }

    // 查询物流信息
    public String queryFreight(String accessToken, String queryDeliveryReq) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("queryDeliveryReq", queryDeliveryReq);

        return executeBusinessRequest("aliexpress.ds.freight.query", accessToken, params);
    }

    private String executeBusinessRequest(String apiPath, String accessToken, Map<String, String> additionalParams) throws ApiException {
        try {

            additionalParams.put("session", accessToken);
            additionalParams.put("format", "json");
            // 生成签名
            String sign = SignatureUtil.generateSignature(additionalParams, clientSecret, apiPath, clientId);
            additionalParams.put("sign", sign);

            ApiResponse response = apiClient.get(Constants.BASE_URL_FOR_BUSINESS, additionalParams);
            if (response.getStatusCode() == 200) {
                return response.getBody();
            } else {
                throw new ApiException("Failed to execute business request, status code: " + response.getStatusCode(), null);
            }
        } catch (IOException e) {
            throw new ApiException("Error executing business request", e);
        }
    }
}
