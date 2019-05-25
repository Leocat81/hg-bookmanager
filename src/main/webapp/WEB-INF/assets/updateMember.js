$(function() {
	//注册修改用户表单的验证事件
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
			//被验证的“姓名”表单字段
			name: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "姓名不能为空"
					},
					//长度验证器
					stringLength: {
						max: 20,
						message: "姓名长度不能超过20个字符"
					}
				}
			},

			//被验证的“身份证号”表单字段
			identityCard: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "身份证号不能为空"
					},
					//长度验证器
					stringLength: {
						min: 18,
						max: 18,
						message: "身份证号长度必须是18个字符"
					},
					//Ajax验证器
					remote: {
						url: "checkExistsByIdentityCard",//默认只传递identityCard参数值
						type: "post",
						message: "身份证号已经存在，请更换",
						//动态传递附加参数。因为当前是修改操作，还得传递用户ID
						data: function() {
							return {
								id: $("input[type=hidden]").val()
							}
						}
					}
				}
			},

			//被验证的“电话”表单字段
			phone: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "电话不能为空"
					},
					//长度验证器
					stringLength: {
						max: 15,
						message: "电话长度不能超过15个字符"
					},
					//Ajax验证器
					remote: {
						url: "checkExistsByPhone",//默认只传递phone参数值
						type: "post",
						message: "电话已经存在，请更换",
						//动态传递附加参数。因为当前是修改操作，还得传递用户ID
						data: function() {
							return {
								id: $("input[type=hidden]").val()
							}
						}
					}
				}
			}
		}
	});
});