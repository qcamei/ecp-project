package com.ecp.bean;

import java.util.List;

public class CartToOrderItemList {
private List<AddSkuToOrderBean> cartItemList;

public List<AddSkuToOrderBean> getCartItemList() {
	return cartItemList;
}

public void setCartItemList(List<AddSkuToOrderBean> cartItemList) {
	this.cartItemList = cartItemList;
}
}
