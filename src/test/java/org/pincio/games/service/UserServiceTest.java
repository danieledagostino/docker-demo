package org.pincio.games.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pincio.games.dto.PagePersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql({"/data.sql"})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void getAllFreeUsersTest() {
        PagePersonDto dto = userService.getAllFreeUsers(1, 2);

        assertEquals(2, dto.getPersonDtoList().size());
    }
}
