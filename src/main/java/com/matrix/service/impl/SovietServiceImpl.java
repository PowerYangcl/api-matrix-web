package com.matrix.service.impl;

import org.springframework.stereotype.Service;

import com.matrix.service.ICountryService;

@Service
public class SovietServiceImpl implements ICountryService {
	public String homeland(String homeland) {
		return "Наш родной город ：" + homeland;
	}
}





























