package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Comment;
import com.woniuxy.pojo.User;
import com.woniuxy.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	@PostMapping("/addcommentservlet")
	@ResponseBody
	public String addcommentservlet(Comment comment,HttpSession session){
		Object obj = session.getAttribute("user");
		if (obj!=null){
			HashMap<String, User> users = (HashMap<String, User>)obj ;
			User user = users.get("loginuser");
			comment.setUsername("移动用户:"+user.getUser_id());
		}else{
			comment.setUsername("游客");
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
	
	@RequestMapping("/removeComment")
	@ResponseBody
	public String deleteComment(Integer comment_id){
		boolean removeComment = commentService.removeComment(comment_id);
		if (removeComment){
			return "删除成功";
		}
		return "删除失败";
	}
	
	@RequestMapping("/updatePraise")
	@ResponseBody
	public String updateCommentPraise(Integer comment_praise,Integer comment_id){
		boolean updateCommentPraise = commentService.updateCommentPraise(comment_praise,comment_id);
		if (updateCommentPraise){
			return "操作成功";
		}
		return "操作失败";
	}
}
