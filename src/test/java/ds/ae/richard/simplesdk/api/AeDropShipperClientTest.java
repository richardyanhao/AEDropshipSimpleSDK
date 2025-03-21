package ds.ae.richard.simplesdk.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import ds.ae.richard.simplesdk.model.TokenResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Richard Yan
 * @date 2025/2/22 21:50
 */
class AeDropShipperClientTest {

    @Test
    void createToken() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void getProductDetails() {
    }

    private static String formatTimestamp(String timestamp) {
        long epochMilli = Long.parseLong(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public static void main(String[] args) {
        try {
            AeDropShipperClient client = new AeDropShipperClient();

//            // System API usage
//            String authCode = "3_509450_B72HDswtFOgpE5fYEDXmCpBz1220"; // Replace with actual auth code
//            TokenResponse tokenResponse = client.createToken(authCode);
//
//            System.out.println("Create Token Access Token: " + tokenResponse.getAccessToken());
//            System.out.println("Create Token Refresh Token: " + tokenResponse.getRefreshToken());
//            System.out.println("Create Token expire_time: " + formatTimestamp(tokenResponse.getExpireTime()));
//            System.out.println("Create Token refresh_token_valid_time: " + formatTimestamp(tokenResponse.getRefreshTokenValidTime()));
//
//            // Assuming refresh token is available
//            String refreshToken = tokenResponse.getRefreshToken();
//            TokenResponse refreshedTokenResponse = client.refreshToken(refreshToken);
//
//            System.out.println("Refresh Token Access Token: " + refreshedTokenResponse.getAccessToken());
//            System.out.println("Refresh Token Refresh Token: " + refreshedTokenResponse.getRefreshToken());
//            System.out.println("Refresh Token expire_time: " + formatTimestamp(refreshedTokenResponse.getExpireTime()) );
//            System.out.println("Refresh Token refresh_token_valid_time: " + formatTimestamp(refreshedTokenResponse.getRefreshTokenValidTime()));
//
//            //Business API usage
//            String accessToken = refreshedTokenResponse.getAccessToken();
            String accessToken = "50000601339tcAyeuejuguOkAjCy7MyuhjmNJyDzwlD5ltRiuop1d6aa8cfQjFK8Y3LN";

//             Get product details
//            String productId = "1005004275827894";
//            String shipToCountry = "US";
//            String targetCurrency = "USD";
//            String targetLanguage = "en";
//            boolean removePersonalBenefit = false;
//            String productDetails = client.getProductDetails(accessToken, productId, shipToCountry, targetCurrency, targetLanguage, removePersonalBenefit);
//            System.out.println("Product Details: " + productDetails);
//
//             Query freight information
//            String queryDeliveryReq = "{     \"productId\": 3256807808787381,     \"selectedSkuId\": \"12000043195277241\",     \"quantity\": 1,     \"shipToCountry\": \"US\",     \"provinceCode\": \"\",     \"cityCode\": \"\",     \"source\": \"\",     \"currency\": \"USD\",     \"language\": \"en_US\",     \"locale\": \"\" }";
//            Gson gson = new Gson();
//            String freightInfo = client.queryFreight(accessToken, queryDeliveryReq);
//            System.out.println("Freight Information: " + freightInfo);

//            String categoryResult = client.dsCategoryGet();

//            String dsExtendRequest = "{}";
//            String paramPlaceOrderRequest"";
//            String dsExtendRequest = client.createOrder(accessToken,dsExtendRequest,paramPlaceOrderRequest);
//            System.out.println("dsExtendRequest"+dsExtendRequest);
//
            //aliexpress.trade.ds.order.get
//            String single_order_query = "{\"order_id\":\"8198471681031315\"}";
//            String queryOrder = client.queryOrder(accessToken,single_order_query);
//            System.out.println("queryOrder"+queryOrder);

            //dsImageSearchV2
            String imagePath = "/Users/yanhao/Downloads/S3ecf5f434c894110854aeec9c010df99y.webp";
            String currency = "USD";
            String lang = "en_US";
            String sort_type = "orders";
            String sort_order= "desc";
            String ship_to = "US";
            String search_type = "same";
            String dsImageSearchV2 = client.dsImageSearchV2(accessToken, imagePath, currency, lang, sort_type, sort_order, ship_to, search_type);
            System.out.println("dsImageSearchV2"+dsImageSearchV2);
//          aliexpress.ds.feed.itemids.get
//            String pageSizeStr = "200";
//            Integer pageSize = Integer.parseInt(pageSizeStr);
//            String feedName ="AEB_i69_FullCategory_TopSellers_20241225";
//            String searchId ="";
//            String feednameGets = client.getDsFeedItemIds(accessToken,pageSize,feedName,searchId);
//            System.out.println("feednameGet"+feednameGets);
//
////          getDsAliexpressAddress
//            String language = "";
//            String countryCode = "US";
//            String isMultiLanguage = "";
//            String addressList =client.getDsAliexpressAddress(accessToken,language,countryCode,isMultiLanguage);
//            System.out.println("address"+addressList);

            //dsCategoryGet
//            String categoryId = "15";
//            String language ="";
//            String app_signature ="";
//            String categoryList =client.dsCategoryGet(accessToken,categoryId,language,app_signature);
//            System.out.println("category"+categoryList);
//            String keyWord = "s";
//            String local ="en_US";
//            String countryCode="US";
//            String categoryIdr = "15";
//            Integer categoryId = Integer.parseInt(categoryIdr);
//            String sortBy="";
//            String pageSizes ="20";
//            Integer pageSize = Integer.parseInt(pageSizes);
//            String pageIndexs ="1";
//            Integer pageIndex = Integer.parseInt(pageIndexs);
//            String currency="USD";
//            String searchExtend="{\"selectionName\":\"\"}";
//
//            String textSearchs = client.textSearch(accessToken,keyWord,local,countryCode,categoryId,  sortBy,  pageSize,  pageIndex,  currency,  searchExtend );
//                System.out.println("textsearch"+textSearchs);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
    }
}