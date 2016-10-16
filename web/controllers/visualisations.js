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

  var _sql_statement = `select tibdocreading.idResponder as idResponder,
tibdocreading.dtSubmit as dtSubmit,
tibdocreading.Reading as calpro,
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
from hh_guts_public.tibdocreading, hh_guts_public.tsurveyreshbi
where tibdocreading.idResponder = tsurveyreshbi.idResponder
and tibdocreading.dtSubmit  = tsurveyreshbi.dtSubmit
and tibdocreading.idResponder = "C0077"`

  connection.query(_sql_statement, function(err, rows, fields) {
    if (err) throw err;

    console.log('Result: ', rows);
    var _data = [];
    async.each(rows, function(row, callback) {
      console.log('Row',row);
      // _data.push(row);
      _data.push([row.RowDataPacket.dtSubmit, row.RowDataPacket.calpro, row.RowDataPacket.hbi_score]);
      callback();
        //process row
    }, function(err) {
        // if any of the file processing produced an error, err would equal that error
        connection.end();
        if( err ) {
          // One of the iterations produced an error.
          // All processing will now stop.
          console.log('Error processing results:', err);
        } else {
          console.log('All results have been processed successfully');
          console.log(_data);
          res.render('vis/visualisations', {
            title: 'Patient Visualisations',
            data: _data
          });
        }
    });
  });
};
