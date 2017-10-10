/*性别圆饼图*/
$(function(){
	loadUsersChartsVar();
	
});

/**
 * 加载参数,界面
 */
function loadUsersChartsVar(){
	//性别圆饼图
	load_users_sex_0();
	//用户月注册数折线图
	load_users_month_regist_number_0();

}

/**
 * 性别圆饼图
 */
function load_users_sex_0(){
	var users_sex_0_chart = echarts.init($("#users_sex_0")[0]);
	// 基于准备好的dom，初始化echarts图表  

	
	
	var url = manageForwardUrl+"/userscharts/getUsersSexCircleChartData.action";
	ajax.send(url, function(data){
		
		
		c(data);
		var title = {};
		var legend = {};
		var series = {};
		title.text = "用户性别分布";
		title.subtext = "南丁格尔玫瑰";
		//获取legend
		var legend_data = [];
		for(var i = 0;i<data.series.data.length;i++){
			
			legend_data.push(data.series.data[i].name);
		}
		legend.data = legend_data;
		
		series.data = data.series.data;
		
		series.name = "性别";
		
		var users_sex_0_option = getCircleOptions(title, legend, series)
		// 为echarts对象加载数据
		users_sex_0_chart.setOption(users_sex_0_option);
		
		
	}, function(data){
		
	}, function(){
		
	})
	
	
}

/**
 * 用户月注册数折线图
 */
function load_users_month_regist_number_0(){
	var users_month_regist_number_0_chart = echarts.init($("#users_month_regist_number_0")[0]);
	// 基于准备好的dom，初始化echarts图表  

	var url = manageForwardUrl+"/userscharts/getUsersMonthRegistNumberChartData.action";
	ajax.send(url, function(data){

		c(data);
		
		var title = {};
		var xAxis = {};
		var series = {};
		title.text = "用户月注册数统计";
		title.subtext = "折线图";
		//获取xAxis
		var xAxis_data = [];
		for(var i = 0;i<data.series.data.length;i++){
			
			xAxis_data.push(data.series.data[i].name + "月");
		}
		xAxis.data = xAxis_data;
		
		series.data = data.series.data;
		series.name = "注册数";
		
		var users_month_regist_number_0_chart_option = getLineOptions(title, xAxis, series)
		// 为echarts对象加载数据
		users_month_regist_number_0_chart.setOption(users_month_regist_number_0_chart_option);
		
		
	}, function(data){
		
	}, function(){
		
	})
	
	
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
	        text: title.text,
	        subtext: title.subtext,
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        x : 'center',
	        y : 'bottom',
	        data:legend.data
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel']
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'性别',
	            type:'pie',
	            radius : [30, 110],
	            center : ['50%', 200],
	            roseType : 'area',
	            x: '50%',               // for funnel
	            max: 40,                // for funnel
	            sort : 'ascending',     // for funnel
	            data:series.data
	        }
	    ]
	};
	
	return options;
}

/**
 * 获取折线图参数
 * @returns {___anonymous3568_5440}
 */
function getLineOptions(title,xAxis,series){
	var options =  {
		    title : {
		        text: title.text,
		        subtext: title.subtext,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['最高值','最低值'],
		        x : 'center',
		        y : 'bottom'
		    	/*data:legend.data*/
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            /*data : ['周一','周二','周三','周四','周五','周六','周日']*/
		            data : xAxis.data
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value}人'
		            }
		        }
		    ],
		    series : [
		        {
		            name:series.name,
		            type:'line',
		            /*data:[11, 11, 15, 13, 12, 13, 10],*/
		            data:series.data,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	
	
	
	return options;
}
