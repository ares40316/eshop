<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<title>身份選擇 - 登入</title>
<!-- 使用 Bootstrap 4 CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background: linear-gradient(to right, #f8f9fa, #e9ecef);
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.login-selection {
	background-color: #fff;
	padding: 40px;
	border-radius: 12px;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
	max-width: 600px;
	width: 100%;
}

.login-selection h1 {
	margin-bottom: 35px;
	font-size: 26px;
	color: #343a40;
}

.btn-group-custom {
	display: flex;
	flex-direction: row;
	gap: 20px;
	justify-content: center;
}

@media ( max-width : 576px) {
	.btn-group-custom {
		flex-direction: column;
	}
}
</style>
</head>
<body>
	<div class="login-selection text-center">
		<h1>
			歡迎光臨沒良心網路商店<br>請先選擇身份登入
		</h1>
		<!-- 使用 Struts 表單，提交到 choose action -->
		<div class="btn-group-custom">
			<s:form action="/user/choose" method="post" cssClass="w-100">
                <s:hidden name="identity" value="user"/>
                <button type="submit" class="btn btn-success btn-lg btn-block">一般會員</button>
            </s:form>

            <s:form action="/user/choose" method="post" cssClass="w-100">
                <s:hidden name="identity" value="admin"/>
                <button type="submit" class="btn btn-primary btn-lg btn-block">管理者</button>
            </s:form>
		</div>
	</div>

	<!-- 引入 jQuery 與 Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>
