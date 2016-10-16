package healthhackau2016.guts;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.DataOutputStream;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import 	android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;


import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      new SendData().execute("ss");
                new PostClass().execute();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void launchSCCAI(View v)

    {
        //noinspection SimplifiableIfStatement

        Intent intent = new Intent(this, sccai.class);
        startActivity(intent);
    }

    public void launchHBI(View v) {
        //noinspection SimplifiableIfStatement

        Intent intent = new Intent(this, hbi.class);
        startActivity(intent);
    }

    public void POSTtest(View v) {
        //noinspection SimplifiableIfStatement

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


    private class PostClass extends AsyncTask<String, Void, Void> {
//https://www.numetriclabz.com/android-post-and-get-request-using-httpurlconnection/


        public PostClass() {

        }


        @Override
        protected Void doInBackground(String... params) {
            try {
                SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String idResponder = SP.getString("idResponder", "no id found!?");
    //            final TextView outputView = (TextView) findViewById(R.id.showOutput);
                URL url = new URL("https://api.wevegotguts.com/api/public/index.php/tsurveyreshbi/insert");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                String urlParameters = "idResponder="+idResponder +
                        "&dtSubmit=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                        "&genWellbeing=" +
                        "&abdPain=" +
                        "&lqdStoolFreq=" +
                        "&adbMass=" +
                        "&jointProb=" +
                        "&eyeProb=" +
                        "&mouthProb=" +
                        "&skinProbUlcers=" +
                        "&skinProbRedBumps=" +
                        "&perianalProb=" +
                        "&fistula=";
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
 //               connection.setRequestProperty("idResponder", idResponder);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();
                int responseCode = connection.getResponseCode();

                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);

     //           final StringBuilder output = new StringBuilder("Request URL " + url);
     //           output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
     //           output.append(System.getProperty("line.separator")  + "Response Code " + responseCode);
     //           output.append(System.getProperty("line.separator")  + "Type " + "POST");
       /*         BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
     //           System.out.println("output===============" + br);
                while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
                }
                br.close();
*/
//                output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

//                MainActivity.this.runOnUiThread(new Runnable() {





            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            return null;
        }

        protected void onPostExecute() {
            Toast.makeText(getApplicationContext(), "Data Sent", Toast.LENGTH_SHORT).show();
        }

    }

}


