package cn.fengylb.mycommunity.mycommunity.service;

import cn.fengylb.mycommunity.mycommunity.dto.*;
import cn.fengylb.mycommunity.mycommunity.exception.CustomizeException;
import cn.fengylb.mycommunity.mycommunity.mapper.QuestionExtMapper;
import cn.fengylb.mycommunity.mycommunity.mapper.QuestionMapper;
import cn.fengylb.mycommunity.mycommunity.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(String search,Integer page, Integer size){
        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        if (page < 1){
            page = 1;
        }
        if (size < 1){
            size = 5;
        }
        int offset = (page -1)*size;

        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);

        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.stream().forEach(question -> {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            if (users != null && users.size() == 1){
                questionDTO.setUser(users.get(0));
            }
            questionDTOS.add(questionDTO);
        });
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(questionDTOS);
        paginationDTO.setPagination(page,size,totalCount);
        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        if (page < 1){
            page = 1;
        }
        if (size < 1){
            size = 5;
        }
        int offset = (page -1)*size;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.stream().forEach(question -> {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            if (users != null && users.size() == 1){
                questionDTO.setUser(users.get(0));
            }
            questionDTOS.add(questionDTO);
        });
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(questionDTOS);
        Integer questionsCount = (int)questionMapper.countByExample(questionExample);
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
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andAccountIdEqualTo(String.valueOf(accountId));
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.stream().forEach(question -> {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            if (users != null && users.size() == 1){
                questionDTO.setUser(users.get(0));
            }
            questionDTOS.add(questionDTO);
        });
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(questionDTOS);
        Integer questionsCount = (int)questionMapper.countByExample(questionExample);
        paginationDTO.setPagination(page,size,questionsCount);
        return paginationDTO;
    }

    public QuestionDTO findById(Long id) {
        Question  question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException("问题已不存在");
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() == 1){
            questionDTO.setUser(users.get(0));
        }
        return  questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            Question question1 = new Question();
            question1.setDescription(question.getDescription());
            question1.setId(question.getId());
            question1.setTag(question.getTag());
            question1.setTitle(question.getTitle());
            question1.setGmtModified(System.currentTimeMillis());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question1,questionExample);
        }
    }

    public void incViewCount(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incViewCount(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
