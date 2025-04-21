<%@ page contentType="text/html;charset=UTF-8"
	import="com.example.pojo.entity.user.User"%>
<%
User user = (User) session.getAttribute("user");
if (user != null && user.getUsername() != null && !"".equals(user.getUsername())) {
	response.sendRedirect("home/index");
	return;
}

request.setAttribute("title", "會員登入");
request.setAttribute("action", "/user/doLogin");
%>
<jsp:forward page="/WEB-INF/views/login.jsp" />
