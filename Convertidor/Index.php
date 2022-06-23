<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Última práctica</title>
    </head>
    <body>
        <?php
        
        // Si recibe algo del post, es que se ha intentado hacer algo
        if ($_SERVER["REQUEST_METHOD"] == "POST") {

            // Si recibe una opción a la que traducir
            if (isset($_POST['tipo'])) {
                
                // Si recibe un fichero
                if (isset($_FILES['archivo'])) {
                    
                    // Se prepara el checkeo del archivo
                    $valido = true;
                    
                    // se recoge el final del fichero para saber la extensión
                    $archivo = $_FILES['archivo'];
                    $spl = substr(strrchr($archivo["name"], "."), 1);
                    // si es uno de los 3 admitidos, no modifica nada
                    // si recibe un fichero que no tiene que ver, para aquí
                    switch ($spl) {
                        case "csv":
                            break;

                        case "sql":
                            break;

                        case "json":
                            break;

                        default :
                            $valido = false;
                            break;
                    }
                    if ($valido == true) {
                        
                        // Recoge el tipo de fichero a convertir 

                        $t = $_POST['tipo'];
                        
                        // Si la extensión del fichero y la elección no es la misma
                        if ($spl != $t) {
                            
                            // Dependiendo de las elecciones, llamará a distintas funciones
                            // que devuelven una cadena de texto que se guarda en un fichero
                            // Se genera también un enlace de descarga

                            if ($spl == "csv" && $t == "json") {
                                $json = csvToJson($archivo["tmp_name"]);
                                file_put_contents('subidas/myfile.json', $json);
                                echo '<a href="subidas/myfile.json" download="myfile.json">Descargar JSON</a><br>';
                            }

                            if ($spl == "csv" && $t == "sql") {
                                $sql = csvToSql($archivo["tmp_name"]);
                                file_put_contents('subidas/myfile.sql', $sql);
                                echo '<a href="subidas/myfile.sql" download="myfile.sql">Descargar SQL</a><br>';
                            }

                            if ($spl == "sql" && $t == "csv") {
                                $sql = sqlToCsv($archivo["tmp_name"]);
                                file_put_contents('subidas/myfile.csv', $sql);
                                echo '<a href="subidas/myfile.csv" download="myfile.csv">Descargar CSV</a><br>';
                            }

                            if ($spl == "sql" && $t == "json") {
                                $json = sqlToJson($archivo["tmp_name"]);
                                file_put_contents('subidas/myfile.json', $json);
                                echo '<a href="subidas/myfile.json" download="myfile.json">Descargar JSON</a><br>';
                            }

                            if ($spl == "json" && $t == "csv") {
                                $csv = jsonToCsv($archivo["tmp_name"]);
                                file_put_contents('subidas/myfile.csv', $csv);
                                echo '<a href="subidas/myfile.csv" download="myfile.csv">Descargar CSV</a><br>';
                            }

                            if ($spl == "json" && $t == "sql") {
                                $sql = jsonToSql($archivo["tmp_name"]);
                                file_put_contents('subidas/myfile.sql', $sql);
                                echo '<a href="subidas/myfile.sql" download="myfile.sql">Descargar SQL</a><br>';
                            }
                        } else {
                            echo "Opción igual a la del fichero, elija una diferente";
                        }
                    } else {
                        echo "Debe subir un fichero de tipo CSV, SQL o JSON";
                    }
                } else {
                    echo "Debe subir un fichero";
                }
            } else {
                echo "Debe seleccionar una opción. ";
            }
        }
        
        // El metodo seguido en todos es partir el string recibido en lineas de datos, limpia todos
        // los caracteres que no nos interesan y empezar a formar la estructura seguida
        // de la elección.

        function sqlToCsv($fname) {

            $sql = file_get_contents($fname);

            $result = explode('INSERT INTO', $sql);
            $a = 0;
            $tablas = substr_count($sql, "INSERT INTO");
            for ($i = 1; $i <= $tablas; $i++) {

                $arrayT = explode('VALUES', $result[$i]);
                $arrayT[0] = str_replace("` (`", ",", $arrayT[0]);
                $arrayT[0] = str_replace("`)", "", $arrayT[0]);
                $arrayT[0] = str_replace("`, `", ",", $arrayT[0]);
                $arrayT[0] = str_replace("`", "", $arrayT[0]);
                $arrayT[0] = str_replace(" ", "", $arrayT[0]);
                $arrayT[0] = preg_replace('/\s+/', '', $arrayT[0]);
                $estructura = explode(',', $arrayT[0]);
                array_shift($estructura);
                $create = "";

                for ($e = 0; $e < count($estructura); $e++) {


                    $create .= "\"$estructura[$e]\"";

                    if ($e == count($estructura) - 1) {
                        $create .= "\r";
                    } else {
                        $create .= ",";
                    }
                }

                $pos1 = strpos($arrayT[1], ";");

                $arrayT[1] = substr($arrayT[1], 0, $pos1);

                $arrayT[1] = str_replace(", ", ",", $arrayT[1]);
                $arrayT[1] = str_replace("'", "", $arrayT[1]);
                $arrayT[1] = str_replace("(", "", $arrayT[1]);
                $arrayT[1] = str_replace(")", "", $arrayT[1]);
                $arrayT[1] = str_replace(" )", "", $arrayT[1]);
                $arrayT[1] = preg_replace('/\s+/', '', $arrayT[1]);
                $datos = explode(',', $arrayT[1]);

                $c = 1;
                $b = 0;
                $t = "";
                do {
                    if ($b != count($datos)) {

                        $t .= "\"$datos[$b]\"";

                        if ($b == count($estructura) * $c - 1) {
                            if (count($datos) != $b) {
                                $t .= "\r";
                            }
                            $c++;
                        } else {
                            $t .= ",";
                        }

                        $b++;
                    } else {
                        break;
                    }
                } while (true);

                $create .= $t;

                return $create;
            }
        }

        function csvToSql($fname) {
            $csv = file_get_contents($fname);

            $output = preg_replace('/\s+/', ' ', $csv);
            $datos = explode(" ", $output);
            array_pop($datos);
            $insert = [];
            $create = "create table pruebas_1 (";
            for ($i = 0; $i < count($datos); $i++) {

                if ($i == 0) {
                    $datos[$i] = str_replace("\"", "", $datos[$i]);
                    $data = explode(',', $datos[$i]);
                    for ($e = 0; $e < count($data); $e++) {
                        $create .= "$data[$e] varchar(255)";
                        if (count($data) - 1 == $e) {
                            $create .= ");\r";
                        } else {
                            $create .= ", ";
                        }
                    }
                } else {
                    $data = explode(',', $datos[$i]);
                    $ins = "insert into pruebas_1 values (";
                    for ($e = 0; $e < count($data); $e++) {
                        $ins .= $data[$e];

                        if (count($data) - 1 == $e) {
                            $ins .= ");\r";
                        } else {
                            $ins .= ", ";
                        }
                    }
                    array_push($insert, $ins);
                }
            }

            $textoF = "";
            $textoF .= $create;

            for ($e = 0; $e < count($insert); $e++) {
                $textoF .= $insert[$e];
            }

            return $textoF;
        }

        function jsonToSql($fname) {

            $json = file_get_contents($fname);

            $arrayT = explode('{', $json);

            $tabla = "";
            $columnas = [];
            $insert = [];

            for ($i = 1; $i < count($arrayT); $i++) {

                $a = $arrayT[$i];
                $a = str_replace(" ", "", $a);
                $a = str_replace("[", "", $a);
                $a = str_replace("]", "", $a);
                $a = str_replace("{", "", $a);
                $a = str_replace("},", "", $a);
                $a = str_replace("}", "", $a);
                $a = str_replace("\"", "", $a);
                $a = str_replace("]", "", $a);
                $a = str_replace("\r\n", "", $a);

                if ($i == 1) {
                    $a = str_replace(":", "", $a);
                    $tabla = $a;
                } else {
                    $estructura = explode(',', $a);
                    $sen = "insert into $tabla values (";
                    for ($e = 0; $e < count($estructura); $e++) {
                        $p = explode(':', $estructura[$e]);

                        if ($i == 2) {
                            array_push($columnas, $p[0]);
                        }
                        $sen .= "\"$p[1]\"";

                        if (count($estructura) - 1 == $e) {
                            $sen .= ");\r";
                        } else {
                            $sen .= ", ";
                        }
                    }
                    array_push($insert, $sen);
                }
            }

            $create = "create table $tabla (";
            for ($e = 0; $e < count($columnas); $e++) {

                $create .= "$columnas[$e] varchar(255)";

                if (count($columnas) - 1 == $e) {
                    $create .= ");\r";
                } else {
                    $create .= ", ";
                }
            }

            $textoF = "";
            $textoF .= $create;

            for ($e = 0; $e < count($insert); $e++) {
                $textoF .= $insert[$e];
            }

            return $textoF;
        }

        function jsonToCsv($fname) {

            $json = file_get_contents($fname);

            $arrayT = explode('},', $json);
            $arrayF = "";

            for ($i = 0; $i < count($arrayT); $i++) {

                $a = $arrayT[$i];
                $a = str_replace(" ", "", $a);
                $a = str_replace("[", "", $a);
                $a = str_replace("]", "", $a);
                $a = str_replace("{", "", $a);
                $a = str_replace("}", "", $a);
                $a = str_replace("\"", "", $a);
                $a = str_replace("]", "", $a);
                $a = str_replace("\r\n", "", $a);

                $estructura = explode(',', $a);

                if ($i == 0) {
                    for ($e = 0; $e < count($estructura); $e++) {
                        if ($e == 0) {
                            $p = explode(':', $estructura[0]);
                            $arrayF .= "\"$p[1]\",";
                        } else {
                            $p = explode(':', $estructura[$e]);
                            if ($e != count($estructura) - 1) {
                                $arrayF .= "\"$p[$i]\",";
                            } else {
                                $arrayF .= "\"$p[$i]\"\r";
                            }
                        }
                    }
                }

                for ($e = 0; $e < count($estructura); $e++) {
                    $p = explode(':', $estructura[$e]);
                    if ($e != count($estructura) - 1) {
                        if ($i == 0 && $e == 0) {
                            $arrayF .= "\"$p[2]\",";
                        } else {
                            $arrayF .= "\"$p[1]\",";
                        }
                    } else {
                        $arrayF .= "\"$p[1]\"\r";
                    }
                }
            }

            return $arrayF;
        }

        function sqlToJson($fname) {

            $sql = file_get_contents($fname);
            $json = "{";

            $result = explode('INSERT INTO', $sql);
            $a = 0;
            $tablas = substr_count($sql, "INSERT INTO");

            for ($i = 1; $i <= $tablas; $i++) {

                $arrayT = explode('VALUES', $result[$i]);
                $arrayT[0] = str_replace("` (`", ",", $arrayT[0]);
                $arrayT[0] = str_replace("`)", "", $arrayT[0]);
                $arrayT[0] = str_replace("`, `", ",", $arrayT[0]);
                $arrayT[0] = str_replace("`", "", $arrayT[0]);
                $arrayT[0] = str_replace(" ", "", $arrayT[0]);
                $estructura = explode(',', $arrayT[0]);

                $pos1 = strpos($arrayT[1], ";");

                $arrayT[1] = substr($arrayT[1], 0, $pos1);

                $arrayT[1] = str_replace(", ", ",", $arrayT[1]);
                $arrayT[1] = str_replace("'", "", $arrayT[1]);
                $arrayT[1] = str_replace("(", "", $arrayT[1]);
                $arrayT[1] = str_replace(")", "", $arrayT[1]);

                $datos = explode(',', $arrayT[1]);

                $t = "\"$estructura[0]\":[";

                $c = 1;
                $b = 0;

                do {

                    if ($b != count($datos)) {

                        if ($c == count($estructura)) {
                            $t .= "},";
                            $c = 1;
                            $t .= "{";
                        } else {
                            if ($b != 0) {
                                $t .= ",";
                            }
                        }

                        if ($b == 0) {
                            $t .= "{";
                        }

                        $t .= "\"$estructura[$c]\":";
                        $t .= "\"$datos[$b]\"";

                        $c++;
                        $b++;
                    } else {
                        $a++;

                        if ($a < $tablas) {
                            $t .= "}],";
                        } else {

                            $t .= "}]";
                        }
                        break;
                    }
                } while (true);

                $json .= $t;
            }
            $json .= "}";
            $json = preg_replace("/\r|\n/", "", $json);
            return $json;
        }

        // Esta última función difiere de las anteriores porque era la más sencilla y se puede
        // usar una función más sencilla
        
        function csvToJson($fname) {

            if (!($fp = fopen($fname, 'r'))) {
                die("No se pudo abrir el fichero.<br>");
            }

            $key = fgetcsv($fp, "1024", ",");

            $json = array();
            while ($row = fgetcsv($fp, "1024", ",")) {
                $json[] = array_combine($key, $row);
            }

            fclose($fp);

            return json_encode($json);
        }
        ?>

        <h3>Subir un archivo:</h3><br>
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="archivo"><br><br>
            <h3>CSV<input type="radio" name="tipo" value="csv"></h3>
            <h3>SQL<input type="radio" name="tipo" value="sql"></h3>
            <h3>JSON<input type="radio" name="tipo" value="json"></h3>
            <input type="submit" value="Subir" name="subir">
        </form>
        
        <p>
            Este conversor funciona con ficheros que contengan una tabla y los datos. <br>
            Los ficheros de ejemplo para traducir están en la carpeta "archivos". <br>
            En la carpeta "subidas" están los archivos generados. De ahí se cogen los ficheros
            para descargar luego. <br>
            Debido a las múltiples opciones que hay, opciones de csv a sql genera una tabla
            con nombre genérico "prueba_1". <br>
            Los csv generados desde sql y json, al no poder contener datos de la tabla, solo guardan la cabecera
            y los datos.
        </p>
    </body>
</html>
