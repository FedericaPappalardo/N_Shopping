package shop.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.ProdottoModelDM;
import shop.bean.ProdottoBean;
import shop.model.CRUDModel;
import shop.model.Carrello;
import shop.model.DriverManagerConnectionPool;

@WebServlet("/ClientControl")
public class ClientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
    public ClientControl() {
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
			model = new ProdottoModelDM(dm);			
		}  else {
			System.out.println("Fuori");
		}
		
		Carrello cart = (Carrello)request.getSession().getAttribute("carrello");
		
		if(cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("carrello", cart);
		}
		String fwpage= "/ClientView.jsp";
		String sort = request.getParameter("sort");
		
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				if(action.equals("details")) {
					String id = request.getParameter("id");
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					fwpage= "/Details.jsp";
				
				} else if(action.equals("addCart")) {
					String id = request.getParameter("id");
					ProdottoBean bean = model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						cart.addProdotto(bean);
						model.doUpdateAcquisto(bean);
					}
				}  else if(action.equals("buy")) {
					cart.deleteProdotti();
					 fwpage= "/Checkout.jsp";
				
				}else if(action.equals("deleteCart")) {
					String id = request.getParameter("id");
					ProdottoBean bean = model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						cart.deleteProdotto(bean);
						model.doUpdateCancel(bean);
						fwpage= "/Carrello.jsp";
					}
				} else if(action.equals("carrello")) {
					
					fwpage= "/Carrello.jsp";
				
				}
			}
		} catch(SQLException | NumberFormatException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());			
		}
		
		request.setAttribute("cart", cart);
		
		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(fwpage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
