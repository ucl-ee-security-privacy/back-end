package ucl.ee.sec.service;


import ucl.ee.sec.entity.User;

public interface UserService {

    //根据用户名密码获取用户
    User getUser(String username, String password);

//    //获取用户名列表
//    Integer getUserId();
//
//    String getUserName();

}
