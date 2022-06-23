function comprobarCajas() {
    var x = document.getElementById("envA").value;
    var y = document.getElementById("envR").value;
    var z = document.getElementById("mensaj").value;

    if (x.length > 0 && z.length > 0 && y.length > 0) {
        document.getElementById("conf").disabled = false;

    } else {
        document.getElementById("conf").disabled = true;
    }
}

function comprobar() {

    let usuario = document.getElementById('envR').value;

    let lista = document.getElementById('listaNombres');
    lista.innerHTML = "";

    if (usuario.length == 0) {

    } else {


        var xmlhttp = new XMLHttpRequest();


        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {

                console.log(this.response);

                if (this.response.includes('[{"') == true) {
                    let json = JSON.parse(this.response);
                    lista.innerHTML = "";

                    if (json.length > 0) {

                        for (var i = 0; i < json.length; i++) {

                            var opt = document.createElement('option');
                            opt.value = json[i]["nombre"];
                            opt.innerHTML = json[i]["nombre"];

                            lista.appendChild(opt);
                        }
                    }
                }
            }
        };

        xmlhttp.open("GET", window.location.origin + "/buscarUsuario/" + usuario, true);
        xmlhttp.send();

        comprobarCajas();
    }
}
