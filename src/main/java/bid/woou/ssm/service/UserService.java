package bid.woou.ssm.service;

import bid.woou.ssm.mapper.UserMapper;
import bid.woou.ssm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by cc on 2017/7/3.
 */

@Service
public class UserService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    public void addUser(User user){
        userMapper.insert(user);
    }
}
