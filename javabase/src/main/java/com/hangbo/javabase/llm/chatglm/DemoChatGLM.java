package com.hangbo.javabase.llm.chatglm;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhipu.oapi.core.ConfigV3;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/10/28 22:47
 */
public class DemoChatGLM {

    public static String sendPostRequest(String targetUrl, String postData) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);

        try {
            // 设置请求头部信息
            httpPost.setHeader("Content-Type", "text/event-stream");

            // 设置POST请求的数据
            StringEntity entity = new StringEntity(postData);
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                String responseString = EntityUtils.toString(response.getEntity());
                return responseString;
            } else {
                return "HTTP Error: " + response.getStatusLine().getStatusCode();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        String targetUrl = "https://open.bigmodel.cn/api/paas/v3/model-api/chatglm_turbo/sse-invoke";
        ModelApiRequest modelApiRequest = new ModelApiRequest();
        modelApiRequest.setRequestId(UUID.randomUUID().toString().replace("-", ""));
    }


    private String createJwt() {
        Algorithm alg;
        try {
            alg = Algorithm.HMAC256(Contant.API_SECRET.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Map<String, Object> payload = new HashMap<>();
        payload.put("api_key", Contant.API_KEY);
        payload.put("exp", System.currentTimeMillis() + 9000);
        payload.put("timestamp", Calendar.getInstance().getTimeInMillis());
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", alg);
        headerClaims.put("sign_type", "SIGN");
        return JWT.create().withPayload(payload).withHeader(headerClaims).sign(alg);
    }

    @Data
    @Accessors
    public static class ModelApiRequest {
        private String requestId;
        private boolean incremental;
        private String returnType;
        private float topP = 0.7f;
        private float temperature;
        private String searchQuery;
        private List<Prompt> prompts;

        @Data
        @Accessors
        static public class Prompt {
            private String role;
            private String content;
        }

    }
}
