package com.example.shop.personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.R;
import com.example.shop.classoop.UserInfo;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhoneActivity extends AppCompatActivity {

    Toolbar toolbarPhone;
    EditText editPhone;
    Button btnChangePhone;
    int id;
    SessionManager sessionManager;
    ArrayList<UserInfo> userInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        Connect();
        actionBar();

        userInfos = new ArrayList<>();
        String url = Server.getinfo + "?id=" + id;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                userInfos.add(new UserInfo(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getString("email"),
                                        jsonObject.getString("sdt")
                                ));
                                editPhone.setText(userInfos.get(0).getSdt());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), "info not found", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void Connect() {
        toolbarPhone = findViewById(R.id.toolbarPhone);
        editPhone = findViewById(R.id.editPhone);
        btnChangePhone = findViewById(R.id.btnChangePhone);
        sessionManager = new SessionManager(this);
        id = sessionManager.GetUser();
    }

    private void actionBar() {
        setSupportActionBar(toolbarPhone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPhone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
