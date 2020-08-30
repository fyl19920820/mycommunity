package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.AccessTokenDTO;
import cn.fengylb.mycommunity.mycommunity.dto.GithubUserDTO;
import cn.fengylb.mycommunity.mycommunity.dto.User;
import cn.fengylb.mycommunity.mycommunity.mapper.UserMapper;
import cn.fengylb.mycommunity.mycommunity.provider.GithubProvider;
import cn.fengylb.mycommunity.mycommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    @Autowired
    private UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam("code")String code, @RequestParam("state")String state, HttpServletRequest request, HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_url);
        accessTokenDTO.setState(state);
        String accessToekn = githubProvider.getAccessToekn(accessTokenDTO);
        GithubUserDTO githubUserDTO = githubProvider.getUser(accessToekn);
        //System.out.println(githubUserDTO.getName());
        if (githubUserDTO != null){
            User user  = new User();
            user.setAccountId(String.valueOf(githubUserDTO.getId()));
            user.setName(githubUserDTO.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUserDTO.getAvatarUrl());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            //request.getSession().setAttribute("user",githubUserDTO);
        }else {

        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/";
    }
}
