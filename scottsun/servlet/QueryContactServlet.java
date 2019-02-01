package scottsun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scottsun.Dao.ContactDao;
import scottsun.Dao.impl.ContactDaoImplements;
import scottsun.entity.Contact;

public class QueryContactServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1. receive id 
		int id = Integer.valueOf(req.getParameter("id"));
		
		//2. Use ContactDao methods to query contact information
		ContactDao dao = new ContactDaoImplements();
		Contact contact = dao.findById(id);
		
		if(contact == null){
			resp.sendRedirect(req.getContextPath() + "/ListContactServlet");
			return;
		}
		
		//3. Display contact information if contact is found
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<title>Edit Contact</title>";
		html += "<meta charset='utf-8'>";
		html += "</head>";
		html += "<body>";
		html += "<form action=UpdateContactServlet?id=" + contact.getId() + " method=POST>";
		html += "<table align=center width=400 border=1px>";
		html += "<tr>";
		html += "<th colspan=2>Edit Contact</th>";
		html += "</tr>";
		html += "<tr><td>First Name</td>"
				+ "<td><input type=text name=firstname value='" + contact.getFirstName() + "'></td></tr>";
		html += "<tr><td>Last Name</td>"
				+ "<td><input type=text name=lastname value='" + contact.getLastName() + "'></td></tr>";
		
		if(contact.getGender().equals("M")){
			html += "<tr><td>Gender</td><td>"
					+ "<input type=radio name=gender value=M checked>Male"
					+ "<input type=radio name=gender value=F>Female</td></tr>";
		} else {
			html += "<tr><td>Gender</td><td>"
					+ "<input type=radio name=gender value=M>Male"
					+ "<input type=radio name=gender value=F checked>Female</td></tr>";
		}
		
		html += "<tr><td>Age</td>"
				+ "<td><input type=text name=age value=" + contact.getAge() + "></td></tr>";
		html += "<tr><td>Telephone Number</td>"
				+ "<td><input type=text name=tel value=" + contact.getTel() + "></td></tr>";
		html += "<tr><td>Email</td>"
				+ "<td><input type=text name=email value=" + contact.getEmail() + "></td></tr>";
		html += "<tr><th colspan=2><input type=submit value=Submit></th></tr>";
		html += "</table></form></body></html>";
		
		resp.getWriter().write(html);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
