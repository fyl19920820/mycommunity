package cn.fengylb.mycommunity.mycommunity.service;

import cn.fengylb.mycommunity.mycommunity.dto.*;
import cn.fengylb.mycommunity.mycommunity.enums.CommentTypeEnum;
import cn.fengylb.mycommunity.mycommunity.exception.CustomizeErrorCode;
import cn.fengylb.mycommunity.mycommunity.exception.CustomizeException;
import cn.fengylb.mycommunity.mycommunity.mapper.CommentMapper;
import cn.fengylb.mycommunity.mycommunity.mapper.QuestionExtMapper;
import cn.fengylb.mycommunity.mycommunity.mapper.QuestionMapper;
import cn.fengylb.mycommunity.mycommunity.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT)){
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (parentComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    public List<CommentDTO> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if (commentList == null || commentList.size() == 0){
            return new ArrayList<>();
        }
        Set<Long> userIdSet = commentList.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        UserExample userExample = new UserExample();
        List<Long> userIdList = new ArrayList<>();
        userIdList.addAll(userIdSet);
        userExample.createCriteria().andIdIn(userIdList);
        List<User> userList = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDTO> commentDTOList = commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOList;
    }
}
