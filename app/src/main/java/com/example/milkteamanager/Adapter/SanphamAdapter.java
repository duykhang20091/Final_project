package com.example.milkteamanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milkteamanager.Activity.Chitietsanpham;
import com.example.milkteamanager.model.Milktea;
import com.example.milkteamanager.model.Sanpham;
import com.squareup.picasso.Picasso;
import com.example.milkteamanager.R;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHolder itemHolder=new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int i) {
        Sanpham sanpham=arraysanpham.get(i);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.loading).error(R.drawable.error).into(holder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham;
        public TextView txtgiasanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham=(ImageView) itemView.findViewById(R.id.imageviewhinhanhsanpham);
            txtgiasanpham=(TextView)itemView.findViewById(R.id.textviewgiasanpham);
            txttensanpham=(TextView)itemView.findViewById(R.id.textviewtensanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, Chitietsanpham.class);
                    intent.putExtra("thongtinsanpham", arraysanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}