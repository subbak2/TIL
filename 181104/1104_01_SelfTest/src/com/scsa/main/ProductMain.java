package com.scsa.main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.scsa.business_logic.ProductManagerImpl;
import com.scsa.business_logic.loadThread;
import com.scsa.business_logic.saveThread;
import com.scsa.business_logic.transferThread;
import com.scsa.domain_object.Product;

public class ProductMain {
	Frame F;
	Panel Header, Body, Footer, Footer1, Footer2;
	List ListUI;
	Label pid, pname, price;
	TextField[] TFs = new TextField[3];
	Button[] buttons = new Button[7];
	ProductManagerImpl dao = ProductManagerImpl.getInstance();

	public static void main(String[] args) {
		ProductMain a = new ProductMain();
		a.createUI();
		a.EventController();
	}

	public void createUI() {
		F = new Frame("SCSA Product Manager");
		F.setSize(400, 600);
		F.setVisible(true);

		Header = new Panel();
		F.add(Header, BorderLayout.NORTH);
		pid = new Label("상품 코드");
		pname = new Label("상품 이름");
		price = new Label("상품 가격");
		TFs[0] = new TextField();
		TFs[1] = new TextField();
		TFs[2] = new TextField();
		Header.add(pid);
		Header.add(TFs[0]);
		Header.add(pname);
		Header.add(TFs[1]);
		Header.add(price);
		Header.add(TFs[2]);
		Header.setLayout(new GridLayout(0, 2));

		Body = new Panel();
		F.add(Body, BorderLayout.CENTER);
		ListUI = new List();
		Body.setLayout(new BorderLayout());
		Body.add(ListUI);

		Footer = new Panel();
		Footer1 = new Panel();
		Footer2 = new Panel();
		F.add(Footer, BorderLayout.SOUTH);
		buttons[0] = new Button("등록");
		buttons[1] = new Button("검색");
		buttons[2] = new Button("수정");
		buttons[3] = new Button("삭제");
		buttons[4] = new Button("Save To File");
		buttons[5] = new Button("Load To File");
		buttons[6] = new Button("Upload");
		Footer1.add(buttons[0]);
		Footer1.add(buttons[1]);
		Footer1.add(buttons[2]);
		Footer1.add(buttons[3]);
		Footer2.add(buttons[4]);
		Footer2.add(buttons[5]);
		Footer2.add(buttons[6]);
		Footer.setLayout(new GridLayout(0, 1));
		Footer.add(Footer1);
		Footer.add(Footer2);

	}

	public void EventController() {
		ButtonHandler handler = new ButtonHandler();
		F.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		for (Button b : buttons) {
			b.addActionListener(handler);
		}
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttons[0]) {
				Product p = new Product();
				p.setPid(TFs[0].getText());
				p.setPname(TFs[1].getText());
				p.setPrice(Integer.parseInt(TFs[2].getText()));
				dao.addProduct(p); // 1) dao에서 addProduct 통해 List에 추가하기
				ListUI.removeAll(); // 2) UI 초기화 한 후, 추가된 List를 UI에 뿌려주기
				for (Product p2 : dao.getList()) {
					ListUI.add("상품 코드 : " + p2.getPid() + "   /   상품 이름 : " + p2.getPname() + "   /   상품 가격 : "
							+ p2.getPrice());
				}
			} else if (e.getSource() == buttons[1]) {
				Product p = new Product();
				String c = TFs[0].getText();
				p = dao.Search(c);
				if (p != null) {
					TFs[0].setText(p.getPid());
					TFs[1].setText(p.getPname());
					TFs[2].setText(Integer.toString(p.getPrice()));
				}
			} else if (e.getSource() == buttons[2]) {
				Product p = new Product();
				p.setPid(TFs[0].getText());
				p.setPname(TFs[1].getText());
				p.setPrice(Integer.parseInt(TFs[2].getText()));
				dao.editProduct(p);
				ListUI.removeAll();
				for (Product p2 : dao.getList()) {
					ListUI.add("상품 코드 : " + p2.getPid() + "   /   상품 이름 : " + p2.getPname() + "   /   상품 가격 : "
							+ p2.getPrice());
				}
			} else if (e.getSource() == buttons[3]) {
				String c = TFs[0].getText();
				dao.delete(c);
				ListUI.removeAll();
				for (Product p2 : dao.getList()) {
					ListUI.add("상품 코드 : " + p2.getPid() + "   /   상품 이름 : " + p2.getPname() + "   /   상품 가격 : "
							+ p2.getPrice());
				}
			} else if (e.getSource() == buttons[4]) {
				Thread t = new Thread(new saveThread());
				t.start();
			} else if (e.getSource() == buttons[5]) {
				Thread t = new Thread(new loadThread());
				t.start();
				try {
					t.join();
					ListUI.removeAll();
					for (Product p2 : dao.getList()) {
						ListUI.add("상품 코드 : " + p2.getPid() + "   /   상품 이름 : " + p2.getPname() + "   /   상품 가격 : "
								+ p2.getPrice());
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			
			} else if (e.getSource() == buttons[6]) {
				Thread t = new Thread(new transferThread());
				t.start();

		}
	}
	}
}

