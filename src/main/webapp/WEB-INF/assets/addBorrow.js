var AddBorrow = function(){
	//查找提示框
	var infoModal = $("#infoModal");
	//存储用户选中行的图书ID
	var bookIds = [];
	//数据显示容器
	var tbody = $("tbody");
	//添加借阅表单
	var addBorrowForm = $("#addBorrowForm");
	//1.查找模板
	var bookTmpl = $("#bookTmpl").html();
	//2.编译模板
	var template = Handlebars.compile(bookTmpl);

	function ajax(data) {
		//3.发起AJAX请求，取得当前页的图书数据
		$.ajax({
			url: "/hg-bookmanager/borrow/bookList",
			type: "get",
			data: data,
			dataType: "json",
			success: function(data) {
				console.debug(data);
				//4.使用模板
				var newHtml = template(data);
				tbody.html(newHtml);

				//反选
				tbody.find("tr:not(:last)").each(function(index, item) {
					var tr = $(item);
					var bookId = tr.attr("data");
					for (var i = 0; i < bookIds.length; i++) {
						if (bookIds[i] == bookId) {
							tr.addClass("bg-primary");
							break;
						}
					}
				});
			}
		});
	}

	//分页按钮单击处理
	function pagination() {
		$(".table").on("click", "a[pageIndex][pageSize]", function(e) {
			//禁用默认事件
			e.preventDefault();

			//获取存储在超链接上的分页数据
			var pageIndex = $(this).attr("pageIndex");
			var pageSize = $(this).attr("pageSize");

			//获取表单里的查询条件
			var keyword = $("#keyword").val();

			//调用AJAX函数
			ajax({pageIndex: pageIndex, pageSize: pageSize, keyword: keyword});
		});
	}

	//搜索按钮单击处理
	function search() {
		$(".form-inline button").click(function() {
			//获取表单里的查询条件
			var keyword = $("#keyword").val();

			//调用AJAX函数
			ajax({keyword: keyword});
		});
	}

	//单击选中行处理
	function selectedRows() {
		$(".table").on("click", "tbody tr:not(:last)", function(e) {
			var tr = $(this);

			var bookId = tr.attr("data");
			
//			tr.toggleClass("bg-primary");

			if (tr.hasClass("bg-primary")) {
				tr.removeClass("bg-primary");
				//取消当前行，把当前行对应的图书ID从数组中移除
				var index = -1;
				for (var i = 0; i < bookIds.length; i++) {
					if (bookIds[i] == bookId) {
						index = i;
						break;
					}
				}
				bookIds.splice(index, 1);
			} else {
				tr.addClass("bg-primary");
				//选中当前行，把当前行对应的图书ID存入数组
				bookIds.push(bookId);
			}
			
			console.debug(bookIds);
		});
	}

	//"添加"按钮单击处理
	function addBorrow() {
		addBorrowForm.validate({
			errorElement: "span",
			errorClass: "help-block",
			rules: {
                identityCard: {
                    required: true,
					rangelength: [18,18],
		        	remote: "/hg-bookmanager/borrow/checkNotExistsByIdentityCard"
                }
            },

            messages: {
            	identityCard: {
                    required: "身份证号不能为空",
					rangelength: "身份证号必须是18位",
		        	remote: "身份证号不存在，请重新输入"
                }
            },

            highlight: function(element) {
                $(element)
                    .closest(".form-group").addClass("has-error");
            },

            unhighlight: function(element) {
                $(element)
                    .closest(".form-group").removeClass("has-error");
            },

            success: function(label) {
                label
                    .closest(".form-group").removeClass("has-error");
            }
		});

		addBorrowForm.find("button").click(function() {
        	if (bookIds.length == 0) {
        		infoModal.modal("show");
        		return;
        	}
        	
        	$.each(bookIds, function(index, bookId) {
				$("<input />", {type: "hidden", name: "bookIds", value: bookId}).appendTo(addBorrowForm);
        	});
        	
        	addBorrowForm.submit();
		});
	}

	return {
		init: function() {
			ajax();
			pagination();
			search();
			selectedRows();
			addBorrow();
		}
	};
}();

$(function() {
	AddBorrow.init();
});