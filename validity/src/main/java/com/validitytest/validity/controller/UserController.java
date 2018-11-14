package com.validitytest.validity.controller;


import com.validitytest.validity.ValidityException;
import com.validitytest.validity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/duplicateUsers")
    @ResponseBody
    public List getDuplicateUsers() throws ValidityException {
        return userService.getDuplicateUserList();
    }

    @RequestMapping("/noneDuplicateUsers")
    @ResponseBody
    public List getNoneDuplicateUsers() throws ValidityException {
        return userService.getNoneDuplicateUserList();
    }

    @RequestMapping("/getAllUsers")
    @ResponseBody
    public List getAllUsers() throws ValidityException {
        List<List> allList = null;
        allList.add(userService.getDuplicateUserList());
        allList.add(userService.getNoneDuplicateUserList());
        return allList;
    }






}
