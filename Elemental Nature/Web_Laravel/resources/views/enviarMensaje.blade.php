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
                    <form method="post" action="{{ url('/enviarMensaje') }}">
                        @csrf
                        <tr>
                            <td class="tdB" colspan=2>
                                Asunto: <input type="text" id="envA" name="asunto" style="width:85%"
                                    oninput="comprobarCajas()" max="30">
                            </td>
                            <td class="tdB" colspan=2>
                                Para: <input type="text" id="envR" name="nombreE" style="width:85%"
                                    oninput="comprobarCajas()" onkeyup="comprobar()" list="listaNombres">
                                    <datalist id="listaNombres">
                                      </datalist>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdB" colspan=4>
                                Introduzca el mensaje:
                                <br>
                                <textarea name="caja" id="mensaj" class="caja" max="500" oninput="comprobarCajas()" cols="10" rows="8"
                                    placeholder="
                       Ponga en asunto el título del mensaje (máx 30 caracteres).

                       Si quiere mandar un mensaje a un administrador, ponga 0.
                       Si el usuario no existe, el mensaje será enviado a admin.

                       Escriba aquí el contenido del mensaje (máx 500 caracteres)."></textarea>
                            </td>
                        </tr>

                        <tr>
                            <td class="tdP" colspan=4>
                                <input type="submit" id="conf" value="ENVIAR" disabled="">
                            </td>
                        </tr>

                    </form>
                    <script src="{{ asset('js/enviar.js') }}" defer></script>
                </table>
            </div>
        </div>
    @endsection

