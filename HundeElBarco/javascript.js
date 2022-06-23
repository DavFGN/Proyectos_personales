function genera_tabla() {
  var body = document.getElementsByTagName("body")[0];
 

  var tabla   = document.createElement("table");
  var tblBody = document.createElement("tbody");
 var nvertical = document.getElementById("nVer").value;
  var nHorizontal = document.getElementById("nHor").value;
  
  document.getElementById('nVerDefinitivo').value=nvertical;
	document.getElementById('nHorDefinitivo').value=nHorizontal;
 
  for (var i = 0; i < nvertical; i++) {
    var hilera = document.createElement("tr");
 
    for (var j = 0; j < nHorizontal; j++) {
     
      var celda = document.createElement("td");
      var textoCelda = document.createTextNode(""+i+""+j);
      celda.appendChild(textoCelda);
      hilera.appendChild(celda);
    }
 
    tblBody.appendChild(hilera);
  }

  tabla.appendChild(tblBody);
  body.appendChild(tabla);
}

function dosF() {
	genera_tabla();
	document.getElementById("nVer").disabled = true;
	document.getElementById("nHor").disabled = true;
	document.getElementById("sig").disabled = false;

	alert("FUNCION TERMINADA, ESTE SERÁ EL TAMAÑO DE LA TABLA DEFINITIVO");
}

