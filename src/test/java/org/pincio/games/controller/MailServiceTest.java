package org.pincio.games.controller;

import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pincio.games.dto.Mail;
import org.pincio.games.dto.PersonDto;
import org.pincio.games.dto.Recipient;
import org.pincio.games.service.MailService;
import org.pincio.games.service.TrustifiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MailServiceTest {

    @Autowired
    private TrustifiApi api;

    @Autowired
    MailService mailService;

    @Test
    public void sendMailTest(){
        Mail mail = new Mail();

        mail.setEmail("principelele@gmail.com");
        mail.setName("Admin");
        mail.setTitle("test di mail");

        Recipient recipient = new Recipient();
        recipient.setEmail("principelele@gmail.com");
        recipient.setName("Daniele");

        mail.setRecipients(Arrays.asList(recipient));

        String response = api.send(mail);

    }

    @Test
    public void sendSubscriptionValidationTest() {
        PersonDto dto = new PersonDto();

        dto.setName("principelele@gmail.com");
        dto.setName("Daniele");

        try {
            mailService.sendSubscriptionValidation(dto);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
