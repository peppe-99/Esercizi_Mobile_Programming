package com.example.socketraw;

import androidx.appcompat.app.AppCompatActivity;

import android.net.InetAddresses;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private EditText etHostAddress;
    private EditText etPort;
    private EditText etSend;
    private TextView tvResponse;
    private String IP = "www.repubblia.it";
    private String PORT = "80";
    private String REQ = "GET /home HTTP/1.1\n" +
                         "Host: www.repubblica.it\n" +
                         "\n";
    private String host;
    private String port;
    private String toSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHostAddress = findViewById(R.id.etHostAddress);
        etPort = findViewById(R.id.etPort);
        etSend = findViewById(R.id.etSend);
        tvResponse = findViewById(R.id.tvResponse);

        etHostAddress.setText(IP);
        etPort.setText(PORT);
        etSend.setText(REQ);
        tvResponse.setMovementMethod(new ScrollingMovementMethod());
    }


    public void sendRequest(View view) {
        host = etHostAddress.getText().toString();
        port = etPort.getText().toString();
        toSend = etSend.getEditableText().toString();

        NetworkTask networkTask = new NetworkTask();
        networkTask.execute();
    }

    class NetworkTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... integers) {
            String dataReceived = "";
            Socket socket = null;
            InetAddress serverAddress;

            try {
                serverAddress = InetAddress.getByName(host);
                socket = new Socket(serverAddress, Integer.parseInt(port));
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            if(socket == null){
                return null;
            }

            try {
                OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
                out.write(toSend);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = "";
                while ((line = in.readLine()) != null) {
                    dataReceived += line + "\n";
                    if(line.length() == 0) break;;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return dataReceived;
        }

        @Override
        protected void onPostExecute(String s) {
            tvResponse.setText(s);
        }
    }
}