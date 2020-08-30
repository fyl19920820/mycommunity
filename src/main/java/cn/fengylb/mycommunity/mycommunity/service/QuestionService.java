package cn.fengylb.mycommunity.mycommunity.service;

import cn.fengylb.mycommunity.mycommunity.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size){
        if (page < 1){
            page = 1;
        }
        if (size < 1){
            size = 5;
        }
        int offset = (page -1)*size;
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.stream().forEach(question -> {
            User user = userMapper.findById(question.getCreator().intValue());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        });
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestions(questionDTOS);
        Integer questionsCount = questionMapper.listCount();
        paginationDTO.setPagination(page,size,questionsCount);
        return paginationDTO;
    }

    public PaginationDTO listByUserAccountId(Long accountId, Integer page, Integer size) {
        if (page < 1){
            page = 1;
        }
        if (size < 1){
            size = 5;
        }
        int offset = (page -1)*size;
        List<Question> questions = questionMapper.listByUserAccountId(accountId,offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.stream().forEach(question -> {
            User user = userMapper.findById(question.getCreator().intValue());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        });
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestions(questionDTOS);
        Integer questionsCount = questionMapper.listCountByUserAccountId(accountId);
        paginationDTO.setPagination(page,size,questionsCount);
        return paginationDTO;
    }

    public QuestionDTO findById(Long id) {
        Question  question = questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator().intValue());
        questionDTO.setUser(user);
        return  questionDTO;
    }
}
