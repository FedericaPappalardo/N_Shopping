package shop.control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/ServletResponseJSON")
public class ServletResponseJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletResponseJSON() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");

		JSONObject json = new JSONObject();

		try {
			int param = 1;
			try {
				param = Integer.parseInt(request.getParameter("param"));
			} catch(NumberFormatException e) {}
			
			String param2 = (String) request.getParameter("param2");
			if (param2 != null) {
				JSONObject o1 = new JSONObject();
				
					o1.append("person", ""+" "+param2 + " ha effettuato l'acquisto con successo in data:  "+new Date().toString());
				
				json.put("info", o1);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		} catch (JSONException e) {

		}

		response.getWriter().print(json.toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
