var MemberList = function() {
	function pagination() {
		//分页（可能带查询条件）
		$("a[pageIndex][pageSize]").click(function(e) {
			//禁用默认事件
			e.preventDefault();
			
			//获取存储在超链接上的分页数据
			var pageIndex = $(this).attr("pageIndex");
			var pageSize = $(this).attr("pageSize");

			//获取表单里的查询条件
			var name = $("#name").val();
			var phone = $("#phone").val();

			//拼接URL
			var url = "memberList?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&name=" + name + "&phone=" + phone;

			//发起请求
			location.href = url;
		});		
	}

	function removeMember() {
		//查找删除用户询问框下的“确定”按钮，并为其注册单击事件，单击时删除对应用户
		var btnDeleteMember = $("#confirmModal button[class*=btn-primary]").click(function() {
			//取出当前按钮上通过btnDeleteMember.attr("data", id)存储的用户ID
			var id = $(this).attr("data");
			//向服务器端处理程序发送删除请求，同时传递被删除的用户ID
			location.href = "deleteMember?id=" + id;
		});

		//查找表格下的“删除”按钮，并为其注册单机事件，单击时弹出询问框
		$(".table button[type=button]").click(function() {
			//获取当前按钮上通过data=${Member.id}存储的用户ID
			var id = $(this).attr("data");
			//把用户ID写入删除用户询问框下的“确定”按钮的data属性，以便在单击确定按钮时取出
			btnDeleteMember.attr("data", id);
		});		
	}

	function removeMsg() {
		//设定定时器，3秒钟后先淡出、再删除class="panel-title"的div
		//setInterval(function() {}, 3000);//每间隔3秒钟执行一次function() {}
		//setTimeout(function() {}, 3000);//3秒钟之后仅仅执行一次function() {}
		setTimeout(function() {
			//fadeOut()		淡出(即慢慢的出现的意思)
			$(".panel-title").fadeOut(function() {
				//remove()	删除
				$(this).remove();
			});
		}, 3000);
	}

	return {
		init: function() {
			pagination();
			removeMember();
			removeMsg();
		}
	}
}();
$(function() {
	MemberList.init();
});