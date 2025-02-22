package ds.ae.richard.simplesdk.api;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

            // System API usage
            String authCode = "3_509450_B72HDswtFOgpE5fYEDXmCpBz1220"; // Replace with actual auth code
            TokenResponse tokenResponse = client.createToken(authCode);

            System.out.println("Create Token Access Token: " + tokenResponse.getAccessToken());
            System.out.println("Create Token Refresh Token: " + tokenResponse.getRefreshToken());
            System.out.println("Create Token expire_time: " + formatTimestamp(tokenResponse.getExpireTime()));
            System.out.println("Create Token refresh_token_valid_time: " + formatTimestamp(tokenResponse.getRefreshTokenValidTime()));

            Thread.sleep(5000);
            // Assuming refresh token is available
            String refreshToken = tokenResponse.getRefreshToken();
            TokenResponse refreshedTokenResponse = client.refreshToken(refreshToken);

            System.out.println("Refresh Token Access Token: " + refreshedTokenResponse.getAccessToken());
            System.out.println("Refresh Token Refresh Token: " + refreshedTokenResponse.getRefreshToken());
            System.out.println("Refresh Token expire_time: " + formatTimestamp(refreshedTokenResponse.getExpireTime()) );
            System.out.println("Refresh Token refresh_token_valid_time: " + formatTimestamp(refreshedTokenResponse.getRefreshTokenValidTime()));

            //Business API usage
            String accessToken = refreshedTokenResponse.getAccessToken();

            // Get product details
            String productId = "1005004275827894";
            String shipToCountry = "US";
            String targetCurrency = "USD";
            String targetLanguage = "en";
            boolean removePersonalBenefit = false;
            String productDetails = client.getProductDetails(accessToken, productId, shipToCountry, targetCurrency, targetLanguage, removePersonalBenefit);
            System.out.println("Product Details: " + productDetails);

            // Query freight information
            String queryDeliveryReq = "{\"quantity\":\"1\",\"shipToCountry\":\"FR\",\"productId\":\"3256802900954148\",\"provinceCode\":\"province\",\"cityCode\":\"city\",\"selectedSkuId\":\"12000023999200390\",\"language\":\"en_US\",\"currency\":\"USD\",\"locale\":\"zh_CN\"}";
            Gson gson = new Gson();
            String freightInfo = client.queryFreight(accessToken, queryDeliveryReq);
            System.out.println("Freight Information: " + freightInfo);

        } catch (ApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
    }
}