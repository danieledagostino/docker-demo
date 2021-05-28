package org.pincio.games.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UploadImageProfileTest {

    final String USER_SUBSCRIPTION =
            "{\n" +
                    "    \"firstName\" : \"Daniele\",\n" +
                    "    \"lastName\" : \"D'Agostino\",\n" +
                    "    \"password\" : \"123\",\n" +
                    "    \"matchingPassword\" : \"123\",\n" +
                    "    \"email\" : \"test@pincio.it\"\n" +
                    "}";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void uploadImageProfileAtSubscription() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "Hello, World!".getBytes()
        );


        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/api/public/subscribe")
                .file(file)
                .param("user", USER_SUBSCRIPTION))
                .andExpect(status().isOk());
    }
}
