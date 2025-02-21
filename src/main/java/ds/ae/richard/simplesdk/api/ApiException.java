package ds.ae.richard.simplesdk.api;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:08
 */
public class ApiException extends Exception {
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}