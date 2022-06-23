@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Galería</div>

                    <div class="card-body">

                        <div id="producto" class="oculto">
                            <div class="foto" style="float: left">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/galeria/1') }}"
                                            class="botonReserva">Aska</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/2') }}"
                                            class="botonReserva">Valkyrie</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/3') }}"
                                            class="botonReserva">Samael</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/4') }}"
                                            class="botonReserva">AncientGreymon</a></li>
                                </ul>
                            </div>

                            <div class="foto" style="float: right">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/galeria/5') }}"
                                            class="botonReserva">Kraken</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/6') }}"
                                            class="botonReserva">Leviatan</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/7') }}"
                                            class="botonReserva">Gaia</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/8') }}"
                                            class="botonReserva">Adamantaimai</a></li>
                                </ul>
                            </div>

                            <div class="foto" style="float: right">
                                <ul class="listaG">
                                    <li class="listaLi"><a href="{{ url('/galeria/9') }}"
                                            class="botonReserva">Odin</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/10') }}"
                                            class="botonReserva">Maotelus</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/11') }}"
                                            class="botonReserva">Azazel</a></li>
                                    <li class="listaLi"><a href="{{ url('/galeria/12') }}"
                                            class="botonReserva">Bahamut</a></li>
                                </ul>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
