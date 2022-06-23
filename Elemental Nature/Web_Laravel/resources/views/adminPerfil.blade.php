@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <div id="producto" class="oculto">
                            <div class="foto" style="float: left">
                                <h3>Editar perfil</h3>
                            </div>
                            <div class="foto" style="float: right">
                                <a href="{{ url('/admin/usuarios') }}" class="botonBandeja">Volver</a>
                            </div>
                        </div>
                        <div class="card-body">
                            <div id="producto" class="oculto">
                                <div class="foto" style="float: left">
                                    <ul class="listaG">
                                        <li class="listaLi">
                                            Código usuario:
                                        </li>
                                        <li class="listaLi">
                                            Usuario:
                                        </li>
                                        <li class="listaLi">
                                            Fecha de registro:
                                        </li>
                                        <li class="listaLi">
                                            Administrador:
                                        </li>
                                        <li class="listaLi">
                                            Baneado:
                                        </li>
                                    </ul>
                                </div>
                                @foreach ($resultado as $data)
                                    <div class="foto" style="float: right; text-align: left;">
                                        <ul class="listaG">
                                            <li class="listaLi">
                                                <span id="cod"> {{ $data->codigo }}</span>
                                            </li>
                                            <li class="listaLi">
                                                 {{ $data->nombre }}
                                            </li>
                                            <li class="listaLi"> {{ $data->fecha }}</li>

                                            <input type="hidden" id="_token" name="_token" value="{{ csrf_token() }}">
                                            <li class="listaLi">

                                                <input type="checkbox" id="admin" name="admin" onclick="datos()" {{ $data->administrador ? 'checked' : '' }}>

                                            </li>
                                            <li class="listaLi">
                                                <input type="checkbox" id="ban" name="ban" onclick="datos()" {{ $data->ban ? 'checked' : '' }}>
                                            </li>
                                        </ul>
                                    </div>
                                @endforeach

                                <script src="{{ asset('js/datosPerfil.js') }}" defer>
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        @endsection
