package scottsun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scottsun.Dao.ContactDao;
import scottsun.Dao.impl.ContactDaoImplements;
import scottsun.entity.Contact;

public class AddContactServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String gender = req.getParameter("gender");
		int age = Integer.valueOf(req.getParameter("age"));
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		
		Contact contact = new Contact();
		contact.setAge(age);
		contact.setEmail(email);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setGender(gender);
		contact.setTel(tel);
		
		ContactDao dao = new ContactDaoImplements();
		dao.addContact(contact);
		System.out.println(req.getContentType());
		
		resp.sendRedirect(req.getContextPath() + "/ListContactServlet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
