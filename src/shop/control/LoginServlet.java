package shop.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.bean.AmministratoreBean;
import shop.bean.ClientBean;
import shop.model.ACCESSModel;
import shop.model.AmministratoreModelDM;
import shop.model.ClientModelDM;
import shop.model.DriverManagerConnectionPool;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "drivermanager";
		} 
		
		ACCESSModel<ClientBean> model1=null;
		ACCESSModel<AmministratoreBean> model2=null;
		
		if (isDriverManager.equals("drivermanager")) {
			DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext()
					.getAttribute("DriverManager");
			System.out.println("DB: " + isDriverManager);
			model1 = new ClientModelDM(dm);
			model2= new AmministratoreModelDM(dm);
			
		} else {
			System.out.println("Fuori");
		}

		boolean cliente=true;
		boolean amministratore=true;
		
		String user= request.getParameter("username");
		String password= request.getParameter("password");
				
		try {
			cliente=model1.doRetrieveByKey(user, password);
			amministratore= model2.doRetrieveByKey(user, password);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
				
		if(cliente && !amministratore) {
			System.out.println("Cliente");
			request.getSession().setAttribute("adminRoles", new Boolean(true));
			response.sendRedirect("ClientControl");
			
		} else if(amministratore && !cliente) {
			System.out.println("Amministratore");
			request.getSession().setAttribute("adminRoles", new Boolean(true));
			response.sendRedirect("AmministratoreControl");
		} else {
		request.setAttribute("error", Boolean.TRUE);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
