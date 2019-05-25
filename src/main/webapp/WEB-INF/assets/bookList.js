var BookList = function() {

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
			var author = $("#author").val();

			//拼接URL
			var url = "bookList?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&name=" + name + "&author=" + author;

			//发起请求
			location.href = url;
		});
	}

	function removeBook() {
		//查找删除图书询问框下的“确定”按钮，并为其注册单击事件，单击时删除对应图书
		var btnDeleteBook = $("#confirmModal button[class*=btn-primary]").click(function() {
			//取出当前按钮上通过btnDeleteBook.attr("data", id)存储的图书ID
			var id = $(this).attr("data");
			//向服务器端处理程序doDeleteBook.jsp发送删除请求，同时传递被删除的图书ID
			location.href = "deleteBook?id=" + id;
		});

		//查找表格下的“删除”按钮，并为其注册单机事件，单击时弹出询问框
		$(".table button[type=button]").click(function() {
			//获取当前按钮上通过data=${book.id}存储的图书ID
			var id = $(this).attr("data");
			//把图书ID写入删除图书询问框下的“确定”按钮的data属性，以便在单击确定按钮时取出
			btnDeleteBook.attr("data", id);
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
			removeBook();
			removeMsg();
		}
	}
}();

$(function() {
	BookList.init();	
});