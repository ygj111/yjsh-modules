<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*, java.util.*, com.hhh.util.pgoffice.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%
	PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
	poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
	// 定义WordDocument对象
	WordDocument doc = new WordDocument();
	String fileId = request.getParameter("fileId");
	String type = (String)request.getAttribute("type");
	
	poCtrl.setWriter(doc);
	
	poCtrl.addCustomToolButton("保存","Save",1);
	poCtrl.setSaveFilePage(request.getContextPath() + "/fileupload/save?fileId="+fileId);
	if(type.equals("xls")){
		poCtrl.webOpen(request.getContextPath() + "/fileupload/download?fileId="+fileId, OpenModeType.xlsNormalEdit, "zhongl");
	}else if(type.equals("doc")){
		poCtrl.webOpen(request.getContextPath() + "/fileupload/download?fileId="+fileId, OpenModeType.docNormalEdit, "zhongl");
	}
	poCtrl.setTagId("PageOfficeCtrl1");
%>
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
<link href="${ctx}/assets/metisMenu/metisMenu.min.css"	rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/thePage.css" />
<script src="${ctx}/assets/jquery-1.12.2.min.js"></script>
<script src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/assets/metisMenu/metisMenu.min.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/common.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/respond.min.js"></script>
	  		<script type='text/javascript' src="${ctx}/assets/bootstrap/html5shiv.min.js"></script>
		<![endif]-->
</head>
<body>
	<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
		<!-- top nav -->
				<div class="clearfix">
				<div class="panel-body">
					<div class="container-fluid" style=" width:auto; height:630px;">
	                    <script type="text/javascript">
						function Save() {
							document.getElementById("PageOfficeCtrl1").WebSave();
						}
						</script>
	               		<po:PageOfficeCtrl id="PageOfficeCtrl1" />
                    </div>
				</div>
    	</div>
	</div>	
</body>
</html>