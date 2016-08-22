<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>login</title>
		<script type="text/javascript" src="../assets/jquery-1.12.2.min.js" ></script>
		<script type="text/javascript" src="../assets/jquery.validate.min.js" ></script>
		<script type="text/javascript" src="../assets/bootstrap/bootstrap.min.js" ></script>
		<link rel="stylesheet" href="../assets/bootstrap/bootstrap.min.css" />
		<link rel="stylesheet" href="../assets/fontawesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="../css/thePage.css" />

		<!--[if lt IE 9]>
  		<script type='text/javascript' src="../assets/bootstrap/respond.min.js"></script>
  		<script type='text/javascript' src="../assets/bootstrap/html5shiv.min.js"></script>
	<![endif]-->
	</head>
	<body >
		<div><OBJECT ID="Dog_ePass1000ND" WIDTH="" HEIGHT="" CLASSID="CLSID:EA9D461E-141D-4329-A1C1-B0107E42A75C" codebase="../assets/download/Dog_ePass1000ND.cab"></OBJECT></div>
		 <div class="container">
			<div class="row">
				<div class="col-lg-4 col-lg-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="login-title">管理员登录</h3>
							
						</div>
						<div class="panel-body">
							<form id="loginform" role="form" method="post" action="../admin/login">
								<fieldset>
									<div class="form-group">
										<i class="fa fa-user fa-fw"></i> <input value="admin" class="form-control" placeholder=" 用户名" id="username" name="username" type="text" autofocus/>
									</div>
									<div class="form-group">
										<i class="fa fa-key fa-fw"></i> <input value="123456" class="form-control" placeholder="密码" name="password" id="password" type="password"/>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" name="rememberMe" id="rememberMe">记住我
										</label>
									</div>
									<button type="submit" class="btn btn-success btn-block">登录</button>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div> 
		<div class="secondLogin" style="display:none;">
			<div class="col-lg-4 col-lg-offset-4">
					<div class="login-panel panel panel-default">
						<div class="panel-heading">
							<h3 class="login-title">加密锁登录</h3>
							<a  onclick="changeLogin()" style="float:right;margin-top:-20px;">
								<input type="hidden" value="login" class="login"></input>
							切换登录</a>
						</div>
						<div class="panel-body">
							<form id="keyLoginform"  method="post" action="../admin/keyToLogin">
								<div style="height:20px;"></div>
								<div style="display:none;">加密锁ID:<input id="dogId" name="key"/></div>
								<div style="display:none;"><button onclick="GetDeviceSerial();">读取加密锁ID</button></div>
								<button type="button" id="keyLogin" class="btn btn-success btn-block">登录</button>
							</form>
						</div>
					</div>
				</div>
		</div>
		<script type="text/javascript">
		$( document ).ready( function () {
			var ua = navigator.userAgent.toLowerCase().toString();
			 var userAgent = navigator.userAgent;
			 //浏览器版本
			 if(userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 ) {
			     $(".panel-heading").append("<a  onclick='changeLogin()' style='float:right;margin-top:-20px;'>切换登录</a>");
			        
			    }; 
			$( "#loginform" ).validate( {
				rules: {
					username: "required",
					password: "required",
					username: {
						required: true,
						minlength: 5
					},
					password: {
						required: true,
						minlength: 6
					}
				},
				messages: {
					username: "请输入用户",
					password: "请输入密码",
					username: {
						required: "请输入您的用户名",
						minlength: "用户名不能小于5个字符"
					},
					password: {
						required: "请输入密码",
						minlength: "密码长度不能小于6位"
					}
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `help-block` class to the error element
					error.addClass( "help-block" );

					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.parent( "label" ) );
					} else {
						error.insertAfter( element );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).parents().addClass( "has-error" ).removeClass( "has-success" );
				},
				unhighlight: function (element, errorClass, validClass) {
					$( element ).parents().addClass( "has-success" ).removeClass( "has-error" );
				}
			} );
			$("#keyLogin").click(function(){
				var dogId = Dog_ePass1000ND.GetDeviceSerial();
				document.getElementById("dogId").value = "";
				var dogId = Dog_ePass1000ND.GetDeviceSerial();
				if(dogId)
				{
					document.getElementById("dogId").value = dogId;
				}
				$("#keyLoginform").submit();
			});
		});
		function changeLogin(){
			var login = $(".login").val();
			if(login == "login"){
				$(".row").attr("style","display:none;");
				$(".login").val("secondLogin");
				$(".secondLogin").attr("style","display:block;");
			}else{
				$(".secondLogin").attr("style","display:none;");
				$(".login").val("login");
				$(".row").attr("style","display:block;");
			}
		}
		function GetDeviceSerial()
		{
			document.getElementById("dogId").value = "";
			var dogId = Dog_ePass1000ND.GetDeviceSerial();
			if(dogId)
			{
				document.getElementById("dogId").value = dogId;
			}
		}
		</script>
	</body>
</html>