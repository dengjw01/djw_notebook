package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import vo.Item;
import dao.*;

public class ItemListServlet extends HttpServlet {
	
	public ItemListServlet() {
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
		String s = "";
		int userid = -1;
		if(request.getParameter("userid")!=null){
			userid = Integer.parseInt(request.getParameter("userid").toString());
			ItemDao dao = new ItemDaoImpl();
			List<Item> list = dao.listByUserId(userid);
			Gson gson = new Gson();
			s = gson.toJson(list);
		}
		PrintWriter out = response.getWriter();
		out.print(s);
		//System.out.print(userid);
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
