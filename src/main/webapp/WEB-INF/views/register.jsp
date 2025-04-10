<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
    String userMsg = (String) session.getAttribute("session_user_msg");
    if (userMsg != null) {
%>
  <script type="text/javascript">
          alert("<%= userMsg %>");
  </script>
<%
      session.removeAttribute("session_user_msg");
    }
%>
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
  
    <!-- 顯示 Struts 的錯誤訊息（例如帳號重複訊息） 
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
                <label for="name">姓名</label>
                <s:textfield name="name" id="name" cssClass="form-control" autocomplete="name" />
              </div>
              <div class="form-group">
                <label for="loginId">帳號</label>
                <s:textfield name="loginId" id="loginId" cssClass="form-control" autocomplete="username" />
              </div>
              <div class="form-group">
                <label for="password">密碼</label>
                <s:password name="password" id="password" cssClass="form-control"  autocomplete="new-password" />
              </div>
              <div class="form-group">
                <label for="tel">電話</label>
                <s:textfield name="tel" id="tel" cssClass="form-control" autocomplete="tel" />
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

<div style="display:none;">
  <s:property value="#session['SESSION_USER_MSG']"/>
</div>

  <!-- 引入 jQuery (完整版) 與 Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
 

</body>
</html>
