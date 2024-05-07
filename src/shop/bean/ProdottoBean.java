package shop.bean;

import java.io.Serializable;
import java.sql.Blob;

import javax.servlet.http.Part;

public class ProdottoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	int codice;
	String nome;
	int prezzo;
	String descrizione;
	int quantita;
	Part foto;
	Blob foto1;
	
	public ProdottoBean() {
		codice=-1;
		nome="";
		prezzo=0;
		descrizione="";
		quantita=0;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Part getFoto() {
		return foto;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

	public Blob getMyFoto() {
		return foto1;
	}
	
	public void setMyFoto(Blob foto1) {
		this.foto1 = foto1;
	}
	@Override
	public String toString() {
		return codice + " , " + nome + " , " + prezzo + " , " + descrizione + " , " + quantita + " , " + foto;
	}

	@Override
	public boolean equals(Object other) {
		return (this.getCodice() == ((ProdottoBean)other).getCodice());
	}
	
	public boolean isEmpty() {
		return codice== -1;
	}
}
