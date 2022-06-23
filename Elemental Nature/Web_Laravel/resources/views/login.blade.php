@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">{{ __('Login') }}</div>

                    <div class="card-body">
                        <form method="POST" action="{{ route('login.login') }}">
                            @csrf

                            <div class="row mb-3">
                                <label for="usuario" class="col-md-4 col-form-label text-md-end">Usuario</label>

                                <div class="col-md-6">
                                    <input id="usuario" type="usuario" class="form-control" name="usuario" required
                                        autocomplete="usuario" autofocus>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label for="pass
                                                                "
                                    class="col-md-4 col-form-label text-md-end">Contraseña</label>

                                <div class="col-md-6">
                                    <input id="pass" type="password" class="form-control" name="pass" required
                                        autocomplete="pass" autofocus>
                                </div>
                            </div>

                            <div
                                class="row mb-9
                                @isset($error) @else
                                hidden @endisset
                            ">
                                <label for="pass" class="col-md-8 col-form-label text-md-end error">Datos incorrectos.
                                    Vuelva a intentarlo</label>
                            </div>
                            <div class="row mb-0">
                                <div class="col-md-8 offset-md-4">
                                    <button type="submit" class="btn btn-primary">
                                        {{ __('Login') }}
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
