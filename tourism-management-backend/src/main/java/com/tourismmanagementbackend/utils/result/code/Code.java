package com.tourismmanagementbackend.utils.result.code;

public interface Code {
	// 成功
	int SUCCESS  = Errs.put(200, "成功");
	// 未知错误
	int ERROR	 = Errs.put(-1, "操作有误");
	// 未知错误
	int LOGIN_OUT	 = Errs.put(-2, "登录失效");
	// 用户名或密码不正确
	int LOGIN_ERROR	 = Errs.put(-3, "用户名或密码不正确");

	//新增学生失败
	int ADD_STUDENT_ERROR	 = Errs.put(-4, "新增失败");

	//修改学生失败
	int UPDATE_STUDENT_ERROR	 = Errs.put(-5, "修改失败");

	//删除学生失败
	int DELETE_STUDENT_ERROR	 = Errs.put(-5, "删除失败");
	//删除评论失败
	int DELETE_REVIEW_ERROR		 = Errs.put(-6, "删除评论失败");

	//删除员工失败
	int DELETE_EMPLOYEE_ERROR	= Errs.put(-7, "删除员工失败");

	//添加员工失败
	int ADD_EMPLOYEE_ERROR		= Errs.put(-8, "添加员工失败");

	//修改员工失败
	int UPDATE_EMPLOYEE_ERROR	= Errs.put(-9, "修改员工失败");

	//修改个人信息失败
	int UPDATE_USERINFO_ERROR   = Errs.put(-10, "修改个人信息失败");

	//修改密码失败
	int UPDATE_PASSWORD_ERROR   = Errs.put(-11, "修改密码失败");


	int ADD_REGISTRATION_ERROR	 = Errs.put(-12, "您没有今天在该景区的预约！请先预约");

	int ADD_SCENIC_AREA_ERROR	 = Errs.put(-13, "该景区已存在！若想修改请编辑");
	//添加评论失败
	int ADD_REVIEW_ERROR   		= Errs.put(-14, "添加评论失败");
	//修改评论失败
	int UPDATE_REVIEW_ERROR   	= Errs.put(-15, "修改评论失败");

	int ADD_RESERVATION_ERROR   	= Errs.put(-16, "您已于该日预约");

}

