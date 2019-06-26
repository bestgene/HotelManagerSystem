/**
 * 
 */
$(function(){
	$.ajax({
		url:'pie',
		type:'get',
		dataType:'json',
		success:function(data){
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
			    backgroundColor: '#2c343c',
			    title: {
			        text: 'Bill Pie',
			        left: 'center',
			        top: 80,
			        textStyle: {
			            color: '#ccc',
			        }
			    },

			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },

			    visualMap: {
			        show: false,
			        min: 80,
			        max: 600,
			        inRange: {
			        	 color: ['yellow', '#de5145', '#c63531']
			        }
			    },
			    series : [
			        {
			            name:'房间类型',
			            type:'pie',
			            radius : '60%',
			            center: ['50%', '50%'],
			            data:[
			            	 {value:data.a, name:'标准间'},
			                 {value:data.b, name:'大床房'},
			                 {value:data.c, name:'总统套房'},
			            ].sort(function (a, b) { return a.value - b.value; }),
			            roseType: 'radius',
			            label: {
			                normal: {
			                    textStyle: {
			                        color: 'rgba(255, 255, 255, 0.3)',
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    lineStyle: {
			                        color: 'rgba(255, 255, 255, 0.3)'
			                    },
			                    smooth: 0.2,
			                    length: 20,
			                    length2: 20
			                }
			            },
			            itemStyle: {
			                normal: {
			                    color: '#c23531',
			                    shadowBlur: 200,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            },

			            animationType: 'scale',
			            animationEasing: 'elasticOut',
			            animationDelay: function (idx) {
			                return Math.random() * 200;
			            }
			        }
			    ]
			};;
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			} 
		},
		error:function(){
			
		}
	});
});