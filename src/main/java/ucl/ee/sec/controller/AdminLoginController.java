package ucl.ee.sec.controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ucl.ee.sec.entity.User;
import ucl.ee.sec.service.UserService;

@Controller
@Slf4j
public class AdminLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin_login")
    public String loginPage() {
        return "admin_login";
    }

    @GetMapping("/admin_login")
    @ResponseBody
    public JSONObject login(@RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password,
                            HttpSession session) {

        //验证@RestController修饰的class的对象 会被spring容器作为单例的Bean来管理
        log.info("object: " + this.toString());
        log.info("thread: " + Thread.currentThread().getId());
        JSONObject uidJSON = new JSONObject();
        //参数校验
        if (username.length() < 2 || username.length() > 20
                || password.length() < 2 || password.length() > 30) {
            uidJSON.put("status", 0);
            uidJSON.put("userid", null);
            return uidJSON;
        }

        //请求转发，会话管理
        try {
            session.setAttribute("user", userService.getUser(username, password));
        } catch (RuntimeException e) {
            uidJSON.put("status", 0);
            uidJSON.put("userid", null);
            return uidJSON;
        }
        uidJSON.put("status", 1);
        User uidSession = (User) session.getAttribute("user");
        uidJSON.put("userid", uidSession.getId());
        return uidJSON;
    }


    @GetMapping("/admin_logout")
    @ResponseBody
    public String logout(HttpSession session) {
        //注销session（在服务器里删除该session）
        session.invalidate();
        return "Logout successfully";
    }


}

