package shop.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import shop.bean.ProdottoBean;
import shop.model.ProdottoModelDM;
import shop.model.CRUDModel;
import shop.model.DriverManagerConnectionPool;

@WebServlet("/AmministratoreControl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class AmministratoreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AmministratoreControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "drivermanager";
		} 
		
		CRUDModel<ProdottoBean> model = null;

		if (isDriverManager.equals("drivermanager")) {
			DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext()
					.getAttribute("DriverManager");
			System.out.println("DB: " + isDriverManager);
			model = new ProdottoModelDM(dm);			
		} else {
			System.out.println("Fuori");
		}

		String sort = request.getParameter("sort");
		
		String action = request.getParameter("action");
				
		try {
			if(action != null) {
				if(action.equals("details")) {
					String id = request.getParameter("id");
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					
				}   else if(action.equals("insert")) {
					String id = request.getParameter("id");
					String nome = request.getParameter("nome");
					String descrizione = request.getParameter("descrizione");
					int prezzo = Integer.parseInt(request.getParameter("prezzo"));
					int quantita = Integer.parseInt(request.getParameter("quantita"));
					Part foto=request.getPart("foto");
					
					ProdottoBean bean = new ProdottoBean();
					bean.setCodice(Integer.parseInt(id));
					bean.setNome(nome);
					bean.setDescrizione(descrizione);
					bean.setPrezzo(prezzo);
					bean.setQuantita(quantita);
					bean.setFoto(foto);					
					
					model.doSave(bean);
					request.setAttribute("message", "Prodotto "+ bean.getNome()+" aggiunto");
					
				} else if(action.equals("delete")) {
					String id = request.getParameter("id");
					ProdottoBean bean = (ProdottoBean) model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						model.doDeleteAm(bean);
						request.setAttribute("message", "Prodotto "+ bean.getNome()+" cancellato");
					}
					
				} else if(action.equals("update")) {
					String id = request.getParameter("id");
					String nome = request.getParameter("nome");
					String descrizione = request.getParameter("descrizione");
					int prezzo = Integer.parseInt(request.getParameter("prezzo"));
					int quantita = Integer.parseInt(request.getParameter("quantita"));	
					
					ProdottoBean bean = new ProdottoBean();
					bean.setCodice(Integer.parseInt(id));
					bean.setNome(nome);
					bean.setDescrizione(descrizione);
					bean.setPrezzo(prezzo);
					bean.setQuantita(quantita);
					
					model.doUpdateAm(bean);
					request.setAttribute("message", "Product "+ bean.getNome()+" aggiornato");
				}
			}
		} catch(SQLException | NumberFormatException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());			
		}
				
		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AmministratoreView.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);	
	}

}
