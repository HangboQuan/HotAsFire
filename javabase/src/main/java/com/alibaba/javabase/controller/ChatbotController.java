package com.alibaba.javabase.controller;

import com.google.gson.Gson;
import com.zhipu.oapi.service.v3.ModelApiRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static com.alibaba.javabase.JavabaseApplication.getToken;

/**
 * @author quanhangbo
 * @date 2023/12/16 17:22
 */
@Slf4j
@RestController
public class ChatbotController {


    @GetMapping(value = "/api/model", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<ResponseBodyEmitter> chat(@RequestParam String query, String timesteap) {
        log.info("query={}", query);
        SseEmitter emitter = new SseEmitter();

        new Thread(() -> {
            try {
                // 在这里执行你的逻辑，发送 SSE 事件
                ModelApiRequest modelApiRequest = new ModelApiRequest();
                modelApiRequest.setRequestId(UUID.randomUUID().toString().replace("_", ""));
                modelApiRequest.setTopP(0.7f);
                modelApiRequest.setIncremental(true);
                modelApiRequest.setTemperature(0.9f);
                modelApiRequest.setPrompt(Arrays.asList(new ModelApiRequest.Prompt("user", query)));

                String jsonPayload = new Gson().toJson(modelApiRequest);

                String apiKey = "9aaeae5ae0f3f03b3bc4030fb7e48188.yGt2pzmZ2xPjB30S";
                String[] ans = apiKey.split("\\.");
                String token = getToken(ans[0], ans[1]);

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://open.bigmodel.cn/api/paas/v3/model-api/chatglm_lite/sse-invoke")
                        .post(RequestBody.create(okhttp3.MediaType.parse("application/json"), jsonPayload))
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                        .header("Accept", "text/event-stream")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    ResponseBody body = response.body();
                    String responseData = body.string();
                    log.info("Sending SSE event: {}", responseData);
                    String data = "data:" + responseData;
                    emitter.send(SseEmitter.event().data(data).name("message"));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    emitter.complete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(emitter);
    }


}
