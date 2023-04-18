package ucl.ee.vulnerable.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RouteController {
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/comment")
    public String commentPage() {
        return "comment";
    }

    @RequestMapping("admin_modify")
    public String adminModifyPage() {
        return "adminModify";
    }

    @RequestMapping("/admin_login")
    public String adminloginPage() {

        return "admin_login";
    }

}
