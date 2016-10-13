package com.bit2016.jdbc.dao.test;

import java.util.List;

import com.bit2016.jdbc.dao.AuthorDao;
import com.bit2016.jdbc.vo.AuthorVo;

public class AuthorDaoTest {

	public static void main(String[] args) {
		// insert test
		insertTest();
		
		//delete Test
		//deleteTest();
		
		//update test
		updateTest();
		
		//getList test
		getListTest();
	}
	public static void updateTest(){
		AuthorVo vo = new AuthorVo();
		vo.setNo(2L);
		vo.setName("이하민");
		
		new AuthorDao().update(vo);
	}
	public static void deleteTest(){
		AuthorDao dao = new AuthorDao();
		
		dao.delete(1L);
		dao.delete(2L);
		dao.delete(3L);
		dao.delete(4L);
		
	}
	public static void getListTest(){
		AuthorDao dao = new AuthorDao();
		List<AuthorVo> list = dao.getList();
		
		System.out.println("====================");
		for(AuthorVo vo : list){
			System.out.println(vo);
		}
		System.out.println("====================");
	}
	
	public static void insertTest() {
		// mock data
		AuthorVo vo = new AuthorVo();
		vo.setNo( 200L );
		vo.setName( "공자" );
		
		AuthorDao dao = new AuthorDao();
		dao.insert(vo);
	}
}
