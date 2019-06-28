package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Comment;

public interface CommentDAO {
	//插入每条评论信息
	@Insert("Insert into t_comment(username,comment_date,comment_text) values(#{username},#{comment_date},#{comment_text})")
	public boolean addcomment(Comment comment);
	
	//展示所有评论
	@Select("select * from t_comment")
	public List<Comment> showAllComment();
}
