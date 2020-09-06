package cn.fengylb.mycommunity.mycommunity.mapper;

import cn.fengylb.mycommunity.mycommunity.dto.Question;

public interface QuestionExtMapper {
    int incViewCount(Question question);

    int incCommentCount(Question question);
}