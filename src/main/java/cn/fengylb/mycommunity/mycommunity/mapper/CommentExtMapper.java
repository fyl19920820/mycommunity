package cn.fengylb.mycommunity.mycommunity.mapper;

import cn.fengylb.mycommunity.mycommunity.dto.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}