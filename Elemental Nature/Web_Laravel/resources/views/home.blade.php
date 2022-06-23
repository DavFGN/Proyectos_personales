@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h3>Usuario: {{ session('usuario') }}
                            @if (session('admin'))
                                (administrador)
                            @endif
                        </h3>
                    </div>

                    <div class="card-body">

                        <div id="producto" class="oculto">
                            <div class="foto" style="float: left">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/mail') }}"
                                            class="botonReserva">Correo interno</a></li>
                                </ul>
                            </div>

                            <div class="foto" style="float: right">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/perfil') }}"
                                            class="botonReserva">Editar perfil</a></li>
                                </ul>
                            </div>

                            <div class="foto" style="float: right">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/partidas') }}"
                                            class="botonReserva">Partidas</a></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
