<?php

namespace App\Http\Controllers;

use Laravel\Lumen\Routing\Controller as BaseController;
use Illuminate\Http\Request;
use LOG;

class TsurveyRESSCCAI extends BaseController
{
    public function insert(Request $request)
    {
        $idResponder = $request->idResponder;
        $dtSubmit = $request->dtSubmit;
        $DBowleFreq = $request->DBowleFreq;
        $NBowleFreq = $request->NBowleFreq;
        $urgency = $request->urgency;
        $blood = $request->blood;
        $GenWellbeing = $request->GenWellbeing;
        $Extracolonics = $request->Extracolonics;

        try {
            app('db')->insert('INSERT INTO `tSurveyResSCCAI`
                            (`idResponder`,
                            `dtSubmit`,
                            `DBowleFreq`,
                            `NBowleFreq`,
                            `urgency`,
                            `blood`,
                            `GenWellbeing`,
                            `Extracolonics`)
                            VALUES
                            (?,?,?,?,?,?,?,?)',
                [$idResponder, $dtSubmit, $DBowleFreq, $NBowleFreq, $urgency, $blood, $GenWellbeing, $Extracolonics]);

        } catch (\Exception $e) {
            $result = array(
                'Result' => 'ERROR',
                'Message' => 'Unable to connect to the database, please try again later.'
            );

            app('log')->debug($e->getMessage());
            echo json_encode($result);
            return;
        }

        $result = array(
            'Result' => 'OK',
            'Message' => 'Survey Inserted'
        );
        echo json_encode($result);
        return;
    }
}
