package com.hangbo.javabase.llm.chatglm;

import com.zhipu.oapi.ClientV3;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.core.httpclient.OkHttpTransport;
import com.zhipu.oapi.service.v3.ModelApiRequest;
import com.zhipu.oapi.service.v3.ModelConstants;
import com.zhipu.oapi.service.v3.StandardEventSourceListener;
import okhttp3.OkHttpClient;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


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


    public static void main(String[] args) {
        testSseCall();
    }

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
        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
        modelApiRequest.setRequestId(requestId);
        return modelApiRequest;
    }

    public static void testSseCall() {
        ModelApiRequest modelApiRequest =
        client.invokeModelApi()
    }

    private static ClientV3 client = new ClientV3.Builder(Contant.API_KEY, Contant.API_SECRET)
            .httpTransport(new OkHttpTransport(getInstance()))
            .build();


}
