package com.matrix.dproxy;

import com.matrix.sproxy.Landlord;

import net.sf.cglib.beans.ImmutableBean;
public class ImmutableBeanTest {
	public static void main(String[] args) {
		Landlord rent = new Landlord("沈腾");
		Landlord zhongjie = (Landlord) ImmutableBean.create(rent);
		zhongjie.rent();
		System.err.println("-----------------------------------------------------------------------------------------------------");
		rent.setName("马丽");   // 租房人换成沈腾他媳妇，没事儿。
		zhongjie.rent();
		
		System.err.println("-----------------------------------------------------------------------------------------------------");
		zhongjie.setName("中介小赵");		// 租房人换成了中介，不是户主，会报异常。
		zhongjie.rent();
	}
}
