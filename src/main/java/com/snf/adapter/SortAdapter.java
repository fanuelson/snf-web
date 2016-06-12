package com.snf.adapter;

import java.io.Serializable;

import org.primefaces.model.SortOrder;

import com.snf.enums.Order;

public class SortAdapter implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String sortField;
	private Order sortOrder;
	
	public SortAdapter(SortOrder sortOrder, String sortField) {
		this.sortField = sortField;
		switch (sortOrder) {
		case ASCENDING:
			this.sortOrder = Order.ASC ;
			break;
		case DESCENDING:
			this.sortOrder = Order.DESC ;
			break;
		default:
			this.sortOrder = null;
			break;
		}
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public Order getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Order sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public boolean isFieldPreenchido() {
		return this.sortField != null;
	}
	
}
