package cn.fengylb.mycommunity.mycommunity.controller;

import cn.fengylb.mycommunity.mycommunity.dto.Comment;
import cn.fengylb.mycommunity.mycommunity.dto.CommentDTO;
import cn.fengylb.mycommunity.mycommunity.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @PostMapping("/comment")
    @ResponseBody
    public  Object comment(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(225);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        return null;
    }
}
