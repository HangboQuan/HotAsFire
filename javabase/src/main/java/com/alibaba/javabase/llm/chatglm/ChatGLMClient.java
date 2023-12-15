package com.alibaba.javabase.llm.chatglm;

import com.google.gson.Gson;
import com.zhipu.oapi.ClientV3;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.core.httpclient.OkHttpTransport;
import com.zhipu.oapi.service.v3.ModelApiRequest;
import com.zhipu.oapi.service.v3.ModelConstants;
import com.zhipu.oapi.service.v3.StandardEventSourceListener;
import okhttp3.*;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.alibaba.javabase.JavabaseApplication.getToken;


/**
 * @author quanhangbo
 * @date 2023/12/14 19:24
 */
public class ChatGLMClient {

    public static OkHttpClient getInstance() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()//构建器
                .proxy(Proxy.NO_PROXY) //来屏蔽系统代理
                .retryOnConnectionFailure(true)
                .connectTimeout(300, TimeUnit.SECONDS)//连接超时
                .writeTimeout(300, TimeUnit.SECONDS)//写入超时
                .readTimeout(300, TimeUnit.SECONDS)//读取超时
                .build();

        okHttpClient.dispatcher().setMaxRequestsPerHost(200); //设置最大并发请求数，避免等待延迟
        okHttpClient.dispatcher().setMaxRequests(200);
        return okHttpClient;
    }


//    public static void main(String[] args) {
//        testSseCall();
//    }

    public static ModelApiRequest sseRequest() {
        ModelApiRequest modelApiRequest = new ModelApiRequest();
        modelApiRequest.setModelId(Constants.ModelChatGLM6B);
        modelApiRequest.setInvokeMethod(Constants.invokeMethodSse);
        // returnType 非必填参数
        modelApiRequest.setReturnType(Constants.RETURN_TYPE_JSON);
        // 可自定义sse listener
        modelApiRequest.setSseListener(new StandardEventSourceListener());
        ModelApiRequest.Prompt prompt = new ModelApiRequest.Prompt(ModelConstants.roleUser, "ChatGPT和你哪个更强大");
        List<ModelApiRequest.Prompt> prompts = new ArrayList<>();
        prompts.add(prompt);
        modelApiRequest.setPrompt(prompts);
        // 关闭搜索示例
        //  modelApiRequest.setRef(new HashMap<String, Object>(){{
        //    put("enable",false);
        // }});
        // 开启搜索示例
        // modelApiRequest.setRef(new HashMap<String, Object>(){{
        //    put("enable",true);
        //    put("search_query","历史");
        //  }});
//        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
        String requestId = UUID.randomUUID().toString().replace("_", "");
        modelApiRequest.setRequestId(requestId);
        return modelApiRequest;
    }

    public static void testSseCall() {
        ModelApiRequest modelApiRequest = new ModelApiRequest();
        client.invokeModelApi(modelApiRequest);
    }

    private static ClientV3 client = new ClientV3.Builder(Contant.API_KEY, Contant.API_SECRET)
            .httpTransport(new OkHttpTransport(getInstance()))
            .build();

    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        ModelApiRequest modelApiRequest = new ModelApiRequest();
        modelApiRequest.setRequestId(UUID.randomUUID().toString().replace("_", ""));
        modelApiRequest.setTopP(0.7f);
        modelApiRequest.setIncremental(true);
        modelApiRequest.setTemperature(0.9f);
        modelApiRequest.setPrompt(new ArrayList<ModelApiRequest.Prompt>() {
            {
                add(new ModelApiRequest.Prompt("user", "我喜欢吴沁倬"));
            }
        });




        // Create JSON payload
//        String jsonPayload = "{\"top_p\": 0.7, \"sseFormat\": \"data\", \"temperature\": 0.9, \"incremental\": true, \"request_id\": \"xfg-1696992276607\", \"prompt\": [{\"role\": \"user\", \"content\": \"我要当太子，应该怎样做\"}]}";

        String jsonPayload = new Gson().toJson(modelApiRequest);

        String apiKey = "9aaeae5ae0f3f03b3bc4030fb7e48188.yGt2pzmZ2xPjB30S";
        String[] ans = apiKey.split("\\.");
        String token = getToken(ans[0], ans[1]);
        // Create a request
        Request request = new Request.Builder()
                .url("http://open.bigmodel.cn/api/paas/v3/model-api/chatglm_lite/sse-invoke")
                .post(RequestBody.create(MediaType.parse("application/json"), jsonPayload))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                .header("Accept", "text/event-stream")
                .build();

        try (Response response = client.newCall(request).execute()) {
            // Print the response code
            System.out.println("Response Code: " + response.code());

            // Print the response body
            System.out.println("Response: " + response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}
