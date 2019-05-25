<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>spring mvc + spring + Mybatis - <decorator:title default="主页" /></title>

    <%-- 引入bootstrap核心CSS文件 --%>
    <link href="${pageContext.request.contextPath}/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <decorator:head />
    <%-- 引入自定义的CSS文件 --%>
    <link href="${pageContext.request.contextPath}/assets/styles/style.css" rel="stylesheet">
  </head>
  <body>
  	<div class="container">
		<page:applyDecorator name="header" />

  		<page:applyDecorator name="nav" />

  		<%-- 主体布局 --%>
  		<div class="row">
  			<%-- 左侧菜单占3份 --%>
			<div class="col-md-3">
				<page:applyDecorator name="menu" />
			</div>
			<%-- 右侧内容占9份 --%>
			<div class="col-md-9">
				<%-- 路径导航 --%>
				<ol class="breadcrumb">
					<decorator:getProperty property="page.breadcrumb" />
				</ol>
				<%-- 带标题的面板 --%>
				<div class="panel panel-default">
					<decorator:getProperty property="page.body" />
				</div>
			</div>  			
  		</div>
  		
  		<page:applyDecorator name="footer" />
  	</div>

    <%-- 引入jQuery的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/jquery-3.2.1.min.js"></script>
    <%-- 引入bootstrap的核心库文件 --%>
    <script src="${pageContext.request.contextPath}/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <%-- 引入第三方插件的核心库文件 --%>
    <decorator:getProperty property="page.plugin.js" />
    <%-- 引入当前页面用户自定义的库文件 --%>
    <decorator:getProperty property="page.page.js" />
  </body>
</html>