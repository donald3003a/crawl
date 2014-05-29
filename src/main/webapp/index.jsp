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
/*     	 $.post("queryArticle.do", function(data) {
    		  $("#articleList2").loaddata(data);
    		},"json");  */ 
    	 articledata=$('#tt').datagrid({ 
    		url:'queryArticle.do', 
    		columns:[[ 
    		{field:'title',title:'文章标题',width:100}, 
    		{field:'categorys',title:'文章类别',width:100}, 
    		{field:'author',title:'作者',width:100}, 
    		{field:'readTimes',title:'访问量',width:60}, 
    		{field:'commentTimes',title:'评论数',width:60}, 
    		{field:'url',title:'文章链接',width:250}, 
    		{field:'devGroup',title:'分组',width:80}, 
    		{field:'articlesrc',title:'文章来源',width:90}, 
    		{field:'updateDate',title:'发表时间',width:100} 
    		
    		]] 
    		}); 
  
    });
    function search_(){
    	var publishDateFrom=$("#publishDateFrom").val();
    	var publishDateTo=$("#publishDateTo").val();
    	var author=$("#name").val();
    	var devGroup=$("#devGroup").val();
    	alert(devGroup);
    	  $('#tt').datagrid('reload', {
    		  publishDateFrom:publishDateFrom,
   	 		  publishDateTo:publishDateTo,
   	 		  devGroup:devGroup,
    		  author:author
          });
    }
  
    </script>
</head>
<body>
    <h2>文章查询</h2>
    <div style="margin:20px 0;"></div>
     <table id="tt" class="easyui-datagrid" title="文章查询" style="margin-left:100px;width:1000px;height:850px;align:center"
            data-options="rownumbers:true,singleSelect:true,url:'',method:'get',toolbar:'#tb'">
    </table>
   
    <div id="tb" style="padding:5px;height:auto">
        <div>
          <form action="queryArticle.do" id="searchForm" method="post">
            <table cellpadding="5">
            	 <tr>
                    <td> Date From:</td>
                    <td><input  id="publishDateFrom"name="publishDateFrom" class="easyui-datebox" style="width:80px"></td>
                    <td>To:</td>
                    <td><input id="publishDateTo"name="publishDateTo" class="easyui-datebox" style="width:80px"></td>
                    <td></td>
                    <td> <a href="#" onclick="search_()" class="easyui-linkbutton" iconCls="icon-search">Search</a></td>
                </tr>
                <tr>
                    <td>作者:</td>
                    <td><input  id="name"name="name" ></input></td>
                    <td>分组:</td>
                    <td><input id="devGroup" name="devGroup" ></input></td>
                     <td>文章来源:</td>
                    <td><input id="articleFrom" name="articleFrom" ></input></td>
                </tr>
                <tr>
                    <td>分类:</td>
                    <td><input id="category" name="category"></input></td>
                     <td>博客ID:</td>
                    <td><input id="blogId" name="blogId"></input></td>
                </tr>
            </table>
        </form>
   
        </div>
    </div>
</body>

</html>