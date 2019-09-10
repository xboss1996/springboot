package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state,
                           HttpServletRequest request)
    {
        AccessTokenDTO accesstokenDTO=new AccessTokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_url(uri);
        accesstokenDTO.setClient_id(id);
        accesstokenDTO.setClient_secret(secret);
        String accessToken = githubProvider.getAccessToken(accesstokenDTO);
        GithubUser user =githubProvider.getUser(accessToken);
        if(user!=null){
            //登录成功，写cookie和Session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登录失败，重新登录
            return "redirect:/";
        }

    }

}
