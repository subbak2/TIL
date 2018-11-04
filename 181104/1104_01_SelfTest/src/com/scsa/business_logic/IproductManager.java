package com.scsa.business_logic;

import java.util.List;

import com.scsa.domain_object.Product;

public interface IproductManager {

	//��ǰ ���� ��� 
	void addProduct(Product p);
	//��ǰ ����
	void editProduct(Product p);
	//��ǰ ����
	void delete(String code);	
	//��ü ��ǰ �˻� 
	List<Product> getList();	
	//��ǰ�˻� 
	Product Search(String code);
}
 