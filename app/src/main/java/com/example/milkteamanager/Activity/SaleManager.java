package com.example.milkteamanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.milkteamanager.Adapter.MemberAdapter;
import com.example.milkteamanager.R;
import com.example.milkteamanager.model.Member;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaleManager extends AppCompatActivity {
    private String user_url = "http://10.102.17.2/Milktea/getuser.php";
    ListView lvmember;
    ArrayList<Member> arrayMember;
    MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_manager);
        Button back = findViewById(R.id.btnBackSale);
        GetData(user_url);
        lvmember = (ListView) findViewById(R.id.lvmember);
        arrayMember = new ArrayList<>();
        adapter = new MemberAdapter(this, R.layout.dong_member, arrayMember);
        lvmember.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SaleManager.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void GetData(String product_url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
    /*    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, product_url, null,
                new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayMember.add(new Member(
                                        object.getInt("user_id"),
                                        object.getString("username"),
                                        object.getString("full_name"),
                                        object.getString("password_hash"),
                                        object.getString("salt"),
                                        object.getString("created_date")

                                ));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SaleManager.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requesQueue.add(jsonArrayRequest);*/
        StringRequest stringRequest=new StringRequest(Request.Method.POST, user_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int Id=0;
                String username="";
                String fullname="";
                String pass="";
                String salt="";
                String date;
                if(response!=null)
                {
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i =0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            Id=jsonObject.getInt("user_id");
                            username=jsonObject.getString("username");
                            fullname=jsonObject.getString("ful_name");
                            pass=jsonObject.getString("password_hash");
                            salt=jsonObject.getString("salt");
                            date=jsonObject.getString("created_date");
                            arrayMember.add(new Member(Id,username,fullname,pass,salt,date));
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        ;
        requestQueue.add(stringRequest);
    }
    }


