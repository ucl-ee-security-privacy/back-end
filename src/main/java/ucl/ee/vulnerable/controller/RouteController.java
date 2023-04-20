package ucl.ee.vulnerable.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Deprecated
@Controller
@Slf4j
public class RouteController {
    @RequestMapping("/login_page")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/comment_page")
    public String commentPage() {
        return "comment";
    }

    @RequestMapping("admin_modify_page")
    public String adminModifyPage() {
        return "adminModify";
    }

    @RequestMapping("/admin_login_page")
    public String adminloginPage() {

        return "admin_login";
    }

    @RequestMapping({"/", "index"})
    public String indexPage() {
        return "index";
    }
}
