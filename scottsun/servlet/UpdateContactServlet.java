package scottsun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scottsun.Dao.ContactDao;
import scottsun.Dao.impl.ContactDaoImplements;
import scottsun.entity.Contact;

public class UpdateContactServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		Contact contact = new Contact();
		
		contact.setId(Integer.valueOf(req.getParameter("id")));
		contact.setFirstName(req.getParameter("firstname"));
		contact.setLastName(req.getParameter("lastname"));
		contact.setAge(Integer.valueOf(req.getParameter("age")));
		contact.setTel(req.getParameter("tel"));
		contact.setEmail(req.getParameter("email"));
		contact.setGender(req.getParameter("gender"));
		
		ContactDao dao = new ContactDaoImplements();
		dao.updateContact(contact);
		
		resp.sendRedirect(req.getContextPath() + "/ListContactServlet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
