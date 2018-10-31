package com.scsa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.scsa.ui.BookManagerUI;
import com.scsa.business_logic.*;
import com.scsa.domain_object.Book;
import com.scsa.domain_object.Magazine;
import com.scsa.exception.ExistISBNException;

public class EventController extends BookManagerUI {
	ListManagement lm = ListManagement.getInstance();
	ButtonHandler handler = new ButtonHandler();
	static int flag = 0;
	
	public void addEvent() {
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(handler);
		}
	}

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttons[0]) {
				if (flag==0)
				{
					String isthis = TFs[0].getText(); 
					try {
						lm.add(new Book(isthis, TFs[1].getText(), TFs[2].getText(),TFs[3].getText(), TFs[4].getText(), 
							TFs[5].getText(), TFs[6].getText()));
					} catch (ExistISBNException e1) {
						e1.printStackTrace();
					}
				for (int i = 0; i < TFs.length; i++) {
					TFs[i].getText();
					TFs[i].setText("");
				}
				Headerlist.removeAll();	
					for (Book b: lm.BookList()) {
						Headerlist.add(b.getIsbn());
						}
				}
				else {
					String isthis = TFs[7].getText(); 
					try {
						lm.add(new Magazine(isthis, TFs[8].getText(), TFs[9].getText(),TFs[10].getText(), TFs[11].getText(), 
								TFs[12].getText(), TFs[13].getText(), TFs[14].getText(), TFs[15].getText()));
					} catch (ExistISBNException e1) {
		
						e1.printStackTrace();
					}
					for (int i = 0; i < TFs.length; i++) {
						TFs[i].getText();
						TFs[i].setText("");
					}
					Headerlist.removeAll();	
						for (Book b: lm.MagazineList()) {
							Headerlist.add(b.getIsbn());
							}
					}	
					
				}
				 else if (e.getSource() == buttons[1]) {
				for (int i = 0; i < TFs.length; i++) {
					TFs[i].getText();
					TFs[i].setText("");
				}
			} else if (e.getSource() == buttons[2]) {
				if (flag==0) {
					String is = TFs[0].getText();
					for (Book b: lm.BookList()){
						if (b.getIsbn().equals(is)) {
							TFs[0].setText(is);
							TFs[1].setText(b.getTitle());
							TFs[2].setText(b.getAuthor());
							TFs[3].setText(b.getPublisher());
							TFs[4].setText(b.getDesc());
							TFs[5].setText(Integer.toString(b.getPrice()));
							TFs[6].setText(Integer.toString(b.getQuantity()));
							return;
						}
					}
					for (int i = 0; i < TFs.length; i++) {
						TFs[i].getText();
						TFs[i].setText("");
					}
				}
				else {
					String is = TFs[7].getText();
					for (Magazine b: lm.MagazineList()){
						if (b.getIsbn().equals(is)) {
							TFs[7].setText(is);
							TFs[8].setText(b.getTitle());
							TFs[9].setText(b.getAuthor());
							TFs[10].setText(b.getPublisher());
							TFs[11].setText(b.getDesc());
							TFs[12].setText(Integer.toString(b.getPrice()));
							TFs[13].setText(Integer.toString(b.getQuantity()));
							TFs[14].setText(Integer.toString(b.getYear()));
							TFs[15].setText(Integer.toString(b.getMonth()));
							return;
						}
					}
					for (int i = 0; i < TFs.length; i++) {
						TFs[i].getText();
						TFs[i].setText("");
					}
				}
				
			} else if (e.getSource() == buttons[3]) {
				card.show(Body, "BookBody");
				Headerlist.removeAll();	
				for (Book b: lm.BookList()) {
					Headerlist.add(b.getIsbn());
					}
				flag = 0;
				ListManagement.flag = 0;
			} else if (e.getSource() == buttons[4]) {
				card.show(Body, "MagazineBody");
				Headerlist.removeAll();	
				for (Book b: lm.MagazineList()) {
					Headerlist.add(b.getIsbn());
					}
				flag = 1;
				ListManagement.flag = 1;
			} else if (e.getSource() == buttons[5])
				System.exit(0);
			else if (e.getSource() == buttons[6]) {
				//save file
				if (flag==1) ListManagement.flag = 1;
				else ListManagement.flag = 0;
				lm.save("out.dat");
				Headerlist.removeAll();	
			}
			else if (e.getSource() == buttons[7]) {
				//load file
				if (flag==1) ListManagement.flag = 1;
				else ListManagement.flag = 0;
				Headerlist.removeAll();	
				lm.load("out.dat");
				if (flag==1) {
				for (Book b: lm.MagazineList()) {
					Headerlist.add(b.getIsbn());
					}
				}
				else {
					for (Book b: lm.BookList()) {
						Headerlist.add(b.getIsbn());
						}
				}
			}
		}
	}
}
