@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h3>Zona administradores
                        </h3>
                    </div>

                    <div class="card-body">

                        <div id="producto" class="oculto">
                            <div class="foto" style="float: left">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/admin/usuarios') }}"
                                            class="botonReserva">Control usuarios</a></li>
                                </ul>
                            </div>

                            <div class="foto" style="float: right">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/admin/mail') }}"
                                            class="botonReserva">Correo (admin)</a></li>
                                </ul>
                            </div>

                            <div class="foto" style="float: right">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/admin/registros') }}"
                                            class="botonReserva">Registros</a></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
