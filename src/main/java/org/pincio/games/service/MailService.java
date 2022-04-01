package org.pincio.games.service;

import freemarker.template.*;
import org.pincio.games.dto.Mail;
import org.pincio.games.dto.PersonDto;
import org.pincio.games.dto.Recipient;
import org.pincio.games.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://api.trustifi.com/

@Service
public class MailService {

    @Autowired
    private TrustifiApi api;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    public String sendTeamleaderRequest() {
        return "";
    }

    public String sendSubscriptionValidation(PersonDto dto) throws IOException, TemplateException {
        Template freemarkerTemplate = freemarkerConfigurer.getConfiguration()
                .getTemplate("subscription_validation.ftl");
        Map<String, Object> templateModel = new HashMap<String, Object>();

        String hostname = InetAddress.getLoopbackAddress().getHostName();
        String port = "";
        if ("localhost".equals(hostname)) {
            port = ":8080";
        }
        templateModel.put("tokenValidationUrl", hostname + port + "/api/public/tokenValidation/");
        templateModel.put("user", dto);

        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);


        Mail mail = new Mail();

        mail.setEmail("principelele@gmail.com");
        mail.setName("Admin");
        mail.setTitle("Pincio-games: mail confirmation request");
        mail.setHtml(htmlBody);

        Recipient recipient = new Recipient();
        recipient.setEmail(dto.getEmail());
        recipient.setName(dto.getName()+" "+dto.getSurname());

        mail.setRecipients(Arrays.asList(recipient));

        String response = ""; //api.send(mail);

        return response;
    }

    public String sendInfoToAll(){

        return "";
    }
}
