package ds.ae.richard.simplesdk.api.system;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:32
 */
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class TokenResponse {

    @SerializedName("refresh_token_valid_time")
    private String refreshTokenValidTime;

    @SerializedName("havana_id")
    private String havanaId;

    private String code;

    @SerializedName("expire_time")
    private String expireTime;

    private String locale;

    @SerializedName("user_nick")
    private String userNick;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("account_id")
    private String accountId;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("account_platform")
    private String accountPlatform;

    @SerializedName("refresh_expires_in")
    private String refreshExpiresIn;

    @SerializedName("expires_in")
    private String expiresIn;

    private String sp;

    @SerializedName("request_id")
    private String requestId;

    @SerializedName("seller_id")
    private String sellerId;

    private String account;

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getRefreshTokenValidTime() {
        return refreshTokenValidTime;
    }

    public void setRefreshTokenValidTime(String refreshTokenValidTime) {
        this.refreshTokenValidTime = refreshTokenValidTime;
    }

    public String getHavanaId() {
        return havanaId;
    }

    public void setHavanaId(String havanaId) {
        this.havanaId = havanaId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountPlatform() {
        return accountPlatform;
    }

    public void setAccountPlatform(String accountPlatform) {
        this.accountPlatform = accountPlatform;
    }

    public String getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(String refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}