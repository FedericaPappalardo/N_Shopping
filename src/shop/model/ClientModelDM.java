package shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shop.bean.ClientBean;

public class ClientModelDM implements ACCESSModel<ClientBean> {

	private DriverManagerConnectionPool dmcp = null;
	public ClientModelDM(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Product Model creation....");
	}

	private static final String TABLE_NAME = "cliente";
	
	@Override
	public boolean doRetrieveByKey(String codice, String password) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM " + ClientModelDM.TABLE_NAME + " WHERE USERID = ? AND PASSWORD = ?";
				
		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);
			preparedStatement.setString(2, password);
				
			ResultSet result = preparedStatement.executeQuery();
			
			if(!result.next()) {
				
				return false;
			}
			
			else {
				
				return true;
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				dmcp.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doSave(ClientBean persona) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + ClientModelDM.TABLE_NAME + 
				"(userID, password, nome, cognome, mail, telefono) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(insertSQL);
												
			preparedStatement.setString(1, persona.getUserID());
			preparedStatement.setString(2, persona.getPassword());
			preparedStatement.setString(3, persona.getNome());
			preparedStatement.setString(4, persona.getCognome());
			preparedStatement.setString(5, persona.getMail());
			preparedStatement.setString(6, persona.getTelefono());
			
			preparedStatement.executeUpdate();
			System.out.println("doSave: " + preparedStatement.toString());
			
			connection.commit();
			
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
				}
				finally {
					dmcp.releaseConnection(connection);
				}
		}
		
	}

	@Override
	public void doUpdate(ClientBean persona) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String updateSQL="UPDATE "+ ClientModelDM.TABLE_NAME + 
				" SET  userID = ?, password= ?, nome = ?, cognome = ?, mail = ?, telefono = ? WHERE userID= ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, persona.getUserID());
			preparedStatement.setString(2, persona.getPassword());
			preparedStatement.setString(3, persona.getNome());
			preparedStatement.setString(4, persona.getCognome());
			preparedStatement.setString(5, persona.getMail());
			preparedStatement.setString(6, persona.getTelefono());
			
			System.out.println("doUpdate " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			}
			finally {
				dmcp.releaseConnection(connection);
			}
		}
		
	}

	@Override
	public void doDelete(ClientBean persona) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String deleteSQL="DELETE FROM " + ClientModelDM.TABLE_NAME + " WHERE userID = ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, persona.getUserID());
			
			System.out.println("doDelete " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			try {
			if(preparedStatement != null)
				preparedStatement.close();
			}
			finally {
				dmcp.releaseConnection(connection);
			}
		}
		
	}

}
