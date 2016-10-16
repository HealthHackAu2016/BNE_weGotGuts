package healthhackau2016.guts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class sccai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sccai);
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

//FIXME replace zeroes with data from form
                "&DBowleFreq=" +((SeekBar)findViewById(R.id.seekBar5)).getProgress() +
                        "&NBowleFreq=" +((SeekBar)findViewById(R.id.seekBar6)).getProgress() +
                        "&blood="  + 0 +
                        "&genWellbeing=" + 0 + "&Extracolonics=" +0 + "&urgency=" + 0;
                new PostClass(getBaseContext(),urlParameters,"https://api.wevegotguts.com/api/public/index.php/tsurveyressccai/insert").execute();

            }
        });
    }

}
