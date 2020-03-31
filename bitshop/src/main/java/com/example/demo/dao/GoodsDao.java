package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.GoodsManager;
import com.example.demo.vo.GoodsVo;

@Repository
public class GoodsDao {
	
	public List<GoodsVo> listAll(){
		return GoodsManager.listAll();
	}
	
	public int insert(GoodsVo g) {
		System.out.println("다오동작");
		return GoodsManager.insert(g);
	}
	
	public int update(GoodsVo g) {
		return GoodsManager.update(g);
	}
	
	public int delete(GoodsVo g) {
		return GoodsManager.delete(g);
	}
}
