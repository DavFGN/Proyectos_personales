function startTime() {
  var today = new Date();
  var h = today.getHours();
  var m = today.getMinutes();
  var s = today.getSeconds();
  m = checkTime(m);
  s = checkTime(s);
  document.getElementById('txt').innerHTML =
  h + ":" + m + ":" + s;
  var t = setTimeout(startTime, 500);
}

function checkTime(i) {
  if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
  return i;
}

function goBack() {
  window.history.back();
}

function comprobarCajas() {
  var x = document.getElementById("envA").value;
  var y = document.getElementById("envR").value;
  var z = document.getElementById("mensaj").value;

    if (x.length > 0 && z.length > 0 && y.length > 0 ) {
        document.getElementById("conf").disabled = false;
        
    } else {
        document.getElementById("conf").disabled = true;
    }
}

var timeleft = 5;
var downloadTimer = setInterval(function(){
  document.getElementById("countdown").innerHTML = "Sesión cerrada, será "
        +"redirigido en "+timeleft+" segundos...";
  timeleft -= 1;
  if(timeleft < 0){
    clearInterval(downloadTimer);
    window.location.href = "Index.jsp";
  }
}, 1000);

function op_gal1 () {
    var pic = "./Img/L1.jpg";
    document.getElementById('cambiar').src = pic.replace();
    
   var t =  ("<html>\n" +
        "    <body>\n" +
        "        <div style='background:rgb(153,255,153);'>\n" +
        "        Espíritu del viento con gran agilidad <br>\n" +
        "        Ataque:    200 <br>\n" +
        "        Defensa:   20% <br>\n" +
        "        Velocidad: 600 <br>\n" +
        "        Bonus: Aumenta un 25% la velocidad de los luchadores (si es tipo viento "
                    + "añade un 25% también de ataque)<br>\n" +
        "        Posiciones preferidas: <br>\n" +
        "        O   &ensp;   X     &ensp;   X <br>\n" +
        "        X   &ensp;   X     &ensp;   O   \n" +
        "        </div>\n" +
        "</html>");

    document.getElementById("textoG").innerHTML = t;

}

