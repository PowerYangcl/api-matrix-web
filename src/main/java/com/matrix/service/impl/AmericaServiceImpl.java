package com.matrix.service.impl;

import org.springframework.stereotype.Service;
import com.matrix.service.ICountryService;

@Service
public class AmericaServiceImpl implements ICountryService {
	public String homeland(String homeland) {
		return "Our homeland is ：" + homeland;
	}
}









