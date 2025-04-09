<%@ page contentType="text/html;charset=UTF-8" %>
<%
    request.setAttribute("title", "管理員登入");
    request.setAttribute("action", "admin/doLogin");
%>
<jsp:forward page="/WEB-INF/views/login.jsp" />
