<?php

namespace App\Http\Controllers;

use Laravel\Lumen\Routing\Controller as BaseController;
use Illuminate\Http\Request;
use LOG;

class Tsurveyreshpi extends BaseController
{
    public function insert(Request $request)
    {
        $idResponder = $request->idResponder;
        $dtSubmit = $request->dtSubmit;
        $genWellbeing = $request->genWellbeing;
        $abdPain = $request->abdPain;
        $lqdStoolFreq = $request->lqdStoolFreq;
        $adbMass = $request->adbMass;
        $jointProb = $request->jointProb;
        $eyeProb = $request->eyeProb;
        $mouthProb = $request->mouthProb;
        $skinProbUlcers = $request->skinProbUlcers;
        $skinProbRedBumps = $request->skinProbRedBumps;
        $perianalProb = $request->perianalProb;
        $fistula = $request->fistula;

        try {
            app('db')->insert('INSERT INTO `tsurveyreshbi`
                            (`idResponder`,
                            `dtSubmit`,
                            `genWellbeing`,
                            `abdPain`,
                            `lqdStoolFreq`,
                            `adbMass`,
                            `jointProb`,
                            `eyeProb`,
                            `mouthProb`,
                            `skinProbUlcers`,
                            `skinProbRedBumps`,
                            `perianalProb`,
                            `fistula`)
                            VALUES
                            (?,?,?,?,?,?,?,?,?,?,?,?,?)',
                [$idResponder, $dtSubmit, $genWellbeing, $abdPain, $lqdStoolFreq, $adbMass, $jointProb, $eyeProb, $mouthProb, $skinProbUlcers, $skinProbRedBumps, $perianalProb, $fistula]);

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
