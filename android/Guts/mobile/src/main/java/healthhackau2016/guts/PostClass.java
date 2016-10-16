package healthhackau2016.guts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



 class PostClass extends AsyncTask<String, Void, Void> {
//https://www.numetriclabz.com/android-post-and-get-request-using-httpurlconnection/

Context baseContext;
     String postdata;
    public PostClass(Context c, String pd) {
        baseContext = c;
        postdata = pd;
    }


    @Override
    protected Void doInBackground(String... params) {
        try {
            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(baseContext);
            String idResponder = SP.getString("idResponder", "no id found!?");
            //            final TextView outputView = (TextView) findViewById(R.id.showOutput);
            URL url = new URL("https://api.wevegotguts.com/api/public/index.php/tsurveyreshbi/insert");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            String urlParameters = postdata;
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
    //    Toast.makeText(getApplicationContext(), "Data Sent", Toast.LENGTH_SHORT).show();
    }

}
