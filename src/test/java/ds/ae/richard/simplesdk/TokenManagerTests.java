package ds.ae.richard.simplesdk;

/**
 * @author Richard Yan
 * @date 2025/2/20 00:52
 */
import ds.ae.richard.simplesdk.api.ApiClient;
import ds.ae.richard.simplesdk.api.ApiException;
import ds.ae.richard.simplesdk.api.ApiResponse;
import ds.ae.richard.simplesdk.api.system.SystemApi;
import ds.ae.richard.simplesdk.api.system.TokenResponse;
import ds.ae.richard.simplesdk.utils.SignatureUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TokenManagerTests {

    @InjectMocks
    private SystemApi tokenManager;

    @Mock
    private ApiClient apiClient;

    @Mock
    private SignatureUtil signatureUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 测试用例1：正常情况 - 成功获取token
     */
    @Test
    public void testCreateToken_Success() throws Exception {
        // 准备
        String code = "valid_code";
        String signature = "valid_signature";
        when(SignatureUtil.generateSignature(anyMap(), anyString(), anyString())).thenReturn(signature);
        ApiResponse mockResponse = new ApiResponse(200, "{\"access_token\":\"abc\"}");
        when(apiClient.post(anyString(), anyMap())).thenReturn(mockResponse);

        // 执行
        TokenResponse result = tokenManager.createToken(code);

        // 验证
        assertNotNull(result);
        assertEquals("abc", result.getAccessToken());
    }

    /**
     * 测试用例2：签名错误 - 抛出异常
     */
    @Test(expected = ApiException.class)
    public void testCreateToken_SignatureError() throws Exception {
        // 准备
        String code = "invalid_code";
        when(SignatureUtil.generateSignature(anyMap(), anyString(), anyString())).thenThrow(new RuntimeException());

        // 执行
        tokenManager.createToken(code);
    }

    /**
     * 测试用例3：API请求失败 - 返回非200状态码
     */
    @Test(expected = ApiException.class)
    public void testCreateToken_ApiRequestFailure() throws Exception {
        // 准备
        String code = "valid_code";
        String signature = "valid_signature";
        when(SignatureUtil.generateSignature(anyMap(), anyString(), anyString())).thenReturn(signature);
        ApiResponse mockResponse = new ApiResponse(400, "");
        when(apiClient.post(anyString(), anyMap())).thenReturn(mockResponse);

        // 执行
        tokenManager.createToken(code);
    }

    /**
     * 测试用例4：参数为空 - 抛出异常
     */
    @Test(expected = ApiException.class)
    public void testCreateToken_EmptyCode() throws Exception {
        // 准备
        String code = "";

        // 执行
        tokenManager.createToken(code);
    }

    /**
     * 测试用例5：异常捕获 - 抛出异常
     */
    @Test(expected = ApiException.class)
    public void testCreateToken_ExceptionCaught() throws Exception {
        // 准备
        String code = "valid_code";
        doThrow(new RuntimeException()).when(apiClient).post(anyString(), anyMap());

        // 执行
        tokenManager.createToken(code);
    }
}