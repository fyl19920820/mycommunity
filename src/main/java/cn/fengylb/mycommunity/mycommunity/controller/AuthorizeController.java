package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.AccessTokenDTO;
import cn.fengylb.mycommunity.mycommunity.dto.GithubUserDTO;
import cn.fengylb.mycommunity.mycommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam("code")String code,@RequestParam("state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("e42bd7e6ba6ffd4cdcc8");
        accessTokenDTO.setClient_secret("95fa927a9259b15f8af7c79b97fa77c4179b188e");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToekn = githubProvider.getAccessToekn(accessTokenDTO);
        GithubUserDTO githubUserDTO = githubProvider.getUser(accessToekn);
        System.out.println(githubUserDTO.getName());
        return "index";
    }
}