function op_gal2 () {
    var pic = "./Img/L2.png";
    document.getElementById('cambiar').src = pic.replace();
    
    var t = "<div style='background:rgb(51,102,0); color:white'>"
    +"Reina de las valquirias, gran poder ofensivo y agilidad  <br>"
    +"Ataque:    400 <br>"
    +"Defensa:   10% <br>"
    +"Velocidad: 500 <br>"
    +"Bonus: Aumenta un 10% la velocidad y 15% de ataque de los" 
    +"luchadores (15% y 10% adicional si es tipo viento)  <br>"
    +"Posiciones preferidas: <br>"
    +"X  &ensp;     O   &ensp;    X <br>"
    +"O  &ensp;     X   &ensp;    X  </div>";
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal3 () {
    var pic = "./Img/L3.jpg";
    document.getElementById('cambiar').src = pic.replace();
    
    var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(255,102,102)'>\n" +
    "Considerado el principe demonio, poder equilibrado  <br>\n" +
    "Ataque:    350 <br>\n" +
    "Defensa:   30% <br>\n" +
    "Velocidad: 350 <br>\n" +
    "Bonus: Aumenta un 10% la defensa y 15% de ataque de los \n" +
    "luchadores (10% de velocidad y 15% de ataque adicional si es tipo fuego)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "O  &ensp;     X   &ensp;    O <br>\n" +
    "X  &ensp;     X   &ensp;    X  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal4 () {
    var pic = "./Img/L4.png";
    document.getElementById('cambiar').src = pic.replace();
            var t = ("<html>\n" +
     "<body>\n" +
     "<div style='background:rgb(153,0,0); color:white'>\n" +
     "Emperador antiguo dragón de fuego, gran poder ofensivo <br>\n" +
     "Ataque:    600 <br>\n" +
     "Defensa:   25% <br>\n" +
     "Velocidad: 150 <br>\n" +
     "Bonus: Aumenta un 25% el ataque de los \n" +
     "luchadores (25% adicional si es tipo <br> fuego)  <br>\n" +
     "Posiciones preferidas:  <br>\n" +
     "X  &ensp;     X   &ensp;    O <br>\n" +
     "X  &ensp;     O   &ensp;    X  \n" +
     "</div>\n" +
     "</html>");
     
     document.getElementById("textoG").innerHTML = t;
}

function op_gal5 () {
    var pic = "./Img/L5.jpg";
    document.getElementById('cambiar').src = pic.replace();
    
    var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(153,255,255)'>\n" +
    "Criatura del abismo oceánico, gran poder ofensivo y defensivo <br>\n" +
    "Ataque:    500 <br>\n" +
    "Defensa:   40% <br>\n" +
    "Velocidad: 100 <br>\n" +
    "Bonus: Aumenta un 15% la defensa y 10% el ataque de los \n" +
    "luchadores (10% y 15% respectivamente adicional si es tipo agua)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "O  &ensp;     X   &ensp;    X <br>\n" +
    "X  &ensp;     X   &ensp;    O  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal6 () {
    var pic = "./Img/L6.png";
    document.getElementById('cambiar').src = pic.replace();
    
     var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(0,51,204); color:white'>\n" +
    "Deidad del mar al que controla a su antojo, poder equilibrado <br>\n" +
    "Ataque:    350 <br>\n" +
    "Defensa:   35% <br>\n" +
    "Velocidad: 300 <br>\n" +
    "Bonus: Aumenta un 8% de ataque, 9% defensa y 8% de velocidad\n" +
    "de los luchadores (25% adicional de velocidad si es tipo agua)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "O  &ensp;     X   &ensp;    X <br>\n" +
    "O  &ensp;     X   &ensp;    X  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal7 () {
    var pic = "./Img/L7.jpg";
    document.getElementById('cambiar').src = pic.replace();
    
     var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(255,153,102)'>\n" +
    "Titán de tierra, poder equilibrado <br>\n" +
    "Ataque:    300 <br>\n" +
    "Defensa:   40% <br>\n" +
    "Velocidad: 300 <br>\n" +
    "Bonus: Aumenta un 10% de ataque, 10% defensa y 5% de velocidad\n" +
    "de los luchadores (25% adicional de velocidad si es tipo tierra)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "X  &ensp;     X   &ensp;    X <br>\n" +
    "X  &ensp;     O   &ensp;    O  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal8 () {
    var pic = "./Img/L8.jpg";
    document.getElementById('cambiar').src = pic.replace();
    
    var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(153,102,0); color:white'>\n" +
    "Tortuga gigante que carga una montaña en sus hombros, gran poder defensivo <br>\n" +
    "Ataque:    300 <br>\n" +
    "Defensa:   60% <br>\n" +
    "Velocidad: 100 <br>\n" +
    "Bonus: Aumenta un 20% de defensa y 5% de ataque\n" +
    "de los luchadores (25% adicional de ataque si es tipo tierra)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "X  &ensp;     X   &ensp;    O <br>\n" +
    "O  &ensp;     X   &ensp;    X  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal9 () {
    var pic = "./Img/L9.png";
    document.getElementById('cambiar').src = pic.replace();
    
       var t = ("<html>\n" +
        "<body>\n" +
        "<div style='background:rgb(255,255,204)'>\n" +
        "Caballero protector de una diosa, gran agilidad y ataque <br>\n" +
        "Ataque:    400 <br>\n" +
        "Defensa:   20% <br>\n" +
        "Velocidad: 400 <br>\n" +
        "Bonus: Aumenta un 15% de ataque y 10% de velocidad\n" +
        "de los luchadores (10% de ataque y 15% de velocidad adicional si es tipo luz)  <br>\n" +
        "Posiciones preferidas: <br>\n" +
        "X  &ensp;     O   &ensp;    O <br>\n" +
        "X  &ensp;     X   &ensp;    X  \n" +
        "</div>\n" +
        "</html>");

    document.getElementById("textoG").innerHTML = t;
}

function op_gal10 () {
    var pic = "./Img/L10.jpg";
    document.getElementById('cambiar').src = pic.replace();
    
     var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(255,255,102)'>\n" +
    "Dragón que protege con su luz de todo mal, poder equilibrado <br>\n" +
    "Ataque:    350 <br>\n" +
    "Defensa:   35% <br>\n" +
    "Velocidad: 300 <br>\n" +
    "Bonus: Aumenta un 15% de defensa y 10% de ataque\n" +
    "de los luchadores (25% adicional de defensa si es tipo luz)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "X  &ensp;     O   &ensp;    X <br>\n" +
    "X  &ensp;     O   &ensp;    X  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal11 () {
    var pic = "./Img/L11.jpg";
    document.getElementById('cambiar').src = pic.replace();
    
    var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(153,153,153)'>\n" +
    "Demonio encerrado durante milenios bajo una pirámide, gran poder defensivo <br>\n" +
    "Ataque:    300 <br>\n" +
    "Defensa:   50% <br>\n" +
    "Velocidad: 200 <br>\n" +
    "Bonus: Aumenta un 20% de ataque y 5% de velocidad\n" +
    "de los luchadores (25% adicional de defensa si es tipo oscuridad)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "X  &ensp;     X   &ensp;    O <br>\n" +
    "X  &ensp;     O   &ensp;    X  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}

function op_gal12 () {
    var pic = "./Img/L12.png";
    document.getElementById('cambiar').src = pic.replace();
    
    var t = ("<html>\n" +
    "<body>\n" +
    "<div style='background:rgb(0,0,0); color:white'>\n" +
    "Dios que teme ser olvidado, gran poder ofensivo <br>\n" +
    "Ataque:    700 <br>\n" +
    "Defensa:   10% <br>\n" +
    "Velocidad: 200 <br>\n" +
    "Bonus: Aumenta un 20% de ataque y 5% de defensa\n" +
    "de los luchadores (25% adicional de ataque si es tipo oscuridad)  <br>\n" +
    "Posiciones preferidas: <br>\n" +
    "O  &ensp;     X   &ensp;    X <br>\n" +
    "X  &ensp;     O   &ensp;    X  \n" +
    "</div>\n" +
    "</html>");
    
    document.getElementById("textoG").innerHTML = t;
}




