package com.scsa.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HelloServlet() {
        System.out.println("HelloServlet()..........");
    }
	
	@Override
	public void destroy() {
        System.out.println("HelloServlet destroy()..........");		
	}
	@Override
	public void init() throws ServletException {
        System.out.println("HelloServlet init()..........");	
	}

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
        System.out.println("HelloServlet doGet()..........");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Ã¹¹øÂ° ¼­ºí¸´");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");
		out.println("¾È³ç Scsa");
		out.println("</h1>");
		out.println("<h2>");
		out.println(new Date());
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
	
	

}




