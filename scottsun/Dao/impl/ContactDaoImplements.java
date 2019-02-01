package scottsun.Dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import scottsun.Dao.ContactDao;
import scottsun.entity.Contact;
import scottsun.utils.BaseDao;
import scottsun.utils.JDBCUtil;

/**
 * Implements all method from ContactDao interface
 * Extends from BaseDao to access Database 
 * @author Scott Sun
 *
 */
public class ContactDaoImplements extends BaseDao implements ContactDao{

	//1. establish connection to the Database
	Connection conn = JDBCUtil.getConnection();
	
	//2. Instantiate Object array and sql String for database
	Object[] paramsValue =null;
	String sql = null;
	
	@Override
	public void addContact(Contact contact) {
		sql = "insert into contact(firstname, lastname, gender, age, tel, email)"
				+ "values(?,?,?,?,?,?)";
		List list = new ArrayList();
		
		list.add(contact.getFirstName());
		list.add(contact.getLastName());
		list.add(contact.getGender());
		list.add(contact.getAge());
		list.add(contact.getTel());
		list.add(contact.getEmail());
		
		paramsValue = list.toArray();
		
		super.update(sql, paramsValue);
	}

	@Override
	public void deleteContactById(int id) {
		sql = "delete from contact where id = ?";
		paramsValue = new Object[] {id};
		super.update(sql, paramsValue);	
	}

	@Override
	public void updateContact(Contact contact) {
		sql = "update contact set firstname = ?, lastname = ?, gender = ?, age = ?, tel = ?, email = ? where id = ?";
		List list = new ArrayList();
		list.add(contact.getFirstName());
		list.add(contact.getLastName());
		list.add(contact.getGender());
		list.add(contact.getAge());
		list.add(contact.getTel());
		list.add(contact.getEmail());
		list.add(contact.getId());
		
		paramsValue = list.toArray();
		
		super.update(sql, paramsValue);
	}

	@Override
	public Contact findById(int id) {
		sql = "select * from contact where id = ?";
		paramsValue = new Object[] {id};
		List<Contact> list = super.query(sql, paramsValue, Contact.class);
		return (list != null && list.size() != 0) ? list.get(0) : null;
	}

	@Override
	public List<Contact> findAll() {
		sql = "select * from contact";
		List<Contact> list = super.query(sql, null, Contact.class);		
		return (list != null && list.size() != 0) ? list : null;
	}

}
