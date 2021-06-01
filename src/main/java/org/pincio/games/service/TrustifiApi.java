package org.pincio.games.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import org.pincio.games.dto.Mail;
import retrofit2.http.*;

@RetrofitClient(baseUrl = "${mail.service.host}/api/i/v1/")
@Sign(accessKeyId = "${mail.service.key}", accessKeySecret = "${mail.service.secret}")
public interface TrustifiApi {

    @POST("email")
    String send(@Body Mail mail);
}
