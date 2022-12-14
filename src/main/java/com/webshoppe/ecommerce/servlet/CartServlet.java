package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webshoppe.ecommerce.bean.Cart;
import com.webshoppe.ecommerce.bean.CartItem;

@WebServlet(urlPatterns = "/cart", loadOnStartup = 1)
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = -8167062864114797993L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartItem cartItem = this.toCartItem(request);

		HttpSession session = request.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");
		
		if (request.getParameter("action").equals("remove")) {
			String itemId = request.getParameter("id");
			cart.remove(itemId);
		} else if (request.getParameter("action").equals("add")) {
			
			if (cart == null) {
				cart = new Cart();
			}
			
			cart.add(cartItem, cart);
		}
		
		session.setAttribute("cart", cart);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);

	}

	private CartItem toCartItem(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");

		CartItem cartItem = new CartItem();
		
		cartItem.setId(id);
		cartItem.setName(name);
		cartItem.setDescription(description);
		cartItem.setPrice(new BigDecimal(price));
		cartItem.setQuantity(cartItem.getQuantity()+1);
		return cartItem;
	}
}
