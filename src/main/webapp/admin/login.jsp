<%@ page contentType="text/html;charset=UTF-8"
	import="com.example.pojo.entity.admin.Admin"%>
<%
Admin admin = (Admin) session.getAttribute("admin");
if (admin != null && admin.getUsername() != null && !"".equals(admin.getUsername())) {
	response.sendRedirect("home/index");
	return;
}

request.setAttribute("title", "管理員登入系統");
request.setAttribute("action", "/admin/doLogin");
%>
<jsp:forward page="/WEB-INF/views/admin_login.jsp" />
