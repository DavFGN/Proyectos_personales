function comprobar() {

    let usuario = document.getElementById('seaN').value;
    let tabla = document.getElementById('resultados');

    if (usuario.length == 0) {

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
                            var textoCelda = document.createTextNode(json[i]["codigo"]);
                            celda.classList.add("tdU");
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            celda = document.createElement("td");
                            celda.classList.add("tdU");
                            var a = document.createElement('a');
                            var linkText = document.createTextNode(json[i]["nombre"]);
                            a.appendChild(linkText);
                            a.title = json[i]["nombre"];
                            a.href = window.location.origin + "/admin/perfil/" + json[i]["codigo"];
                            celda.appendChild(a);
                            tr.appendChild(celda);

                            celda = document.createElement("td");
                            celda.classList.add("tdU");
                            textoCelda = document.createTextNode(json[i]["fecha"]);
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            celda = document.createElement("td");
                            celda.classList.add("tdU");
                            textoCelda = document.createTextNode(json[i]["administrador"]);
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            celda = document.createElement("td");
                            celda.classList.add("tdU");
                            textoCelda = document.createTextNode(json[i]["ban"]);
                            celda.appendChild(textoCelda);
                            tr.appendChild(celda);

                            tabla.appendChild(tr);
                        }
                    }
                }
            }
        };

        xmlhttp.open("GET", window.location.origin + "/buscarUsuario/" + usuario, true);
        xmlhttp.send();

    }
}
