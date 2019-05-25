<%@ page pageEncoding="UTF-8"%>
 		<%-- 添加导航条 --%>
  		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<%-- 导航头 --%>
				<div class="navbar-header">
					<%-- 品牌图标 --%>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/home">
						<%-- 使用字体图标来代表品牌图标 --%>
						<span class="glyphicon glyphicon-book" aria-hidden="true"></span>
					</a>
				</div>
				<%-- 导航主体 --%>
				<div class="collapse navbar-collapse">
					<%-- 顶部导航菜单 --%>
					<ul class="nav navbar-nav">
						<li${empty sessionScope.__s ? ' class="active"' : ''}>
							<a href="${pageContext.request.contextPath}/home?__s=">${empty sessionScope.__s ? '<b>' : ''}首页${empty sessionScope.__s ? '</b>' : ''}</a>
						</li>
						<li${sessionScope.__s eq 'member' ? ' class="active"' : ''}><a href="${pageContext.request.contextPath}/member/memberList?__s=member">${sessionScope.__s eq 'member' ? '<b>' : ''}用户管理${sessionScope.__s eq 'member' ? '</b>' : ''}</a></li>
						<li${sessionScope.__s eq 'book' ? ' class="active"' : ''}><a href="${pageContext.request.contextPath}/book/bookList?__s=book">${sessionScope.__s eq 'book' ? '<b>' : ''}图书管理${sessionScope.__s eq 'book' ? '</b>' : ''}</a></li>
						<li${sessionScope.__s eq 'borrow' ? ' class="active"' : ''}><a href="${pageContext.request.contextPath}/borrow/borrowList?__s=borrow">${sessionScope.__s eq 'borrow' ? '<b>' : ''}借阅管理${sessionScope.__s eq 'borrow' ? '</b>' : ''}</a></li>
					</ul>
					<%-- 右侧导航注销链接 --%>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="${pageContext.request.contextPath}/logout">注销</a>
						</li>
					</ul>
				</div>
			</div>
  		</nav>