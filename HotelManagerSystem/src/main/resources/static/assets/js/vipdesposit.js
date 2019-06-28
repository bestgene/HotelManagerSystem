/**
 * 
 */
layui.use('table', function() {
	var table = layui.table;
	table.render({
		elem : '#test',
		url : 'vipbill',
		width : 800,
		cellMinWidth : 80,
		totalRow : true,
		cols : [ [ {
			field : 'id',
			title : 'ID',
			sort : true,
			fixed : true,
			totalRowText : '合计'
		}, {
			field : 'name',
			title : '用户名',
		}, {
			field : 'tel',
			title : '联系方式',
		}, {
			field : 'money',
			title : '余额',
			sort : true,
			totalRow : true
		} ] ],
		page : true,
		limit : 5,
		limits : [ 5, 10, 15, 20 ],
		done : function(res, curr, count) {
			$('th').css({
				'background-color' : '#009688',
				'color' : '#fff'
			});
			$('.layui-btn').click(function(){
				console.debug(res.desposit);
				layer.alert(res.desposit, {
					title: ['当前会员充值总额：', 'background-color:#009688;color:#fff;align:center;']
			    	,skin: 'layer-ext-moon'//皮肤
			    	,shade: 0.3
			    	,offset: 't'
			    	,anim: 6
			     });
			})
		}
	});
	
	var $ = layui.$, active = {
		    getCheckLength: function(){ //获取选中数目
		      var checkStatus = table.checkStatus('idTest')
		      ,data = checkStatus.data;
		    }
		  };
		  $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
		  
		
});

