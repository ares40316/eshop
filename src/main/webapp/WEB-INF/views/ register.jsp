<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>會員註冊</title>
    <style>
        body { font-family: 微軟正黑體; background-color: #f7f7f7; }
        .container { width: 400px; margin: 50px auto; background: #fff; padding: 30px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .form-title { text-align: center; margin-bottom: 20px; font-size: 22px; }
        .btn { width: 100%; background: #6366f1; color: white; padding: 10px; border: none; border-radius: 6px; }
        .btn:hover { background: #4f46e5; }
        .msg { color: red; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-title">會員註冊</div>

        <s:form action="/user/doRegister" method="post">
            <s:textfield name="user.name" label="使用者名稱" required="true"/>
            <s:textfield name="user.loginId" label="帳號" required="true"/>
            <s:password name="user.password" label="密碼" required="true"/>
            <s:textfield name="user.tel" label="電話"/>
            <s:submit value="註冊" cssClass="btn"/>
        </s:form>

        <s:if test="#session.SESSION_USER_MSG != null">
            <div class="msg"><s:property value="#session.SESSION_USER_MSG"/></div>
        </s:if>
    </div>
</body>
</html>
