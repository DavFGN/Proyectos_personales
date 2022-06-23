<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use Session;

class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    protected function showLogin(Request $request)
    {
        if ($request->session()->has('usuario')) {
            return redirect("/menu");
        } else {
            return view('login');
        }
    }

    protected function showMenu(Request $request)
    {
        if ($request->session()->has('usuario')) {
            return view('home');
        } else {
            return redirect("/login");
        }
    }

    protected function login(Request $request)
    {
        $usuario = $request->post('usuario');
        $pass = $request->post('pass');

        $query = "select * from usuario where nombre = '$usuario' and pass = '$pass'";

        $resultado = DB::select($query);

        if (count($resultado)) {

            foreach ($resultado as $key => $item) {
                if ($item->administrador == true) {
                    $request->session()->put('admin', "true");
                }
                if ($item->ban == true) {
                    $request->session()->put('ban', "true");
                }
                $request->session()->put('codigo', "$item->codigo");
                $request->session()->put('fecha', "$item->fecha");
            }

            $request->session()->put('usuario', "$usuario");
            return redirect("/menu");
        } else {
            return view('login')->with('error', 'error');
        }
    }

    protected function cerrarSesion(Request $request)
    {
        $request->session()->flush();
        return redirect("/login");
    }

    protected function perfil(Request $request)
    {

        if ($request->session()->has('usuario')) {
            return view('perfil');
        } else {
            return redirect("/login");
        }
    }

    protected function disponibilidadNombre($usuario)
    {

        $query = "select nombre from usuario where nombre = '$usuario'";

        $resultado = DB::select($query);

        if (count($resultado)) {
            echo "No";
        } else {
            echo "Ok";
        }
    }

    protected function actualizarNombre($codigo, $usuario, Request $request)
    {

        $cod = $request->session()->get('codigo');
        if ($codigo == $cod) {
            DB::update("update usuario set nombre = '$usuario' where codigo = $codigo");
            $request->session()->flush();
        } else {
            echo "No tienes permisos para realizar esta acción";
        }
    }

    protected function cambiarPassword(Request $request)
    {

        if ($request->session()->has('usuario')) {
            return view('cambiarPass');
        } else {
            return redirect("/login");
        }
    }

    protected function actualizarPassword(Request $request)
    {

        $usuario = $request->session()->get('usuario');
        $passA = $request->post('passA');
        $passN = $request->post('passN');

        $query = "select codigo from usuario where nombre = '$usuario' and pass = '$passA'";

        $resultado = DB::select($query);

        if (count($resultado)) {

            DB::update("update usuario set pass = '$passN' where nombre = '$usuario'");
            $request->session()->flush();
            return redirect("/login");
        } else {
            return view('cambiarPass')->with('error', 'error');
        }
    }

    protected function partidas(Request $request)
    {

        if ($request->session()->has('usuario')) {

            $cod = $request->session()->get('codigo');

            $query = "select * from Partida where ganador = $cod or perdedor = $cod order by fecha desc limit 16";
            $query2 = "select luchador, count(luchador) as usado from equipo where jugador
            = $cod group by luchador order by usado desc limit 3";

            $resultado = DB::select($query);
            $resultado2 = DB::select($query2);

            $data = array(
                'partidas' => $resultado,
                'luchadores' => $resultado2
            );

            return view('partidas')->with('partidas', $resultado)->with('luchadores', $resultado2);
        } else {
            return redirect("/login");
        }
    }

    protected function mail(Request $request)
    {
        $usuario = $request->session()->get('usuario');
        $query = "select correo.codigo as cod, asunto, correo.fecha as fecha from correo, usuario
        where usuario.nombre = '$usuario' and correo.usu_r = usuario.codigo limit 10";

        $resultado = DB::select($query);


        return view('mail')->with('resultado', $resultado);
    }

    protected function envios(Request $request)
    {
        $usuario = $request->session()->get('usuario');
        $query = "select correo.codigo as cod, correo.fecha as fecha, asunto from correo, usuario where usuario.nombre =
        '$usuario' and correo.usu_e = usuario.codigo limit 10";

        $resultado = DB::select($query);


        return view('mail')->with('resultado', $resultado);
    }

    protected function enviarMensaje(Request $request)
    {

        if ($request->session()->has('usuario')) {
            return view('enviarMensaje');
        } else {
            return redirect("/login");
        }
    }

    protected function buscarUsuario($usuario)
    {

        $query = "select codigo, nombre, fecha, administrador, ban from usuario where nombre like '$usuario%'";

        $resultado = DB::select($query);

        if (count($resultado)) {
            echo json_encode($resultado);
        } else {
            echo "No hay coincidencias";
        }
    }

    protected function enviarM(Request $request)
    {

        $asunto = $request->post('asunto');
        $nombreE = $request->post('nombreE');
        $caja = $request->post('caja');

        $comp = "select codigo from Usuario where nombre like '$nombreE'";

        $resultado = DB::select($comp);

        if (count($resultado)) {

            foreach ($resultado as $key => $item) {
                $cod = $item->codigo;
            }
        } else {

            $cod = 0;
        }

        $usuario = $request->session()->get('codigo');

        if ($cod == 0) {
            $admin = 'true';
        } else {
            $admin = 'false';
        }

        $sql = "insert into Correo
        (usu_e, usu_r, asunto, contenido, fecha, administrador)
        values ($usuario, $cod, '$asunto', '$caja', now(), $admin)";

        DB::insert($sql);

        return redirect("/envios");
    }

    protected function verCorreo(Request $request)
    {

        $codigo = $request->post('codigo');

        $query = "select contenido, asunto, nombre from correo, usuario
        where correo.codigo = $codigo and usuario.codigo = correo.usu_e";

        $resultado = DB::select($query);

        // foreach ($resultado as $key => $item) {
        //     $win = $item->nombre;
        // }

        return view('mensaje')->with('resultado', $resultado);
    }

    protected function admin(Request $request)
    {

        if ($request->session()->has('admin')) {
            return view('admin');
        } else {
            return redirect("/menu");
        }
    }

    protected function administrarUsuarios(Request $request)
    {

        if ($request->session()->has('admin')) {

            return view('adminUsuarios');
        } else {
            return redirect("/menu");
        }
    }

    protected function adminPerfil($codigo, Request $request)
    {

        if ($request->session()->has('admin')) {

            $query = "select codigo, nombre, fecha, administrador, ban from usuario where codigo = $codigo ";

            $resultado = DB::select($query);
            if (count($resultado)) {

                return view('adminPerfil')->with('resultado', $resultado);
            } else {
                return redirect("/admin/usuarios");
            }
        } else {
            return redirect("/menu");
        }
    }

    protected function adminPerfilDatos($codigo, Request $request)
    {

        if ($request->session()->has('admin')) {

            $admin = $request->post('admin');
            $ban = $request->post('ban');

            $query = "update usuario set administrador = $admin, ban = $ban where codigo = $codigo ";

            DB::update($query);
        } else {
            return redirect("/menu");
        }
    }

    protected function adminRegistros(Request $request)
    {
        if ($request->session()->has('admin')) {

            return view('registros');
        } else {
            return redirect("/menu");
        }
    }

    protected function adminRegistrosBusqueda($nombre, $fecha, Request $request)
    {

        if ($request->session()->has('admin')) {

            $nom = "";
            $fec = "";

            if ($nombre != "no") {
                $nom = " and
                nombre like '%$nombre' ";
            }

            if ($fecha != "no") {
                $fec = " and fecha_ult::date = date '$fecha' ";
            }

            $query = "select registro.codigo as reg_cod, cod_usuario as cod_usu,
                usuario.nombre as nombre, fecha_ult as fecha, ip
                from Registro, usuario
                where usuario.codigo = cod_usuario" .
                $nom . $fec .
                "order by fecha_ult desc limit 10";

            $resultado = DB::select($query);
            if (count($resultado)) {
                echo json_encode($resultado);
            } else {
                echo "No hay coincidencias";
            }
        }
    }

    protected function adminMail(Request $request)
    {

        if ($request->session()->has('admin')) {

            $query = "select correo.codigo as cod, asunto, correo.fecha as fecha from correo, usuario
        where usuario.codigo = 0 and correo.usu_r = usuario.codigo limit 10";

            $resultado = DB::select($query);

            return view('adminMail')->with('resultado', $resultado);
        } else {
            return redirect("/menu");
        }
    }

    protected function adminEnvios(Request $request)
    {

        if ($request->session()->has('admin')) {
            $query = "select correo.codigo as cod, correo.fecha as fecha, asunto
         from correo, usuario
         where usuario.codigo = 0 and correo.usu_e = usuario.codigo limit 10";

            $resultado = DB::select($query);


            return view('adminMail')->with('resultado', $resultado);
        } else {
            return redirect("/menu");
        }
    }

    protected function adminEnviarMensaje(Request $request)
    {

        if ($request->session()->has('admin')) {
            return view('adminEnviarMensaje');
        } else {
            return redirect("/menu");
        }
    }

    protected function adminEnviarM(Request $request)
    {

        $asunto = $request->post('asunto');
        $nombreE = $request->post('nombreE');
        $caja = $request->post('caja');

        $comp = "select codigo from Usuario where nombre like '$nombreE'";

        $resultado = DB::select($comp);

        if (count($resultado)) {

            foreach ($resultado as $key => $item) {
                $cod = $item->codigo;
            }
        } else {

            $cod = 0;
        }

        if ($cod == 0) {
            $admin = 'true';
        } else {
            $admin = 'false';
        }

        $sql = "insert into Correo
        (usu_e, usu_r, asunto, contenido, fecha, administrador)
        values (0, $cod, '$asunto', '$caja', now(), $admin)";

        DB::insert($sql);

        return redirect("/admin/envios");
    }

    protected function adminVerCorreo(Request $request)
    {

        $codigo = $request->post('codigo');

        $query = "select contenido, asunto, nombre from correo, usuario
        where correo.codigo = $codigo and usuario.codigo = correo.usu_e";

        $resultado = DB::select($query);

        // foreach ($resultado as $key => $item) {
        //     $win = $item->nombre;
        // }

        return view('adminMensaje')->with('resultado', $resultado);
    }


    protected function visualizar($opcion)
    {
        switch ($opcion) {
            case '1':
                $t = $this->l1;
                $img = ("img/L1.jpg");
                break;
            case '2':
                $t = $this->l2;
                $img = "img/L2.png";
                break;
            case '3':
                $t = $this->l3;
                $img = "img/L3.jpg";
                break;
            case '4':
                $t = $this->l4;
                $img = "img/L4.png";
                break;
            case '5':
                $t = $this->l5;
                $img = "img/L5.jpg";
                break;
            case '6':
                $t = $this->l6;
                $img = "img/L6.png";
                break;
            case '7':
                $t = $this->l7;
                $img = "img/L7.jpg";
                break;
            case '8':
                $t = $this->l8;
                $img = "img/L8.jpg";
                break;
            case '9':
                $t = $this->l9;
                $img = "img/L9.png";
                break;
            case '10':
                $t = $this->l10;
                $img = "img/L10.jpg";
                break;
            case '11':
                $t = $this->l11;
                $img = "img/L11.jpg";
                break;
            case '12':
                $t = $this->l12;
                $img = "img/L12.png";
                break;
            default:
                return redirect("/galeria");
                break;
        }

        $data = array(
            'luchador' => $t,
            'img' => $img
        );

        return view('luchador')->with($data);
    }

    private $l1 =  "<div class=\"textoI\" style='background:rgb(153,255,153); color: #000;'>\n" .
        "        Espíritu del viento con gran agilidad <br>\n" .
        "        Ataque:    200 <br>\n" .
        "        Defensa:   20% <br>\n" .
        "        Velocidad: 600 <br>\n" .
        "        Bonus: Aumenta un 25% la velocidad de los luchadores (si es tipo viento "
        . "añade un 25% también de ataque)<br>\n" .
        "        Posiciones preferidas: <br>\n" .
        "        O   &ensp;   X     &ensp;   X <br>\n" .
        "        X   &ensp;   X     &ensp;   O   \n";

    private $l2 = ("<div class=\"textoI\" style='background:rgb(51,102,0); color:white'>"
        . "Reina de las valquirias, gran poder ofensivo y agilidad  <br>"
        . "Ataque:    400 <br>"
        . "Defensa:   10% <br>"
        . "Velocidad: 500 <br>"
        . "Bonus: Aumenta un 10% la velocidad y 15% de ataque de los"
        . "luchadores (15% y 10% adicional si es tipo viento)  <br>"
        . "Posiciones preferidas: <br>"
        . "X  &ensp;     O   &ensp;    X <br>"
        . "O  &ensp;     X   &ensp;    X  </div>");

    private $l3 = ("<div class=\"textoI\" style='background:rgb(255,102,102)'>\n" .
        "Considerado el principe demonio, poder equilibrado  <br>\n" .
        "Ataque:    350 <br>\n" .
        "Defensa:   30% <br>\n" .
        "Velocidad: 350 <br>\n" .
        "Bonus: Aumenta un 10% la defensa y 15% de ataque de los \n" .
        "luchadores (10% de velocidad y 15% de ataque adicional si es tipo fuego)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "O  &ensp;     X   &ensp;    O <br>\n" .
        "X  &ensp;     X   &ensp;    X  \n");

    private $l4 = ("<div class=\"textoI\" style='background:rgb(153,0,0); color:white'>\n" .
        "Emperador antiguo dragón de fuego, gran poder ofensivo <br>\n" .
        "Ataque:    600 <br>\n" .
        "Defensa:   25% <br>\n" .
        "Velocidad: 150 <br>\n" .
        "Bonus: Aumenta un 25% el ataque de los \n" .
        "luchadores (25% adicional si es tipo <br> fuego)  <br>\n" .
        "Posiciones preferidas:  <br>\n" .
        "X  &ensp;     X   &ensp;    O <br>\n" .
        "X  &ensp;     O   &ensp;    X  \n" .
        "</div>\n");

    private $l5 = ("<div class=\"textoI\" style='background:rgb(153,255,255); color: #000;'>\n" .
        "Criatura del abismo oceánico, gran poder ofensivo y defensivo <br>\n" .
        "Ataque:    500 <br>\n" .
        "Defensa:   40% <br>\n" .
        "Velocidad: 100 <br>\n" .
        "Bonus: Aumenta un 15% la defensa y 10% el ataque de los \n" .
        "luchadores (10% y 15% respectivamente adicional si es tipo agua)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "O  &ensp;     X   &ensp;    X <br>\n" .
        "X  &ensp;     X   &ensp;    O  \n");

    private $l6 = ("<div class=\"textoI\" style='background:rgb(0,51,204); color:white'>\n" .
        "Deidad del mar al que controla a su antojo, poder equilibrado <br>\n" .
        "Ataque:    350 <br>\n" .
        "Defensa:   35% <br>\n" .
        "Velocidad: 300 <br>\n" .
        "Bonus: Aumenta un 8% de ataque, 9% defensa y 8% de velocidad\n" .
        "de los luchadores (25% adicional de velocidad si es tipo agua)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "O  &ensp;     X   &ensp;    X <br>\n" .
        "O  &ensp;     X   &ensp;    X  \n");

    private $l7 = ("<div class=\"textoI\" style='background:rgb(255,153,102); color: #000;'>\n" .
        "Titán de tierra, poder equilibrado <br>\n" .
        "Ataque:    300 <br>\n" .
        "Defensa:   40% <br>\n" .
        "Velocidad: 300 <br>\n" .
        "Bonus: Aumenta un 10% de ataque, 10% defensa y 5% de velocidad\n" .
        "de los luchadores (25% adicional de velocidad si es tipo tierra)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "X  &ensp;     X   &ensp;    X <br>\n" .
        "X  &ensp;     O   &ensp;    O  \n");

    private $l8 = ("<div class=\"textoI\" style='background:rgb(153,102,0); color:white'>\n" .
        "Tortuga gigante que carga una montaña en sus hombros, gran poder defensivo <br>\n" .
        "Ataque:    300 <br>\n" .
        "Defensa:   60% <br>\n" .
        "Velocidad: 100 <br>\n" .
        "Bonus: Aumenta un 20% de defensa y 5% de ataque\n" .
        "de los luchadores (25% adicional de ataque si es tipo tierra)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "X  &ensp;     X   &ensp;    O <br>\n" .
        "O  &ensp;     X   &ensp;    X  \n");

    private $l9 = ("<div class=\"textoI\" style='background:rgb(255,255,204); color: #000;'>\n" .
        "Caballero protector de una diosa, gran agilidad y ataque <br>\n" .
        "Ataque:    400 <br>\n" .
        "Defensa:   20% <br>\n" .
        "Velocidad: 400 <br>\n" .
        "Bonus: Aumenta un 15% de ataque y 10% de velocidad\n" .
        "de los luchadores (10% de ataque y 15% de velocidad adicional si es tipo luz)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "X  &ensp;     O   &ensp;    O <br>\n" .
        "X  &ensp;     X   &ensp;    X  \n" .
        "</div>\n");

    private $l10 = ("<div class=\"textoI\" style='background:rgb(255,255,102); color: #000;'>\n" .
        "Dragón que protege con su luz de todo mal, poder equilibrado <br>\n" .
        "Ataque:    350 <br>\n" .
        "Defensa:   35% <br>\n" .
        "Velocidad: 300 <br>\n" .
        "Bonus: Aumenta un 15% de defensa y 10% de ataque\n" .
        "de los luchadores (25% adicional de defensa si es tipo luz)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "X  &ensp;     O   &ensp;    X <br>\n" .
        "X  &ensp;     O   &ensp;    X  \n" .
        "</div>\n");

    private $l11 = ("<div class=\"textoI\" style='background:rgb(153,153,153); color: #000;'>\n" .
        "Demonio encerrado durante milenios bajo una pirámide, gran poder defensivo <br>\n" .
        "Ataque:    300 <br>\n" .
        "Defensa:   50% <br>\n" .
        "Velocidad: 200 <br>\n" .
        "Bonus: Aumenta un 20% de ataque y 5% de velocidad\n" .
        "de los luchadores (25% adicional de defensa si es tipo oscuridad)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "X  &ensp;     X   &ensp;    O <br>\n" .
        "X  &ensp;     O   &ensp;    X  \n");

    private $l12 = ("<div class=\"textoI\" style='background:rgb(0,0,0); color:white'>\n" .
        "Dios que teme ser olvidado, gran poder ofensivo <br>\n" .
        "Ataque:    700 <br>\n" .
        "Defensa:   10% <br>\n" .
        "Velocidad: 200 <br>\n" .
        "Bonus: Aumenta un 20% de ataque y 5% de defensa\n" .
        "de los luchadores (25% adicional de ataque si es tipo oscuridad)  <br>\n" .
        "Posiciones preferidas: <br>\n" .
        "O  &ensp;     X   &ensp;    X <br>\n" .
        "X  &ensp;     O   &ensp;    X  \n");
}
