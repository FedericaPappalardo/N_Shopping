package shop.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.bean.ClientBean;
import shop.model.ACCESSModel;
import shop.model.ClientModelDM;
import shop.model.DriverManagerConnectionPool;

@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Registrazione() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isDriverManager = request.getParameter("driver");
		if(isDriverManager == null || isDriverManager.equals("")) {
			isDriverManager = "drivermanager";
		} 
		
		ACCESSModel<ClientBean> model = null;

		if (isDriverManager.equals("drivermanager")) {
			DriverManagerConnectionPool dm = (DriverManagerConnectionPool) getServletContext()
					.getAttribute("DriverManager");
			System.out.println("DB: " + isDriverManager);
			model = new ClientModelDM(dm);			
		} else {
			System.out.println("Fuori");
		}
		String fwpage= "/Registrazione.jsp";
	try {
		String userID = request.getParameter("userID");
		String password = request.getParameter("pass");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String mail = request.getParameter("mail");
		String telefono =request.getParameter("telefono");
		
		ClientBean bean = new ClientBean();
		bean.setUserID(userID);
		bean.setPassword(password);
		bean.setNome(nome);
		bean.setCognome(cognome);
		bean.setMail(mail);
		bean.setTelefono(telefono);
		
		model.doSave(bean);
		 fwpage= "/ClientControl";
		
	} catch(SQLException  e) {
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
