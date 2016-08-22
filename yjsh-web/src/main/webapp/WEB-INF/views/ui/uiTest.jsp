<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*, java.util.*, com.hhh.util.pgoffice.*"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<link href="${ctx}/assets/bootstrap/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
<link href="${ctx}/assets/metisMenu/metisMenu.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/assets/css/thePage.css" />
<script src="${ctx}/assets/jquery-1.12.2.min.js"></script>
<script src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/assets/js/thePage.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/assets/js/admin/common.js"></script> --%>
</head>
<body>
	<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
	
		<jsp:include page="/WEB-INF/views/admin/inc/nav_top.jsp"></jsp:include>
		
		<!-- top nav -->
				<div class="clearfix">
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content">
		      <div class="col-lg-12">
					<h3 class="header" id="title_text">UI表单</h3>
				</div>
				<div class="panel-body">
					<div class="container-fluid" style=" width:auto; height:700px;">
                    
		<div id="showspace">
			<!-- panel -->
             <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                     
                      
                        <div class="panel-body">
                        	<form id="mainForm" method="post">
                        	<div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-3" style="margin-left: 0px; margin-bottom: 5px">
                                        
                                    </div>
                                    <div class="col-sm-9 text-right" style="margin-bottom: 5px">
                                        <div class="btn-group">
                                            <!-- <button class="btn btn-default" id="btn_modify" type="button">
                                                <i class="fa fa-pencil"></i> 修改
                                            </button> -->
                                            <button class="btn btn-default" id="btn_add" type="button">
                                                <i class="fa fa-plus"></i> 新增
                                            </button>
                               				<!-- <button class="btn btn-default" id="btn_look" type="button">
                                                <i class="fa fa-plus"></i> 在线预览
                                            </button> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- data table -->
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover text-center" id="dict">
                                                <thead>
                                                    <tr>
                                                       <th class="text-center"><input type="checkbox" id="checkAll" name="checkAll"></th>
                                                          <th class="text-center">序号</th>
                                                        <th class="text-center">id</th>
                                                        <th class="text-center">name</th>
                                                        <th class="text-center">realname</th>
                                                        <th class="text-center">num</th>
                                                        <th class="text-center">操作</th>
                                                    </tr>
                                                </thead>
                                                <tbody class="tbody">
                                                </tbody>
                                            </table>
                                        </div>
                                       
                                    </div>
                                    
                                </div>
                                <div class="row">
                                    <!-- pagination -->
                                    <div class="col-sm-6">第<c:out value="${pageNum }"/>页/共<c:out value="${totalPageNum }"/>页</div>
                                    <div class="col-sm-6">
                                        <ul class="pagination navbar-right" style="margin: 0px">
                                        
                                        	<c:if test="${pageNum > 1}"><li>
                                            <a href="${ctx}/main/listApp?page=1">首页</a></li></c:if>
                                        	<c:if test="${pageNum > 1 }"><li>
                                            <a href="${ctx}/main/listApp?page=${pageNum - 1}">上一页</a></li></c:if>
                                            <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/listApp?page=${pageNum + 1}">下一页</a></li></c:if>
                                             <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/listApp?page=${totalPageNum}">最后一页</a></li></c:if>
                                        </ul>
                                    </div>
                                 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
            </div>
		</div>
		</div>
				</div>
    	</section>
    	</div>	
	</div>
	
	<!--Modal-->
    <div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        	模态框（Modal）标题
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="dictForm" role="form" method="post" action="saveApp">
                       
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="btn_save" class="btn btn-primary" onclick="save1();">
                        	保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        
     <script language="javascript">
     	$(document).ready(function(){
     		$.ajax({
				type : "POST",
				url : "${ctx}/ui/test/getAllList",
				data :  {}, 
				contentType: "application/json; charset=utf-8", 
				dataType : "json",
				async:false,
				success : function(data) {
					for(var i=0;i<data.length;i++){
						var id = data[i].id;
						var name = data[i].name;
						var realname= data[i].realname;
						var num = data[i].num;
						var t = i+1;
						$(".tbody").append("<tr><td class='text-center'>"+
							"<input type='checkbox' name='code' value="+id+"></td>"+
							"<td>"+t+"</td>"+
							"<td class=''>"+id+"</td>"+
							"<td class=''>"+name+"</td>"+
							"<td class=''>"+realname+"</td>"+
							"<td class=''>"+num+"</td>"+
							"<td class=''><a class='btn_modify' onclick='abc("+id+");' id="+id+">修改</a></td></tr>");
					}
				}
				
			});
     	});
   		$('#btn_look').on('click', function() {
   			$.ajax({
				type : "POST",
				url : "${ctx}/ui/test/word",
				data :  {}, 
				dataType : "json",
				async:false,
				success : function(data) {
					$("#dictForm").empty();
					$("#dictForm").append(data.str);
				}
				
			});
   	   	});
   		// 新增操作
   		$('#btn_add').on('click', function() { 
   			// 清空所有的input
   			$('#dictForm input').val("");
   			
   			$('#myModalLabel').text('新增产品数据');		// 修改label
   			$("#dictUpdate").modal('toggle');
   			$('#appname').attr("readonly",false); 
   			$.ajax({
				type : "POST",
				url : "${ctx}/ui/test/createUI",
				data :  {}, 
				dataType : "json",
				async:false,
				success : function(data) {
					$("#dictForm").empty();
					$("#dictForm").append(data.str);
				}
				
			});
   		 	
   		});
   		function abc(cid){
   			$.ajax({
				type : "POST",
				url : "${ctx}/ui/test/findUiTestById",
				data :  {id:cid}, 
				dataType : "json",
				async:true,
				success : function(data) {
					var uiId = data.id;
					var uiname = data.name;
					var uirealname = data.realname;
					var uinum = data.num;
					$.ajax({
						type : "POST",
						url : "${ctx}/ui/test/createUI",
						data :  {}, 
						dataType : "json",
						async:true,
						success : function(data) {
							$("#dictForm").empty();
							$("#dictForm").append(data.str);
							$("#id").val(uiId);
							$("#name").val(uiname);
							$("#realname").val(uirealname);
							$("#num").val(uinum);
						}
						
					});
				}
				
			});
			$('#myModalLabel').text('修改产品数据');		// 修改label
			$("#dictUpdate").modal('toggle');
   		}
   		function save1(){
   			//获取xml中improtClass，得到实体类的全限定名
   			var entity=$("#dictForm").find("table").attr("importClass");
   			//序列化form表单
   			var data = $('#dictForm').serializeArray();
   			var o = {};
   			//将表单生成json格式字符串
   			$.each(data, function() {    
   		       if (o[this.name]) {    
   		           if (!o[this.name].push) {    
   		               o[this.name] = [o[this.name]];    
   		           }    
   		           o[this.name].push(this.value || '');    
   		       } else {    
   		           o[this.name] = this.value || '';    
   		       }    
   		   	});    
   			var jsondata = JSON.stringify(o);
   			//bean:form表单提交的实体数据
   			//entity:实体类权限定名
   			var requestData={
   					"bean":jsondata,
   					"entity":entity
   			};
   			 $.ajax({
					type : "POST",
					url : "${ctx}/ui/test/save1",
					data :  requestData, 
					
					dataType : "json",
					async:false,
					success : function(data) {
						alert("保存成功");
					}
					
				});
   		}
   		
   		
		
     </script>
    
     
</body>
</html>