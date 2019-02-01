package scottsun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scottsun.Dao.ContactDao;
import scottsun.Dao.impl.ContactDaoImplements;
import scottsun.entity.Contact;

public class ListContactServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Connect with the database and get all the contacts
		ContactDao dao = new ContactDaoImplements();
		List<Contact> list = dao.findAll();
		
		//setup HTML content type and character encoding 
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		PrintWriter pw = resp.getWriter();
		
		String html = "";
		html +="<!doctype html>";
		html +="<html>";
		html +="<head>";
		html +="<title>Show all Contacts</title>";
		html +="</head>";
		html +="<body>";
		html +="<center><h3>All Contacts</h3></center>";
		html +="<table border='1' align='center' width='800'>";
		html +="<tr>";
		html +="<th>ID</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Age</th>"
				+ "<th>Tel</th><th>Email</th><th>Function</th>";
		html +="</tr>";
		
		if(list != null){
			for (Contact contact : list) {
				html += "<tr>";
				html += "<td>" + contact.getId() + "</td>";
				html += "<td>" + contact.getFirstName() + "</td>";
				html += "<td>" + contact.getLastName() + "</td>";
				html += "<td>" + contact.getGender() + "</td>";
				html += "<td>" + contact.getAge() + "</td>";
				html += "<td>" + contact.getTel() + "</td>";
				html += "<td>" + contact.getEmail() + "</td>";
				html += "<td> ";
				html += "<a href='" + req.getContextPath() + "/DeleteContactServlet?id=" + contact.getId() + "'>Delete</a>";
				/*
				 passing parameter through URL for contact ID
				 */
				html += " | ";
				html += "<a href='" + req.getContextPath() + "/QueryContactServlet?id=" + contact.getId() + "'>Edit</a>";
				html += "</td></tr>";
			}
		}
		html +="</table>";
		html +="<br><center><a href='" + req.getContextPath() + "/addContact.html'>Add new Contact</a></center>";
		html +="</body>";
		html +="</html>";
		
		pw.write(html);
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
