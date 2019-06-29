package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Comment;

public interface CommentService {
	
	public boolean addcomment(Comment comment);
	
	public List<Comment> showAllComment();
	
	public boolean removeComment(Integer comment_id);
	
	public boolean updateCommentPraise(Integer comment_praise,Integer comment_id);
}
