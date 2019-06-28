package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Comment;
import com.woniuxy.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	@PostMapping("/addcommentservlet")
	@ResponseBody
	public String addcommentservlet(Comment comment){
		Session session = SecurityUtils.getSubject().getSession();
		Integer userid = (Integer) session.getAttribute("user_id");
		if(userid==null){
			comment.setUsername("游客");
		}else{
			comment.setUsername("移动用户:"+userid);
		}
		String result = "添加评论失败";
		boolean addcomment = commentService.addcomment(comment);
		if (addcomment){
			result = "添加评论成功";
		}
		return result;
	}
	
	@GetMapping("/showcommentservlet")
	@ResponseBody
	public List<Comment> showcommentservlet(){
		return commentService.showAllComment();
	}
}
