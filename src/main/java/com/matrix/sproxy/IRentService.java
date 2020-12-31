package com.matrix.sproxy;

import com.alibaba.fastjson.JSONObject;

public interface IRentService {
	public void rent();
	public String address();
	public JSONObject roomInfo(Integer area, String standard, String unit);
}
