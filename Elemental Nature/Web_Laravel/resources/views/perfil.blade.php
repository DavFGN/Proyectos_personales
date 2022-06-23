@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h3>Editar perfil</h3>
                    </div>
                    <div class="card-body">

                        <div class="foto" id="partidas" style="float: left; min-width: 70%;">
                            <table class="tablaP">
                                <tr style="border: 2px solid blue">
                                    <td class="tdU">
                                        Codigo Usuario:
                                    </td>
                                    <td class="tdU">
                                        <span id="cod"> {{ session('codigo') }}</span>
                                    </td>
                                </tr>
                                <tr style="border: 2px solid blue">
                                    <td class="tdU">
                                        Usuario:
                                    </td>
                                    <td class="tdU">
                                        <input id="usuario" class="input1" type="text" onkeyup="comprobar()"
                                            value="{{ session('usuario') }}">
                                    </td>
                                </tr>
                                <tr style="border: 2px solid blue">
                                    <td class="tdU">
                                        Fecha de registro:
                                    </td>
                                    <td class="tdU">
                                        {{ session('fecha') }}
                                    </td>
                                </tr>
                                <tr style="border: 2px solid blue">
                                    <td class="tdU">
                                        Administrador:
                                    </td>
                                    <td class="tdU">
                                        @if (session('admin'))
                                            Sí
                                        @else
                                            No
                                        @endif
                                    </td>
                                </tr>
                                <tr style="border: 2px solid blue">
                                    <td class="tdU">
                                        Baneado:
                                    </td>
                                    <td class="tdU">
                                        @if (session('ban'))
                                            Sí
                                        @else
                                            No
                                        @endif
                                    </td>
                                </tr>
                                <tr style="border: 2px solid blue">
                                    <td class="tdU">
                                        Cambiar contraseña:
                                    </td>
                                    <td class="tdU">
                                        <a href="{{ url('/perfil/actualizar/password') }}" class="btn btn-dark">Ir</a>
                                    </td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    @endsection

    <script src="{{ asset('js/nombre.js') }}" defer></script>
