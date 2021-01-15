package com.matrix.dproxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orange {
	private String color;
	private Integer size;	// 重量大小
	private String type;  // 苹果、橙子或其他
	private Boolean ripeFruit;  // 是否成熟
}
