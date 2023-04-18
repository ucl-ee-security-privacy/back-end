package ucl.ee.vulnerable.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucl.ee.vulnerable.entity.User;
import ucl.ee.vulnerable.mapper.UserMapper;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
        log.info("call new UserServiceImpl()");
    }

    @Override
    public User getUser(String username, String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, password);
        if (user == null) throw new RuntimeException();
        return user;
    }

//    @Override
//    public Integer getUserId() {
//        return new userMapper.getUserIdByUsername();
//    }
//
//    @Override
//    public Integer getUserId() {
//        return new userMapper.getUserIdByUsername();
//    }
}
