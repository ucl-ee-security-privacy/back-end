package ucl.ee.vulnerable.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucl.ee.vulnerable.entity.Comment;
import ucl.ee.vulnerable.entity.User;
import ucl.ee.vulnerable.mapper.CommentMapper;

import java.sql.Timestamp;

import java.util.List;

@RestController
@Slf4j
public class CommentController {


    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/comment/comment_list")
    public List<Comment> getCommentList(HttpSession session) {
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") != null) {
            return commentMapper.getTopComment();
        }
        return null;
    }


    @PostMapping("/comment/submit")
    public int submitComment(@RequestParam("content") String submitContent, HttpSession session) {
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Comment comment = new Comment();
            comment.setUserid(user.getUserid());
            comment.setContent(submitContent);
            comment.setPosttime(new Timestamp(System.currentTimeMillis()));
            commentMapper.insertComment(comment);
            return 1;
        }
        return 0;
    }


}
