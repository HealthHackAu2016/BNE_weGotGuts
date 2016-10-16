<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$app->get('/', function () use ($app) {
    return $app->version();
});

$app->get('/foo', function () use ($app) {
    return '<h1>Hellow world</h1>';
});

//tsurveyreshbi
$app->post('tsurveyreshbi/insert', 'Tsurveyreshbi@insert');
//End tsurveyreshbi

//TsurveyRESSCCAI
$app->post('tsurveyressccai/insert', 'TsurveyRESSCCAI@insert');
//End TsurveyRESSCCAI
