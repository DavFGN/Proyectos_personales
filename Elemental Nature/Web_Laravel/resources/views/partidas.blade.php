@extends('layouts.app')

@section('content')
    <div class="container">

        <div id="producto" class="oculto">

            <div class="foto" id="partidas" style="float: left; min-width: 70%;">
                <table class="tablaP">
                    <tr style="border: 2px solid blue">
                        <td class="tdU">
                            Codigo Partida
                        </td>
                        <td class="tdU">
                            Ganador
                        </td>
                        <td class="tdU">
                            Perdedor
                        </td>
                        <td class="tdU" colspan=2>
                            Fecha
                        </td>
                    </tr>
                    @php
                        use Illuminate\Support\Facades\DB;
                    @endphp
                    @if (count($partidas) > 0)
                        @foreach ($partidas as $data)
                            @php
                                $ganador = $data->ganador;
                                $perdedor = $data->perdedor;

                                $query = "select nombre from Usuario where codigo = $ganador";
                                $query2 = "select nombre from Usuario where codigo = $perdedor";

                                $resultado = DB::select($query);
                                $resultado2 = DB::select($query2);

                                foreach ($resultado as $key => $item) {
                                    $win = $item->nombre;
                                }

                                foreach ($resultado2 as $key => $item) {
                                    $loo = $item->nombre;
                                }
                            @endphp
                            <tr style="border: 2px solid blue;">
                                <td>{{ $data->codigo }}</td>
                                <td>{{ $win }}</td>
                                <td>{{ $loo }}</td>
                                <td>{{ $data->fecha }}</td>
                            </tr>
                        @endforeach
                    @else
                        <tr style="border: 2px solid blue;">
                            <td colspan="4">Aún no has jugado ninguna partida</td>
                        </tr>
                    @endif
                </table>
            </div>

            <div class="foto" id="luchadores" style="float: right; height: 475px;">

                @php
                    $first = true;
                    $second = true;
                    $third = true;

                @endphp
                @foreach ($luchadores as $data)
                    @php
                        $luch = $data->luchador;
                        $cant = $data->usado;

                        switch ($luch) {
                            case 'L1':
                                $img = 'url(/img/L1.jpg)';
                                break;
                            case 'L2':
                                $img = 'url(/img/L2.png)';
                                break;
                            case 'L3':
                                $img = 'url(/img/L3.jpg)';
                                break;
                            case 'L4':
                                $img = 'url(/img/L4.png)';
                                break;
                            case 'L5':
                                $img = 'url(/img/L5.jpg)';
                                break;
                            case 'L6':
                                $img = 'url(/img/L6.png)';
                                break;
                            case 'L7':
                                $img = 'url(/img/L7.jpg)';
                                break;
                            case 'L8':
                                $img = 'url(/img/L8.jpg)';
                                break;
                            case 'L9':
                                $img = 'url(/img/L9.png)';
                                break;
                            case 'L10':
                                $img = 'url(/img/L10.jpg)';
                                break;
                            case 'L11':
                                $img = 'url(/img/L11.jpg)';
                                break;
                            case 'L12':
                                $img = 'url(/img/L12.png)';
                                break;
                            default:
                                $img = 'url(/img/index.jpg)';
                                break;
                        }

                        $style = "style=\"background-image: $img !important; background-repeat: no-repeat;
                                                                                                                         background-size: 100% 100%;\"";

                        if ($first == true) {
                            echo "<div class='luchP' $style> <div class='texto-encima'>1º: $cant veces</div></div> ";

                            $first = false;
                        } elseif ($second == true) {
                            echo "<div class='luchS' $style>
                                            <div class='texto-encimaM'>2º: $cant veces</div></div> ";

                            $second = false;
                        } elseif ($third == true) {
                            echo "<div class='luchT' $style>
                                            <div class='texto-encimaA'>3º: $cant veces</div></div> ";

                            $third = false;
                        }

                    @endphp
                @endforeach

            </div>

        </div>

    </div>
@endsection
