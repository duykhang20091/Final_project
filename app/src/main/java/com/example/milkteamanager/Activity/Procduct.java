package com.example.milkteamanager.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.milkteamanager.Adapter.LoaispAdapter;
import com.example.milkteamanager.Adapter.SanphamAdapter;
import com.example.milkteamanager.R;
import com.example.milkteamanager.model.Loaisp;
import com.example.milkteamanager.model.Sanpham;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

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

public class Procduct extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView lvManhinhchinh;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    //public static ArrayList<Giohang> manggiohang;
    private String product_url = "http://192.168.1.2/Milktea/getproduct.php";
    private String type_url = "http://192.168.1.2/Milktea/gettypeproduct.php";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procduct);
        trustEveryone();
        ActionBar();
        ActionViewFlipper();
        GetDuLieuLoaiSp();
        GetDuLieuSPMoiNhat();
       // CatchOnItemListView();
        Anhxa();

    }
    public void ActionBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
   /* private void CatchOnItemListView() {
        lvManhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        Intent intent=new Intent(Procduct.this,Home.class );
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    /*case 1:
                        Intent intent1=new Intent(Procduct.this,DienThoaiActivity.class );
                        intent1.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent intent2=new Intent(Procduct.this,LapTopActivity.class );
                        intent2.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent intent3=new Intent(Procduct.this,LienHeActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        Intent intent4=new Intent(Procduct.this,ThongTinActivity.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        Intent intent5=new Intent(Procduct.this,Phanhoi.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

            }
        });
    }
            */
    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(type_url, new Response.Listener<JSONArray>() {
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
    private void GetDuLieuLoaiSp() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(type_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            id=jsonObject.getInt("idproduct");
                            tenloaisp=jsonObject.getString("name");
                            hinhanhloaisp=jsonObject.getString("image");
                            mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3,new Loaisp(0,"Liên Hệ","https://cdn2.iconfinder.com/data/icons/social-media-network-fill-flat-icon/512/Viber-2-512.png"));
                    mangloaisp.add(4,new Loaisp(0,"Thông Tin","https://cdn.pixabay.com/photo/2016/06/15/15/02/info-1459077_960_720.png"));
                    mangloaisp.add(5,new Loaisp(0,"Phản Hồi","https://www.pngkey.com/png/detail/11-113921_phone-icon-in-a-circle-phone-icon-png.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // CheckConnection.ShowToast_short(getApplicationContext(),error.toString()+"sai rồi");
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void Anhxa()
    {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        toolbar=(Toolbar)findViewById(R.id.tbManhinhchinh) ;
        viewFlipper=(ViewFlipper)findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh=(RecyclerView)findViewById(R.id.recycleViewmanhinhchinh);
        lvManhinhchinh=(ListView)findViewById(R.id.lvManhinhchinh);
        mangloaisp=new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang Chính","https://img.icons8.com/plasticine/2x/home-page.png"));
        loaispAdapter=new LoaispAdapter(mangloaisp,getApplicationContext());
        lvManhinhchinh.setAdapter(loaispAdapter);
        mangsanpham=new ArrayList<>();
        sanphamAdapter=new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);
       /* if(manggiohang!=null)
        {

        }
        else
            manggiohang=new ArrayList<>();*/
    }
    private void ActionViewFlipper() {
        ArrayList<String>mangquangcao=new ArrayList<>();
        mangquangcao.add("https://i.ytimg.com/vi/AUZ3AQD19Q8/maxresdefault.jpg");
        mangquangcao.add("https://advertisingvietnam.com/wp-content/uploads/2018/03/tiki-min.png");
        mangquangcao.add("https://i.ytimg.com/vi/3B-53z916DY/maxresdefault.jpg");
        mangquangcao.add("https://images.kienthuc.net.vn/zoomh/500/uploaded/nguyenvan/2016_12_07/Ho/ngam-ho-ngoc-ha-cuc-quyen-ru-khi-quang-cao-dien-thoai-Hinh-3.jpg");
        for(int i=0;i<mangquangcao.size();i++)
        {
            ImageView imageView=new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_to_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
}

