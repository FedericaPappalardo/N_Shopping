package shop.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.Part;

import shop.bean.ProdottoBean;

public class ProdottoModelDM implements CRUDModel<ProdottoBean> {

	private DriverManagerConnectionPool dmcp = null;
	public ProdottoModelDM(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		
		System.out.println("DriverManager Product Model creation....");
	}
	private static final String TABLE_NAME = "prodotto";
	@Override
	public ProdottoBean doRetrieveByKey(String codice) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		ProdottoBean bean= new ProdottoBean();
		
		String selectSQL="SELECT * FROM " + ProdottoModelDM.TABLE_NAME + " WHERE codice = ?";
		
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, Integer.parseInt(codice));
			
			System.out.println("doRetriveByKey: " + preparedStatement.toString());
			ResultSet rs= preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
			}
			
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
				}
				finally {
					dmcp.releaseConnection(connection);
				}
		}
		
		return bean;
	}

	@Override
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatment=null;
		
		Collection<ProdottoBean> prodotti= new LinkedList<ProdottoBean>();
		
		String selectSQL= "SELECT * FROM " + ProdottoModelDM.TABLE_NAME;
		
		
		if(order != null && !order.equals("")){
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			
			connection=  dmcp.getConnection();
			preparedStatment= connection.prepareStatement(selectSQL);
			
			ResultSet rs= preparedStatment.executeQuery();
			
			while(rs.next()) {
				
				ProdottoBean bean= new ProdottoBean();
				
				bean.setCodice(rs.getInt("codice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setMyFoto(rs.getBlob("foto"));
				
				prodotti.add(bean);
			}
		}
		finally {
			try {
			if(preparedStatment != null)
				preparedStatment.close();
			}
			finally {
				dmcp.releaseConnection(connection);
			}
		}
		
		return prodotti;
		
	}

	@Override
	public void doSave(ProdottoBean prodotto) throws SQLException, IOException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		 InputStream inputStream = null;
		
		 String insertSQL="INSERT INTO " + ProdottoModelDM.TABLE_NAME + 
				"(codice, nome, descrizione, prezzo, quantita, foto) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			Part prova= prodotto.getFoto();
			if (prova != null) {
	          inputStream = prova.getInputStream();
	        } 
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(insertSQL);
												
			preparedStatement.setInt(1, prodotto.getCodice());
			preparedStatement.setString(2, prodotto.getNome());
			preparedStatement.setString(3, prodotto.getDescrizione());
			preparedStatement.setInt(4, prodotto.getPrezzo());
			preparedStatement.setInt(5, prodotto.getQuantita());
			preparedStatement.setBlob(6, inputStream);
			
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
	public void doUpdateAm(ProdottoBean prodotto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String updateSQL="UPDATE "+ ProdottoModelDM.TABLE_NAME + 
				" SET  nome = ?, descrizione = ?, prezzo = ?, quantita = ? WHERE codice= ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setString(1, prodotto.getNome());
			preparedStatement.setString(2, prodotto.getDescrizione());
			preparedStatement.setInt(3, prodotto.getPrezzo());
			preparedStatement.setInt(4, prodotto.getQuantita());
						
			preparedStatement.setInt(5, prodotto.getCodice());
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
	public void doUpdateAcquisto(ProdottoBean prodotto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String updateSQL="UPDATE "+ ProdottoModelDM.TABLE_NAME + 
				" SET  quantita = ? WHERE codice= ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(updateSQL);
			int qua=prodotto.getQuantita() -1;
			
			preparedStatement.setInt(2, prodotto.getCodice());
			preparedStatement.setInt(1, qua);
			
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
	public void doUpdateCancel(ProdottoBean prodotto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String updateSQL="UPDATE "+ ProdottoModelDM.TABLE_NAME + 
				" SET  quantita = ? WHERE codice= ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(updateSQL);
			int qua=prodotto.getQuantita() + 1;
			
			preparedStatement.setInt(2, prodotto.getCodice());
			preparedStatement.setInt(1, qua);
			
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
	public void doDeleteAm(ProdottoBean prodotto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String deleteSQL="DELETE FROM " + ProdottoModelDM.TABLE_NAME + " WHERE codice = ?";
		
		try {
			connection=  dmcp.getConnection();
			preparedStatement= connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, prodotto.getCodice());
			
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
