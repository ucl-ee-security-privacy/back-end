package ucl.ee.sec.mapper;

import org.apache.ibatis.annotations.*;
import ucl.ee.sec.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO webcomment(userid,posttime,content) VALUES (${userid},${posttime},${content});")
    @Options(useGeneratedKeys = true, keyProperty = "commentid")
    int insertComment(Comment comment);

    @ResultType(Comment.class)
    @Select("SELECT * FROM webcomment WHERE commentid=${commentid};")
    Comment getCommentById(@Param("commentid") int commentid);

    @Results(
            id = "commentList", value = {
            @Result(property = "commentid", column = "commentid"),
            @Result(property = "userid", column = "userid"),
            @Result(property = "posttime", column = "posttime"),
            @Result(property = "content", column = "content"),
    }
    )
    @Select("SELECT * FROM webcomment LIMIT ${start},${end};")
    Comment getComment(@Param("start") int start, @Param("end") int end);

    @ResultMap("commentList")
    @Select("SELECT * FROM webcomment LIMIT 0,100;")
    Comment getTopComment();

    @ResultType(Integer.class)
    @Select("SELECT posttime FROM webcomment WHERE userid=${userid};")
    Integer getCommentNumByCommentname(@Param("userid") String userid);

    //如果已经定义过@Results，可以直接用@ResultMap来调取
    @ResultMap("commentList")
    @Select("SELECT * FROM webcomment ORDER BY ${order_by_sql};")
    List<Comment> getCommentListOrderly(@Param("order_by_sql") String order_by_sql);

    @Deprecated
    @Delete("DELETE FROM webcomment WHERE commentid=${commentid};")
    int deleteCommentById(int commentid);

    @Update("UPDATE webcomment SET posttime=${posttime} where commentid=${commentid};")
    int updateCommentNumById(@Param("posttime") String posttime, @Param("commentid") int commentid);

}
