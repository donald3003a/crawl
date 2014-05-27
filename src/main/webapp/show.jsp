<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="themes/icon.css">
 <script type="text/javascript" src="jquery.min.js"></script>
 <script type="text/javascript" src="jquery.easyui.min.js"></script>
 <script>
 $("#id").combobox("loaddata",data);  data变量
 </script>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DataGrid Complex Toolbar</title>
    <link rel="stylesheet" type="text/css" href="../../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../demo.css">
    <script type="text/javascript" src="../../jquery.min.js"></script>
    <script type="text/javascript" src="../../jquery.easyui.min.js"></script>
    <script>
    function search(){
    	$("#searchForm").submit() ;
    }

    </script>
</head>
<body>
    <h2>文章查询</h2>
    <div style="margin:20px 0;"></div>
    <table class="easyui-datagrid" title="DataGrid Complex Toolbar" style="width:700px;height:850px"
            data-options="rownumbers:true,singleSelect:true,url:'datagrid_data1.json',method:'get',toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'itemid',width:80">Item ID</th>
                <th data-options="field:'productid',width:100">Product</th>
                <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
                <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                <th data-options="field:'attr1',width:240">Attribute</th>
                <th data-options="field:'status',width:60,align:'center'">Status</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:5px;height:auto">
        <div>
          <form action="queryArticle.do" id="searchForm" method="post">
            <table cellpadding="5">
            	 <tr>
                    <td> Date From:</td>
                    <td><input  name="publishDateFrom" class="easyui-datebox" style="width:80px"></td>
                    <td>To:</td>
                    <td><input name="publishDateTo" class="easyui-datebox" style="width:80px"></td>
                    <td></td>
                    <td>    <a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a></td>
                </tr>
                <tr>
                    <td>作者:</td>
                    <td><input  name="name" ></input></td>
                    <td>分组:</td>
                    <td><input name="devGroup" ></input></td>
                     <td>网站来源:</td>
                    <td><input name="articleFrom" ></input></td>
                </tr>
                <tr>
                    <td>分类:</td>
                    <td><input name="category"></input></td>
                     <td>博客ID:</td>
                    <td><input name="blogId"></input></td>
                </tr>
            </table>
        </form>
   
        </div>
    </div>
</body>
</html>
</body>
</html>