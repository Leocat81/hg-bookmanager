$(function() {
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
			//被验证的“用户名”表单字段
			username: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "用户名不能为空"
					}
				}
			},

			//被验证的“密码”表单字段
			password: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "密码不能为空"
					}
				}
			},
			phone: {
				//验证器集合
				validators: {
					//非空验证器
					notEmpty: {
						//验证未通过时的提示消息
						message: "手机号不能为空"
					}
				}
			}
		}
	});
});