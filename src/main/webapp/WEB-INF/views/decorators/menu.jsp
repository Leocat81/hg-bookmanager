<%@ page pageEncoding="UTF-8"%>
				<%-- 列表组菜单 --%>
				<div class="list-group text-center">
					<a href="javascript:;" class="list-group-item disabled"><b>功能菜单</b></a>
					<a href="${pageContext.request.contextPath}/member/memberList?__s=member" class="list-group-item${sessionScope.__s eq 'member' ? ' active' : ''}">用户管理</a>
					<a href="${pageContext.request.contextPath}/book/bookList?__s=book" class="list-group-item${sessionScope.__s eq 'book' ? ' active' : ''}">图书管理</a>
					<a href="${pageContext.request.contextPath}/borrow/borrowList?__s=borrow" class="list-group-item${sessionScope.__s eq 'borrow' ? ' active' : ''}">借阅管理</a>
				</div>