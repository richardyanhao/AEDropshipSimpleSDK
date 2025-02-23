package ds.ae.richard.simplesdk.api;

import java.util.HashMap;
import java.util.Map;

import ds.ae.richard.simplesdk.api.business.BusinessApi;
import ds.ae.richard.simplesdk.api.system.SystemApi;
import ds.ae.richard.simplesdk.model.TokenResponse;

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
     * @param code
     * @return TokenResponse
     * @throws ApiException
     */
    public TokenResponse createToken(String code) throws ApiException {
        return systemApi.createToken(code);
    }

    /**
     * refresh token
     * @param refreshToken
     * @return TokenResponse
     * @throws ApiException
     */
    public TokenResponse refreshToken(String refreshToken) throws ApiException {
        return systemApi.refreshToken(refreshToken);
    }

    /**
     * getProductDetails
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


}
