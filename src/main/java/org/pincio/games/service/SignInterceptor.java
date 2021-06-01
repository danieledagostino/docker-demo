package org.pincio.games.service;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SignInterceptor extends BasePathMatchInterceptor {

    private String accessKeyId;

    private String accessKeySecret;

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Override
    public Response doIntercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request newReq = request.newBuilder()
                .addHeader("x-trustifi-key", accessKeyId)
                .addHeader("x-trustifi-secret", accessKeySecret)
                .build();
        return chain.proceed(newReq);
    }
}
