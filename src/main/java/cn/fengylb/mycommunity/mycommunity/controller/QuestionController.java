package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.CommentDTO;
import cn.fengylb.mycommunity.mycommunity.dto.QuestionDTO;
import cn.fengylb.mycommunity.mycommunity.enums.CommentTypeEnum;
import cn.fengylb.mycommunity.mycommunity.service.CommentService;
import cn.fengylb.mycommunity.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    private String question(@PathVariable(name = "id")Long id, Model model){
        QuestionDTO questionDTO =  questionService.findById(id);
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id,CommentTypeEnum.QUESTION);
        questionService.incViewCount(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOList);
        return "question";
    }
}
