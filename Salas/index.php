<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Examen 5</title>
        <style>
            table {
                background-color: acquamarine;
                margin: auto;
                color: black;
                border: 2px solid black;
                border-collapse: collapse;
                padding: 5px;
                border-radius: 20px;
                text-align: center;
            }
            td {
                padding: 5px;
                height: 40px;
                width: 30px;
                border: 2px solid black;
            }

            tr {
                padding: 5px;
            }
        </style>
    </head>
    <body>

        <div>
            <select id="sala" onchange="modo()">
                <option value="elegir">Elija una sala</option>
                <option value="sala1">Sala 1</option>
                <option value="sala2">Sala 2</option>
                <option value="sala3">Sala 3</option>
            </select>
            <br>
            <div id="info">

            </div>
        </div>

        <div id="tabla">

        </div>

        <script>

            function modo() {

                let u = document.getElementById('sala').value;
                let sa = document.getElementById('tabla');
                document.getElementById('info').innerHTML = "";
                if(u != "elegir"){
                sa.innerHTML = "";
                var xmlhttp = new XMLHttpRequest();

                xmlhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {

                        let json = JSON.parse(this.response);

                        var tabla = document.createElement("table");
                        var tblBody = document.createElement("tbody");
                        let filas = json.length / 5;
                        let contador = 0;
                        console.table(json);
                        for (var i = 0; i < filas; i++) {
                            var hilera = document.createElement("tr");

                            for (var j = 0; j < 5; j++) {

                                let asi = contador + 1;
                                if (json[contador]["ocupado"] == "0") {

                                    var celda = document.createElement("td");
                                    var textoCelda = document.createTextNode("" + asi);
                                    celda.id = "a" + contador;
                                    
                                    celda.setAttribute("onclick", "add(" + json[contador]["asiento"] + ")");
                                    
                                    celda.appendChild(textoCelda);
                                    hilera.appendChild(celda);

                                } else {
                                    var celda = document.createElement("td");
                                    var textoCelda = document.createTextNode("" + asi);
                                    celda.style.backgroundColor = "red";
                                    celda.id = "a" + contador;
                                    celda.setAttribute("onclick", "add(" + json[contador]["asiento"] + ")");
                                    celda.appendChild(textoCelda);
                                    hilera.appendChild(celda);
                                }

                                contador++;
                            }

                            tblBody.appendChild(hilera);
                        }

                        tabla.appendChild(tblBody);
                        sa.appendChild(tabla);
                    }
                }
                ;
                xmlhttp.open("GET", "prim.php?u=" + u, true);
                xmlhttp.send();
            } else {
                sa.innerHTML = "";
            }
            }

            function add(asiento) {
                console.log(asiento);
                let actual = asiento - 1;
                let u = document.getElementById('sala').value;
                asie = document.getElementById("a" + actual);
                asie.style.backgroundColor = "red";

                var xmlhttp = new XMLHttpRequest();

                xmlhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                     document.getElementById('info').innerHTML = this.response;   
                    }
                }

                ;
                xmlhttp.open("GET", "seg.php?a=" + asiento + "&u=" + u, true);
                xmlhttp.send();
            }
        </script>
    </body>
</html>
