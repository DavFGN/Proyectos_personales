@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="usuarioD">
            <table class="tablaP">
                    <tr>
                        <td class="tdU" colspan=5>
                            Nombre:
                            <input type="text" id="seaN" name="searchN" max="30" onkeyup="comprobar()">
                        </td>
                <tr>
                    <td class="tdU">
                        Codigo
                    </td>
                    <td class="tdU">
                        Nombre
                    </td>
                    <td class="tdU">
                        Fecha alta
                    </td>
                    <td class="tdU">
                        Administrador
                    </td>
                    <td class="tdU">
                        Baneado
                    </td>
                </tr>
                <tbody id="resultados"></tbody>
            </table>

        </div>
        <script src="{{ asset('js/listaUsuarios.js') }}" defer></script>
    </div>
@endsection
