package com.zhengpj.wechatmini.controller;

import com.zhengpj.wechatmini.service.impl.ContactsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhengpeijian
 * @date 2021/3/26 18:57
 */
@RestController
@RequestMapping("api")

@Api(value = "ContactController|好友表控制器")
public class ContactController {
    @Autowired
    ContactsService contactsService;

    @GetMapping("contact")
    @ApiOperation(value = "根据用户id, 好友id建立好友关系")
    public boolean addContact(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "friendId", required = true) int friendId) {
        return contactsService.addContact(userId, friendId);
    }

    @DeleteMapping("contact")
    @ApiOperation(value = "根据用户id, 好友id删除好友关系")
    public boolean deleteContact(@RequestParam(value = "userId", required = true) int userId, @RequestParam(value = "friendId", required = true) int friendId) {
        return contactsService.deleteContact(userId, friendId);
    }

    @PostMapping("contact")
    @ApiOperation(value = "根据用户id, 好友id更新好友关系")
    public boolean updateContact(@RequestParam(value = "userId", required = true) int userId,
                                 @RequestParam(value = "friendId", required = true) int friendId,
                                 @RequestParam(value = "contact", required = true) int contact) {
        return contactsService.updateContact(userId, friendId, contact);
    }
}
