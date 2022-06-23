@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="usuarioD">
            <table class="tablaP">
                    <tr>
                        <td class="tdU" colspan=2>
                            Por nombre:
                            <input type="text" id="nombre" name="nombre" max="30" onkeyup="comprobar()">
                        </td>
                        <td class="tdU" colspan=2>
                            Por fecha:
                            <input type="date" id="fecha" name="fecha" onchange="comprobar()">
                        </td>
                <tr>
                    <td class="tdU">
                        Codigo
                    </td>
                    <td class="tdU">
                        Nombre
                    </td>
                    <td class="tdU">
                        Fecha acceso
                    </td>
                    <td class="tdU">
                        IP
                    </td>
                </tr>
                <tbody id="resultados"></tbody>
            </table>

        </div>
        <script src="{{ asset('js/registros.js') }}" defer></script>
    </div>
@endsection
