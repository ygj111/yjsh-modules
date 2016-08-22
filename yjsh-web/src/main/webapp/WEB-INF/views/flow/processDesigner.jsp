<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>流程展现</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../../assets/snaker/snaker.css" type="text/css" media="all" />
		<script src="../../assets/raphael-min.js" type="text/javascript"></script>
		<script src="../../assets/jquery-1.12.2.min.js" type="text/javascript"></script>
		<script src="../../assets/jQuery-ui/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
		<script src="../../assets/snaker/dialog.js" type="text/javascript"></script>
		<script src="../../assets/snaker/snaker.designer.js" type="text/javascript"></script>
		<script src="../../assets/snaker/snaker.model.js" type="text/javascript"></script>
		<script src="../../assets/snaker/snaker.editors.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
				var json="${process}";
				var model = "";
				if(json) {
					model=eval("(" + json + ")");
				}
				$('#snakerflow').snakerflow({
					basePath : "../../assets/snaker/",
                    ctxPath : "../../",
					restore : model,
                    formPath : "forms/",
					tools : {
						save : {
							onclick : function(data) {
								saveModel(data);
							}
						}
					}
				});
			});
			
			function saveModel(data) {
				$.ajax({
					type:'POST',
					url:"../../admin/flow/deployXml",
					data:"model=" + data + "&id=${processId}",
					async: false,
					globle:false,
					error: function(){
						alert('数据处理错误！');
						return false;
					},
					success: function(data){
						if(data == true) {
							window.parent.flow.getFlowList(0,'');
						} else {
							alert('数据处理错误！');
						}
					}
				});
			}
			window.onload=function(){
				window.parent.document.getElementById('flowiframe').height = document.body.scrollHeight;
			}
		</script>					
</head>
<body>
<div id="toolbox">
<div id="toolbox_handle">工具集</div>
<div class="node" id="save"><img src="../../assets/snaker/images/save.gif" />&nbsp;&nbsp;保存</div>
<div>
<hr />
</div>
<div class="node selectable" id="pointer">
    <img src="../../assets/snaker/images/select16.gif" />&nbsp;&nbsp;Select
</div>
<div class="node selectable" id="path">
    <img src="../../assets/snaker/images/16/flow_sequence.png" />&nbsp;&nbsp;transition
</div>
<div>
<hr/>
</div>
<div class="node state" id="start" type="start"><img
	src="../../assets/snaker/images/16/start_event_empty.png" />&nbsp;&nbsp;start</div>
<div class="node state" id="end" type="end"><img
	src="../../assets/snaker/images/16/end_event_terminate.png" />&nbsp;&nbsp;end</div>
<div class="node state" id="task" type="task"><img
	src="../../assets/snaker/images/16/task_empty.png" />&nbsp;&nbsp;task</div>
<div class="node state" id="task" type="custom"><img
	src="../../assets/snaker/images/16/task_empty.png" />&nbsp;&nbsp;custom</div>
<div class="node state" id="task" type="subprocess"><img
	src="../../assets/snaker/images/16/task_empty.png" />&nbsp;&nbsp;subprocess</div>
<div class="node state" id="fork" type="decision"><img
	src="../../assets/snaker/images/16/gateway_exclusive.png" />&nbsp;&nbsp;decision</div>
<div class="node state" id="fork" type="fork"><img
	src="../../assets/snaker/images/16/gateway_parallel.png" />&nbsp;&nbsp;fork</div>
<div class="node state" id="join" type="join"><img
	src="../../assets/snaker/images/16/gateway_parallel.png" />&nbsp;&nbsp;join</div>
</div>

<div id="properties">
<div id="properties_handle">属性</div>
<table class="properties_all" cellpadding="0" cellspacing="0">
</table>
<div>&nbsp;</div>
</div>

<div id="snakerflow"></div>
</body>
</html>
