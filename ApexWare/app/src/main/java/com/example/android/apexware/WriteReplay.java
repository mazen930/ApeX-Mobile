package com.example.android.apexware;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WriteReplay extends AppCompatActivity {
    EditText replayContent;
    User user = SharedPrefmanager.getInstance(WriteReplay.this).getUser();
    final String token=user.getToken();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_replay);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.myblue));

        Toolbar toolbar =findViewById(R.id.WriteReplayToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Replay to message");

        ActionBar actionbar =WriteReplay.this.getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.close);//------> make it change with profile picture

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        replayContent=findViewById(R.id.replayContent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.replay_option, menu);
        return true;
    }
    public void replayMessage(MenuItem item){
        getResponse(Request.Method.POST,
                Routes.compose,
                null,
                new VolleyCallback(){
                    @Override
                    public void onSuccessResponse(String response) {
                        try {
                            // converting response to json object
                            JSONObject obj = new JSONObject(response);

                            // if no error in response
                            if (response != null) {
                                // getting the result from the response
                                String temp=obj.getString("id");//TODO what i do with returned id ??
                                if(temp!="Receiver id is not found"){
                                    int x=0;
                                    Toast.makeText(getApplicationContext(),"Successfuly sent",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } else {
                                int x=0;
                                Toast.makeText(
                                        getApplicationContext(), "Unsuccessful operation", Toast.LENGTH_SHORT)
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },replayContent.getText().toString().trim(),
                "to send id paren",
                token);

    }
    public void getResponse(
            int method,
            String url,
            JSONObject jsonValue,
            final VolleyCallback callback,
            final String content,
            final String parent,
            final String token) {
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                callback.onSuccessResponse(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                int x=0;
                                Toast.makeText(
                                        getApplicationContext(), "Server Error", Toast.LENGTH_SHORT)
                                        .show();
                                error.getMessage();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("content", content);
                        params.put("parent", parent);
                        params.put("token",token);
                        return params;
                    }
                };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}