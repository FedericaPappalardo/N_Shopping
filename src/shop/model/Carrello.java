package shop.model;

import java.util.ArrayList;
import java.util.List;

import shop.bean.ProdottoBean;

public class Carrello {

	List<ProdottoBean> prodotti;
	
	public Carrello() {
		prodotti= new ArrayList<ProdottoBean>();
	}

	public void addProdotto(ProdottoBean prodotto) {
		prodotti.add(prodotto);
	}
	
	public void deleteProdotto(ProdottoBean prodotto) {
		for(ProdottoBean it: prodotti) {
			if(it.equals(prodotto)) {
				prodotti.remove(it);
				break;
			}
		}
	
	}
	
	public List<ProdottoBean> getProdotti(){
		return prodotti;
	}
	
	public void deleteProdotti() {
		prodotti.clear();
	}
}
