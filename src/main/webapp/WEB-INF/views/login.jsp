<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>图书管理系统 - 登录</title>

    <%-- 引入bootstrap核心CSS文件 --%>
    <link href="${pageContext.request.contextPath}/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%-- 引入boostrap validator核心CSS文件 --%>
    <link href="${pageContext.request.contextPath}/assets/bootstrapvalidator-0.5.3-dist/css/bootstrapValidator.min.css" rel="stylesheet">
  </head>
  <body>
  	<div class="container">
  		<%-- 添加页头 --%>
  		<div class="row">
			<div class="col-md-offset-4 col-md-4">
		  		<div class="page-header">
		  			<h1 class="text-center">用户登录</h1>
		  		</div>
			</div>
  		</div>

  		<%-- 登录表单 --%>
  		<form:form action="login" modelAttribute="user" cssClass="form-horizontal">
  			<%-- 表单元素都需要样式form-group --%>
	  		<div class="form-group">
	  			<label for="username" class="col-md-4 control-label">用户名：</label>
	  			<div class="col-md-4">
	  				<form:input path="username" cssClass="form-control" placeholder="请输入用户名" />
	  				<form:errors path="username" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="password" class="col-md-4 control-label">密码：</label>
	  			<div class="col-md-4">
	  				<%-- 要在文本框前或后添加图标按钮，才需要input-group --%>
	  				<div class="input-group">
		  				<%-- 图标按钮的语法，中间的span可以换成文本或其它图标 --%>
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
		  				</div>
		  				<form:password path="password" cssClass="form-control" placeholder="请输入密码" />
	  				</div>
	  				<form:errors path="password" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<%-- col-md-offset-4表示左侧推开4份宽度，这样做的目的是希望按钮和上面的文本框左侧纵向对齐 --%>
	  			<div class="col-md-offset-4 col-md-8">
	  				<button type="submit" class="btn btn-primary">登录</button>
	  				<a href="/hg-bookmanager/register" class="btn">免费注册</a>
	  			</div>
	  		</div>
  		</form:form>
  	</div>

    <%-- 引入jQuery的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/jquery-3.2.1.min.js"></script>
    <%-- 引入bootstrap的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
   	<%-- 引入boostrap validator的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/bootstrapvalidator-0.5.3-dist/js/bootstrapValidator.min.js"></script>
   	<%-- 引入登录表单验证的脚本文件 --%>
    <script src="${pageContext.request.contextPath}/assets/login.js"></script>
  </body>
</html>