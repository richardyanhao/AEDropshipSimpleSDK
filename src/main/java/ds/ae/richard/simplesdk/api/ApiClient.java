package ds.ae.richard.simplesdk.api;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:07
 */
public class ApiClient {
    // 创建一个单例HttpClient实例
    private static final HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    /**
     * 发送GET请求
     *
     * @param url    请求的URL
     * @param params 请求参数
     * @return ApiResponse 包含响应状态码和响应体
     * @throws ApiException 如果请求失败
     */
    public ApiResponse get(String url, Map<String, String> params) throws ApiException {
        try {
            String fullUrl = url + "?" + encodeParams(params);

            System.out.println(fullUrl);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .GET()
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new ApiResponse(response.statusCode(), response.body());
        } catch (IOException | InterruptedException e) {
            throw new ApiException("GET request failed", e);
        }
    }

    /**
     * 发送POST请求
     *
     * @param url    请求的URL
     * @param params 请求参数
     * @return ApiResponse 包含响应状态码和响应体
     * @throws ApiException 如果请求失败
     */
    public ApiResponse post(String url, Map<String, String> params) throws ApiException {
        try {
            String encodedParams = encodeParams(params);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(encodedParams))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new ApiResponse(response.statusCode(), response.body());
        } catch (IOException | InterruptedException e) {
            throw new ApiException("POST request failed", e);
        }
    }

    /**
     * 将参数编码为x-www-form-urlencoded格式
     *
     * @param params 请求参数
     * @return 编码后的参数字符串
     */
    private String encodeParams(Map<String, String> params) {
        return params.entrySet()
            .stream()
            .map(entry -> {
                try {
                    return entry.getKey() + "=" + urlEncode(entry.getValue());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            })
            .collect(Collectors.joining("&"));
    }

    private String urlEncode(String value) throws IOException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }
}
