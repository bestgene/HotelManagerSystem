<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仿微博评论</title>
<link rel="stylesheet" href="css/style1.css">
<link rel="stylesheet" href="css/comment.css">
</head>
<body>
	<!--
    此评论textarea文本框部分使用的https://github.com/alexdunphy/flexText此插件
-->
	<div class="commentAll">
		<!--评论区域 begin-->
		<div class="reviewArea clearfix">
			<textarea class="content comment-input" placeholder="请输入评论&hellip;"
				onkeyup="keyUP(this)"></textarea>
			<a href="javascript:;" class="plBtn">评论</a>
		</div>
		<!--评论区域 end-->
		<!--回复区域 begin-->
		<div class="comment-show" id="aaa"></div>
	</div>
	<!--回复区域 end-->

	<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.flexText.js"></script>
	<!--textarea高度自适应-->
	<script type="text/javascript">
		$(function() {
			$('.content').flexText();
		});
	</script>
	<!--textarea限制字数-->
	<script type="text/javascript">
		function keyUP(t) {
			var len = $(t).val().length;
			if (len > 139) {
				$(t).val($(t).val().substring(0, 140));
			}
		}
	</script>



	<!--点击评论创建评论条-->
	<script type="text/javascript">
		$('.commentAll')
				.on(
						'click',
						'.plBtn',
						function() {
							var myDate = new Date();
							//获取当前年
							var year = myDate.getFullYear();
							//获取当前月
							var month = myDate.getMonth() + 1;
							//获取当前日
							var date = myDate.getDate();
							var h = myDate.getHours(); //获取当前小时数(0-23)
							var m = myDate.getMinutes(); //获取当前分钟数(0-59)
							if (m < 10)
								m = '0' + m;
							var s = myDate.getSeconds();
							if (s < 10)
								s = '0' + s;
							var now = year + '-' + month + "-" + date + " " + h
									+ ':' + m + ":" + s;
							//获取输入内容
							var oSize = $(this).siblings('.flex-text-wrap')
									.find('.comment-input').val();
							console.log(oSize);

							//动态创建评论模块
							oHtml = '<div class="comment-show-con clearfix"><div class="comment-show-con-img pull-left"><img src="images/header-img-comment_03.png" alt=""></div> <div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"> <a href="#" class="comment-size-name">移动用户：'+"${sessionScope.TEL_IN_SESSION }"+' </a> <span class="my-pl-con">&nbsp;'
									+ oSize
									+ '</span> </div> <div class="date-dz"> <span class="date-dz-left pull-left comment-time">'
									+ now
									+ '</span> <div class="date-dz-right pull-right comment-pl-block"><a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">0</i>)</a> </div> </div><div class="hf-list-con"></div></div> </div>';
							if (oSize.replace(/(^\s*)|(\s*$)/g, "") != '') {
								$(this).parents('.reviewArea ').siblings(
										'.comment-show').prepend(oHtml);

								$(this).siblings('.flex-text-wrap').find(
										'.comment-input').prop('value', '')
										.siblings('pre').find('span').text('');

								//ajax传值

								$.ajax({//异步请求返回给后台
									url : '/HotelManagerSystem/comment/addcommentservlet',//servlet 
									type : 'POST',
									data : {
										'comment_date' : now,//日期	
										'comment_text' : oSize
									//评论	
									},
									success : function(data) {
										alert(data);
									},
									error : function(data) {
										//alert(data)
									}
								});

							}
						});
	</script>

	<!--删除评论块-->
	<script type="text/javascript">
		$('.commentAll').on(
				'click',
				'.removeBlock',
				function() {
					var oT = $(this).parents('.date-dz-right').parents(
							'.date-dz').parents('.all-pl-con');
					if (oT.siblings('.all-pl-con').length >= 1) {
						oT.remove();
					} else {
						$(this).parents('.date-dz-right').parents('.date-dz')
								.parents('.all-pl-con').parents('.hf-list-con')
								.css('display', 'none')
						oT.remove();
					}
					$(this).parents('.date-dz-right').parents('.date-dz')
							.parents('.comment-show-con-list').parents(
									'.comment-show-con').remove();

				})
	</script>
	<!--点赞-->
	<script type="text/javascript">
		
		$('.comment-show').on('click', '.date-dz-z', function() {
			var zNum = $(this).find('.z-num').html();
			var id = $(this).find('.commentId').val();
			if ($(this).is('.date-dz-z-click')) {
				zNum--;
				$(this).removeClass('date-dz-z-click red');
				$(this).find('.z-num').html(zNum);
				$(this).find('.date-dz-z-click-red').removeClass('red');
			} else {
				zNum++;
				$(this).addClass('date-dz-z-click');
				$(this).find('.z-num').html(zNum);
				$(this).find('.date-dz-z-click-red').addClass('red');
			}
			$.ajax({
				url:"/HotelManagerSystem/comment/updatePraise",
				data:{
					comment_id:id,
					comment_praise:zNum,
				},
				success:function(data){
					alert(data);
				}
			})
			
		});

		/*    debugger; */

		$(function() {
			$.ajax({//异步请求返回给后台
						url : '/HotelManagerSystem/comment/showcommentservlet',//servlet 
						type : 'get',
						data : {
						/* 'date' :now,//日期	
						'username':"David Beckham",//用户名
						'text':oSize//评论	 */
						},
						dataType : 'json',
						success : function(data) {
							/* alert(JSON.stringify(data)); */
							var str = "";
							/* $("#aaa").empty(); */
							var allComment = data;
							for(var i=0;i<allComment.length;i++){ 
								var single = allComment[i];
		str += '<div class="comment-show-con clearfix"><div class="comment-show-con-img pull-left"><img src="images/header-img-comment_03.png" alt="">'
										+ '</div><div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"><a href="#" class="comment-size-name">'
										+ single.username
										+ '</a> <span class="my-pl-con">&nbsp;'
										+ single.comment_text
										+ '</span>'
										+ '</div><div class="date-dz"><span class="date-dz-left pull-left comment-time">'
										+ single.comment_date
										+ '</span><div class="date-dz-right pull-right comment-pl-block"><a href="javascript:removeComment(\''+single.comment_id+'\');" class="removeBlock">删除</a>'
										+'<a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"> <i class="date-dz-z-click-red"></i>赞(<i class="z-num">'+single.comment_praise+'</i>)<input type="hidden" class="commentId" value="'+single.comment_id+'"></a></div></div><div class="hf-list-con"></div></div></div>'
							}

							$("#aaa").append(str);
						},
						
						error : function(data) {
							//alert(data);
						}
					});
		});
		function removeComment(id){
			$.ajax({
				url:'/HotelManagerSystem/comment/removeComment',
				data:{
					comment_id:id,
				},
				success:function(data){
					alert(data);
				}
			})
		}
		
		
	</script>
</body>
</html>