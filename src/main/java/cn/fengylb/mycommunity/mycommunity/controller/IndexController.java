package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.PaginationDTO;
import cn.fengylb.mycommunity.mycommunity.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "size",defaultValue = "5")Integer size,
                        @RequestParam(name = "search", required = false) String search){
        PaginationDTO paginationDTO = questionService.list(search,page,size);
        model.addAttribute("pagination",paginationDTO);
        model.addAttribute("search", search);
        log.info("这是一个日志{}","啊啊啊啊");
        return "index";
    }
}
