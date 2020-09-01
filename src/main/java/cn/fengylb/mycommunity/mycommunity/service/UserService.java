package cn.fengylb.mycommunity.mycommunity.service;

import cn.fengylb.mycommunity.mycommunity.dto.User;
import cn.fengylb.mycommunity.mycommunity.dto.UserExample;
import cn.fengylb.mycommunity.mycommunity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() == 0){
            userMapper.insert(user);
        }else if (users.size() == 1){
            User dbUser = users.get(0);
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            dbUser.setGmtModified(System.currentTimeMillis());
            userMapper.updateByPrimaryKey(dbUser);
        }
    }
}
