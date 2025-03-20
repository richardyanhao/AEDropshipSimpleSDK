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

    public static final String API_TOKEN_CREATE = "/auth/token/create";
    public static final String API_TOKEN_REFRESH = "/auth/token/refresh";

    // Business API Endpoints
    public static final String API_PRODUCT_DETAIL = "aliexpress.ds.product.get";
    public static final String API_FREIGHT_QUERY = "aliexpress.ds.freight.query";
    public static final String API_CREATE_ORDER = "aliexpress.ds.order.create";
    public static final String API_QUERY_ORDER = "aliexpress.trade.ds.order.get";
    public static final String API_QUERY_ORDER_TRACKING = "aliexpress.ds.order.tracking.get";
    public static final String API_DS_FEED_ITEMIDS_GET = "aliexpress.ds.feed.itemids.get";
    public static final String API_DS_ALIEXPRESS_ADDRESS_GET = "aliexpress.ds.address.get";
    public static final String API_DS_IMAGE_SEARCH = "aliexpress.ds.image.searchV2";
    public static final String API_DS_CATEGORY_GET = "aliexpress.ds.category.get";
    public static final String API_DS_FEEDNAME_GET = "aliexpress.ds.feedname.get";
    public static final String API_DS_MEMBER_BENFIT_GET = "aliexpress.ds.member.benefit.get";
    public static final String API_DS_TEXT_SEARCH = "aliexpress.ds.text.search";
}
