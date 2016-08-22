<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="X-UA-Compatible" content="IE=9">
	<script type="text/javascript" src="../assets/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js"></script>
	<link href="../assets/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
	<link rel="stylesheet" href="${ctx}/css/thePage.css" />
	<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/jquery.dataTables.min.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.js" ></script>
	<link rel="stylesheet" href="${ctx}/assets/datatables/1.10.10/dataTables.bootstrap.min.css" />
	<script type="text/javascript" src="${ctx}/assets/plupload/plupload.full.min.js" ></script>
	 	<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
	<style>
		.yl{cursor:pointer;}
		.yl:hover{color:#0000ff;}
		td{padding-left:50px;}
		th{padding-left:50px;}
	</style>
	 <script type="text/javascript">
			   $(function() {  
				   var applyTable = $('#apply_table').DataTable({
						processing: true,
					    serverSide: true,
					    lengthChange:false,
						paging: true,
					    searching:false,
					    ordering: false,
						info:false,
				   	 	ajax: {
				   	 			url : "${ctx}/fileupload/searchAll",
								type:'post',
								data:{}
							},
						columns:[
							{'data': 'fileId',
								'render':function(data){
									return "<input type='checkbox' id='" + data +"' name='id' value='" + data +"'>";
								}
				       		},
							{'data':'fileName'},
							{'data':'fileSize'},
							{'data':'fileType'},
							{'data':'updateDate'},
							{'data': 'fileId',
							     'render': function ( data, type, full, meta ) {
							    		 return "<p class='yl' fileid='"+data+"' onclick=\"look('"+data+"');\">预览</p>";
							      }
							}
						]
					});
				 /*   $.ajax({
						type : 'post',
						data : {},
						dataType : 'json',
						async:true,
						url : "${ctx}/fileupload/searchAll",
						success : function(data) {
							 var tbody="";
								$.each(data, function (index, file) {
								 tbody+="<tr><td><input type='checkbox' name='id' value="+file.fileId+"></td>"+
								 "<td>"+file.fileName+"</td><td>"+file.fileSize+"</td><td>"+file.fileType+"</td><td>"+file.updateDate+"</td><td><p class='yl' onclick=\"look('"+file.fileId+"');\">预览</p></td></tr>";
							 });
							 $("#apply_table").append(tbody);
						}
					});  */
				   
				 // 删除操作
					$('#deleteFile').on('click', function() {
						// 判断表格中是否有行被选中
						var id;
						
						$('input[name="id"]').each(function(i) {
							if ($(this).prop('checked')) {
								id += $(this).attr('value') + ";";
							}
						});
						
						if (id == null) {
							alert("请选择一条需要删除的数据！");
							return;
						}
						else {
							if (confirm("确认删除选中的文件！？")) {
								// 更换Action位置
								 $.ajax({
										type : 'post',
										data : {'id':id},
										url : "${ctx}/fileupload/deleteFile",
										success : function(data) {
											if(data == "succ"){
												alert("删除成功！");
												window.location.reload();
											}else{
												alert("删除失败");
											}
											
										},
										error:function(e){
											alert("error")	;
										}
									}); 
							}
						}
					});
			   $('#checkAll').on('click', function() {
			   		$("input[name='id']").prop('checked', $(this).prop('checked'));
			   	});
				   });
			   function look(fileId){
		   			var width = screen.width*0.75, height = screen.height;
					var wt=(screen.width - width) / 2;
		   			window.open('${ctx}/fileupload/look?fileId='+fileId,'_blank','height='+height+',width='+width+',top=20,left='+wt+',toolbar=no,scrollbars=yes,menubar=no,resizable=no,location=no,status=no');
			   }
			   function uploadLook(fileId){
			    	var fileid = $("#" + fileId).find(".yl").attr("fileid");
		   			var width = screen.width*0.75, height = screen.height;
					var wt=(screen.width - width) / 2;
		   			window.open('${ctx}/fileupload/look?fileId='+fileid,'_blank','height='+height+',width='+width+',top=20,left='+wt+',toolbar=no,scrollbars=yes,menubar=no,resizable=no,location=no,status=no');
			   } 
	</script> 
</head>
<body style="text-align:center;">

<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
		<!-- top nav -->
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
				<div class="clearfix">
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content">
		      <div id="showspace">
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-info ">
									<div class="panel-heading">查看文件</div>
										<div class="panel-body">
					 						<div style="text-align:right;"><button id="uploader" class="btn btn-success btn-sm" type="button" style="margin-right:10px;">选择文件</button><button id="deleteFile"  class="btn btn-success btn-sm" type="button">删除操作</button>
											</div>
								<!--search -->
								<!-- data table -->
								<table class="table table-hover table-striped table-bordered" id="apply_table">
									<thead>
										<tr class="123">
											<th><input type="checkbox" id="checkAll" name="selectAll"> 全选</th>
											<th>文件名</th>
											<th>文件大小</th>
											<th>文件类型</th>
											<th>上传时间/上传进度</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<!-- data table end-->
                                 
							</div>
						</div>
					</div>
				</div>
				
			</div>
    	</section>
    	</div>
</div>
</body>
<script type="text/javascript">
var uploader = new plupload.Uploader({
		            runtimes : 'html5,flash,silverlight,html4',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html
		            flash_swf_url : '${ctx}/assets/plupload/Moxie.swf',
		            silverlight_xap_url : '${ctx}/assets/plupload/Moxie.xap',
		            url : '${ctx }/fileupload/upload',//上传文件路径
		            max_file_size : '3gb',//100b, 10kb, 10mb, 1gb
		            chunk_size : '1mb',//分块大小，小于这个大小的不分块
		            unique_names : true,//生成唯一文件名
		            browse_button : 'uploader', 
		            filters : [ {
		                title : 'Image files',
		                extensions : 'jpg,gif,png'
		            }, {
		                title : 'Zip files',
		                extensions : 'zip,7z,rar'
		            }, {
		                title : 'office files',
		                extensions : 'doc,xls,xlsx,docx,txt'
		            } ],
		            
		            init : {
		                FilesAdded: function(up, files) {
		                
		                    uploader.start();
		                    var tbody="";
		                    plupload.each(files, function(file) {
								  tbody+="<tr id='"+file.id+"'><td><input type='checkbox' name='id' value="+file.id+"></td>"+
								 "<td>"+file.name+"</td><td>"+plupload.formatSize(file.size)+"</td><td class='fileType'></td><td ><div  class='progress progress-striped' >"+
							        "	<div class='progress-bar progress-bar-warning bar'  role='progressbar'  aria-valuenow='0' aria-valuemin='0' aria-valuemax='100' style='width: 0%;'>"+
							        "</div></div></td><td><p class='yl' onclick=\"uploadLook('"+file.id+"');\">预览</p></td></tr>"; 
							 
	        			});
	                	$(".123").after(tbody);
		                    return false;
		                },
		                FileUploaded : function(up, file, info) {//文件上传完毕触发
		                	var response = $.parseJSON(info.response);
		                	$.each(response, function (index, file1) {
		                		console.log(file1.fileId+"fileid");
		                		if("文件已存在，请修改文件名!" == file1.fileName){
		                			alert("文件已存在，请修改文件名!");
		                			$("#" + file.id).find(".bar").attr("style","background-color:#ff0000;");
		                			$("#" + file.id).attr("style","display:none");
		                		}
		                		$("#" + file.id).find(".fileType").text(file1.fileType);
		                		$("#" + file.id).find(".yl").attr("fileid",file1.fileId);
		                		$("#" + file.id).find("input[name='id']").val(file1.fileId);
		                	});
		                	console.log("fileupload"+info.response);
		                },
		                UploadComplete : function( uploader,files ) {
		                    console.log("所有文件上传完毕");
		                },
		                UploadProgress : function( uploader,file ) {
		                    //$("#progress").html("上传进度为：" + file.percent + "%");
		                    console.log( file.id +"12345");
								$("#" + file.id).find(".bar").css(
				                'width',
				                file.percent + '%'
				            );
								$("#" + file.id).find(".bar").text(file.percent+'%');
		                }
		            }
		        });
		        
		        uploader.init();
</script>
</html>
