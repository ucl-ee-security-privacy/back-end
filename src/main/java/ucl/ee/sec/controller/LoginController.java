package ucl.ee.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ucl.ee.sec.entity.User;
import ucl.ee.sec.mapper.UserMapper;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userMapper.getUserById(id);
    }


    @GetMapping("/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userMapper.getUserByUsername(username);
    }

    @GetMapping("/getUserListOrderly/{order_by_sql}")
    public List<User> getUserListOrderly(@PathVariable("order_by_sql") String order_by_sql) {
        return userMapper.getUserListOrderly(order_by_sql);
    }

    @GetMapping("/deleteUserById/{id}")
    public int deleteUserById(@PathVariable("id") int id) {
        return userMapper.deleteUserById(id);
    }

    @GetMapping("/updateUsernameById/{username}/{id}")
    public long updateUsernameById(@PathVariable("username") String username,
                                   @PathVariable("id") int id) {
        userMapper.updateUsernameById(username, id);
        return id;
    }

}
