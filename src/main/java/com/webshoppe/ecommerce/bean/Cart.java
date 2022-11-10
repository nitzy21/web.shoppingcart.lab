package com.webshoppe.ecommerce.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Cart {
	private Map<String, CartItem> items;

	public Cart() {
		items = new HashMap<>();
	}

	public int countItems() {
		return items.size();
	}

	public void add(CartItem item, Cart cart) {

		int quantity;
		
		if (item == null) {
			throw new IllegalArgumentException("Cart item cannot be null");
		}

		for (CartItem cartItem : cart.getItemsAsList()) {
			if (item.getId().equals(cartItem.getId())) {
				quantity = cartItem.getQuantity() + 1;
				item.setQuantity(quantity);
				System.out.println("Quantity" + quantity);
			}
		}
		
//		if (getItemsAsList().contains(item)) {
//
//			int quantity = item.getQuantity() + 1;
//			item.setQuantity(quantity);
//			System.out.println("Quantity" + quantity);
//
////        	CartItem storeItem = items.get(item.getId());
////        	int quantity = storeItem.getQuantity() + 1;
////        	storeItem.setQuantity(quantity);
////        	System.out.println("Quani" + quantity);
////        	items.replace(item.getId().trim(), storeItem);
//		}

		items.put(item.getId().trim(), item);

	}

	public void remove(String id) {
		CartItem item = items.get(id.trim());
		if (item == null) {
			throw new RuntimeException("Item not found");
		}
		items.remove(id.trim());
	}

	public Map<String, CartItem> getItems() {
		return items;
	}

	public List<CartItem> getItemsAsList() {
		return new ArrayList<>(items.values());
	}

	public BigDecimal getGrandTotal() {
		int grandTotal = 0;
		for (CartItem item : this.getItemsAsList()) {
//			grandTotal = grandTotal.add(item.getTotalPrice());
//			grandTotal += (int) item.getTotalPrice();
			grandTotal += item.getTotalPrice().intValue();
		}

		return BigDecimal.valueOf(grandTotal);
	}
}
