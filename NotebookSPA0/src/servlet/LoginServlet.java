package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.*;

import java.sql.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int userid = 0;		
		username = MyUtil.bytes2string(username);
		password = MyUtil.bytes2string(password);
		
		// ֱ�ӷ������ݿ�
		boolean success = false;
		Connection conn = new DBUtil().getConnection();
		ResultSet rs = null;
		PreparedStatement pst = null;
		String sql = "select * from [user] where [username]=? and password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				success = true;
				userid = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			try {
				pst.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}	
		
		HttpSession session = request.getSession(true);
		if(success){			 
	        session.setAttribute("userid", userid); 
	        session.setAttribute("username", username);
	        PrintWriter out = response.getWriter();
		    // ���userid
		    out.print(200);
		    System.out.print(200);
		    out.flush();
		    out.close();	          
			//response.sendRedirect("../index.html?id="+userid);
		}else{	
			session.invalidate();
			//response.sendRedirect("../login_fail.html");
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
