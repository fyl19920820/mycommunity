package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.Question;
import cn.fengylb.mycommunity.mycommunity.dto.QuestionDTO;
import cn.fengylb.mycommunity.mycommunity.dto.User;
import cn.fengylb.mycommunity.mycommunity.mapper.QuestionMapper;
import cn.fengylb.mycommunity.mycommunity.mapper.UserMapper;
import cn.fengylb.mycommunity.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title.trim().equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        ;
        if (description == null || description.trim().equals("")) {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null || tag.trim().equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setAccountId(user.getAccountId());
        question.setViewCount(0);
        question.setCommentCount(0);
        question.setLikeCount(0);
        questionService.createOrUpdate(question);
        return "redirect:/";

    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id")Long id,Model model){
        QuestionDTO questionDTO = questionService.findById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id",questionDTO.getId());
        return "publish";
    }
}
