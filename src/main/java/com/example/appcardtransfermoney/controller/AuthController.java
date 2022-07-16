package com.example.appcardtransfermoney.controller;

import com.example.appcardtransfermoney.payload.LoginDto;
import com.example.appcardtransfermoney.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    MyAuthService myAuthService;

    @PostMapping(value = "/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
        return myAuthService.loginToSystem(loginDto);
    }

    // uzpdp
    //eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZHAiLCJpYXQiOjE2NTc5NDE4MzUsImV4cCI6MTY1Nzk3NzgzNX0.9KWjY3lJAu4cE-IonvyXUz-FUx-fZtFKJVxnLmY3uNppV4JXNruPf0yLXKEARQYp6nXzXuYF-aTGRPuYpKbLJQ

    //ecma
    //eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlY21hIiwiaWF0IjoxNjU3OTQyMDIxLCJleHAiOjE2NTc5NzgwMjF9.AKBJJxnW9psPEj4heQN5yNloT4QYVH07sTEE7FcxxQ7Q52mo3hotzDOxmZsNDFL2oSA40TYqITR7MMEfm2InXQ
}