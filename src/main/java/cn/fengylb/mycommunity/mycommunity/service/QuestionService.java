package cn.fengylb.mycommunity.mycommunity.service;

import cn.fengylb.mycommunity.mycommunity.dto.Question;
import cn.fengylb.mycommunity.mycommunity.dto.QuestionDTO;
import cn.fengylb.mycommunity.mycommunity.dto.User;
import cn.fengylb.mycommunity.mycommunity.mapper.QuestionMapper;
import cn.fengylb.mycommunity.mycommunity.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.stream().forEach(question -> {
            User user = userMapper.findById(question.getCreator().intValue());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        });
        return questionDTOS;
    }
}
