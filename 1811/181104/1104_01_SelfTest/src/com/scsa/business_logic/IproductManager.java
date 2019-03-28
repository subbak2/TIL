package com.scsa.business_logic;

import java.util.List;

import com.scsa.domain_object.Product;

public interface IproductManager {

	//상품 정보 등록 
	void addProduct(Product p);
	//상품 수정
	void editProduct(Product p);
	//상품 삭제
	void delete(String code);	
	//전체 상품 검색 
	List<Product> getList();	
	//상품검색 
	Product Search(String code);
}
 