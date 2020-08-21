package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.AccessTokenDTO;
import cn.fengylb.mycommunity.mycommunity.dto.GithubUserDTO;
import cn.fengylb.mycommunity.mycommunity.provider.GithubProvider;
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
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.url}")
    private String redirect_url;
    @GetMapping("/callback")
    public String callback(@RequestParam("code")String code,@RequestParam("state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_url);
        accessTokenDTO.setState(state);
        String accessToekn = githubProvider.getAccessToekn(accessTokenDTO);
        GithubUserDTO githubUserDTO = githubProvider.getUser(accessToekn);
        System.out.println(githubUserDTO.getName());
        return "index";
    }
}
