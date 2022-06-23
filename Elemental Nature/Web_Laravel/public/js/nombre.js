let codigo = document.getElementById('cod').textContent;;
let nombreA = document.getElementById('usuario').value;
let caja = document.getElementById('usuario');

function comprobar() {

    let usuario = document.getElementById('usuario').value;

    if (usuario.length == 0 || usuario == nombreA) {

        caja.classList.remove("noDisponible");
        caja.classList.remove("disponible");

    } else {

        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {

                let texto = this.responseText;

                caja.classList.remove("noDisponible");
                caja.classList.remove("disponible");

                if (texto.includes("Ok")) {
                    caja.classList.add("disponible");
                } else {
                    caja.classList.add("noDisponible");
                }
            }
        };

        xmlhttp.open("GET", window.location.href + "/" + usuario, true);
        xmlhttp.send();

    }
}

$(".input1").on('keyup', function (e) {
    if (e.key === 'Enter' || e.keyCode === 13) {

        let usuario = document.getElementById('usuario').value;

        caja.classList.remove("noDisponible");
        caja.classList.remove("disponible");

        caja.classList.add("nombreCambio");
        console.log("Nombre cambiado a " + usuario)

        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                sessionStorage.clear();
            }
        };

        xmlhttp.open("GET", window.location.href + "/actualizar/" + codigo + "/" + usuario, true);
        xmlhttp.send();

    }
});
