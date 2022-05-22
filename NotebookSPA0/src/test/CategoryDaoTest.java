package test;

import java.util.List;

import com.google.gson.Gson;

import dao.CategoryDao;
import vo.Category;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Category> list = new CategoryDao().list();
		String s = new Gson().toJson(list);
		System.out.println(s);
	}

}
