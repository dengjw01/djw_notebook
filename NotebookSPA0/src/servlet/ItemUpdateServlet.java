package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import vo.*;

public class ItemUpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ItemUpdateServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");		
		int id = 0;
		if(request.getParameter("item_id")!=null){
			// ��ȡ��������һ��Ҫ����֤
			id = Integer.parseInt(request.getParameter("item_id"));
		}
		// ���²�����Ҳ��Ҫ��֤
		String item_text = new String(request.getParameter("item_text").getBytes("ISO-8859-1"),"utf-8");
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));	
		String date_paid_str = request.getParameter("date_paid");		
		// yyyy-MM-dd��Stringת���� java.sql.Date
		java.sql.Date date_paid = java.sql.Date.valueOf(date_paid_str);
		
		// ��ȡ�ı����ݣ���item�����װ
		Item item = new Item();
		item.setId(id);
		item.setItem_text(item_text);		
		item.setAmount(amount);
		item.setDate_paid(date_paid);
		
		//����daoʵ���࣬��ɶ����ݿ�item��ĸ��²��
		ItemDao dao = new ItemDaoImpl();
		Boolean b = dao.update(item);
		String a = b?"1":"0";
		PrintWriter out = response.getWriter();
		//System.out.print(s);
		out.print(a);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
