package scottsun.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Data Access Object with two methods:
 * 	1. data update (to add, to delete, and to update/edit data)
 * 	2. query data
 * 
 * @author Scott Sun
 *
 */
public class BaseDao {
	// instantiate object variables 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * data update (to add, to delete, and to update/edit data)
	 * 
	 * @param sql         SQL statement to be executed（insert,delete,update)
	 * @param paramsValue An object array to replace place holders in the SQL statement. If no paramsValue, pass in null 
	 */
	public void update(String sql, Object[] paramsValue) {
		try {
			// 1. connect to database
			conn = JDBCUtil.getConnection();

			// 2. get PreparedStatement
			pstmt = conn.prepareStatement(sql);

			// 3. get count on MetaData parameters
			int count = pstmt.getParameterMetaData().getParameterCount();

			// 4. use MetaData to pass in values to place holders in the SQL statement 
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < count; i++) {
					// loop through all place holders
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}

			// 5. execute sql 
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(conn, pstmt, rs);

		}
	}

	// cls ==> Class.forName(com.qfedu.Student);
	/**
	 * a method to query through the database
	 * 
	 * @param sql         sql to query the database
	 * @param paramsValue An object array to replace place holders in the SQL statement. If no paramsValue, pass in null 
	 * @param cls         class type for the List collections
	 * @return List collection with a passed in class type 
	 */
	public <T> List<T> query(String sql, Object[] paramsValue, Class<T> cls) {

		try {
			// 1. List collection 
			List<T> list = new ArrayList<T>();

			// 2. class type
			T t = null;

			// 3. connect to the database 
			Connection conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			// 4. use MetaData to pass in values to place holders in the SQL statement 
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < pstmt.getParameterMetaData().getParameterCount(); i++) {
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}

			// 5. execute query, return a ResultSet
			rs = pstmt.executeQuery();

			// 6. get ResultSetMetaData
			ResultSetMetaData rsMetaData = rs.getMetaData();
			// get column count in the database table 
			int columnCount = rsMetaData.getColumnCount();

			// 7. Traversing through the ResultSet  
			while (rs.next()) {
				// class object to be stored
				t = cls.newInstance();

				// 8. Traversing every column in the database table, get column name; through column name, get data and store in the t class object
				for (int i = 0; i < columnCount; i++) {
					// get column name 
					String columnName = rsMetaData.getColumnName(i + 1);
					// get data from each column 
					Object value = rs.getObject(columnName);

					// pass in value through BeanUtils
					BeanUtils.setProperty(t, columnName, value);
				}

				// add t object to the list
				list.add(t);

			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch(InvocationTargetException e){
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}

		return null;
	}
}

