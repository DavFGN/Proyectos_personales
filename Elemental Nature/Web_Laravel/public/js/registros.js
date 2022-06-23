function comprobar() {

    let usuario = document.getElementById('nombre').value;
    let fecha = document.getElementById('fecha').value;
    let tabla = document.getElementById('resultados');

    if (usuario.length == 0 && fecha.length == 0) {

        tabla.innerHTML = "";

    } else {

        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {

                if (this.response.includes('[{"') == true) {
                    let json = JSON.parse(this.response);
                    tabla.innerHTML = "";

                    if (json.length > 0) {

                        for (var i = 0; i < json.length; i++) {

                            var tr = document.createElement('tr');

                            var celda = document.createElement("td");
                            var textoCelda = document.createTextNode(json[i]["reg_cod"]);
                            celda.classList.add("tdU");
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            var celda = document.createElement("td");
                            var textoCelda = document.createTextNode(json[i]["cod_usu"] + "(" + json[i]["nombre"] + ")");
                            celda.classList.add("tdU");
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            celda = document.createElement("td");
                            celda.classList.add("tdU");
                            textoCelda = document.createTextNode(json[i]["fecha"]);
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            celda = document.createElement("td");
                            celda.classList.add("tdU");
                            textoCelda = document.createTextNode(json[i]["ip"]);
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            tabla.appendChild(tr);
                        }
                    }
                }
            }
        };

        let url = window.location.origin + "/admin/registros/busqueda/";

        if (usuario.length == 0) {
            url += "no";
        } else {
            url += usuario;
        }

        url += "/";

        if (fecha.length == 0) {
            url += "no";
        } else {
            url += fecha;
        }

        console.log(url);

        xmlhttp.open("GET", url, true);
        xmlhttp.send();

    }
}
