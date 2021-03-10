package com.maven.app01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		List<UserData> list = getUserList();
		
		out.println("<h2> Welcome to User Data </h2> <br> <hr>");
		
		String html = "<table borger='1'><tr><th>ID</th><th>Title</th><th>Body</th><th>Category Id</th><th>User ID</th></tr>";
		
		for (UserData userData : list) {
			html+= "<tr> <td> "+ userData.getId() +"</td> <td> "+ userData.getTitle() +" </td> <td> "+ userData.getBody() +" </td> <td> "+ userData.getCategory_id() +" </td> <td> "+ userData.getUsers_id() +"</td> </tr>";
		}
		
		html+= "</table>";
		
		out.println(html);
	}

	private List<UserData> getUserList() {
		String viewAll_Query="SELECT * FROM article";
		ArrayList<UserData> list = new ArrayList<UserData>();
		
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs=null;
		
		UserData userData = null;
		
		//Getting Databse Connection
		con=ConnectionPool.getConnection();
				
		try {
			
			ps=con.prepareStatement(viewAll_Query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				userData = new UserData();
				
				userData.setId(rs.getString(1));
				userData.setTitle(rs.getString(2));
				userData.setBody(rs.getString(3));
				userData.setCategory_id(rs.getInt(4));
				userData.setUsers_id(rs.getInt(5));
				
				list.add(userData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
