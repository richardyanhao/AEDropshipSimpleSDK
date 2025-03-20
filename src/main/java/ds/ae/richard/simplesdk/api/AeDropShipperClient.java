package ds.ae.richard.simplesdk.api;

import java.util.HashMap;
import java.util.Map;

import ds.ae.richard.simplesdk.api.business.BusinessApi;
import ds.ae.richard.simplesdk.api.system.SystemApi;
import ds.ae.richard.simplesdk.model.TokenResponse;
import ds.ae.richard.simplesdk.utils.Constants;

/**
 * @author Richard Yan
 * @date 2025/2/22 15:31
 */
public class AeDropShipperClient {
    private final SystemApi systemApi;
    private final BusinessApi businessApi;

    public AeDropShipperClient() {
        systemApi = new SystemApi();
        businessApi = new BusinessApi();
    }

    /**
     * create token
     *
     * @param code
     * @return TokenResponse
     * @throws ApiException
     */
    public TokenResponse createToken(String code) throws ApiException {
        return systemApi.createToken(code);
    }

    /**
     * refresh token
     *
     * @param refreshToken
     * @return TokenResponse
     * @throws ApiException
     */
    public TokenResponse refreshToken(String refreshToken) throws ApiException {
        return systemApi.refreshToken(refreshToken);
    }

    /**
     * getProductDetails
     *
     * @param accessToken
     * @param productId
     * @param shipToCountry
     * @param targetCurrency
     * @param targetLanguage
     * @param removePersonalBenefit
     * @return String response
     * @throws ApiException
     */
    public String getProductDetails(String accessToken, String productId, String shipToCountry,
                                    String targetCurrency, String targetLanguage, boolean removePersonalBenefit) throws Exception {
        return businessApi.getProductDetails(accessToken, productId, shipToCountry, targetCurrency, targetLanguage, removePersonalBenefit);
    }

    public String queryFreight(String accessToken, String queryDeliveryReq) throws Exception {
        return businessApi.queryFreight(accessToken, queryDeliveryReq);
    }

    // 创建订单
    public String createOrder(String accessToken, String dsExtendRequest, String paramPlaceOrderRequest) throws Exception {
        return businessApi.createOrder(accessToken, dsExtendRequest, paramPlaceOrderRequest);
    }

    // 查询订单
    public String queryOrder(String accessToken, String singleOrderQuery) throws Exception {
        return businessApi.queryOrder(accessToken, singleOrderQuery);
    }

    // 查询订单物流状态
    public String queryOrderTracking(String accessToken, String aeOrderId, String language) throws Exception {
        return businessApi.queryOrderTracking(accessToken, aeOrderId, language);
    }

    public String getDsFeedItemIds(String accessToken, Number pageSize, String feedName, String searchId) throws Exception {
        return businessApi.getDsFeedItemIds(accessToken, pageSize, feedName, searchId);
    }

    public String getDsAliexpressAddress(String accessToken, String language, String countryCode, String isMultiLanguage) throws Exception {
        return businessApi.getDsAliexpressAddress(accessToken, language, countryCode, isMultiLanguage);
    }

    public String dsCategoryGet(String accessToken, String categoryId, String language, String app_signature) throws Exception {
        return businessApi.dsCategoryGet(accessToken, categoryId, language, app_signature);

    }

    public String feednameGet(String accessToken, String app_signature) throws Exception {
        return businessApi.feednameGet(accessToken, app_signature);
    }

    public String memberBenfitGet(String accessToken) throws Exception {
        return businessApi.memberBenfitGet(accessToken);
    }

    public String textSearch(String accessToken, String keyWord, String local, String countryCode, Number categoryId, String sortBy, Number pageSize, Number pageIndex, String currency, String searchExtend) throws Exception {
        return businessApi.textSearch(accessToken, keyWord, local, countryCode, categoryId, sortBy, pageSize, pageIndex, currency, searchExtend);
    }

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
        return businessApi.dsImageSearchV2(
                accessToken, imagePath,currency, lang, sortType, sortOrder, shipTo, searchType
        );
    }


}

