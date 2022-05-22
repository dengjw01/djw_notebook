package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Category;
import vo.Item;

public class CategoryDao {
	public List<Category> list(){
		List<Category> list = new ArrayList<Category>();
		DBUtil db = new DBUtil();
		Connection conn = db.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from category order by orders";
		try {
			pst = conn.prepareStatement(sql);			
			rs = pst.executeQuery();
			while (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setCategory_text(rs.getString("category_text"));
				c.setOrders(rs.getInt("orders"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeAll(rs, pst, conn);
		}
		return list;
	}
}
