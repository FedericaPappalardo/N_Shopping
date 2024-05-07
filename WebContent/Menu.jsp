<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.* , shop.bean.ProdottoBean"%>
    
    <%
 	Collection<?> products = (Collection<?>) request.getAttribute("products");
 
 	String error = (String)request.getAttribute("error");
 	
 	if(products == null && error == null) {
 		response.sendRedirect(response.encodeRedirectURL("./MenuServlet"));
 		return;
 	}
 	 	
 	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
 %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="MenuStyle.css" rel="stylesheet" type="text/css">

<script> 
$(function () {
	  $('[data-toggle="popover"]').popover({html: true});
	})
</script>
</head>
<body>
<header>

   <nav class="navbar navbar-expand-lg navbar-light">
  <a class="navbar-brand" href="#">SHOP</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto align-items-center">
      <li class="nav-item active">
        <a class="navbar-brand" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="navbar-brand" href="#">About</a>
      </li>
       <li class="nav-item">
        <a class="navbar-brand" href="Contatto.html">Contact</a>
      </li>
       </ul>
	<form action="Login.jsp" class="form-inline my-2 my-lg-0">
       <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
    </form>
  </div>
</nav>
  
  </header>
  <br>
	<%
		if(products != null && products.size() > 0) {
			
			Iterator<?> it  = products.iterator();
				
	%>
<main role="main">

  <div class="album">
    <div class="container">

<div class="row">
	<% while(it.hasNext()) {
		ProdottoBean bean = (ProdottoBean)it.next(); %>
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <img src="./getPicture?id=<%=bean.getCodice()%>" onerror="this.src='./imgs/nophoto.png'" class="w3-hover-opacity" style="width:300px;height:280px">
            <div class="card-body">
              <p class="card-text"><%=bean.getNome()%></p>
              <p class="card-text"><%=bean.getPrezzo()%> &euro;</p>
              <div class="d-flex justify-content-between align-items-center">
               <button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Dettagli Prodotto" data-placement="bottom"
             			data-content=" 
             			<b>Codice=</b> <%=bean.getCodice() %> <br>
             			<b>Nome=</b> <%=bean.getNome()%> <br>
             			<b>Descrizione=</b> <%=bean.getDescrizione() %> <br>
             			<b>Prezzo=</b> <%=bean.getPrezzo()%> &euro; <br>
             			<b>Quantita=</b> <%=bean.getQuantita()%> ">Dettagli</button>
                
              </div>
            </div>
          </div>
        </div>
        <% } %>
      </div>
      </div>
      </div>
      
</main>

<% } 
	
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

<!-- Footer -->
<footer class="page-footer font-small blue">

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3">&copy; 2020 Copyright </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->
</body>
</html>