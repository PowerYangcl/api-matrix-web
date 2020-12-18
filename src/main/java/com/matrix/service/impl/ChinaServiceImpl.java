package com.matrix.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.matrix.service.ICountryService;

@Primary
@Service
public class ChinaServiceImpl implements ICountryService {
	public String homeland(String homeland) {
		// 这里可以处理AmericaServiceImpl.java或者SovietServiceImpl.java的逻辑，虽然他们都继承同一个接口
		return "我们的故乡是：" + homeland;
	}
}



