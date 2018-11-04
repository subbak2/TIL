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

	// ��ǰ ���� ���
	public void addProduct(Product p) {
		if (this.Search(p.getPid()) == null) {
			list.add(p);
		}
	}

	// ��ǰ ����
	public void editProduct(Product p) {
		Product p2 = this.Search(p.getPid());
		if (p != null) {
			p2.setPname(p.getPname());
			p2.setPrice(p.getPrice());
		}
	}

	// ��ǰ ����
	public void delete(String code) {
		Product p = this.Search(code);
		if (p != null) {
			list.remove(p);
		}
	}

	// ��ü ��ǰ �˻�
	public List<Product> getList() {
		return list;
	}

	// ��ǰ�˻�1 - ��ǰ �ڵ带 �̿��� �˻�
	public Product Search(String code) {
		for (Product p : list) {
			if (p.getPid().equals(code))
				return p;
		}
		return null;
	}
	
}
