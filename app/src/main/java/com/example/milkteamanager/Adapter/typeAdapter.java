package com.example.milkteamanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milkteamanager.R;
import com.example.milkteamanager.model.TypeProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class typeAdapter extends BaseAdapter {
    ArrayList<TypeProduct>arraylisttypepro;
    Context context;

    public typeAdapter(ArrayList<TypeProduct> arraylisttypepro, Context context) {
        this.arraylisttypepro = arraylisttypepro;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arraylisttypepro.size();
    }

    @Override
    public Object getItem(int i) {
        return arraylisttypepro.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class  ViewHolder{
        TextView txttypepro;
        ImageView imgtypepro;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null)
        {
            viewHolder =new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.dong_listview,null);

            viewHolder.txttypepro=(TextView) view.findViewById(R.id.txttype);
            viewHolder.imgtypepro=(ImageView)view.findViewById(R.id.imgviewtypepro);
            view.setTag(viewHolder);
        }else{
                viewHolder = (ViewHolder) view.getTag();
                TypeProduct tyepro= (TypeProduct) getItem(i);
                viewHolder.txttypepro.setText(tyepro.getName());
                Picasso.with(context).load(tyepro.getImage())
                   .placeholder(R.drawable.noimg)
                    .error(R.drawable.error)
                    .into(viewHolder.imgtypepro);

        }
        TypeProduct loaisp=(TypeProduct) getItem(i);
        viewHolder.txttypepro.setText(loaisp.getName());
        Picasso.with(context).load(loaisp.getImage()).placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.imgtypepro);
        return view;
    }
}
