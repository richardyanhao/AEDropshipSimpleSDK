package ds.ae.richard.simplesdk.api;

import ds.ae.richard.simplesdk.api.business.BusinessApi;
import ds.ae.richard.simplesdk.api.system.SystemApi;
import ds.ae.richard.simplesdk.model.TokenResponse;

/**
 * Client for handling API requests in Ae Drop Shipper.
 *
 * @author Richard Yan
 * @date 2025/2/22
 */
public class AeDropShipperClient {

    private final SystemApi systemApi;
    private final BusinessApi businessApi;

    public AeDropShipperClient() {
        this.systemApi = new SystemApi();
        this.businessApi = new BusinessApi();
    }

    /**
     * Creates a new token using the provided code.
     *
     * @param code Authorization code
     * @return TokenResponse object containing token information
     * @throws ApiException if an error occurs during token creation
     */
    public TokenResponse createToken(String code) throws ApiException {
        return systemApi.createToken(code);
    }

    /**
     * Refreshes the token using the provided refresh token.
     *
     * @param refreshToken Refresh token
     * @return TokenResponse object containing new token information
     * @throws ApiException if an error occurs during token refresh
     */
    public TokenResponse refreshToken(String refreshToken) throws ApiException {
        return systemApi.refreshToken(refreshToken);
    }

    /**
     * Retrieves product details.
     *
     * @param accessToken Access token
     * @param productId Product ID
     * @param shipToCountry Destination country
     * @param targetCurrency Target currency
     * @param targetLanguage Target language
     * @param removePersonalBenefit Flag to remove personal benefit
     * @return String representing product details
     * @throws Exception if an error occurs during API request
     */
    public String getProductDetails(String accessToken, String productId, String shipToCountry,
        String targetCurrency, String targetLanguage, boolean removePersonalBenefit)
        throws Exception {
        return businessApi.getProductDetails(accessToken, productId, shipToCountry, targetCurrency,
            targetLanguage, removePersonalBenefit);
    }

    /**
     * Queries freight information.
     *
     * @param accessToken Access token
     * @param queryDeliveryReq Delivery query request
     * @return String representing freight information
     * @throws Exception if an error occurs during API request
     */
    public String queryFreight(String accessToken, String queryDeliveryReq) throws Exception {
        return businessApi.queryFreight(accessToken, queryDeliveryReq);
    }

    /**
     * Creates a new order.
     *
     * @param accessToken Access token
     * @param dsExtendRequest DS extend request
     * @param paramPlaceOrderRequest Place order request parameter
     * @return String representing order creation response
     * @throws Exception if an error occurs during API request
     */
    public String createOrder(String accessToken, String dsExtendRequest, String paramPlaceOrderRequest)
        throws Exception {
        return businessApi.createOrder(accessToken, dsExtendRequest, paramPlaceOrderRequest);
    }

    /**
     * Queries order information.
     *
     * @param accessToken Access token
     * @param singleOrderQuery Single order query request
     * @return String representing order information
     * @throws Exception if an error occurs during API request
     */
    public String queryOrder(String accessToken, String singleOrderQuery) throws Exception {
        return businessApi.queryOrder(accessToken, singleOrderQuery);
    }

    /**
     * Queries order tracking information.
     *
     * @param accessToken Access token
     * @param aeOrderId AE order ID
     * @param language Language preference
     * @return String representing order tracking information
     * @throws Exception if an error occurs during API request
     */
    public String queryOrderTracking(String accessToken, String aeOrderId, String language) throws Exception {
        return businessApi.queryOrderTracking(accessToken, aeOrderId, language);
    }

    /**
     * Retrieves DS feed item IDs.
     *
     * @param accessToken Access token
     * @param pageSize Page size
     * @param feedName Feed name
     * @param searchId Search ID
     * @return String representing DS feed item IDs
     * @throws Exception if an error occurs during API request
     */
    public String getDsFeedItemIds(String accessToken, Number pageSize, String feedName, String searchId)
        throws Exception {
        return businessApi.getDsFeedItemIds(accessToken, pageSize, feedName, searchId);
    }

    /**
     * Retrieves DS AliExpress address.
     *
     * @param accessToken Access token
     * @param language Language preference
     * @param countryCode Country code
     * @param isMultiLanguage Multi-language flag
     * @return String representing DS AliExpress address
     * @throws Exception if an error occurs during API request
     */
    public String getDsAliexpressAddress(String accessToken, String language, String countryCode,
        String isMultiLanguage) throws Exception {
        return businessApi.getDsAliexpressAddress(accessToken, language, countryCode, isMultiLanguage);
    }

    /**
     * Retrieves DS category information.
     *
     * @param accessToken Access token
     * @param categoryId Category ID
     * @param language Language preference
     * @param appSignature Application signature
     * @return String representing DS category information
     * @throws Exception if an error occurs during API request
     */
    public String dsCategoryGet(String accessToken, String categoryId, String language, String appSignature)
        throws Exception {
        return businessApi.dsCategoryGet(accessToken, categoryId, language, appSignature);
    }

    /**
     * Retrieves feed name information.
     *
     * @param accessToken Access token
     * @param appSignature Application signature
     * @return String representing feed name information
     * @throws Exception if an error occurs during API request
     */
    public String feednameGet(String accessToken, String appSignature) throws Exception {
        return businessApi.feednameGet(accessToken, appSignature);
    }

    /**
     * Retrieves member benefit information.
     *
     * @param accessToken Access token
     * @return String representing member benefit information
     * @throws Exception if an error occurs during API request
     */
    public String memberBenfitGet(String accessToken) throws Exception {
        return businessApi.memberBenfitGet(accessToken);
    }

    /**
     * Performs a text search.
     *
     * @param accessToken Access token
     * @param keyWord Search keyword
     * @param local Local preference
     * @param countryCode Country code
     * @param categoryId Category ID
     * @param sortBy Sort by preference
     * @param pageSize Page size
     * @param pageIndex Page index
     * @param currency Currency preference
     * @param searchExtend Search extension
     * @return String representing search results
     * @throws Exception if an error occurs during API request
     */
    public String textSearch(String accessToken, String keyWord, String local, String countryCode, Number categoryId,
        String sortBy, Number pageSize, Number pageIndex, String currency, String searchExtend)
        throws Exception {
        return businessApi.textSearch(accessToken, keyWord, local, countryCode, categoryId, sortBy, pageSize,
            pageIndex, currency, searchExtend);
    }

    /**
     * Performs an image search using DS.
     *
     * @param accessToken Access token
     * @param imagePath Image path
     * @param currency Currency preference
     * @param lang Language preference
     * @param sortType Sort type
     * @param sortOrder Sort order
     * @param shipTo Shipping destination
     * @param searchType Search type
     * @return String representing image search results
     * @throws Exception if an error occurs during API request
     */
    public String dsImageSearchV2(String accessToken, String imagePath, String currency, String lang,
        String sortType, String sortOrder, String shipTo, String searchType)
        throws Exception {
        return businessApi.dsImageSearchV2(accessToken, imagePath, currency, lang, sortType, sortOrder, shipTo,
            searchType);
    }
}