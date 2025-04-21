<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
    String adminMsg = (String) session.getAttribute("session_admin_msg");
    if (adminMsg != null) {
%>
      <script type="text/javascript">
          alert("<%= adminMsg %>");
      </script>
<%
      session.removeAttribute("session_admin_msg");
    }
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<title><c:out value="${title}" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: "微軟正黑體", sans-serif;
	background: linear-gradient(to bottom right, #f3f4f6, #e0e7ff);
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.login-box {
	background-color: #fff;
	padding: 40px;
	border-radius: 16px;
	box-shadow: 0 12px 28px rgba(0, 0, 0, 0.12);
	width: 100%;
	max-width: 380px;
}

h2 {
	text-align: center;
	margin-bottom: 24px;
	color: #374151;
}

label {
	display: block;
	margin-bottom: 6px;
	color: #4b5563;
	font-size: 14px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 12px;
	margin-bottom: 16px;
	border: 1px solid #d1d5db;
	border-radius: 8px;
	font-size: 15px;
	transition: border-color 0.3s;
}

input[type="text"]:focus, input[type="password"]:focus {
	border-color: #6366f1;
	outline: none;
}

.btn {
	width: 100%;
	padding: 12px;
	background-color: #6366f1;
	color: white;
	border: none;
	border-radius: 8px;
	font-weight: bold;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: #4f46e5;
}

.error {
	color: #dc2626;
	text-align: center;
	margin-top: 10px;
	font-size: 14px;
}

/* 成功訊息顯示區塊 */
.success {
	color: #15803d;
	background-color: #d1fae5;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #10b981;
	border-radius: 8px;
	text-align: center;
	font-size: 14px;
}

@media ( max-width : 480px) {
	.login-box {
		padding: 24px;
		border-radius: 12px;
	}
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function validateAndSubmit() {
		let username= $("input[name='username']").val().trim();
		let password = $("input[name='password']").val().trim();

		if (!username) {
			alert("管理員名稱不能為空");
			return false;
		}

		if (!password) {
			alert("密碼不能為空");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div class="login-box">
		<h2>
			<c:out value="${title}" />
		</h2>

		<!-- 顯示註冊成功訊息（從 session 帶入） -->
		<c:if test="${not empty sessionScope.SESSION_ADMIN_MSG}">
			<div class="success">${sessionScope.SESSION_ADMIN_MSG}</div>
		</c:if>

		<!-- 管理員登入表單 -->
		<s:form id="adminloginForm" action="%{#request.contextPath}/admin/doLogin"
			method="post" onsubmit="return validateAndSubmit();">
			<s:textfield name="username" key="管理員帳號" label="管理員帳號" required="true"
				cssClass="input" />
			<s:password name="password" key="管理員密碼" label="管理員密碼" required="true"
				cssClass="input" />
			<s:submit value="登入管理後台" cssClass="btn" />
		</s:form>

		<c:if test="${not empty msg}">
			<div class="error">${msg}</div>
		</c:if>
	</div>

</body>
</html>
