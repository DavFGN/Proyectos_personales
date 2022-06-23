function datos() {

    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

        }
    };

    let csrfToken = document.getElementById("_token").value;

    var codigo = document.getElementById("cod").textContent;
    var admin = document.getElementById("admin");
    var ban = document.getElementById("ban");

    var datos = "";

    if (admin.checked == true) {
        datos += "admin=true&";
    } else {
        datos += "admin=false&";
    }

    if (ban.checked == true) {
        datos += "ban=true";
    } else {
        datos += "ban=false";
    }

    xmlhttp.open("POST", window.location.origin + "/admin/perfil/" + codigo, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.setRequestHeader('X-CSRF-Token', csrfToken);
    xmlhttp.send(datos);


}
