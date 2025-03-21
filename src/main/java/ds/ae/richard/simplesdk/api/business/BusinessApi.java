package ds.ae.richard.simplesdk.api.business;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import ds.ae.richard.simplesdk.api.ApiClient;
import ds.ae.richard.simplesdk.api.ApiException;
import ds.ae.richard.simplesdk.api.ApiResponse;
import ds.ae.richard.simplesdk.config.ConfigManager;
import ds.ae.richard.simplesdk.utils.Constants;
import ds.ae.richard.simplesdk.utils.SignatureUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import static ds.ae.richard.simplesdk.utils.Constants.API_DS_IMAGE_SEARCH;

/**
 * Business API class for handling business requests.
 *
 * @authro Richard Yan
 * @date 2025/2/22
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
        String targetCurrency, String targetLanguage, boolean removePersonalBenefit)
        throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ship_to_country", shipToCountry);
        params.put("product_id", productId);
        params.put("target_currency", targetCurrency);
        params.put("target_language", targetLanguage);
        params.put("remove_personal_benefit", String.valueOf(removePersonalBenefit));

        return executeBusinessRequest(Constants.API_PRODUCT_DETAIL, accessToken, params);
    }

    // 查询物流信息
    public String queryFreight(String accessToken, String queryDeliveryReq) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("queryDeliveryReq", queryDeliveryReq);

        return executeBusinessRequest(Constants.API_FREIGHT_QUERY, accessToken, params);
    }

    // 获取Feed项目ID
    public String getDsFeedItemIds(String accessToken, Number pageSize, String feedName, String searchId)
        throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("page_size", pageSize.toString());
        params.put("feed_name", feedName);
        params.put("search_id", searchId);
        return executeBusinessRequest(Constants.API_DS_FEED_ITEMIDS_GET, accessToken, params);
    }

    // 获取地址
    public String getDsAliexpressAddress(String accessToken, String language, String countryCode,
        String isMultiLanguage) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("language", language);
        params.put("countryCode", countryCode);
        params.put("isMultiLanguage", isMultiLanguage);

        return executeBusinessRequest(Constants.API_DS_ALIEXPRESS_ADDRESS_GET, accessToken, params);
    }

    // 图片搜索
    public String dsImageSearchV2(
        String accessToken,
        String imagePath,
        String currency,
        String lang,
        String sortType,
        String sortOrder,
        String shipTo,
        String searchType
    ) throws Exception {
        String imageBase64 = convertImageToBase64(imagePath);
        Map<String, String> param0Map = new HashMap<>();
        param0Map.put("image_base64", imageBase64);
        param0Map.put("currency", currency);
        param0Map.put("lang", lang);
        param0Map.put("sort_type", sortType);
        param0Map.put("sort_order", sortOrder);
        param0Map.put("ship_to", shipTo);
        param0Map.put("search_type", searchType);

        String param0Json = new ObjectMapper().writeValueAsString(param0Map);
        Map<String, String> params = new HashMap<>();
        params.put("param0", param0Json);
        return executeBusinessRequest(API_DS_IMAGE_SEARCH, accessToken, params);
    }

    private String convertImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        if (!file.exists()) {
            throw new IOException("图片文件不存在: " + imagePath);
        }
        return Base64.getEncoder()
            .encodeToString(java.nio.file.Files.readAllBytes(file.toPath()));
    }

    // 获取类别
    public String dsCategoryGet(String accessToken, String categoryId, String language, String appSignature)
        throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("language", language);
        params.put("app_signature", appSignature);

        return executeBusinessRequest(Constants.API_DS_CATEGORY_GET, accessToken, params);
    }

    // 获取Feed名称
    public String feednameGet(String accessToken, String appSignature) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("app_signature", appSignature);

        return executeBusinessRequest(Constants.API_DS_FEEDNAME_GET, accessToken, params);
    }

    // 获取会员福利
    public String memberBenfitGet(String accessToken) throws Exception {
        Map<String, String> params = new HashMap<>();

        return executeBusinessRequest(Constants.API_DS_MEMBER_BENFIT_GET, accessToken, params);
    }

    // 文本搜索
    public String textSearch(String accessToken, String keyWord, String local, String countryCode,
        Number categoryId, String sortBy, Number pageSize, Number pageIndex,
        String currency, String searchExtend) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("keyWord", keyWord);
        params.put("local", local);
        params.put("countryCode", countryCode);
        params.put("categoryId", categoryId.toString());
        params.put("sortBy", sortBy);
        params.put("pageSize", pageSize.toString());
        params.put("pageIndex", pageIndex.toString());
        params.put("currency", currency);
        params.put("searchExtend", searchExtend);

        return executeBusinessRequest(Constants.API_DS_TEXT_SEARCH, accessToken, params);
    }

    // 创建订单
    public String createOrder(String accessToken, String dsExtendRequest, String paramPlaceOrderRequest)
        throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ds_extend_request", dsExtendRequest);
        params.put("param_place_order_request4_open_api_d_t_o", paramPlaceOrderRequest);

        return executeBusinessRequest(Constants.API_CREATE_ORDER, accessToken, params);
    }

    // 查询订单
    public String queryOrder(String accessToken, String singleOrderQuery) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("single_order_query", singleOrderQuery);

        return executeBusinessRequest(Constants.API_QUERY_ORDER, accessToken, params);
    }

    // 查询订单物流状态
    public String queryOrderTracking(String accessToken, String aeOrderId, String language) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ae_order_id", aeOrderId);
        params.put("language", language == null ? "en_US" : language);

        return executeBusinessRequest(Constants.API_QUERY_ORDER_TRACKING, accessToken, params);
    }

    private String executeBusinessRequest(String apiPath, String accessToken, Map<String, String> additionalParams)
        throws ApiException {
        try {
            additionalParams.put("session", accessToken);
            additionalParams.put("format", "json");
            String sign = SignatureUtil.generateSignature(additionalParams, clientSecret, apiPath, clientId);
            additionalParams.put("sign", sign);

            ApiResponse response;
            if (API_DS_IMAGE_SEARCH.equalsIgnoreCase(apiPath)) {
                response = apiClient.post(Constants.BASE_URL_FOR_BUSINESS, additionalParams);
            } else {
                response = apiClient.get(Constants.BASE_URL_FOR_BUSINESS, additionalParams);
            }

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