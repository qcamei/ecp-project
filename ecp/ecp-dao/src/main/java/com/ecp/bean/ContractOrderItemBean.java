package com.ecp.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ContractOrderItemBean
 * @Description 用于生成合同（合同中商品列表）
 * @author Administrator
 * @Date 2017年6月29日 下午3:53:19
 * @version 1.0.0
 */
public class ContractOrderItemBean {

	/*
	 "create_time":"2017-06-27T17:45:12.000+08:00",
					"picture_url":"/static/images/iphone_index.png",
					"item_id":1,
					"num":2,
					"sku_id":2,
					"picture_id":2,
					"picture_status":1,
					"primitive_price":222,
					"sort_number":1,
					"sku_name":"Apple8新品颜色:RED版本:V2","id":59,
					"order_id":"1000000937189443",
					"cid":13,
					"discount_price":0,
					"pay_price":222,
					"pay_price_total":444}, 
	 */
	private Long item_id;
	private Integer num;
	private Long sku_id;
	private BigDecimal primitive_price;
	private String sku_name;
	private String order_id;
	private Long cid;
	private BigDecimal discount_price;
	private BigDecimal pay_price;
	private BigDecimal pay_price_total;
	
	private String create_time;
	private String picture_url;
	private Long picture_id;
	private Integer picture_status;
	
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getSku_id() {
		return sku_id;
	}
	public void setSku_id(Long sku_id) {
		this.sku_id = sku_id;
	}
	public BigDecimal getPrimitive_price() {
		return primitive_price;
	}
	public void setPrimitive_price(BigDecimal primitive_price) {
		this.primitive_price = primitive_price;
	}
	public String getSku_name() {
		return sku_name;
	}
	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public BigDecimal getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(BigDecimal discount_price) {
		this.discount_price = discount_price;
	}
	public BigDecimal getPay_price() {
		return pay_price;
	}
	public void setPay_price(BigDecimal pay_price) {
		this.pay_price = pay_price;
	}
	public BigDecimal getPay_price_total() {
		return pay_price_total;
	}
	public void setPay_price_total(BigDecimal pay_price_total) {
		this.pay_price_total = pay_price_total;
	}
	
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public Long getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(Long picture_id) {
		this.picture_id = picture_id;
	}
	public Integer getPicture_status() {
		return picture_status;
	}
	public void setPicture_status(Integer picture_status) {
		this.picture_status = picture_status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
}
