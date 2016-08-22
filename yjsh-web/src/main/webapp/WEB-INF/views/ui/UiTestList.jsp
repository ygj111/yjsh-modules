<%@ page contentType="text/html;charset=UTF-8"%>
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
                                            <button class="btn btn-default" id="btn_modify" type="button">
                                                <i class="fa fa-pencil"></i> 修改
                                            </button>
                                            <button class="btn btn-default" id="btn_add" type="button">
                                                <i class="fa fa-plus"></i> 新增
                                            </button>
                               
<!--                                             <button class="btn btn-default" id="btn_del" type="button" data-toggle="tooltip" -->
<!--                                                     data-placement="right" title="删除"> -->
<!--                                                 <i class="fa fa-minus"></i> 删除 -->
<!--                                             </button> -->
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
                                                    </tr>
                                                </thead>
                                                <tbody>
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
           <div style="display:none;" >
               <form id="serviceListByFrom" action="${pageContext.request.contextPath}/main/getServiceList" method="post">
                    <input type="text" id="appNameForFwdr" name="appname"/>
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
                        <div class="form-group">
                            <label for="app_appname" class="control-label">产品名称</label>
                            <input type="text" class="form-control" name="appname" id="appname">
                        </div>
                        <div class="form-group">
                            <label for="app_description" class="control-label">中文描述</label>
                            <input type="text" class="form-control" name="description" id="description">
                        </div>
                      
                        <div class="form-group">
                            <label for="app_version" class="control-label">版本</label>
                            <input type="text" class="form-control" id="version" name="version">
                        </div>
                        
                        <div class="form-group">
                            <label for="app_type" class="control-label">单位类型</label>
                            <select id="select" name="type" class="form-control" style="width:570px; height:35px">
                                 <c:forEach items="${regions}" var="orgType">
										<option value="${orgType}">${orgType}</option>
									</c:forEach>
                            </select>
                        </div>
                       
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="btn_save" class="btn btn-primary" onclick="save();">
                        	保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        
     <script language="javascript">
     	// 全选方法
   		$('#checkAll').on('click', function() {
   			console.info('checkall appname:' + $(this).prop("checked"));
   	   		$("input[name='appname']").prop('checked', $(this).prop('checked'));
   	   	});
     	
   		// 新增操作
   		$('#btn_add').on('click', function() { 
   			// 清空所有的input
   			$('#dictForm input').val("");
   			
   			$('#myModalLabel').text('新增产品数据');		// 修改label
   			$("#dictUpdate").modal('toggle');
   			
   			$('#appname').attr("readonly",false); 
   			// 更换Action位置
   			/* $('#dictForm').attr('action', '${ctx}/saveApp'); */
   			
   		});
   		
   		function save(){
   			
   		     var appname=$("#appname").val();
   			if(appname==null || appname==""){
   				alert("产品名称不能为空")
   				return false;
   			} 
   			var reg = /^[\u4e00-\u9fa5]|[a-zA-Z]|[0-9]$/;
   			if(reg.test(appname) != true){
   				alert("请输入正确的产品名称");
   				return false;
   			}
   			var readonly = $('#appname').attr("readonly"); 
   			//判断是否为新增
   			if(readonly != "readonly"){
   				var succ = "";
   			 	$.ajax({
					type : "POST",
					url : "${ctx}/main/findAppByAppname",
					data :  {appname:appname}, 
					dataType : "json",
					async:false,
					success : function(data) {
						//产品不存在
						if(data == true){
							succ = "yes";
						}else{
							alert("产品已存在！");
							succ = "no";
						}
					}
					
				});
   			 	if(succ == "no"){
      				return succ;
   			 	}
      		}
   			var description=$("#description").val()
   			var des = description.replace(/^\s+/,"");
   			if(des==null || des==""){
   				alert("产品中文描述不能为空")
   				return false;
   			} 
   			var version1 = $("#version").val();
   			var ver = version1.replace(/^\s+/,"");
   			if(ver == null || ver == ""){
   				alert("版本不能为空！");
   				return false;
   			}
   			if (confirm("确认保存？")) {
   				
   				document.forms['dictForm'].submit();
   				
   				alert("保存成功！");
   				

   	      } else {

   	           return false;

   	      }
   		}
   		
 
   		// 修改操作
   		$('#btn_modify').on('click', function() {
   			// 判断表格中是否有行被选中
   			var appname;
   			var check = $("input[name='appname']").attr("checked");
   			var num = 0;
   			$('input[name="appname"]').each(function(i) {
   				if ($(this).prop('checked')) {
   					appname = $(this).attr('value');
   					num++;
   					// 将选中行的数据填充到模态窗口
   					var td = $(this).parent().nextAll();
   					$('#appname').val(appname);
   				   
   					$('#description').val(td.eq(4).text());
   					$('#version').val(td.eq(2).text());
   				    
   					var orgType = td.eq(3).text();
   					var count=$("#select option").length;  
   				  	for(var i=0;i<count;i++)  { 
   				  		if($("#select ").get(0).options[i].text == orgType)  {    
   				            $("#select").get(0).options[i].selected = true;    
   				            
   				            break;    
   				        }    
   				    }  
   	
   					return;
   				}
   			});
   			if(num>1){
   				alert("请只选择一条需要修改的数据！");
   				return;
   			}
   			if (appname == null) {
   				alert("请选择一条需要修改的数据！");
   				return;
   			}
   			
   			$('#myModalLabel').text('修改产品数据');		// 修改label
   			$("#dictUpdate").modal('toggle');
   		   
   			$('#appname').attr("readonly",true); 
   			/* // 更换Action位置
   			$('#dictForm').attr('action', '${ctx}/saveApp'); */
   			
   			
   		});
   		
   		// 删除操作
   		$('#btn_del').on('click', function() {
   			// 判断表格中是否有行被选中
   			var appname;
   			
   			$('input[name="appname"]').each(function(i) {
   				if ($(this).prop('checked')) {
   					appname += $(this).attr('value') + ";";
   				}
   			});
   			
   			if (appname == null) {
   				alert("请选择一条需要删除的数据！");
   				return;
   			}
   			else {
   				if (confirm("确认删除选中的产品！？")) {
   					// 更换Action位置
   		   			$('#mainForm').attr('action', '${ctx}/main/deleteApp');
   					$('#mainForm').submit();
   					alert("删除成功！");
   				}
   				
   			}
   		});
		
   		//进入产品服务列表调用方法
   		function fwdr(appname){
   			$("#appNameForFwdr").val(appname);
   			$("#serviceListByFrom").submit();
   		}
     </script>
    
     
</body>
</html>