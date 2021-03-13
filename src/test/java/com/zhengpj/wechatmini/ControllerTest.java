package com.zhengpj.wechatmini;

import com.zhengpj.wechatmini.controller.UserController;
import com.zhengpj.wechatmini.entity.Moment;
import com.zhengpj.wechatmini.entity.User;
import com.zhengpj.wechatmini.service.MomentService;
import com.zhengpj.wechatmini.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;

import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @author zhengpeijian
 * @date 2021/3/9 17:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    UserService userService;

    @Autowired
    MomentService momentService;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void testContexLoads() {
        Assert.assertThat(userController, notNullValue());
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(120820);
        user.setSex("1");
        user.setNickname("zpj");
        user.setSignature("keep running");
        assert userService.addUser(user);
    }

    @Test
    public void testAddMoment(){
        Moment moment = new Moment();
        moment.setId(1);
        moment.setContent("testing");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("mills="+System.currentTimeMillis()+", timestamp="+timestamp.toString());
        moment.setTimestamp(timestamp);
        moment.setUserid(120820);
        assert momentService.addMoment(moment);
    }
}
