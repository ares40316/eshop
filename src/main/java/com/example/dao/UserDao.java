package com.example.dao;

import com.example.pojo.entity.user.User;

public interface UserDao {
    
    /**
     * 根據使用者的帳號和密碼，查詢使用者資料。
     * 
     * @param user 包含使用者登入資訊的物件，通常是帳號與密碼
     * @return 如果找到匹配的使用者資料，則回傳 User 物件；否則回傳 null
     */
    public User getUsername(User user);

    /**
     * 新增一個使用者資料到資料庫。
     * 
     * @param user 要新增的 User 物件，包含使用者的所有資料
     * @return 無回傳值，成功則資料會被儲存至資料庫
     */
    public void addUser(User user);
    
    /**
     * 根據 loginId 查詢使用者資料
     * 
     * @param loginId 使用者登入帳號
     * @return 如果找到，返回對應的 User 物件；否則返回 null
     */
    public User findUserByUsername(String username);
    
    /**
     * 根據電話查詢使用者資料。
     * @param tel 使用者的電話
     * @return 如果找到該電話對應的使用者，返回 User 物件；否則返回 null
     */
    public User findUserByPhone(String phone);
    
    /**
     * 根據信箱查詢使用者資料。
     * @param email 使用者的信箱
     * @return 如果找到該信箱對應的使用者，返回 User 物件；否則返回 null
     */
    public User findUserByEmail(String email);
}
