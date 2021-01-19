package com.matrix.dproxy.mixin;

import net.sf.cglib.proxy.Mixin;
public class MiXinTest {
	public static void main(String[] args) {
		Object[] delegates = {new WealthServiceImpl(), new PowerServiceImpl()}; 
		Mixin mixin = Mixin.create(new Class[] {IWealthInterface.class, IPowerInterface.class, ISuperFather.class}, delegates);
		ISuperFather delegate = (ISuperFather) mixin;
		delegate.wealth();
		delegate.power();
	}
}

// 