@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="card-header">
            <div id="producto" class="oculto">
                <div class="foto" style="float: left">
                    <a href="{{ url('/mail') }}" class="botonBandeja">Mensajes recibidos</a>
                </div>

                <div class="foto" style="float: right">
                    <a href="{{ url('/envios') }}" class="botonBandeja">Mensajes enviados</a>
                </div>
                <div class="foto" style="float: right">
                    <a href="{{ url('/enviarMensaje') }}" class="botonBandeja">Enviar mensaje</a>
                </div>
            </div>

            <div class="bandejaD">
                <table class="tablaP">
                    <tr>
                        <td class="tdP">
                            Codigo
                        </td>
                        <td class="tdP" colspan=2>
                            Asunto
                        </td>
                        <td class="tdP" colspan=2>
                            Fecha
                        </td>
                    </tr>

                    @foreach ($resultado as $data)
                        <tr>
                            <td class="tdP">
                                {{ $data->cod }}
                            </td>
                            <td class="tdP" colspan=2>
                                <form method="POST" action="{{ route('mensaje') }}">
                                    @csrf
                                    <input type="hidden" name="codigo" value="{{ $data->cod }}">
                                    <button type="submit" class="asunto">
                                        {{ $data->asunto }}
                                    </button>
                                </form>
                            </td>
                            <td class="tdP">
                                {{ $data->fecha }}
                            </td>
                        </tr>
                    @endforeach

                    </tr>
                </table>
            </div>
        @endsection
