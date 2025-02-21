package ds.ae.richard.simplesdk.api;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:08
 */
public class ApiResponse {
    private int statusCode;
    private String body;
    //private T data;

    public ApiResponse(int statusCode, String rawBody) {
        this.statusCode = statusCode;
        this.body = rawBody;
        //this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}
