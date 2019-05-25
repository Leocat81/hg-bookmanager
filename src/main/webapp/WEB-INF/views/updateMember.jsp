<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<title>修改用户页</title>
	<%-- 引入boostrap validator核心CSS文件 --%>
    <link href="${pageContext.request.contextPath}/assets/bootstrapvalidator-0.5.3-dist/css/bootstrapValidator.min.css" rel="stylesheet">
</head>

<content tag="breadcrumb">
	<li><a href="member/memberList">用户管理</a></li>
	<li>修改用户</li>
</content>

<content tag="plugin.js">
	<%-- 引入boostrap validator的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/bootstrapvalidator-0.5.3-dist/js/bootstrapValidator.min.js"></script>
</content>
<content tag="page.js">
   	<%-- 引入添加用户表单验证的脚本文件 --%>
    <script src="${pageContext.request.contextPath}/assets/updateMember.js"></script>
</content>

<content tag="body">
	<%-- 面板主体 --%>
	<div class="panel-body">
		<%-- 添加图书页页头 --%>
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
		  		<div class="page-header">
		  			<h3 class="text-center">修改用户</h3>
		  		</div>
			</div>
		</div>

  		<%-- 添加用户失败的提示消息 --%>
		<c:if test="${not empty requestScope.msg}">
	  		<div class="row">
				<div class="col-md-offset-3 col-md-6">
				    <div class="alert alert-danger" role="alert">${requestScope.msg}</div>
				</div>
	  		</div>
		</c:if>

  		<%-- 添加用户表单 --%>
	  	<form:form modelAttribute="member" cssClass="form-horizontal">
	  		<div class="form-group">
	  			<label for="name" class="col-md-3 control-label">真实姓名：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		  				</div>
		  				<form:hidden path="id"/>
		  				<form:input path="name" cssClass="form-control" placeholder="请输入真实姓名" />
	  				</div>
	  				<form:errors path="name" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="identityCard" class="col-md-3 control-label">身份证号：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>
		  				</div>
		  				<form:input path="identityCard" cssClass="form-control" placeholder="请输入身份证号" />
	  				</div>
	  				<form:errors path="identityCard" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="phone" class="col-md-3 control-label">联系电话：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
		  				</div>
		  				<form:input path="phone" cssClass="form-control" placeholder="请输入联系电话" />
	  				</div>
	  				<form:errors path="phone" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<div class="col-md-offset-3 col-md-9">
	  				<button type="submit" class="btn btn-primary">
	  					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 修改
	  				</button>
	  				<a href="memberList" class="btn btn-default">返回</a>
	  			</div>
	  		</div>
	  	</form:form>
	</div>
</content>