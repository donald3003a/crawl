<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <title>文章发表查询系统</title>
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
     <script type="text/javascript" src="jquery.min.js"></script>
    <script type="text/javascript" src="jquery.easyui.min.js"></script> 
    <script>
    var articledata;
    $(function(){
    	 articledata=$('#tt').datagrid({ 
    		url:'queryArticle.do', 
    		columns:[[ 
    		{field:'title',title:'文章标题',width:200}, 
    		{field:'categorys',title:'文章类别',width:100}, 
    		{field:'author',title:'作者',width:100}, 
    		{field:'readTimes',title:'访问量',width:40}, 
    		{field:'commentTimes',title:'评论数',width:40}, 
    		{field:'url',title:'文章链接',width:350,
    			formatter:function(rowdata){
        			return "<a  target='blank_' href="+rowdata+">"+rowdata+"</a>";
        		}
    		}, 
    		{field:'devGroup',title:'分组',width:80}, 
    		{field:'source',title:'文章来源',width:90}, 
    		{field:'blogId',title:'博客ID',width:90}, 
    		{field:'publishDate',title:'发表时间',width:150,
    			formatter:function(rowdata){
    			return json2TimeStamp(rowdata);
    		}
    		} 
    		
    		]] 
    		}); 
  
    });
    function search_(){
    	alert();
    	var publishDateFrom=$("#publishDateFrom").datebox('getValue');   
    	var publishDateTo=$("#publishDateTo").datebox('getValue');
    	var author=$("#name").val();
    	var devGroup=$("#devGroup").val();
    	var articleFrom=$("#articleFrom").val();
    	var blogId=$("#blogId").val();
    	  $('#tt').datagrid('reload', {
    		  publishDateFrom:publishDateFrom,
   	 		  publishDateTo:publishDateTo,
   	 		  group:devGroup,
   	 		  articleSource:articleFrom,
   	 		  blogId:blogId,
    		  author:author
          });
    }
    
  //时间格式化    

    function json2TimeStamp(milliseconds){

       if(milliseconds==null||milliseconds=="")
          return "";
        var datetime = new Date();
        datetime.setTime(milliseconds);
        var year=datetime.getFullYear();
             //月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
        var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
        var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
        return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
    } 

function report_(type){
	if("html"==type){
		$("#report").submit();
	}else{
		$("#reportdownload").submit();
	}
	
}
  
    </script>
</head>
<body>
    <h2>文章查询</h2>
    <div style="margin:20px 0;"></div>
     <table id="tt" class="easyui-datagrid" title="文章查询" style="margin-left:100px;width:1300px;height:850px;align:center"
            data-options="rownumbers:true,singleSelect:true,url:'',method:'get',toolbar:'#tb'">
    </table>
   
    <div id="tb" style="padding:5px;height:auto">
        <div>
          <form action="queryArticle.do" id="searchForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>作者:</td>
                    <td><input  id="name"name="name" ></input></td>
                    <td>分组:</td>
                    <td><input id="devGroup" name="devGroup" ></input></td>
                     <td>文章来源:</td>
                    <td><input id="articleFrom" name="articleFrom" ></input></td>
                     <td>分类:</td>
                    <td><input id="category" name="category"></input></td>
                </tr>
                <tr>
                     <td>博客ID:</td>
                    <td><input id="blogId" name="blogId"></input></td>
                    <td> Date From:</td>
                    <td><input  id="publishDateFrom"name="publishDateFrom" class="easyui-datebox" style="width:80px"></td>
                    <td>To:</td>
                    <td><input id="publishDateTo"name="publishDateTo" class="easyui-datebox" style="width:80px"></td>
                    <td> <a href="#" onclick="search_()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
                    <a href="#" onclick="reset_()" class="easyui-linkbutton"> 重置</a>
                    </td><td>
                     <a href="#" onclick="report_('html')" class="easyui-linkbutton" >查看报表</a>
                      <a href="#" onclick="report_('pdf')" class="easyui-linkbutton" >下载报表</a>
                     </td>
                </tr>
            </table>
        </form>
        <form id="report" action="queryHtmlReport.do"></form>
        <form id="reportdownload" action="downloadPdfReport.do" target="blank_"></form>
   
        </div>
    </div>
</body>

</html>