package scottsun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scottsun.Dao.ContactDao;
import scottsun.Dao.impl.ContactDaoImplements;

public class DeleteContactServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		
		ContactDao dao = new ContactDaoImplements();
		
		dao.deleteContactById(id);
		resp.sendRedirect(req.getContextPath() + "/ListContactServlet");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
