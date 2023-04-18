package ucl.ee.sec.mapper;

import org.apache.ibatis.annotations.*;
import ucl.ee.sec.entity.Comment;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO webcomment VALUES ( null,${userid},${posttime},${content});")
    int insertComment(Comment comment);

    @ResultType(Comment.class)
    @Select("SELECT * FROM webcomment WHERE commentid=${commentid};")
    Comment getCommentById(@Param("commentid") int commentid);

    @ResultType(Comment.class)
    @Select("SELECT * FROM webcomment LIMIT ${start},${end};")
    List<Comment> getComment(@Param("start") int start, @Param("end") int end);

    @ResultType(Comment.class)
    @Select("SELECT * FROM webcomment LIMIT 0,100;")
    List<Comment> getTopComment();

    @ResultType(Timestamp.class)
    @Select("SELECT posttime FROM webcomment WHERE userid=${userid};")
    Timestamp getCommentNumByCommentname(@Param("userid") String userid);

    //如果已经定义过@Results，可以直接用@ResultMap来调取
    @ResultType(Comment.class)
    @Select("SELECT * FROM webcomment ORDER BY ${order_by_sql};")
    List<Comment> getCommentListOrderly(@Param("order_by_sql") String order_by_sql);

    @Deprecated
    @Delete("DELETE FROM webcomment WHERE commentid=${commentid};")
    int deleteCommentById(int commentid);

    @Update("UPDATE webcomment SET posttime=${posttime} where commentid=${commentid};")
    int updateCommentNumById(@Param("posttime") String posttime, @Param("commentid") int commentid);

}
