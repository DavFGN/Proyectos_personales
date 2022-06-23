<?php

$u = $_REQUEST["u"];
$a = $_REQUEST["a"];

$conexion = new mysqli("localhost", "root", "", "exf");
if ($conexion->connect_errno) {
    echo "Falló la conexión" . $conexion->connect_errno;
} else {

    $sql = "update $u set ocupado = 1 where asiento = $a";

    $conexion->query($sql);

    echo "Asiento reservado";
}


