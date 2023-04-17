package ucl.ee.sec.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucl.ee.sec.entity.Comment;
import ucl.ee.sec.entity.User;
import ucl.ee.sec.mapper.CommentMapper;

import java.sql.Timestamp;
import java.util.List;

@Controller
@Slf4j
public class CommentController {


    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/comment/comment_list")
    @ResponseBody
    public List<Comment> getCommentList(HttpSession session) {
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") != null) {
            return commentMapper.getTopComment();
        }
        return null;
    }

    @PostMapping("/comment/submit")
    @ResponseBody
    public int submitComment(@RequestBody JSObject submitObject, HttpSession session) {
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Comment comment = new Comment();
            comment.setUserId(user.getId());
            comment.setContent((String) submitObject.getMember("content"));
            comment.setPostTime(Timestamp.valueOf((String) submitObject.getMember("posttime")));
            commentMapper.insertComment(comment);
            return 1;
        }
        return 0;
    }

    @RequestMapping("/comment")
    public String commentPage() {
        return "comment";
    }


}
