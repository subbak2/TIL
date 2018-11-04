package com.scsa.business_logic;

import java.util.ArrayList;
import java.util.List;

import com.scsa.domain_object.Product;

public class ProductManagerImpl implements IproductManager {
	private static ProductManagerImpl instance = new ProductManagerImpl();
	List<Product> list = new ArrayList<>();

	public static ProductManagerImpl getInstance() {
		return instance;
	}

	public ProductManagerImpl() {
	}

	// 상품 정보 등록
	public void addProduct(Product p) {
		if (this.Search(p.getPid()) == null) {
			list.add(p);
		}
	}

	// 상품 수정
	public void editProduct(Product p) {
		Product p2 = this.Search(p.getPid());
		if (p != null) {
			p2.setPname(p.getPname());
			p2.setPrice(p.getPrice());
		}
	}

	// 상품 삭제
	public void delete(String code) {
		Product p = this.Search(code);
		if (p != null) {
			list.remove(p);
		}
	}

	// 전체 상품 검색
	public List<Product> getList() {
		return list;
	}

	// 상품검색1 - 상품 코드를 이용한 검색
	public Product Search(String code) {
		for (Product p : list) {
			if (p.getPid().equals(code))
				return p;
		}
		return null;
	}
	
}
