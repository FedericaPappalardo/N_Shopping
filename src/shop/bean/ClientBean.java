package shop.bean;

import java.io.Serializable;

public class ClientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String userID;
	String password;
	String nome;
	String cognome;
	String mail;
	String telefono;
	String dataAcquisto;
	
	public ClientBean() {
		userID="";
		password="";
		nome="";
		cognome="";
		mail="";
		telefono="";
		dataAcquisto="";
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(String dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	@Override
	public String toString() {
		return userID + " , " + password + " , " + nome + " , " + cognome + " , " + mail + " , " + telefono 
				+ " , " + dataAcquisto; 
	}
}
