<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* , shop.bean.ProdottoBean"%>
<%
// Check user credentials
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
if ((adminRoles == null) || (!adminRoles.booleanValue()))
{	
    response.sendRedirect("./Login.jsp");
    return;
}
%>
<%
 	Collection<?> products = (Collection<?>) request.getAttribute("products");
 
 	String error = (String)request.getAttribute("error");
 	
 	if(products == null && error == null) {
 		response.sendRedirect(response.encodeRedirectURL("./AmministratoreControl"));
 		return;
 	}
 	
 	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
 %>   
    
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="Style.css" rel="stylesheet" type="text/css">
		<title>Amministratore</title>
</head>

<body>
<header class="top">
<a href="Menu.jsp" class="p">Logout</a>

<h1>Gestione Shop</h1>
</header>	
	
<table>
<thead>
	<tr>
		<th>Codice</th>
		<th>Nome</th>
		<th>Descrizione</th>
		<th>Action</th>
	</tr>
	</thead>
	<%
		if(products != null && products.size() > 0) {
			
			Iterator<?> it  = products.iterator();
			while(it.hasNext()) {
				ProdottoBean bean = (ProdottoBean)it.next();	
	%>
				<tbody>
				<tr>
				<td><%=bean.getCodice()%></td>
				<td><%=bean.getNome()%></td>
				<td><%=bean.getDescrizione()%></td>
				<td>
					<a href="<%=response.encodeURL("AmministratoreControl?action=details&id=" + bean.getCodice())%>">Details</a>
					<a href="<%=response.encodeURL("AmministratoreControl?action=delete&id=" + bean.getCodice())%>">Delete</a>
				</td>
			</tr>
			</tbody>
	<% 		} 
	 	} else { %>
	<tr>
		<td colspan="4">No product available</td>
	</tr>	
	<% } %>
</table>

	<%
		if(product != null && !product.isEmpty()) {
	%>
		<h2>Details</h2>
		<table>
			<thead>
			<tr>
				<th>Codice</th>
				<th>Nome</th>
				<th>Descrizione</th>
				<th>Prezzo</th>
				<th>Quantita</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td><%=product.getCodice()%></td>
				<td><%=product.getNome()%></td>
				<td><%=product.getDescrizione()%></td>
				<td><%=product.getPrezzo()%></td>
				<td><%=product.getQuantita()%></td>
				</tr>
			</tbody>
		</table>
		<br>
		<div class="container">
		<form action="<%=response.encodeURL("AmministratoreControl")%>" enctype="multipart/form-data" method="POST">
			<h3>UPDATE</h3>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="id" value="<%=product.getCodice() %>">
		      <div class="row">
		      <div class="col-25">
		        <label for="nome">Nome</label>
		      </div>
		      <div class="col-75">
		        <input name="nome" type="text" maxlength="20" placeholder="enter nome" value="<%=product.getNome() %>"><br>
		      </div>
		      </div>
		      <div class="row">
		      <div class="col-25">
		        <label for="descrizione">Descrizione</label>
		      </div>
		      <div class="col-75">
		        <textarea name="descrizione" maxlength="100" rows="3" placeholder="enter descrizione"><%=product.getDescrizione() %></textarea><br>
		      </div>
		      </div>
		      <div class="row">
		      <div class="col-25">
		        <label for="prezzo">Prezzo</label>
		      </div>
		      <div class="col-75">
		        <input name="prezzo" type="number" min="0" value="<%=product.getPrezzo() %>"><br>
		      </div>
		      </div>
		      <div class="row">
		      <div class="col-25">
		        <label for="quantita">Quantita</label>
		      </div>
		      <div class="col-75">
		       <input name="quantita" type="number" min="1" value="<%=product.getQuantita() %>"><br>
		      </div>
		      </div>
		      <div class="row">
		      <input type="submit" value="Submit">
		      <input type="reset" value="Reset">
		    </div>
		</form>		
		</div>
		
	<%  } %>

<%
	String message = (String)request.getAttribute("message");
	if(message != null && !message.equals("")) {
%>
	<p style="color: green;"><%=message %></p>
<%
	}
	if(error != null && !error.equals("")) {
%>
	<p style="color: red;">Error: <%= error%></p>
<%
	}
%>	
<br>
<div class="container">
<form action="<%=response.encodeURL("AmministratoreControl")%>" enctype="multipart/form-data"  method="POST">
<h3>INSERT</h3>
<input type="hidden" name="action" value="insert">
 <div class="row">
      <div class="col-25">
        <label for="id">Codice</label>
      </div>
      <div class="col-75">
        <input type="text" name="id" placeholder="Codice" required>
      </div>
      </div>
      <div class="row">
      <div class="col-25">
        <label for="nome">Nome</label>
      </div>
      <div class="col-75">
        <input name="nome" type="text" maxlength="20" placeholder="enter nome" required><br>
      </div>
      </div>
      <div class="row">
      <div class="col-25">
        <label for="descrizione">Descrizione</label>
      </div>
      <div class="col-75">
        <textarea name="descrizione" maxlength="100" rows="3" placeholder="enter descrizione" required></textarea><br>
      </div>
      </div>
      <div class="row">
      <div class="col-25">
        <label for="prezzo">Prezzo</label>
      </div>
      <div class="col-75">
        <input name="prezzo" type="number" min="0" value="0" required><br>
      </div>
      </div>
      <div class="row">
      <div class="col-25">
        <label for="quantita">Quantita</label>
      </div>
      <div class="col-75">
       <input name="quantita" type="number" min="1" value="1" required><br>
      </div>
      </div>
      <div class="row">
      <div class="col-25">
        <label for="foto">Foto</label>
      </div>
       <div class="col-75">
       <input type="file" name="foto" value="" maxlength="255"><br>
      </div>
       </div>
      <div class="row">
      <input type="submit" value="Submit">
      <input type="reset" value="Reset">
    </div>
</form>
</div>
</body>
</html>