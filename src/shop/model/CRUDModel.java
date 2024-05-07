package shop.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface CRUDModel<T> {

	public T doRetrieveByKey(String codice) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T prodotto) throws SQLException, FileNotFoundException, IOException;
	
	public void doUpdateAcquisto(T prodotto) throws SQLException;
	
	public void doUpdateAm(T prodotto) throws SQLException, IOException;
	
	public void doDeleteAm(T prodotto) throws SQLException;
	
	public void doUpdateCancel(T prodotto) throws SQLException;
	
}
