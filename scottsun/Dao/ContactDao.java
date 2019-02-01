package scottsun.Dao;

import java.util.List;

import scottsun.entity.Contact;

public interface ContactDao {
	
	/**
	 * add a new contact to the contact list
	 * @param contact new contact to be added 
	 */
	public void addContact(Contact contact);
	
	/**
	 * delete contact by its ID
	 * @param id the id of the contact to be deleted
	 */
	public void deleteContactById(int id);
	
	/**
	 * update contact
	 * @param contact contact to be updated
	 */
	public void updateContact(Contact contact);
	
	/**
	 * find a contact by IF
	 * @param id the id of the contact
	 * @return Contact the contact with the id user search for
	 */
	public Contact findById(int id);
	
	/**
	 * find all contacts in the database
	 * @return List<Contact> a list of contacts in the database
	 */
	public List<Contact> findAll();
}
