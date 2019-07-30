package com.example.milkteamanager.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.milkteamanager.Adapter.SanphamAdapter;
import com.example.milkteamanager.R;
import com.example.milkteamanager.connect.Server;
import com.example.milkteamanager.model.Sanpham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class Warehouse extends AppCompatActivity {
    Toolbar tbwarehouse;
    RecyclerView recyclerViewmanhinhchinh;

    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    private String product_url = "http://10.102.17.2/Milktea/getproduct.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        tbwarehouse=(Toolbar)findViewById(R.id.toolbarwarehouse);
        ActionToolbar();
        Anhxa();
        trustEveryone();



        GetDuLieuSPMoiNhat();

        //GetData(product_url);

    }
    private void GetData(String product_url){
        RequestQueue requesQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, product_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(Warehouse.this, response.toString(),Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Warehouse.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requesQueue.add(jsonArrayRequest);
    }
    private void ActionToolbar() {
        setSupportActionBar(tbwarehouse);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbwarehouse.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Warehouse.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    int ID=0;
                    String Tensanpham="";
                    int Giasanpham=0;
                    String Hinhanhsanpham="";
                    String Motasanpham="";
                    int IDloaisanpham=0;
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            ID=jsonObject.getInt("id");
                            Tensanpham=jsonObject.getString("name");
                            Giasanpham=jsonObject.getInt("price");
                            Hinhanhsanpham=jsonObject.getString("imageproduct");
                            Motasanpham=jsonObject.getString("describeproduct");
                            IDloaisanpham=jsonObject.getInt("idtype");
                            mangsanpham.add(new Sanpham(ID,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDloaisanpham));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }
    public void Anhxa()
    {
      recyclerViewmanhinhchinh=(RecyclerView)findViewById(R.id.recycleViewmanhinhchinh);
        mangsanpham=new ArrayList<>();
        sanphamAdapter=new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);

    }
}
