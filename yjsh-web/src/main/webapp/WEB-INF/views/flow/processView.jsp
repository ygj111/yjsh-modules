<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>流程状态</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../../assets/snaker/snaker.css" type="text/css" media="all" />
		<script src="../../assets/raphael-min.js" type="text/javascript"></script>
		<script src="../../assets/jquery-1.12.2.min.js" type="text/javascript"></script>
		<script src="../../assets/jQuery-ui/jquery-ui.min.js" type="text/javascript"></script>
		<script src="../../assets/snaker/dialog.js" type="text/javascript"></script>
		<script src="../../assets/snaker/snaker.designer.js" type="text/javascript"></script>
		<script src="../../assets/snaker/snaker.model.js" type="text/javascript"></script>
		<script src="../../assets/snaker/snaker.editors.js" type="text/javascript"></script>

<script type="text/javascript">
	function display(process, state) {
		/** view*/
		$('#snakerflow').snakerflow($.extend(true,{
			basePath : "../../assets/snaker/",
            ctxPath : "../../",
            orderId : "${order.id}",
			restore : eval("(" + process + ")"),
			editable : false
			},eval("(" + state + ")")
		));
	}
</script>
</head>
	<body>
			<div id="snakerflow"></div>
	
		<script type="text/javascript">
		$.ajax({
				type:'GET',
				url:"../../admin/flow/processtojson",
				data:"processId=${processId}&orderId=${order.id}",
				async: false,
				globle:false,
				error: function(){
					alert('数据处理错误！');
					return false;
				},
				success: function(data){
					display(data.process, data.state);
				}
			});
		</script>
	</body>
</html>
