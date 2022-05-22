package test;

import java.math.BigDecimal;

import com.google.gson.Gson;

import dao.ItemDao;
import dao.ItemDaoImpl;
import vo.Item;

public class ItemDaoTest {

	public static void main(String[] args) {		
		Item item = new Item();
		item.setItem_text("Âò½Ì²Ä");
		item.setAmount(new BigDecimal("10.98"));
		item.setDate_paid(java.sql.Date.valueOf("2020-11-11"));
		item.setCategory_id(3);
		item.setCategory_text("Ê³");
		item.setUser_id(1);
		
		String s = new Gson().toJson(item);
		System.out.println(s);
		
		/*
		ItemDao dao = new ItemDaoImpl();
		boolean success = dao.save(item);
		if(success){			
			System.out.println("Success!");
		}else{
			System.out.println("Fail!");
		}
		*/
	}

}
