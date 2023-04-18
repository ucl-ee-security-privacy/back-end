package ucl.ee.vulnerable.mapper;

import org.apache.ibatis.annotations.*;
import ucl.ee.vulnerable.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(userid,username,password) VALUES (${userid},${username},${password});")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    @Deprecated
    int insertUser(User user);

    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE userid=${userid};")
    User getUserById(@Param("userid") int userid);

    //用@Results来替换原xml配置的<resultMap />
    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE username=${username};")
    User getUserByUsername(@Param("username") String username);

    @ResultType(Integer.class)
    @Select("SELECT userid FROM user WHERE username=${username};")
    Integer getUserIdByUsername(@Param("username") String username);

    //如果已经定义过@Results，可以直接用@ResultMap来调取
    @Results(
            id = "userList", value = {
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    }
    )
    @Select("SELECT * FROM user ORDER BY ${order_by_sql};")
    @Deprecated
    List<User> getUserListOrderly(@Param("order_by_sql") String order_by_sql);

    @Deprecated
    @Delete("DELETE FROM user WHERE userid=${userid};")
    int deleteUserById(int userid);

    @Deprecated
    @Update("UPDATE user SET username=${username} where userid=${userid};")
    int updateUsernameById(@Param("username") String username, @Param("userid") int userid);

    @ResultType(User.class)
    @Select("SELECT * FROM user WHERE username=${username} and password=${password};")
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}