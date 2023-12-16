package com.alibaba.javabase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import okhttp3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class JavabaseApplication {

	private static final long expireMillis = 30 * 60 * 1000L;

	// 缓存服务
	public static Cache<String, String> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(expireMillis - (60 * 1000L), TimeUnit.SECONDS)
			.build();

	public static String getToken(String apiKey, String apiSecret) {
		// 缓存Token
		String token = cache.getIfPresent(apiKey);
		if (null != token) {
			return token;
		}
		// 创建Token
		Algorithm algorithm = Algorithm.HMAC256(apiSecret.getBytes(StandardCharsets.UTF_8));
		Map<String, Object> payload = new HashMap<>();
		payload.put("api_key", apiKey);
		payload.put("exp", System.currentTimeMillis() + expireMillis);
		payload.put("timestamp", Calendar.getInstance().getTimeInMillis());
		Map<String, Object> headerClaims = new HashMap<>();
		headerClaims.put("alg", "HS256");
		headerClaims.put("sign_type", "SIGN");
		token = JWT.create().withPayload(payload).withHeader(headerClaims).sign(algorithm);
		cache.put(apiKey, token);
		return token;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavabaseApplication.class, args);
//
//		OkHttpClient client = new OkHttpClient();
//
//		// Create JSON payload
//		String jsonPayload = "{\"top_p\": 0.7, \"sseFormat\": \"data\", \"temperature\": 0.9, \"incremental\": true, \"request_id\": \"xfg-1696992276607\", \"prompt\": [{\"role\": \"user\", \"content\": \"我要当太子，应该怎样做\"}]}";
//
//		String apiKey = "9aaeae5ae0f3f03b3bc4030fb7e48188.yGt2pzmZ2xPjB30S";
//		String[] ans = apiKey.split("\\.");
//		String token = getToken(ans[0], ans[1]);
//		// Create a request
//		Request request = new Request.Builder()
//				.url("http://open.bigmodel.cn/api/paas/v3/model-api/chatglm_lite/sse-invoke")
//				.post(RequestBody.create(MediaType.parse("application/json"), jsonPayload))
//				.header("Authorization", "Bearer " + token)
//				.header("Content-Type", "application/json")
//				.header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
//				.header("Accept", "text/event-stream")
//				.build();
//
//		try (Response response = client.newCall(request).execute()) {
//			// Print the response code
//			System.out.println("Response Code: " + response.code());
//
//			// Print the response body
//			System.out.println("Response: " + response.body().string());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
