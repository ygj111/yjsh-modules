function adminmenu(){
	/*******************生成后台管理菜单*********************/
	function leftMenuBar(){
		$.get("../admin/user/usermenu", function(data, status){
			if("success" == status){
				var len = data.length;
				var html='<ul class="metismenu" id="admin_left_menu">';
				for(var i = 0; i < len; i++){
					var menu = data[i];
					if(menu.name == "系统管理" || menu.name == "流程管理" || menu.name == "PageOffice示例" || menu.name == "文件管理"){
						if(menu.child){
							html+='<li><a href="'+menu.url+'" aria-expanded="true">'+menu.name+'<span class="glyphicon arrow"></span></a>';
							html += leftSubMenu(menu.subMenu);
							html+='</li>';
						}else{
							html+='<li><a href="'+menu.url+'" >'+menu.name+'</a>';
							html+='</li>';
						}
					}
				}
				html += '</ul>';
				$("#left_menu_bar").html(html);
				$('#admin_left_menu').metisMenu({ toggle: false });
			}
		});
	}
	
	function leftSubMenu(submenu){
		var len = submenu.length;
		var html='<ul aria-expanded="true">';
		for(var i=0; i<len; i++){
			var menu = submenu[i];
			if(menu.child){
				html+='<li><a href="'+menu.url+'" aria-expanded="true">'+menu.name+'<span class="glyphicon arrow"></span></a>';
				html += leftSubMenu(menu.subMenu);
				html+='</li>';
			}else{
				html+='<li><a href="'+menu.url+'" >'+menu.name+'</a>';
				html+='</li>';
			}
		}
		html += '</ul>';
		return html;
	}
	
	return {
		leftMenuBar:leftMenuBar
	};
}
$(document).ready(function(){
	window.menu = adminmenu();
	menu.leftMenuBar();
});