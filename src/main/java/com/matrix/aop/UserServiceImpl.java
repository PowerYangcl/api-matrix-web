package com.matrix.aop;

public class UserServiceImpl implements IUserService {
	public String add() {
		System.out.println("add() 添加完成");
		return "finished";
	}
	
	public boolean delete(Object agrs) {
		System.out.println("delete() 删除完成");
		return true;
	}
}
