<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${not empty sessionScope.session_user_msg}">
  <script type="text/javascript">
    alert('${fn:replace(sessionScope.session_user_msg, "\'", "\\\'")}');
  </script>
  <c:remove var="session_user_msg" scope="session"/>
</c:if>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<title>用戶註冊</title>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-color: #f8f9fa;
}

.card {
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.card-header {
	background-color: #007bff;
	color: #fff;
}
</style>
</head>
<body>
	<div class="container mt-5">

		<!--顯示 Struts 的錯誤訊息（例如帳號重複訊息）
    <div class="row justify-content-center">
      <div class="col-md-6">
        <s:actionerror />
      </div>
    </div>--> 
		<!-- 註冊表單 -->
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header text-center">
						<h4>用戶註冊</h4>
					</div>
					<div class="card-body">
						<!-- 表單 action 指向 save 方法 -->
						<s:form action="save" method="post" cssClass="form" theme="simple">
							<div class="form-group">
								<label for="username">用戶名</label>
								<s:textfield name="user.username" id="username"
									cssClass="form-control" maxlength="20" required="true"
									placeholder="必填，20字以內" autocomplete="username" />
							</div>
							<div class="form-group">
								<label for="password">密碼</label>
								<s:password name="user.password" id="password"
									cssClass="form-control" maxlength="20" required="true"
									placeholder="必填，20字以內" autocomplete="new-password" />
							</div>
							<div class="form-group">
								<label for="email">電子郵件</label>
								<s:textfield name="user.email" id="email"
									cssClass="form-control" type="email"
									placeholder="example@domain.com" />
							</div>
							<div class="form-group">
								<label for="phone">電話</label>
								<s:textfield name="user.phone" id="phone"
									cssClass="form-control" maxlength="12" placeholder="0912345678"
									autocomplete="tel" />
							</div>
							<div class="form-group text-center">
								<s:submit value="註冊" cssClass="btn btn-primary" />
							</div>
						</s:form>
					</div>
					<div class="card-footer text-center">
						<a href="login.jsp">已有帳號？點此登入</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="display: none;">
		<s:property value="#session['SESSION_USER_MSG']" />
	</div>

	<!-- 引入 jQuery (完整版) 與 Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
