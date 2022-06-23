<?php

$u = $_REQUEST["u"];

$conexion = new mysqli("localhost", "root", "", "exf");
if ($conexion->connect_errno) {
    echo "Falló la conexión" . $conexion->connect_errno;
} else {


    $sql = "select * from $u";

    $resultado = $conexion->query($sql);
    $array = [];
    
    while ($fila = $resultado->fetch_assoc()) {
        array_push($array, $fila);
    }

    $json = json_encode($array, JSON_UNESCAPED_UNICODE);

    echo $json;
}