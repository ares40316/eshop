package com.example.action;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.UserService;
import com.example.constant.ConstantName;
import com.example.pojo.entity.user.User;

public class RegisterAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private User user;

	@Autowired
	private UserService userService;

	/**
	 * 使用模型驅動來封裝 User 物件
	 * 
	 * @return 返回 User 物件
	 */
	@Override
	public User getModel() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	/**
	 * 跳轉至註冊頁面
	 * 
	 * @return 返回註冊頁面
	 */
	public String register() {
		return SUCCESS;
	}

	/**
	 * 處理註冊邏輯
	 * 
	 * @return 註冊成功返回 SUCCESS，失敗返回 INPUT
	 */

	public String save() {
		// 註冊前的資料檢查
		if (!doCheck(user)) {
			addActionError("帳號已註冊，請重新註冊帳號");
			return INPUT;
		}

		try {
			// 設置註冊時間
			setUserCreateDate(user);

			// 呼叫 UserService 來保存使用者資料
			userService.addUser(user);

			// 註冊成功，將訊息設置到 session
			getSession().setAttribute(ConstantName.SESSION_USER_MSG, "註冊成功，請登入");
		} catch (Exception e) {
			addActionError("註冊失敗，請重試");
			// 註冊失敗，將錯誤訊息設置到 session
			 getSession().setAttribute(ConstantName.SESSION_USER_MSG, "註冊失敗，請重試");
			return INPUT;
		}

		return SUCCESS;
	}

	/**
	 * 註冊前資料檢查，這裡可以擴展更多檢查邏輯
	 * 
	 * @param user 註冊的使用者資料
	 * @return 若檢查通過返回 true，否則返回 false
	 */
	private boolean doCheck(User user) {
		// 先檢查基本欄位是否有填寫
		if (user == null || user.getUsername() == null || user.getUsername().trim().isEmpty()
				|| user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			return false;
		}

		// 檢查此帳號是否已經註冊
		// 假設 userService.findUserByUsername() 會返回已存在的使用者，若不存在則返回 null
		User existingUser = userService.findUserByUsername(user.getUsername());
		if (existingUser != null) {
			// 帳號已經存在，不允許重複註冊
			// 若電話已存在，透過 session 設置提示訊息，讓 register.jsp 顯示 alert
			getSession().setAttribute(ConstantName.SESSION_USER_MSG, "帳號已經存在，不允許重複註冊");
			return false;
		}

		// 檢查此信箱是否已經註冊
		// 假設 userService.findUserByEmail() 會返回已存在的使用者，若不存在則返回 null
		User existingEmail = userService.findUserByEmail(user.getEmail());
		if (existingEmail != null) {
			// 信箱已經存在，不允許重複註冊
			// 若信箱已存在，透過 session 設置提示訊息，讓 register.jsp 顯示 alert
			getSession().setAttribute(ConstantName.SESSION_USER_MSG, "信箱已經存在，不允許重複註冊");
			return false;
		}

		// 檢查電話是否已經存在
		User existingTel = userService.findUserByPhone(user.getPhone());
		if (existingTel != null) {
			// 若電話已存在，透過 session 設置提示訊息，讓 register.jsp 顯示 alert
			getSession().setAttribute(ConstantName.SESSION_USER_MSG, "該電話已有人使用，請重新輸入");
			return false;
		}

		// 若以上檢查都通過，則返回 true
		return true;
	}

	/**
	 * 設置使用者的創建日期
	 * 
	 * @param user 使用者資料
	 */
	private void setUserCreateDate(User user) {
		user.setCreatedAt(LocalDateTime.now());
	}

	// Getter and Setter
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
