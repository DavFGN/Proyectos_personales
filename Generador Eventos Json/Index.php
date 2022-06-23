<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Examen 4 (ejercicio 2)</title>
    </head>
    <body>

        <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            
            // Recogemos datos de formularios

            $nombre = $_POST['nombre'];
            $horario = $_POST['horario'];
            $participante = $_POST['participante'];
            $organizadores = $_POST['org'];
            
            // Si el horario no es válido, no continua 

            if ($horario == "mañana" || $horario == "tarde") {
                
                // Si mete 0 o menos participantes, no continua
                
                if (!$participante < 1) {

                    // prepara el array principal que se convertirá en el json
                    
                    $array = [];
                    
                    // guardamos datos simples en clave valor
                    
                    $array["nombre"] = $nombre;
                    $array["horario"] = $horario;
                    $array["participante"] = $participante;
                    
                    // los organizadores pueden ser uno o varios, por lo que hay
                    // que partir la cadena recibida en los nombres
                    // Si hay uno solo, arrayt es considerado un array con 1 elemento
                    
                    $arrayT = explode(', ', $organizadores);

                    // recorremos el arrayT buscando si existe el fichero json de dicho
                    // organizador. Si no existe, no introduce en el array principal
                    // al organizador. 
                    
                    $hayOrganizador = false;
                    for ($i = 0; $i < count($arrayT); $i++) {
                        if (($o = @file_get_contents("$arrayT[$i].json")) === false) {
                            echo "Fallo introduciendo al organizador $arrayT[$i], no existe. <br>";
                        } else {
                            echo "Introducido el organizador $arrayT[$i]. <br>";
                            $o = file_get_contents("$arrayT[$i].json");
                            $array["organizadores"][$i] = $o;
                            $hayOrganizador = true;
                        }
                    }
                    
                    // En caso de que no hubiera ningún organizador, no genera ningún
                    // documento
                    
                    if ($hayOrganizador == true) {

                    // Hacemos encode al array para convertirlo en json con formateo
                    // de caracteres y forzando que sea un objeto
                        
                    $a = json_encode($array, JSON_UNESCAPED_UNICODE, JSON_FORCE_OBJECT);

                    // Un do while para comprobar si existe un fichero con ese nombre
                    // generar otro para no sobreescribirlo
                    
                    $existe = false;
                    $contador = 0;
                    do {
                        $nombre_fichero = "archivo$contador.json";
                        if (file_exists($nombre_fichero)) {
                            $contador++;
                        } else {
                            file_put_contents("$nombre_fichero", $a);
                            $existe = true;
                        }
                    } while ($existe == false);
                    
                    // Finalmente, un echo indicando el nombre del fichero recién generado
                    
                    echo "<br>JSON generado con nombre $nombre_fichero. <br>";
                    
                    } else {
                        echo "<br>No había ningún organizador de los que ha introducido,"
                        . " no se ha generado el documento. <br>";
                    }
                } else {
                    echo "Debe poner un número de participantes superior a 0.";
                }
            } else {
                echo "Debe poner un horario válido.";
            }
            
            echo "<hr>";
        }
        ?>

        <form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
            <h3>Nombre de la actividad: <input type="text" name="nombre" value="prueba"></h3>
            <h3>Horario, solo puede valer "mañana" o "tarde": <input type="text" name="horario" value="tarde"></h3>
            <h3>Número de participantes (mayor que 0): <input type="number" name="participante" value="1"></h3>
            <h3>Organizadores (escribir nombres separados por comas y espacio, con mayúscula al principio):
                <input type="text" name="org" value="Domingo, Ana"></h3>
            <input type="submit"> <br>
        </form>
    </body>
</html>
