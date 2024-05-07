<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.* , shop.bean.ProdottoBean, shop.model.Carrello"%>
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
    
Carrello cart = (Carrello)request.getAttribute("cart");
	
 	if(cart == null) {
 		response.sendRedirect(response.encodeRedirectURL("./ClientControl"));
 		return;
 	}	
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="Style.css" rel="stylesheet" type="text/css">
		
</head>
<body>
<header>
<a href="ClientView.jsp" class="p">INDIETRO</a>
<h2>Carrello</h2>
</header>

<table>
<thead>
	<tr>
		<th>Nome</th>
		<th>Prezzo</th>
		<th>Action</th>
	</tr>	
	</thead>
	<%
	List<ProdottoBean> prodcart = cart.getProdotti();
		if(prodcart.size() > 0) {
			for(ProdottoBean prod: prodcart) {
	%>
			<tbody>
			<tr>
				<td><%=prod.getNome()%></td>
				<td><%=prod.getPrezzo()%></td>
				<td><a href="<%=response.encodeURL("ClientControl?action=deleteCart&id=" + prod.getCodice())%>">Delete from cart</a>
			</tr>
			</tbody>
	<% 		}
		} else {
	%>
		<tr><td colspan="2">No product available in the cart</td></tr>
	<%
		}
	%>
</table>
<%	if(prodcart.size() > 0) {
%>
	<a href="<%=response.encodeURL("ClientControl?action=buy")%>">Buy</a>
<%  } %>

</body>
</html>