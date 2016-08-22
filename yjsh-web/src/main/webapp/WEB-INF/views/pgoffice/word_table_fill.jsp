<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*, java.util.*, com.hhh.util.pgoffice.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%
	PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
	poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
	
	// 定义WordDocument对象
	WordDocument doc = new WordDocument();
		
	ArrayList data = (ArrayList)request.getAttribute("tableData");
	PageWord.fillTableData(doc, "PO_table1", 1, 2, data);
	
	poCtrl.setWriter(doc);
	
	poCtrl.setSaveFilePage("save_doc.jsp");
	
	poCtrl.webOpen(request.getContextPath() + "/doc/table_test.docx", OpenModeType.docNormalEdit, "zhongl");
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
		<jsp:include page="../admin/inc/nav_top.jsp"></jsp:include>
		<!-- End of top nav -->
		<!-- sidebar -->
		<div class="clearfix">
    		<aside class="sidebar">
     			 <nav class="sidebar-nav" id="left_menu_bar">
     			 </nav>
    		</aside>
    		<section class="content">
		      <div class="col-lg-12">
					<h3 class="header" id="title_text">Word中表格数据填充</h3>
				</div>
				<div class="panel-body">
					<div class="container-fluid" style=" width:auto; height:700px;">
                    	<script type="text/javascript">
						function Save() {
							document.getElementById("PageOfficeCtrl1").WebSave();
						}
						</script>
                        <po:PageOfficeCtrl id="PageOfficeCtrl1" />
                     </div>
				</div>
    	</section>
    	</div>
		<!-- End of sidebar -->
		
	</div>
</body>
</html>