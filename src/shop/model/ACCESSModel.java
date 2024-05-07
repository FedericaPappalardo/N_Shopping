package shop.model;

import java.sql.SQLException;

public interface ACCESSModel<T> {
	
	public boolean doRetrieveByKey(String codice , String password) throws SQLException;

	public void doSave(T persona) throws SQLException;
	
	public void doUpdate(T persona) throws SQLException;
	
	public void doDelete(T persona) throws SQLException;
	
}
