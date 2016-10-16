const mysql = require('mysql');
const async = require('async');

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'guts',
  password : 'gutsHH2016',
  database : 'hh_guts_public'
});

connection.connect();

// connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
//   if (err) throw err;
//   console.log('The solution is: ', rows[0].solution);
// });

// connection.end();


exports.getVisualisations = (req, res) => {

  var calpro_sql_statement = `select distinct tsurveyreshbi.idResponder as idResponder,
DATE_FORMAT(tsurveyreshbi.dtSubmit,'%Y/%m/%d') as dtSubmit,
ifnull(tibdocreading.Reading,'NaN') as calpro
from hh_guts_public.tsurveyreshbi left outer join  hh_guts_public.tibdocreading on tsurveyreshbi.idResponder = tibdocreading.idResponder and tibdocreading.dtSubmit = tsurveyreshbi.dtSubmit
where tsurveyreshbi.idResponder = 'C0077'`;

var hbi_sql_statement = `select distinct tsurveyreshbi.idResponder as idResponder,
DATE_FORMAT(tsurveyreshbi.dtSubmit,'%Y/%m/%d') as dtSubmit,
tsurveyreshbi.genWellbeing +
tsurveyreshbi.abdPain +
tsurveyreshbi.lqdStoolFreq +
tsurveyreshbi.adbMass +
tsurveyreshbi.jointProb +
tsurveyreshbi.eyeProb +
tsurveyreshbi.mouthProb +
tsurveyreshbi.skinProbUlcers +
tsurveyreshbi.skinProbRedBumps +
tsurveyreshbi.perianalProb +
tsurveyreshbi.fistula as hbi_score
from hh_guts_public.tsurveyreshbi left outer join  hh_guts_public.tibdocreading on tsurveyreshbi.idResponder = tibdocreading.idResponder and tibdocreading.dtSubmit = tsurveyreshbi.dtSubmit
where tsurveyreshbi.idResponder = 'C0077'`;

var fbsleep_sql_statement = "SELECT dtRecorded, NoAwakenings FROM hh_guts_public.tfsleep WHERE idResponder = 'C0077'"
var fbactivity_sql_statement = "SELECT dtRecorded, ActivityCalories from hh_guts_public.tfbactivities WHERE idResponder = 'C0077'"

  connection.query(calpro_sql_statement, function(err, calpro_rows, fields) {
    if (err) throw err;

    // console.log('Result: ', rows);
    var _calpro = [];
    // var _hbi = [];
    async.each(calpro_rows, function(row, calpro_callback) {
      // console.log('Row',row);
      _calpro.push([row.dtSubmit, row.calpro]);
      // _hbi.push([row.dtSubmit, row.hbi_score]);
      calpro_callback();
        //process row
    }, function(err) {
        // if any of the file processing produced an error, err would equal that error
        if( err ) {
          // One of the iterations produced an error.
          // All processing will now stop.
          console.log('Error processing results:', err);
        } else {
          console.log('All calpro results have been processed successfully');

          connection.query(hbi_sql_statement, function(err, hbi_rows, fields) {
            if (err) throw err;

            var _hbi = [];

            async.each(hbi_rows, function(row, hbi_callback) {
              // console.log('Row',row);
              // _calpro.push([row.dtSubmit, row.calpro]);
              _hbi.push([row.dtSubmit, row.hbi_score]);
              hbi_callback();
                //process row
            }, function(err) {
              if( err ) {
                console.log('Error processing hbi results:', err);
              } else {
                console.log('All hbi results have been processed successfully');

                connection.query(fbsleep_sql_statement, function(err, fbsleep_rows, fields) {
                  if (err) throw err;
                  var _fbsleep = [];
                  async.each(fbsleep_rows, function(row, fbsleep_callback) {
                    _fbsleep.push([row.dtRecorded, row.NoAwakenings]);
                    fbsleep_callback();
                  }, function(err) {
                    if( err ) {
                      console.log('Error processing fitbit sleep results:', err);
                    } else {
                      console.log('All fitbit sleep results have been processed successfully');


                      connection.query(fbactivity_sql_statement, function(err, fbactivity_rows, fields) {
                        if (err) throw err;
                        var _fbactivity = [];
                        async.each(fbactivity_rows, function(row, fbactivity_callback) {
                          _fbactivity.push([row.dtRecorded, row.ActivityCalories]);
                          fbactivity_callback();
                        }, function(err) {
                          if( err ) {
                            console.log('Error processing fitbit activity results:', err);
                          } else {
                            console.log('All fitbit activity results have been processed successfully');

                            res.render('vis/visualisations', {
                              title: 'Patient Visualisations',
                              calpro: JSON.stringify(_calpro),
                              hbi: JSON.stringify(_hbi),
                              fbsleep: JSON.stringify(_fbsleep),
                              fbactivities: JSON.stringify(_fbactivity)
                            });
                          }
                        });
                      });
                    }
                  });
                });




              }
            });
          });
        }
    });
  });
};
