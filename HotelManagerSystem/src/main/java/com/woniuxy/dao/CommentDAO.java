package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Comment;

public interface CommentDAO {
	//插入每条评论信息
	@Insert("Insert into t_comment(username,comment_date,comment_text) values(#{username},#{comment_date},#{comment_text})")
	public boolean addcomment(Comment comment);
	
	//展示所有评论
	@Select("select * from t_comment")
	public List<Comment> showAllComment();
	
	//根据commentId删除评论
	@Delete("delete from t_comment where comment_id=#{comment_id}")
	public boolean removeComment(Integer comment_id);
	
	//更新点赞数量
	@Update("update t_comment set comment_praise=#{arg0} where comment_id=#{arg1}")
	public boolean updateCommentPraise(Integer comment_praise,Integer comment_id);
}
