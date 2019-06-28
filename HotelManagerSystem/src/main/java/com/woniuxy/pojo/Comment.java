package com.woniuxy.pojo;

import java.io.Serializable;

public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer CommentId;
	private String username;
	private String comment_date;
	private String comment_text;
	public Comment(Integer commentId, String username, String comment_date, String comment_text) {
		super();
		CommentId = commentId;
		this.username = username;
		this.comment_date = comment_date;
		this.comment_text = comment_text;
	}
	public Comment() {
		super();
	}
	@Override
	public String toString() {
		return "Comment [CommentId=" + CommentId + ", username=" + username + ", comment_date=" + comment_date
				+ ", comment_text=" + comment_text + "]";
	}
	public Integer getCommentId() {
		return CommentId;
	}
	public void setCommentId(Integer commentId) {
		CommentId = commentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	
	
}
