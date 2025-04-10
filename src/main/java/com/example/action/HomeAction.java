package com.example.action;

public class HomeAction extends BaseAction {

private static final long serialVersionUID = 1L;
    
    // 用來接收表單提交的身份參數（值為 "user" 或 "admin"）
    private String identity;
    
    @Override
    public String execute() {
        if ("admin".equalsIgnoreCase(identity)) {
            // 若身份為管理者，返回 "adminLogin" 結果
            return "adminLogin";
        } else if ("user".equalsIgnoreCase(identity)) {
            // 若身份為一般會員，返回 "userLogin" 結果
            return "userLogin";
        }
        // 若身份未選擇或不正確，返回 INPUT（通常保持在目前畫面）
        return INPUT;
    }
    
    // Getter 與 Setter
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
