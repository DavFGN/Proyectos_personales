<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>ej2</title>
</head>
<body>

<?php

if(isset($_REQUEST['generar'])){
	$orgs=explode(",",$_REQUEST['orgs']);
	unset($_REQUEST['orgs']);
	unset($_REQUEST['generar']);
	$claves=array_keys($_REQUEST);
	$valores=array_values($_REQUEST);
	$cad='[{';
	for($i=0;$i<count($claves);$i++){
		$cad.='"'.$claves[$i].'": "'.$valores[$i].'",';}
	$cad.='"Organizadores": [';
	for($i=0;$i<count($orgs);$i++){
		$org=$orgs[$i];
		$datos=file_get_contents("$org.json");
		$cad.=$datos;
		$cad.=',';}
	$cad.=']}]';
	$json=fopen("$_REQUEST[nombre].json","w");
	fputs($json,$cad);
	echo 'Documento generado en el servidor';
}
else{
	echo '<form method="post">
		Nombre de la actividad:<br>
		<input type="text" name="nombre" required><br>
		Horario:<br>
		<select name="horario">
		<option value="Mañana">Mañana</option>
		<option value="Tarde">Tarde</option>
		</select><br>
		Número de participantes:<br>
		<input type="number" name="número de participantes" required><br>
		Organizadores (separados por comas):<br>
		<textarea name="orgs" required></textarea><br>
		<input type="submit" name="generar" value="Generar"></form>';}

?>

</body>