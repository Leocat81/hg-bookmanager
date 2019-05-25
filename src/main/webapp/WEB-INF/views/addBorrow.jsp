<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
	<title>添加借阅页</title>
</head>

<content tag="breadcrumb">
	<li><a href="${pageContext.request.contextPath}/borrow/borrowList">借阅管理</a></li>
	<li>添加借阅</li>
</content>

<content tag="plugin.js">
    <%-- 引入Handlebars模板引擎核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/handlebars-v3.0.1.js"></script>
   	<%-- 引入boostrap validator的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/jqueryvalidation-1.16.0-dist/jquery.validate.min.js"></script>
</content>
<content tag="page.js">
   	<%-- 引入添加借阅表单验证的脚本文件 --%>
    <script src="${pageContext.request.contextPath}/assets/addBorrow.js"></script>
</content>

<content tag="body">
	<div class="row">
		<div class="col-md-7">
			<%-- 带标题的面板 --%>
			<div class="panel panel-default">
				<%-- 面板主体 --%>
				<div class="panel-body">
					<%-- 按书名或作者搜索的表单 --%>
					<form class="form-inline">
						<div class="form-group">
							<label for="keyword">关键字：</label>
							<input type="text" name="keyword" id="keyword" class="form-control" placeholder="按书名或作者模糊匹配" value="${requestScope.keyword}">
						</div>
						<button type="button" class="btn btn-primary">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
						</button>
					</form>
				</div>

				<%-- 以表格显示图书数据 --%>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>书名</th>
							<th>作者</th>
							<th>出版社</th>
							<th>出版时间</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-5">
			<%-- 带标题的面板 --%>
			<div class="panel panel-default">
				<%-- 面板主体 --%>
				<div class="panel-body">
					<%-- 添加借阅页页头 --%>
					<div class="row">
						<div class="col-md-offset-1 col-md-10">
					  		<div class="page-header">
					  			<h3 class="text-center">添加借阅</h3>
					  		</div>
						</div>
					</div>

			  		<%-- 添加借阅失败的提示消息 --%>
					<c:if test="${not empty requestScope.msg}">
				  		<div class="row">
							<div class="col-md-offset-1 col-md-10">
							   <div class="alert alert-danger" role="alert">${requestScope.msg}</div>
					  		</div>
						</div>
					</c:if>

			  		<%-- 添加借阅表单 --%>
			  		<form:form id="addBorrowForm" modelAttribute="addBorrow">
				  		<div class="form-group">
				  			<label for="identityCard" class="control-label">身份证号：</label>
				  			<form:input path="identityCard" cssClass="form-control" placeholder="请输入身份证号"/>
				  			<form:errors path="identityCard" />
				  		</div>
				  		<div class="form-group text-center">
			  				<button type="button" class="btn btn-primary">
			  					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加
			  				</button>
			  				<a href="borrowList" class="btn btn-default">返回</a>
				  		</div>
			  		</form:form>
				</div>
			</div>
		</div>
	</div>

  	<%-- 没有选择图书的提示框 --%>
	<div class="modal fade bs-example-modal-sm" id="infoModal">
		<div class="modal-dialog modal-sm">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal">
	        			<span>&times;</span>
	        		</button>
	        		<h4 class="modal-title">提示</h4>
	      		</div>
	      		<div class="modal-body">
	        		请至少选择一本图书！
	      		</div>
	      		<div class="modal-footer">
	      			<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
	      		</div>
	    	</div>
		</div>
	</div>

  	<script id="bookTmpl" type="text/x-handlebars-template">
		<%-- 有图书数据 --%>
		{{#if pageList}}
			<%-- 以表格形式显示图书数据 --%>
			{{#each pageList}}
				<tr data="{{this.id}}">
					<td>{{this.name}}</td>
					<td>{{this.author}}</td>
					<td>{{this.publishing.name}}</td>
					<td>{{this.createDate}}</td>
				</tr>
			{{/each}}
			<%-- 显示分页连接 --%>
			<tr>
				<td colspan="4">
					<%-- 分页 --%>
					<nav>
						<ul class="pager">
							{{#if isFirst}}
								<li class="disabled"><a href="javascript:;">首页</a></li>
								<li class="disabled"><a href="javascript:;">上一页</a></li>
							{{else}}
								<li><a pageIndex="1" pageSize={{pageSize}} href="javascript:;">首页</a></li>
								<li><a pageIndex={{previousIndex}} pageSize={{pageSize}} href="javascript:;">上一页</a></li>
							{{/if}}
							{{#if isLast}}
								<li class="disabled"><a href="javascript:;">下一页</a></li>
								<li class="disabled"><a href="javascript:;">尾页</a></li>
							{{else}}
								<li><a pageIndex={{nextIndex}} pageSize={{pageSize}} href="javascript:;">下一页</a></li>
								<li><a pageIndex={{totalPages}} pageSize={{pageSize}} href="javascript:;">尾页</a></li>
							{{/if}}
						</ul>
					</nav>
				</td>
			</tr>
		<%-- 没有图书数据 --%>
		{{else}}
			<tr>
				<td colspan="4" class="text-center">没有更多图书数据</td>
			</tr>
		{{/if}}
	</script>
</content>