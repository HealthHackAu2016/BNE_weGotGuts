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
import android.widget.SeekBar;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String idResponder = SP.getString("idResponder", "no id found!?");
                String urlParameters = "idResponder="+idResponder +
                        "&dtSubmit=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                        "&genWellbeing=" +
                        "&abdPain=" +((SeekBar)findViewById(R.id.seekBarHBIAbdominal)).getProgress() +
                        "&lqdStoolFreq=" +
                        "&adbMass=" +
                        "&jointProb=" +
                        "&eyeProb=" +
                        "&mouthProb=" +
                        "&skinProbUlcers=" +
                        "&skinProbRedBumps=" +
                        "&perianalProb=" +
                        "&fistula=";
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
