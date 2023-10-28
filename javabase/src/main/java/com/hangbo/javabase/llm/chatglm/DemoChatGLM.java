package com.hangbo.javabase.llm.chatglm;

import com.zhipu.oapi.ClientV3;
import com.zhipu.oapi.core.httpclient.ApacheHttpClientTransport;

/**
 * @author quanhangbo
 * @date 2023/10/28 22:47
 */
public class DemoChatGLM {

    public static void main(String[] args) {
        ClientV3 client = new ClientV3.Builder("")
                .httpTransport(new ApacheHttpClientTransport())// 传输层默认使用okhttpclient，如果需要修改位其他http client（如apache），可以在这里指定。注意apache不支持sse调用
                .build();
    }
}
