<!doctype html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>{{ config('app.name', 'Laravel') }}</title>

    <!-- Scripts -->
    <script src="{{ asset('js/app.js') }}" defer></script>
    <script src="{{ asset('js/js.js') }}" defer></script>
    <!-- Fonts -->
    <link rel="dns-prefetch" href="//fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet">

    <!-- Styles -->
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <link href="{{ asset('css/estilos.css') }}" rel="stylesheet">
</head>

<body>
    <div class="ag-leaf-block">
        <div class="ag-format-container">
            <div id="app" class="fondoLayout">
                <nav class="navbar navbar-expand-md navbar-light bg-white shadow-sm fondoLayout">
                    <div class="container">
                        <a class="navbar-brand" href="{{ url('/') }}">
                            <img class="imglogo" alt="logo" src="{{ asset('img/logoVicsio.png') }}">
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="{{ __('Toggle navigation') }}">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">

                            <!-- Right Side Of Navbar -->
                            <ul class="navbar-nav ms-auto">
                                @if (strpos(Request::url(), 'galeria') !== false)
                                    <li class="nav-item">
                                        <a class="navbar-brand dropdown-item nombreApp"
                                            href="/galeria">{{ __('Galería') }}</a>
                                    </li>
                                @else
                                    <li class="nav-item">
                                        <a class="navbar-brand dropdown-item nombreApp" href="{{ url('/') }}">
                                            {{ config('app.name', 'Laravel') }} </a>
                                    </li>
                                    @if (Session::has('usuario'))
                                        @if (session('admin'))
                                            <li class="nav-item">
                                                <a class="navbar-brand dropdown-item nombreApp" href="/admin">Zona
                                                    administración</a>
                                            </li>
                                        @endif
                                        <li class="nav-item">
                                            <a class="navbar-brand dropdown-item nombreApp"
                                                href="{{ route('login.showMenu') }}">{{ __('Menú') }}</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="navbar-brand dropdown-item nombreApp"
                                                href="{{ route('login.close') }}">{{ __('Cerrar sesión') }}</a>
                                        </li>

                            </ul>
                        </div>
                        @endif
                        @endif

                    </div>
            </div>
            </nav>
        </div>

        <div class="ag-leaf-block">
            <div class="ag-format-container">
                <main class="py-4">
                    @yield('content')
                </main>
            </div>
            <div id="js-ag-leaf_box"></div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js"></script>

        <script>
            var e = document.getElementById('js-ag-leaf_box'),
                t = window.innerWidth,
                a = 2 * window.innerHeight;

            for (i = 0; i < 12; i++) {
                var n = document.createElement('span');
                n.classList.add('js-ag-leaf');

                TweenLite.set(n, {
                    x: fnRandom(0, 2),
                    y: fnRandom(8, a),
                    z: fnRandom(-200, 200)
                }), e.appendChild(n), r(n);
            }

            function r(e) {
                TweenMax.to(e, fnRandom(8, 15), {
                    x: t + 100,
                    ease: Linear.easeNone,
                    repeat: -1,
                    delay: -15
                }), TweenMax.to(e, fnRandom(4, 2), {
                    y: "+=100",
                    //rotationZ: fnRandom(0, 180),
                    repeat: -1,
                    yoyo: true,
                    ease: Sine.easeInOut
                }), TweenMax.to(e, fnRandom(2, 2), {
                    //rotationX: fnRandom(0, 360),
                    //rotationY: fnRandom(0, 360),
                    repeat: -1,
                    yoyo: true,
                    ease: Sine.easeInOut,
                    delay: -5
                })
            }

            function fnRandom(e, t) {
                return e + Math.random() * (t - e);
            }
        </script>


</body>

</html>
