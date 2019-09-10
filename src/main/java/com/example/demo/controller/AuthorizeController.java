package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String id;
    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.redirect.uri}")
    private String uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state)
    {
        AccessTokenDTO accesstokenDTO=new AccessTokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_url(uri);
        accesstokenDTO.setClient_id(id);
        accesstokenDTO.setClient_secret(secret);
        githubProvider.getAccessToken(accesstokenDTO);
        return "index";
    }

}
