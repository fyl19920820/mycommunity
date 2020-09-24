package cn.fengylb.mycommunity.mycommunity.mapper;

import cn.fengylb.mycommunity.mycommunity.dto.Question;
import cn.fengylb.mycommunity.mycommunity.dto.QuestionQueryDTO;

import java.util.List;

public interface QuestionExtMapper {
    int incViewCount(Question question);

    int incCommentCount(Question question);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}