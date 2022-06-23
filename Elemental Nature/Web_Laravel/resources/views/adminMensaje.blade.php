@extends('layouts.app')

@section('content')
    <div class="container">

        <div class="card-header">
            <div id="producto" class="oculto">
                <div class="foto" style="float: left">
                    <a href="{{ url('/admin/mail') }}" class="botonBandeja">Mensajes recibidos</a>
                </div>

                <div class="foto" style="float: right">
                    <a href="{{ url('/admin/envios') }}" class="botonBandeja">Mensajes enviados</a>
                </div>
                <div class="foto" style="float: right">
                    <a href="{{ url('/admin/enviarMensaje') }}" class="botonBandeja">Enviar mensaje</a>
                </div>
            </div>
            </div>

            <div class="bandejaD">
                <table class="tablaP">
                    <tr>
                        @foreach ($resultado as $data)
                            <td class="tdB" colspan="2">
                                Asunto: {{ $data->asunto }}
                            </td>
                            <td class="tdB">
                                Remitente: {{ $data->nombre }}
                            </td>
                    </tr>
                    <tr>
                        <td class="tdB" colspan=3>
                            {{ $data->contenido }}
                        </td>
                    </tr>
                    @endforeach
                </table>
            </div>
        @endsection
