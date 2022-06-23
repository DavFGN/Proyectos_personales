<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/galeria', function () {
    return view('galeria');
});
Route::get(
    '/galeria/{opcion}',
    [
        'uses' => 'App\Http\Controllers\Controller@visualizar'
    ]
);

Route::get('/login', 'App\Http\Controllers\Controller@showLogin')->name('login.showLogin');
Route::post('/login', 'App\Http\Controllers\Controller@login')->name('login.login');
Route::get('/menu', 'App\Http\Controllers\Controller@showMenu')->name('login.showMenu');
Route::get('/cerrarSesion', 'App\Http\Controllers\Controller@cerrarSesion')->name('login.close');

Route::get('/mail', 'App\Http\Controllers\Controller@mail')->name('mail');
Route::get('/envios', 'App\Http\Controllers\Controller@envios')->name('envios');
Route::post('/mail/mensaje', 'App\Http\Controllers\Controller@verCorreo')->name('mensaje');
Route::get('/buscarUsuario/{usuario}', 'App\Http\Controllers\Controller@buscarUsuario')->name('buscarUsuario');
Route::get('/enviarMensaje', 'App\Http\Controllers\Controller@enviarMensaje')->name('enviarMensaje');
Route::post('/enviarMensaje', 'App\Http\Controllers\Controller@enviarM')->name('enviarM');
Route::get('/perfil', 'App\Http\Controllers\Controller@perfil')->name('perfil');
Route::get(
    '/perfil/{nombre}',
    [
        'uses' => 'App\Http\Controllers\Controller@disponibilidadNombre'
    ]
);
Route::get(
    '/perfil/actualizar/{codigo}/{nombre}',
    [
        'uses' => 'App\Http\Controllers\Controller@actualizarNombre'
    ]
);
Route::get('/perfil/actualizar/password', 'App\Http\Controllers\Controller@cambiarPassword')->name('perfil.password');
Route::post('/perfil/actualizar/password', 'App\Http\Controllers\Controller@actualizarPassword')->name('perfil.Actualizar.password');
Route::get('/partidas', 'App\Http\Controllers\Controller@partidas')->name('partidas');

Route::get('/admin/usuarios', 'App\Http\Controllers\Controller@administrarUsuarios')->name('admin.usuarios');
Route::get('/admin/perfil/{usuario}', 'App\Http\Controllers\Controller@adminPerfil')->name('adminPerfil');
Route::post('/admin/perfil/{usuario}', 'App\Http\Controllers\Controller@adminPerfilDatos')->name('adminPerfilDatos');
Route::get('/admin/registros', 'App\Http\Controllers\Controller@adminRegistros')->name('admin.registros');
Route::get('/admin/registros/busqueda/{nombre}/{fecha}', 'App\Http\Controllers\Controller@adminRegistrosBusqueda')->name('admin.registrosBusqueda');
Route::get('/admin/mail', 'App\Http\Controllers\Controller@adminMail')->name('admin.mail');
Route::get('/admin/envios', 'App\Http\Controllers\Controller@adminEnvios')->name('admin.envios');
Route::post('/admin/mail/mensaje', 'App\Http\Controllers\Controller@adminVerCorreo')->name('admin.mensaje');
Route::get('/admin/enviarMensaje', 'App\Http\Controllers\Controller@adminEnviarMensaje')->name('admin.enviarMensaje');
Route::post('/admin/enviarMensaje', 'App\Http\Controllers\Controller@adminEnviarM')->name('admin.enviarM');
Route::get('/admin', 'App\Http\Controllers\Controller@admin')->name('admin');
