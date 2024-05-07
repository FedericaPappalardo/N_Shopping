<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="CheckoutStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="ajaxJSON.js"></script>

<script>
function displayResults(listJSON, id) {
	try { 
		var json = JSON.parse(listJSON) 
		var obj = document.getElementById(id);		
		if(obj != null) {
			while (obj.hasChildNodes()) {
			    obj.removeChild(obj.childNodes[0]);
			}
			
			var rdfs = json.info.person;
			var len = rdfs.length;
			for(var i=0; i<len; i++) {
            	var label = document.createElement("p");
            	label.className = "added";
            	label.appendChild( document.createTextNode(rdfs[i]));            
            	obj.appendChild(label);
			}
			console.log("Handle JSON results");
			document.getElementById("completato").hidden=false;
			document.getElementById("call").style.display="none";
			document.getElementById("result").style.visibility="visible";
		}
	} catch(e1) {
	}
	 
}

var i = 0;
function count(){
	var obj = document.getElementById("count");
	obj.innerHTML = i;
	i = i + 1;
    setTimeout(count, 1000);
}

function openPage(pageURL)
{
window.location.href = pageURL;
}
</script>
</head>
<body onload="count();">
<h2>Responsive Checkout Form</h2>

<div class="row">
  <div class="col-75">
    <div class="containerC">
      
      
        <div class="row">
          <div class="col-50">
            <h3>Indirizzo Di Fatturazione</h3>
            <label for="fname"><i class="fa fa-user"></i>Nome Completo</label>
            <input type="text" id="fname" name="firstname" placeholder="John M. Doe">
            <label for="email"><i class="fa fa-envelope"></i> Email</label>
            <input type="text" id="email" name="email" placeholder="john@example.com">
            <label for="adr"><i class="fa fa-address-card-o"></i>Indirizzo</label>
            <input type="text" id="adr" name="address" placeholder="Via Luigi Guercio 336">
            <label for="city"><i class="fa fa-institution"></i> Citta</label>
            <input type="text" id="city" name="city" placeholder="New York">

            <div class="row">
              <div class="col-50">
                <label for="state">Stato</label>
                <input type="text" id="state" name="state" placeholder="IT">
              </div>
              <div class="col-50">
                <label for="zip">Cap</label>
                <input type="text" id="zip" name="zip" placeholder="87452">
              </div>
            </div>
          </div>

          <div class="col-50">
            <h3>Payment</h3>
            <label for="fname">Carte accettate</label>
            <div class="icon-container">
              <i class="fa fa-cc-visa" style="color:navy;"></i>
              <i class="fa fa-cc-amex" style="color:blue;"></i>
              <i class="fa fa-cc-mastercard" style="color:red;"></i>
              <i class="fa fa-cc-discover" style="color:orange;"></i>
            </div>
            <label for="cname">Nome Intestatario</label>
            <input type="text" id="cname" name="cardname" placeholder="John More Doe">
            <label for="ccnum">Numero Carta di Credito</label>
            <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
            <label for="expmonth">Scadenza Mese</label>
            <input type="text" id="expmonth" name="expmonth" placeholder="September">
            <div class="row">
              <div class="col-50">
                <label for="expyear">Scadenza Anno</label>
                <input type="text" id="expyear" name="expyear" placeholder="2018">
              </div>
              <div class="col-50">
                <label for="cvv">CVV</label>
                <input type="text" id="cvv" name="cvv" placeholder="352">
              </div>
            </div>
          </div>
          
        </div>
        <label>
          <input type="checkbox" checked="checked" name="sameadr"> Indirizzo di spedizione uguale alla fatturazione
        </label>
    </div>
  </div>
</div>
<br>
<div id="result" class="alert alert-primary"style="visibility: hidden"></div>
<br>
<input id="times" name="times" type="hidden" value="5"><br>
<input id="timeout" name="timeout" type="hidden" value="5000" min=0><br>
<input type="button" id="call" onclick="i=i+10;ajaxCall('result','/N_Shopping/ServletResponseJSON',displayResults,document.getElementById('times').value, document.getElementById('fname').value, document.getElementById('timeout').value);" value="Effettua il pagamento" class="btn">
<input type="button" id="completato" hidden="true" value="Torna alla pagina di menu" class="btn" onclick="openPage('ClientView.jsp')">
<div id="wait"><img src="imgs/loading.gif"></div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
