var BorrowList = function() {
	var confirmReturnModal = $("#confirmReturnModal");
	var confirmRemoveModal = $("#confirmRemoveModal");

	function pagination() {
		//分页（可能带查询条件）
		$("a[pageIndex][pageSize]").click(function(e) {
			//禁用默认事件
			e.preventDefault();

			//获取存储在超链接上的分页数据
			var pageIndex = $(this).attr("pageIndex");
			var pageSize = $(this).attr("pageSize");

			//获取表单里的查询条件
			var bookName = $("#bookName").val();
			var memberName = $("#memberName").val();

			//拼接URL
			var url = "borrowList?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&bookName=" + bookName + "&memberName=" + memberName;

			//发起请求
			location.href = url;
		});
	}

	function returnBorrow() {
		//查找归还借阅询问框下的“确定”按钮，并为其注册单击事件，单击时归还对应图书
		var btnReturnBorrow = confirmReturnModal.find("button[class*=btn-primary]").click(function() {
			//取出当前按钮上通过btnDeleteBook.attr("data", id)存储的借阅ID
			var id = $(this).attr("data");
			//向服务器端处理程序/borrow/returnBorrow?id=" + id发送删除请求，同时传递被删除的借阅ID
			location.href = "/hg-bookmanager/borrow/returnBorrow?id=" + id;
		});

		$(".table").find("button:contains('归还')").click(function() {
			//显示询问框
			confirmReturnModal.modal("show");
			//获取当前按钮上通过data=${borrow.id}存储的借阅ID
			var id = $(this).attr("data");
			//把借阅ID写入归还图书询问框下的“确定”按钮的data属性，以便在单击确定按钮时取出
			btnReturnBorrow.attr("data", id);
		});
	}

	function removeBorrow() {
		//查找删除借阅询问框下的“确定”按钮，并为其注册单击事件，单击时删除对应借阅
		var btnRemoveBorrow = confirmRemoveModal.find("button[class*=btn-primary]").click(function() {
			//取出当前按钮上通过btnDeleteBook.attr("data", id)存储的借阅ID
			var id = $(this).attr("data");
			//向服务器端处理程序doDeleteBorrow.jsp发送删除请求，同时传递被删除的借阅ID
			location.href = "deleteBorrow?id=" + id;
		});

		$(".table").find("button:contains('删除')").click(function() {
			//显示询问框
			confirmRemoveModal.modal("show");
			//获取当前按钮上通过data=${borrow.id}存储的借阅ID
			var id = $(this).attr("data");
			//把借阅ID写入删除借阅询问框下的“确定”按钮的data属性，以便在单击确定按钮时取出
			btnRemoveBorrow.attr("data", id);
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
			returnBorrow();
			removeBorrow();
			removeMsg();
		}
	}
}();

$(function() {
	BorrowList.init();
});