<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>借阅管理页</title>

<content tag="breadcrumb">
	<li>借阅管理</li>
</content>

<content tag="page.js">
   	<%-- 引入借阅列表的脚本文件 --%>
    <script src="${pageContext.request.contextPath}/assets/borrowList.js"></script>
</content>

<content tag="body">
	<%-- 删除借阅后的提示消息 --%>
	<c:if test="${not empty sessionScope.msg}">
	<div class="panel-title">
		<div class="alert alert-info alert-dismissible fade in">
			<button type="button" class="close" data-dismiss="alert">
				<span>&times;</span>
			</button>
			${sessionScope.msg}
		</div>
	</div>
	<%-- 刚刚显示完消息，删除session作用域的key值为msg的对象 --%>
	<c:remove var="msg" scope="session" />
</c:if>
	<%-- 面板主体 --%>
	<div class="panel-body">
	  	<form:form method="get" modelAttribute="searchBorrow" cssClass="form-inline">
			<div class="form-group">
				<label for="bookName">书名：</label>
		  		<form:input path="bookName" cssClass="form-control" placeholder="书名，支持模糊匹配" />
			</div>
			<div class="form-group">
				<label for="memberName">借阅者姓名：</label>
		  		<form:input path="memberName" cssClass="form-control" placeholder="借阅者姓名，支持模糊匹配" />
			</div>
			<button type="submit" class="btn btn-primary">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 搜索
			</button>
			<a href="addBorrow" class="btn btn-default">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加借阅
			</a>
	  	</form:form>
	</div>
	
	<%-- 以表格显示借阅数据 --%>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>书名</th>
				<th>借阅者</th>
				<th>借书时间</th>
				<th>归还时间</th>
				<th class="text-center">操作</th>
			</tr>
		</thead>
		<tbody>
			<%-- 有借阅数据 --%>
			<c:set var="pager" value="${requestScope.pagination}"></c:set>
			<c:if test="${pager.totalElements gt 0}" var="flag">
				<%-- 以表格形式显示借阅数据 --%>
				<c:forEach var="borrow" items="${pager.pageList}">
					<tr>
						<td>${borrow.book.name}</td>
						<td>${borrow.member.name}</td>
						<td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${borrow.returnDate}" pattern="yyyy-MM-dd"/></td>
						<td class="text-center">
							<c:if test="${empty borrow.returnDate}" var="flag2">
								<button type="button" data="${borrow.id}" class="btn btn-default btn-xs">
									<span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> 归还
								</button>
							</c:if>
							<c:if test="${not flag2}">
								<button type="button" data="${borrow.id}" class="btn btn-default btn-xs">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除
								</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<%-- 显示分页连接 --%>
				<tr>
					<td colspan="5">
						<%-- 分页 --%>
						<nav>
							<ul class="pager">
								<c:if test="${pager.isFirst}" var="isFirst">
									<li class="disabled"><a href="javascript:;">首页</a></li>
									<li class="disabled"><a href="javascript:;">上一页</a></li>
								</c:if>
								<c:if test="${not isFirst}">
									<li><a pageIndex="0" pageSize="${pager.pageSize}" href="javascript:;">首页</a></li>
									<li><a pageIndex="${pager.previousIndex}" pageSize="${pager.pageSize}" href="javascript:;">上一页</a></li>
								</c:if>
								<c:if test="${pager.isLast}" var="isLast">
									<li class="disabled"><a href="javascript:;">下一页</a></li>
									<li class="disabled"><a href="javascript:;">尾页</a></li>
								</c:if>
								<c:if test="${not isLast}">
									<li><a pageIndex="${pager.nextIndex}" pageSize="${pager.pageSize}" href="javascript:;">下一页</a></li>
									<li><a pageIndex="${pager.totalPages}" pageSize="${pager.pageSize}" href="javascript:;">尾页</a></li>
								</c:if>
							</ul>
						</nav>
					</td>
				</tr>
			</c:if>
			<%-- 没有借阅数据 --%>
			<c:if test="${not flag}">
				<tr>
					<td colspan="5" class="text-center">
						没有更多借阅数据
					</td>
				</tr>
			</c:if>			
		</tbody>
	</table>

  	<%-- 归还借阅图书前的询问框 --%>
	<div class="modal fade bs-example-modal-sm" id="confirmReturnModal">
		<div class="modal-dialog modal-sm">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal">
	        			<span>&times;</span>
	        		</button>
	        		<h4 class="modal-title">提示</h4>
	      		</div>
	      		<div class="modal-body">
	        		你确定要归还吗？
	      		</div>
	      		<div class="modal-footer">
	      			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        		<button type="button" class="btn btn-primary">确定</button>
	      		</div>
	    	</div>
		</div>
	</div>
	
  	<%-- 删除借阅前的询问框 --%>
	<div class="modal fade bs-example-modal-sm" id="confirmRemoveModal">
		<div class="modal-dialog modal-sm">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal">
	        			<span>&times;</span>
	        		</button>
	        		<h4 class="modal-title">提示</h4>
	      		</div>
	      		<div class="modal-body">
	        		你确定要删除吗？
	      		</div>
	      		<div class="modal-footer">
	      			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        		<button type="button" class="btn btn-primary">确定</button>
	      		</div>
	    	</div>
		</div>
	</div>
</content>