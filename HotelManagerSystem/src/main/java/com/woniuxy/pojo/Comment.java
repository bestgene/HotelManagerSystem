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
	//评论id
	private Integer comment_id;
	//用户名
	private String username;
	//评论时间
	private String comment_date;
	//评论内容
	private String comment_text;
	//点赞数量
	private Integer comment_praise;
	public Comment(Integer comment_id, String username, String comment_date, String comment_text,
			Integer comment_praise) {
		super();
		this.comment_id = comment_id;
		this.username = username;
		this.comment_date = comment_date;
		this.comment_text = comment_text;
		this.comment_praise = comment_praise;
	}
	public Comment() {
		super();
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
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
	public Integer getComment_praise() {
		return comment_praise;
	}
	public void setComment_praise(Integer comment_praise) {
		this.comment_praise = comment_praise;
	}
	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", username=" + username + ", comment_date=" + comment_date
				+ ", comment_text=" + comment_text + ", comment_praise=" + comment_praise + "]";
	}
	
	
	
	
}
