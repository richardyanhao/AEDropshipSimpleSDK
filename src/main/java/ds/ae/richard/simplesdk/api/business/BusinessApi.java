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

    // 创建订单
    public String createOrder(String accessToken, String dsExtendRequest, String paramPlaceOrderRequest) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ds_extend_request", dsExtendRequest);
        params.put("param_place_order_request4_open_api_d_t_o", paramPlaceOrderRequest);

        return executeBusinessRequest("aliexpress.ds.order.create", accessToken, params);
    }

    // 查询订单
    public String queryOrder(String accessToken, String singleOrderQuery) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("single_order_query", singleOrderQuery);

        return executeBusinessRequest("aliexpress.trade.ds.order.get", accessToken, params);
    }

    // 查询订单物流状态
    public String queryOrderTracking(String accessToken, String aeOrderId, String language) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ae_order_id", aeOrderId);
        params.put("language", language == null ? "en_US" : language);

        return executeBusinessRequest("aliexpress.ds.order.tracking.get", accessToken, params);
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
