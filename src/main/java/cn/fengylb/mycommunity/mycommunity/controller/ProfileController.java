package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.PaginationDTO;
import cn.fengylb.mycommunity.mycommunity.dto.User;
import cn.fengylb.mycommunity.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{section}")
    public String profile(HttpServletRequest request, Model model,
                          @RequestParam(value = "page",defaultValue = "1")Integer page,
                          @RequestParam(value = "size",defaultValue = "5")Integer size,
                          @PathVariable(name = "section")String section){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        PaginationDTO paginationDTO = questionService.listByUserAccountId(Long.valueOf(user.getAccountId()),page,size);
        model.addAttribute("pagination",paginationDTO);
        model.addAttribute("section",section);
        if (section.equals("questions")){
            model.addAttribute("sectionName","我的提问");
        }else if (section.equals("replies")){
            model.addAttribute("sectionName","我的回复");
        }
        return "profile";
    }
}
