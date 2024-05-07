package shop.bean;

import java.io.Serializable;

public class AmministratoreBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String codID;
	String password;
	String nome;
	String cognome;
	
	public AmministratoreBean() {
		codID="";
		password="";
		nome="";
		cognome="";
	}

	public String getCodID() {
		return codID;
	}

	public void setCodID(String codID) {
		this.codID = codID;
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

	@Override
	public String toString() {
		return codID + " , " + password + " , " + nome + " , " + cognome;
	}
}
