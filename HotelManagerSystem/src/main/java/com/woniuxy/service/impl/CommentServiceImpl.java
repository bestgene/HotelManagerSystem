package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.CommentDAO;
import com.woniuxy.pojo.Comment;
import com.woniuxy.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDAO commentDAO;
	
	@Override
	public boolean addcomment(Comment comment) {
		return commentDAO.addcomment(comment);
	}

	@Override
	public List<Comment> showAllComment() {
		return commentDAO.showAllComment();
	}

	@Override
	public boolean removeComment(Integer comment_id) {
		return commentDAO.removeComment(comment_id);
	}

	@Override
	public boolean updateCommentPraise(Integer comment_praise,Integer comment_id) {
		return commentDAO.updateCommentPraise(comment_praise,comment_id);
	}

}
