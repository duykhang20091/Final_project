package com.example.milkteamanager.Adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.milkteamanager.Activity.User;
import com.example.milkteamanager.R;
import com.example.milkteamanager.model.Member;

import java.util.List;

public class MemberAdapter extends BaseAdapter {
    private Context contex;
    private int layout;
    private List<Member>memberList;

    public MemberAdapter(Context contex, int layout, List<Member> memberList) {
        this.contex = contex;
        this.layout = layout;
        this.memberList = memberList;
    }
    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtID,txtUsername,txtDate,txtFull_name;
        ImageView imgEdit,imgDelete;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            holder.txtID=(TextView) view.findViewById(R.id.txtid_user);
            holder.txtUsername=(TextView)view.findViewById(R.id.txtusername);
            holder.txtFull_name=(TextView)view.findViewById(R.id.txtfullname);
            holder.txtDate=(TextView)view.findViewById(R.id.txtdate);
            holder.imgEdit=(ImageView)view.findViewById(R.id.imgviewedit);
            holder.imgDelete=(ImageView)view.findViewById(R.id.imgdelete);
            view.setTag(holder);
        }else{
            holder=(ViewHolder)view.getTag();
        }
        Member member =memberList.get(i);
        holder.txtID.setText(member.getUserid());
        holder.txtUsername.setText(member.getUsername());
        holder.txtFull_name.setText(member.getFullname());
        holder.txtDate.setText("Created date:"+ member.getCreated_date());

        return view;
    }
}