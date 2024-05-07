package shop.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.bean.ProdottoBean;
import shop.model.CRUDModel;
import shop.model.DriverManagerConnectionPool;
import shop.model.ProdottoModelDM;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuServlet() {
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

			String action = request.getParameter("action");
			try {
				if(action != null) {
					if(action.equals("about")) {
						
					}   else if(action.equals("contact")) {
												
					}  else if(action.equals("login")) {
						response.sendRedirect("LoginServlet");
						
					} else if(action.equals("details")) {
						String id = request.getParameter("id");
						request.removeAttribute("product");
						request.setAttribute("product", model.doRetrieveByKey(id));
					} 
				}
			} catch( SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());			
			}
			
			String sort = request.getParameter("sort");
			try {
				request.removeAttribute("products");
				request.setAttribute("products", model.doRetrieveAll(sort));
			} catch(SQLException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());
			}
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Menu.jsp");
			dispatcher.forward(request, response);

		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
