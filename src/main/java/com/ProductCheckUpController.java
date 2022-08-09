package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductDetails
 */
public class ProductCheckUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductCheckUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		String prod = request.getParameter("productid");
		int id = Integer.parseInt(prod);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "Ritesh@123");
			PreparedStatement stmt = con.prepareStatement("select * from Product where productId = ? ");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				out.println("<h1><center>Product Details<center></h1>");
				out.println("<hr>");
				out.println("ProductId :- " + rs.getInt(1)+"<br/>");
				out.println("ProductName :- " + rs.getString(2)+"<br/>");
				out.println("ProductCost :- " + rs.getString(3));
			}
			else{
				rd.include(request, response);
				out.println("<h3><center><span style = 'color:red;'>Invalid productId , Try Again !!!</span></center></h3>");
			}
		}
		catch(Exception e) {
			out.println(e);
		}
		
	}

}
