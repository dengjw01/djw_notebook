package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import util.MyUtil;
import vo.Item;

public class ItemAddServlet extends HttpServlet {

	public ItemAddServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String item_text = new String(request.getParameter("item_text").getBytes("ISO-8859-1"),"utf-8");
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));
		String date_paid_str = request.getParameter("date_paid");		
		// yyyy-MM-dd��Stringת���� java.sql.Date
		java.sql.Date date_paid = java.sql.Date.valueOf(date_paid_str);
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		item_text = MyUtil.bytes2string(item_text);
		Item item = new Item();
		item.setItem_text(item_text);
		item.setAmount(amount);
		item.setDate_paid(date_paid);
		item.setCategory_id(category_id);
		item.setUser_id(user_id);
		ItemDao dao = new ItemDaoImpl();
		Boolean b = dao.save(item);
		String s = b?"1":"0";
		PrintWriter out = response.getWriter();
		//System.out.print(s);
		out.print(s);
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
