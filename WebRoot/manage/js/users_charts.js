/*性别圆饼图*/
$(function(){
	loadUsersChartsVar();
	
});

/**
 * 性别圆饼图
 */
function load_users_sex_0(){
	var users_sex_0_chart = echarts.init($("#users_sex_0")[0]);
	// 基于准备好的dom，初始化echarts图表  

	
	
	var url = manageForwardUrl+"/users/getUsersSexCircleChartData.action";
	ajax.send(url, function(data){
		
		var title = {};
		var legend = {};
		var series = {};
		title.text = "用户性别分布";
//		title.subtext = "subtext";
		
		legend.data = ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎'];
		
		series.data = [
					    {value:335, name:'直接访问'},
					    {value:310, name:'邮件营销'},
					    {value:234, name:'联盟广告'},
					    {value:135, name:'视频广告'},
					    {value:1548, name:'搜索引擎'}
					];
		
		series.name = "来源";
		
		var users_sex_0_option = getCircleOptions(title, legend, series)
		// 为echarts对象加载数据
		users_sex_0_chart.setOption(users_sex_0_option);
		
		
	}, function(data){
		
	}, function(){
		
	})
	
	
}


/**
 * 加载参数,界面
 */
function loadUsersChartsVar(){
	
	load_users_sex_0();

}



/**
 * 获取圆饼图参数;
 * @param title	标题对象;	
 * @param legend	legend对象;
 * @param series	series对象;
 * @returns {___anonymous_users_sex_0_option}
 */
function getCircleOptions(title,legend,series){
	var options = {
		title : {
			text : title.text,
			subtext : title.subtext,
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			/*data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']*/
			data : legend.data
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : 1548
						}
					}
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		series : [ {
			name : series.name,
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			/*data:[
			    {value:335, name:'直接访问'},
			    {value:310, name:'邮件营销'},
			    {value:234, name:'联盟广告'},
			    {value:135, name:'视频广告'},
			    {value:1548, name:'搜索引擎'}
			]*/
			data : series.data
		} ]
	};
	return options;
}
