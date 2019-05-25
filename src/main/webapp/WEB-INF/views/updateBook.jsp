<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<title>修改图书页</title>
	<%-- 引入boostrap validator核心CSS文件 --%>
    <link href="${pageContext.request.contextPath}/assets/bootstrapvalidator-0.5.3-dist/css/bootstrapValidator.min.css" rel="stylesheet">
    <%-- 引入boostrap datetimepicker核心CSS文件 --%>
    <link href="${pageContext.request.contextPath}/assets/bootstrapdatetimepicker-4.17.47-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>

<content tag="breadcrumb">
	<li><a href="bookList">图书管理</a></li>
	<li>修改图书</li>
</content>

<content tag="plugin.js">
	<%-- 引入boostrap validator的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/bootstrapvalidator-0.5.3-dist/js/bootstrapValidator.min.js"></script>   	
    <%-- 引入moment的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/moment-2.10.0-dist/js/moment-with-locales.min.js"></script>
   	<%-- 引入boostrap datetimepicker的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/bootstrapdatetimepicker-4.17.47-dist/js/bootstrap-datetimepicker.min.js"></script>
</content>
<content tag="page.js">
   	<%-- 引入修改图书表单验证的脚本文件 --%>
    <script src="${pageContext.request.contextPath}/assets/updateBook.js"></script>
</content>

<content tag="body">
	<%-- 面板主体 --%>
	<div class="panel-body">
		<%-- 修改图书页页头 --%>
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
		  		<div class="page-header">
		  			<h3 class="text-center">修改图书</h3>
		  		</div>
			</div>
		</div>

  		<%-- 修改图书失败的提示消息 --%>
		<c:if test="${not empty requestScope.msg}">
	  		<div class="row">
				<div class="col-md-offset-3 col-md-6">
				    <div class="alert alert-danger" role="alert">${requestScope.msg}</div>
				</div>
	  		</div>
		</c:if>

  		<%-- 修改图书表单 --%>
	  	<form:form modelAttribute="book" cssClass="form-horizontal">
	  		<div class="form-group">
	  			<label for="name" class="col-md-3 control-label">书名：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-book" aria-hidden="true"></span>
		  				</div>
						<%-- 添加隐藏域，用于传递当前被修改的图书ID --%>
						<form:hidden path="id"/>
		  				<form:input path="name" cssClass="form-control" placeholder="请输入书名" />
	  				</div>
	  				<form:errors path="name" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="author" class="col-md-3 control-label">作者：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		  				</div>
		  				<form:input path="author" cssClass="form-control" placeholder="请输入作者" />
	  				</div>
	  				<form:errors path="author" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="price" class="col-md-3 control-label">价格：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-yen" aria-hidden="true"></span>
		  				</div>
		  				<form:input path="price" cssClass="form-control" placeholder="请输入价格" />
	  				</div>
	  				<form:errors path="price" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="num" class="col-md-3 control-label">册数：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-yen" aria-hidden="true"></span>
		  				</div>
		  				<form:input path="num" cssClass="form-control" placeholder="请输入册数" />
	  				</div>
	  				<form:errors path="num" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="createDate" class="col-md-3 control-label">出版时间：</label>
	  			<div class="col-md-6">
	  				<div class="input-group">
		  				<div class="input-group-addon">
		  					<span class="glyphicon glyphicon-time" aria-hidden="true"></span>
		  				</div>
		  				<form:input path="createDate" cssClass="form-control" placeholder="请输入出版时间" />
	  				</div>
	  				<form:errors path="createDate" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="publishing" class="col-md-3 control-label">出版社：</label>
	  			<div class="col-md-6">
	  				<form:select path="publishing.id" items="${sessionScope.publishings}" itemLabel="name" itemValue="id" cssClass="form-control" placeholder="请选择出版社">
	  				</form:select>
	  				<form:errors path="publishing.id" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<label for="summary" class="col-md-3 control-label">图书摘要：</label>
	  			<div class="col-md-6">
	  				<form:textarea path="summary" cssClass="form-control noresize" placeholder="请输入图书摘要" />
	  				<form:errors path="summary" />
	  			</div>
	  		</div>
	  		<div class="form-group">
	  			<div class="col-md-offset-3 col-md-9">
	  				<button type="submit" class="btn btn-primary">
	  					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 修改
	  				</button>
	  				<a href="bookList" class="btn btn-default">返回</a>
	  			</div>
	  		</div>
	  	</form:form>
	</div>
</content>