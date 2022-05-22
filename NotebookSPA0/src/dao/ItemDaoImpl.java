package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.Item;

public class ItemDaoImpl implements ItemDao {

	public ItemDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean update(Item item) {
		// TODO Auto-generated method stub
		Boolean result = false;
		Connection conn = new DBUtil().getConnection();
		PreparedStatement pst = null;
		String sql = "update item set item_text=?,amount=?,date_paid=? where id=?";
		//String sql2 = "update Item set item_text = '"+item.getItem_text()+"',amount="+item.getAmount()+",date_paid='"+item.getDate_paid()+"' where id="+item.getId();		
		try {
			pst = conn.prepareStatement(sql);			
			pst.setString(1, item.getItem_text());
			pst.setBigDecimal(2, item.getAmount());
			pst.setDate(3, item.getDate_paid());
			pst.setInt(4, item.getId());									
			int i = pst.executeUpdate();
			if(i==1){
				result = true;
			}
			System.out.println(pst);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		return result;
	}

	@Override
	public Item get(int id) {
		// TODO Auto-generated method stub
		Item item = null;
		Connection conn = new DBUtil().getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from Item where id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()){
				item = new Item();
				item.setId(id);
				item.setItem_text(rs.getString("item_text"));
				item.setAmount(rs.getBigDecimal("amount"));
				item.setDate_paid(rs.getDate("date_paid"));
			}
		}catch(Exception e){
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
		return item;
	}

	@Override
	public Boolean save(Item item) {		
		Boolean result = false;
		Connection conn = new DBUtil().getConnection();
		PreparedStatement pst = null;
		String sql = "insert into item(item_text,amount,date_paid,user_id,category_id) values(?,?,?,?,?);";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getItem_text());
			pst.setBigDecimal(2, item.getAmount());
			pst.setDate(3, item.getDate_paid());
			pst.setInt(4, item.getUser_id());
			pst.setInt(5, item.getCategory_id());
			int i = pst.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public List<Item> listByUserId(int userid) {
		// TODO Auto-generated method stub
		List<Item> list = new ArrayList<Item>();
		DBUtil db = new DBUtil();
		Connection conn = db.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select A.*,B.Category_text from item A left join Category B on A.category_id = B.id where A.user_id = ? order by id desc";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
			rs = pst.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setItem_text(rs.getString("item_text"));
				item.setAmount(rs.getBigDecimal("amount"));
				item.setDate_paid(rs.getDate("date_paid"));		
				item.setUser_id(userid);
				item.setCategory_id(rs.getInt("category_id"));
				item.setCategory_text(rs.getString("category_text"));
				list.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeAll(rs, pst, conn);
		}
		return list;
	}

	public Boolean delete(String id) {
		        Boolean result = false;
		//1:构建sql语句
				String sql  = "delete from item where id=?";
				//2:数据库连接
				DBUtil db = new DBUtil();
				//3:创建预处理语句对象
				PreparedStatement pstat = null;
				Connection con = db.getConnection();
				try {
					pstat = con.prepareStatement(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//4:语句对象参数赋值
				try {
					pstat.setString(1, id);//学号
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//5:执行语句
				int i = 0;
				try {
					i = pstat.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//6:关闭对象
				//7:返回结果
				if (i>0) {
					result = true;
				}else{
					
				}
				return result;
	}
	
}
