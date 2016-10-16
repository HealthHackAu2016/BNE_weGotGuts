package healthhackau2016.guts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class hbi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hbi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String idResponder = SP.getString("idResponder", "no id found!?");
                String urlParameters = "idResponder="+idResponder +
                        "&dtSubmit=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                        "&genWellbeing=" +((SeekBar)findViewById(R.id.seekBar)).getProgress() +
                        "&abdPain=" +((SeekBar)findViewById(R.id.seekBarHBIAbdominal)).getProgress() +
                        "&lqdStoolFreq=" + ((EditText)findViewById(R.id.NumberLiquidStools)).getText() +
                        "&adbMass=" +((SeekBar)findViewById(R.id.seekBar4)).getProgress() +
                        "&jointProb=" +(((CheckBox)findViewById(R.id.checkBoxArthralgia)).isChecked() ? 1 : 0 ) +
                        "&eyeProb=" +(((CheckBox)findViewById(R.id.checkBoxUveitis)).isChecked() ? 1 : 0 ) +
                        "&mouthProb=" +(((CheckBox)findViewById(R.id.checkBoxAphthous)).isChecked() ? 1 : 0 ) +
                        "&skinProbUlcers=" +(((CheckBox)findViewById(R.id.checkBoxPyoderma)).isChecked() ? 1 : 0 ) +
                        "&skinProbRedBumps=" + (((CheckBox)findViewById(R.id.checkBoxErythema)).isChecked() ? 1 : 0 ) +
                        "&perianalProb=" + (((CheckBox)findViewById(R.id.checkBoxFissure)).isChecked() ? 1 : 0 ) +
                        "&fistula=" + (((CheckBox)findViewById(R.id.checkBoxFistula)).isChecked() ? 1 : 0 );
                new PostClass(getBaseContext(),urlParameters,"https://api.wevegotguts.com/api/public/index.php/tsurveyreshbi/insert").execute();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
