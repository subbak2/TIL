package com.scsa.user;

import com.scsa.model.dao.UserDAO;
import com.scsa.model.vo.User;

public class UserTest {
	public static void main(String[] args) {
		try {
			UserDAO userDAO = new UserDAO();
			User[] users = new User[] {
					new User("abcddd", "abc", "ȫ�浿", "abc@scsa.com"),
					new User("sdddc", "sa", "��ö��", "ul-ssu@scsa.com")					
			};
			userDAO.insertUser2(users);
//			boolean flag = userDAO.insertUser("test4", "test", "�׽�Ʈ", "test@scsa.com");
//			System.out.println("���: "+flag);
//			if (flag) {
//				System.out.println(userDAO.selectUser("test4"));
//			}
//			User user = new User();
//			user.setUserId("test");
//			user.setName("�ǳ�");
//			user.setPassword("1111");
//			user.setEmail("javabara@scsa.com");
//			userDAO.updateUser(user);
//			
//			for (User u : userDAO.selectUserList()) {
//				System.out.println(u.toString());
//			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
