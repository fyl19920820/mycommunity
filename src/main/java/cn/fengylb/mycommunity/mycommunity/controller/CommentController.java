package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.Comment;
import cn.fengylb.mycommunity.mycommunity.dto.CommentDTO;
import cn.fengylb.mycommunity.mycommunity.dto.ResultDTO;
import cn.fengylb.mycommunity.mycommunity.dto.User;
import cn.fengylb.mycommunity.mycommunity.exception.CustomizeErrorCode;
import cn.fengylb.mycommunity.mycommunity.exception.CustomizeException;
import cn.fengylb.mycommunity.mycommunity.mapper.CommentMapper;
import cn.fengylb.mycommunity.mycommunity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/comment")
    @ResponseBody
    public  ResultDTO comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
