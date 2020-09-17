package cn.fengylb.mycommunity.mycommunity.mapper;

import cn.fengylb.mycommunity.mycommunity.dto.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incViewCount(Question question);

    int incCommentCount(Question question);

    List<Question> selectRelated(Question question);
}