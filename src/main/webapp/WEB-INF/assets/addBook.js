$(function() {
	//渲染日历控件
	$("#createDate").datetimepicker({
		format: "YYYY-MM-DD",				//指定日期格式
		locale: moment.locale("zh-cn")		//指定语言
	});
	//注册添加图书表单的验证事件
	$("form").bootstrapValidator({
		//验证中使用到的字体图标
		feedbackIcons: {
			//验证通过时的图标
			valid: "glyphicon glyphicon-ok",
			//验证未通过的图标
			invalid: "glyphicon glyphicon-remove",
			//验证过程中使用的图标
			validating: "glyphicon glyphicon-refresh"
		},
		//被验证的字段集合
		fields: {
			//被验证的“书名”表单字段
			name: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "书名不能为空"
					},
					//长度验证器
					stringLength: {
						max: 50,
						message: "书名长度不能超过50个字符"
					},
					//Ajax验证器
					remote: {
						url: "checkExistsByName",
						type: "post",
						message: "书名已经存在，请更换"
					}
				}
			},

			//被验证的“作者”表单字段
			author: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "作者不能为空"
					},
					//长度验证器
					stringLength: {
						max: 20,
						message: "作者长度不能超过20个字符"
					}
				}
			},

			//被验证的“价格”表单字段
			price: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "价格不能为空"
					},
					//范围验证器
					between: {
						min: 0,
						max: 10000000,
						inclusive: false,	//是否包含最小值和最大值
						message: "价格必须大于0，且小于10000000"
					}
				}
			},

			//被验证的“出版时间”表单字段
			createDate: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "出版时间不能为空"
					}
				}
			},

			//被验证的“出版社”表单字段
			publishing: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "出版社不能为空"
					},
					//长度验证器
					stringLength: {
						max: 50,
						message: "出版社长度不能超过50个字符"
					}
				}
			},

			//被验证的“图书摘要”表单字段
			summary: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "图书摘要不能为空"
					},
					//长度验证器
					stringLength: {
						max: 200,
						message: "图书摘要长度不能超过200个字符"
					}
				}
			}
		}
	});
});