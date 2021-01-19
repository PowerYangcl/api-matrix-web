package com.matrix.dproxy;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peach {
	private String color;
	private JSONObject size;	// 重量大小
	private String type;  // 苹果、橙子或其他 
	private Boolean ripeFruit;  // 是否成熟
}
