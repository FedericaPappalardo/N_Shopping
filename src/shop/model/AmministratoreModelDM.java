package shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shop.bean.AmministratoreBean;

public class AmministratoreModelDM implements ACCESSModel<AmministratoreBean> {

	private DriverManagerConnectionPool dmcp = null;
	public AmministratoreModelDM(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Product Model creation....");
	}

	private static final String TABLE_NAME = "amministratore";
	
	@Override
	public boolean doRetrieveByKey(String codice, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM " + AmministratoreModelDM.TABLE_NAME + " WHERE CODID = ? AND PASSWORD = ?";
		
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
	public void doSave(AmministratoreBean persona) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="INSERT INTO " + AmministratoreModelDM.TABLE_NAME + 
				"(codID, password, nome, cognome) VALUES(?, ?, ?, ?)";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(insertSQL);
												
			preparedStatement.setString(1, persona.getCodID());
			preparedStatement.setString(2, persona.getPassword());
			preparedStatement.setString(3, persona.getNome());
			preparedStatement.setString(4, persona.getCognome());
			
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
	public void doUpdate(AmministratoreBean persona) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String updateSQL="UPDATE "+ AmministratoreModelDM.TABLE_NAME + 
				" SET  codID = ?, password= ?, nome = ?, cognome = ? WHERE codID= ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, persona.getCodID());
			preparedStatement.setString(2, persona.getPassword());
			preparedStatement.setString(3, persona.getNome());
			preparedStatement.setString(4, persona.getCognome());
						
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
	public void doDelete(AmministratoreBean persona) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String deleteSQL="DELETE FROM " + AmministratoreModelDM.TABLE_NAME + " WHERE codID = ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, persona.getCodID());
			
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
