package com.woniuxy.pojo;

import java.io.Serializable;

public class Comment implements Serializable{
	//评论id
	private Integer comment_id;
	//用户
	private User user;
	//房间id
	private House house;
	//该用户所评论的具体内容
	private String comment_content;
	private Integer flag;

	@Override
	public String toString() {
		return "Comment{" +
				"comment_id=" + comment_id +
				", user=" + user +
				", house=" + house +
				", comment_content='" + comment_content + '\'' +
				", flag=" + flag +
				'}';
	}

	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	
}
