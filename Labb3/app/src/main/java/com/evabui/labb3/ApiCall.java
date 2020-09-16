package com.evabui.labb3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiCall extends AsyncTask<String, String, String> {

    private Context ctx;

    public ApiCall(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String urlformat = "http://ws.audioscrobbler.com/2.0?method=artist.getsimilar&artist=%s&api_key=9c5a948d04a69746929d5157ae859244";
            URL apiUrl = new URL(String.format(urlformat, strings[0]));
            HttpURLConnection connection = (HttpURLConnection)apiUrl.openConnection();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            InputStreamReader reader = new InputStreamReader(inputStream);
            int c = 0;
            StringBuilder buffer = new StringBuilder();
            while((c = reader.read()) != -1) {
                buffer.append((char)c);
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OJ NÅGOT GICK HELT JÄVLA FEL";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent back = new Intent("android.com.evabui.apicallback");
        back.putExtra("result",s);
        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(back);
    }
}