@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Cambiar contraseña (deberá iniciar sesión de nuevo)</div>
                    <div class="card-body">
                        <form method="POST" action="{{ route('perfil.Actualizar.password') }}">
                            @csrf
                        <div id="producto" class="oculto">
                            <div class="foto" style="float: left">
                                <ul class="listaG">
                                    <li class="listaLi">
                                        Contraseña actual:
                                    </li>
                                    <li class="listaLi">
                                        Contraseña nueva:
                                    </li>
                                    <li class="listaLi">
                                        Repite la contraseña:
                                    </li>
                                </ul>
                            </div>

                            <div class="foto" style="float: right; text-align: left;">
                                <ul class="listaG">
                                    <li class="listaLi">
                                        <input id="passA" name="passA" class="" type="password" onkeyup="comprobar()">
                                    </li>
                                    <li class="listaLi">
                                        <input id="passN" name="passN" class="" type="password" onkeyup="comprobar()">
                                    </li>
                                    <li class="listaLi">
                                        <input id="passNR" class="" type="password" onkeyup="comprobar()">
                                    </li>
                                </ul>
                            </div>
                            <button type="submit" id="bot" class="btn btn-primary" disabled>
                                Actualizar
                            </button>
                        </div>
                        <div
                                class="row mb-9
                                @isset($error) @else
                                hidden @endisset
                            ">
                                <label for="pass" class="col-md-8 col-form-label text-md-end error">Datos incorrectos.
                                    Vuelva a intentarlo</label>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection

<script src="{{ asset('js/pass.js') }}" defer></script>
