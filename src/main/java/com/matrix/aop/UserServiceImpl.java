package com.matrix.aop;

public class UserServiceImpl implements IUserService {
	public String add() {
		System.out.println("add() 添加完成");
		return "finished";
	}
	
	public boolean delete(Object args) {
		System.out.println("delete() 删除完成，参数：" + args);
		return true;
	}
}
