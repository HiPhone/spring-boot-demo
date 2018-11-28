package org.hiphone.mybatis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hiphone.mybatis.entitys.UserDTO;
import org.hiphone.mybatis.model.ResultMessage;
import org.hiphone.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HiPhone
 */
@Slf4j
@RestController
@Api(value = "UserController", description = "用户操作的接口类")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/users")
    @ApiOperation(value = "获取所有用户的信息", notes = "返回数据库中所有用户数据")
    public ResultMessage listAllUsers() {
        log.info("There is a request for all users' data...");
        return userService.listAllUserData();
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    @ApiOperation(value = "获取单个用户的信息", notes = "根据传入用户id获取该用户的信息")
    public ResultMessage getUserById(@PathVariable Long id) {
        log.info("There is a request for getting user data by id, the id is {}", id);
        return userService.getUserById(id);
    }

    @ResponseBody
    @PostMapping("/users")
    @ApiOperation(value = "创建新用户信息", notes = "根据传入用户信息插入数据库")
    public ResultMessage insertNewUser(@RequestBody UserDTO user) {
        log.info("Start to insert new user data into database...");
        return userService.insertNewUser(user);
    }

    @ResponseBody
    @DeleteMapping("/users/{id}")
    @ApiOperation(value = "删除单个用户信息", notes = "根据传入用户id删除该用户的信息")
    public ResultMessage deleteUserById(@PathVariable Long id) {
        log.info("There is a request for deleting user data by id, the id is {}", id);
        return userService.deleteUserById(id);
    }

    @ResponseBody
    @PutMapping("/users")
    @ApiOperation(value = "更新单个用户的信息", notes = "根据传入用户信息更新数据库数据")
    public ResultMessage updateUserById(@RequestBody UserDTO user) {
        log.info("There is a request for updating user data by id, the id is {}", user.getId());
        return userService.updateUserById(user);
    }
}
