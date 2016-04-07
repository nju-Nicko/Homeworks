<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="user" uri="/WEB-INF/tlds/users.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教学负责人数据统计页面</title>
<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/manager_teacher_stats.js"></script>
<script src="../js/highcharts.js"></script>
<script src="../js/modules/exporting.js"></script>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/manager_style.css" rel="stylesheet">
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: '选课人数历年统计柱状图'
            },
            subtitle: {
                text: 'Source: nju.software institute.tss'
            },
            xAxis: {
                categories: ['软工1', '软工2', '软工3', '数据结构', 'javaEE'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '人数 (个)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' 个'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor || '#FFFFFF'),
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'Year 2012',
                data: [107, 31, 635, 203, 2]
            }, {
                name: 'Year 2013',
                data: [133, 156, 947, 408, 6]
            }, {
                name: 'Year 2014',
                data: [973, 914, 4054, 732, 34]
            }]
        });
    });
    
$(function () {
    $('#container2').highcharts({
        title: {
            text: '近期课程作业均分曲线图',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: nju.software institute.tss',
            x: -20
        },
        xAxis: {
            categories: ['作业1', '作业2', '作业3', '作业4', '作业5', '作业6',
                '作业7', '作业8', '作业9', '作业10', '作业11', '作业12']
        },
        yAxis: {
            title: {
                text: '分数 (分)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '分'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '软工1',
            data: [7.0, 6.9, 9.5, 4.5, 8.2, 1.5, 5.2, 6.5, 3.3, 8.3, 3.9, 9.6]
        }, {
            name: '软工2',
            data: [9.0, 3.0, 5.7, 8.3, 7.0, 2.0, 4.8, 4.1, 8.9, 4.1, 8.6, 2.5]
        }, {
            name: '软工3',
            data: [9.0, 6.0, 3.5, 8.4, 3.5, 7.0, 8.6, 7.9, 4.3, 9.0, 3.9, 1.0]
        }, {
            name: '数据结构',
            data: [3.9, 4.2, 5.7, 8.5, 1.9, 5.2, 7.0, 6.6, 4.2, 9.9, 6.6, 4.8]
        },{
            name: 'javaEE',
            data: [7.8, 8.7, 6.2, 4.3, 8.5, 7.0, 9.3, 6.6, 4.8, 7.0, 5.9, 5.0]
        },]
    });
});

		</script>
<body onload="init()">
    <jsp:include page="../nav.jsp"/>
    <ul class="nav nav-tabs" style="position: absolute; left: 0px; top: 35px; font-family: 微软雅黑; width: 100%;">
   <li><a href="manager_course_stats.jsp">课程</a></li>
   <li><a href="manager_student_stats.jsp">学生</a></li>
   <li><a href="manager_assistant_stats.jsp">助教</a></li>
   <li><a href="manager_teacher_stats.jsp">教师</a></li>
   <li class="active"><a href="#">报表</a></li>
</ul>
<div id="container" style="position: relative; top: 100px; min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
<div id="container2" style="position: relative; top: 150px; min-width: 310px;  max-width: 800px; height: 400px; margin: 0 auto"></div>
</body>
</html>