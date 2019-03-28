package com.scsa.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;

public class BookManagerUI {
	protected Frame f;
	protected Button[] buttons;
	protected List Headerlist;
	protected Panel Header, Body, BookBody, MagazineBody, Footer0, Footer1, Footer2, Footer3;
	protected Label isbnLabel, TitleLabel, AuthorLabel, 
	PublisherLabel, DescLabel, PriceLabel, QuantityLabel, YearLabel, MonthLabel,
	isbnLabel2, TitleLabel2, AuthorLabel2, PublisherLabel2, DescLabel2, PriceLabel2, QuantityLabel2;
	
	protected TextField[] TFs;  
	protected CardLayout card; 
	
	public void createUI() {
		f = new Frame("BookManager UI");
		buttons = new Button[8]; 
		buttons[0] = new Button("저장");
		buttons[1] = new Button("취소");
		buttons[2] = new Button("검색");
		buttons[3] = new Button("책등록");
		buttons[4] = new Button("잡지등록");
		buttons[5] = new Button("종료");
		buttons[6] = new Button("Save To File");
		buttons[7] = new Button("Load To File");

		Headerlist = new List();
		Header = new Panel();
		
		f.add(Header, BorderLayout.NORTH);
		Header.setLayout(new GridLayout(1, 0));	
		Header.add(Headerlist);
		
		Body = new Panel();
		BookBody = new Panel();
		BookBody.setLayout(new GridLayout(0, 1));
		MagazineBody = new Panel();
		MagazineBody.setLayout(new GridLayout(0, 1));
		card = new CardLayout();
		Body.setLayout(card);
		Body.add(BookBody, "BookBody");
		Body.add(MagazineBody, "MagazineBody");
		card.show(Body, "BookBody");
		isbnLabel = new Label("Isbn");		
		TitleLabel = new Label("Title");
		AuthorLabel = new Label("Author");
		PublisherLabel = new Label("Publisher");
		DescLabel = new Label("Desc");
		PriceLabel = new Label("Price");
		QuantityLabel = new Label("Quantity");
		YearLabel = new Label("Year");
		MonthLabel = new Label("Month");
		
		isbnLabel2 = new Label("Isbn");		
		TitleLabel2 = new Label("Title");
		AuthorLabel2 = new Label("Author");
		PublisherLabel2 = new Label("Publisher");
		DescLabel2 = new Label("Desc");
		PriceLabel2 = new Label("Price");
		QuantityLabel2 = new Label("Quantity");
		
		TFs = new TextField[16]; 
		for (int i=0; i<TFs.length; i++){
			TFs[i] = new TextField();
		}
		
		BookBody.add(isbnLabel);
		BookBody.add(TFs[0]);
		BookBody.add(TitleLabel);
		BookBody.add(TFs[1]);
		BookBody.add(AuthorLabel);
		BookBody.add(TFs[2]);
		BookBody.add(PublisherLabel);
		BookBody.add(TFs[3]);
		BookBody.add(DescLabel);
		BookBody.add(TFs[4]);
		BookBody.add(PriceLabel);
		BookBody.add(TFs[5]);
		BookBody.add(QuantityLabel);
		BookBody.add(TFs[6]);

		MagazineBody.add(isbnLabel2);
		MagazineBody.add(TFs[7]);
		MagazineBody.add(TitleLabel2);
		MagazineBody.add(TFs[8]);
		MagazineBody.add(AuthorLabel2);
		MagazineBody.add(TFs[9]);
		MagazineBody.add(PublisherLabel2);
		MagazineBody.add(TFs[10]);
		MagazineBody.add(DescLabel2);
		MagazineBody.add(TFs[11]);
		MagazineBody.add(PriceLabel2);
		MagazineBody.add(TFs[12]);
		MagazineBody.add(QuantityLabel2);
		MagazineBody.add(TFs[13]);
		MagazineBody.add(YearLabel);
		MagazineBody.add(TFs[14]);
		MagazineBody.add(MonthLabel);
		MagazineBody.add(TFs[15]);
		f.add(Body, BorderLayout.CENTER);
		
		Footer0 = new Panel();
		f.add(Footer0, BorderLayout.SOUTH);
		Footer0.setLayout(new GridLayout(0, 1));
		Footer1 = new Panel();
		Footer0.add(Footer1);
		Footer1.add(buttons[0]);
		Footer1.add(buttons[1]);
		Footer1.add(buttons[2]);

		Footer2 = new Panel();
		Footer0.add(Footer2);
		Footer2.add(buttons[3]);
		Footer2.add(buttons[4]);
		Footer2.add(buttons[5]);
		
		Footer3 = new Panel();
		Footer0.add(Footer3);
		Footer3.add(buttons[6]);
		Footer3.add(buttons[7]);

		f.setSize(400, 700);
		f.setVisible(true);
	}
}
