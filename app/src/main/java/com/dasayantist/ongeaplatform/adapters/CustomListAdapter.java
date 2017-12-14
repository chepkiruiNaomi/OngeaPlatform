package com.dasayantist.ongeaplatform.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.dasayantist.ongeaplatform.R;

import java.util.ArrayList;

import com.dasayantist.ongeaplatform.models.Counsellor;


public class CustomListAdapter extends BaseAdapter {


    Context mContext;
    ArrayList<Counsellor> temporaryArray;
    ArrayList<Counsellor> permanentArray;

    public CustomListAdapter(Context context, ArrayList<Counsellor> data) {
        this.mContext = context;
        this.temporaryArray = data;
        this.permanentArray = new ArrayList<>();
        this.permanentArray.addAll(data);
    }

    public void refresh(ArrayList<Counsellor> data){
        permanentArray.clear();
        permanentArray.addAll(data);
    }
    @Override
    public int getCount() {
        return temporaryArray.size();// # of items in your arraylist
    }

    @Override
    public Object getItem(int position) {
        return temporaryArray.get(position);// get the actual movie
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemNames = (TextView) convertView.findViewById(R.id.itemNames);
            viewHolder.itemPhone = (TextView) convertView.findViewById(R.id.itemPhome);
            viewHolder.itemLocation = (TextView) convertView.findViewById(R.id.itemLocation);
            viewHolder.itemSpec = (TextView) convertView.findViewById(R.id.itemSpec);
            viewHolder.imgCall = (ImageView) convertView.findViewById(R.id.imgCall);
            viewHolder.imgSMS = (ImageView) convertView.findViewById(R.id.imgSMS);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Counsellor counsellor = temporaryArray.get(position);
        viewHolder.itemNames.setText(counsellor.getName());
        viewHolder.itemPhone.setText(counsellor.getPhone());
        viewHolder.itemLocation.setText(counsellor.getLocation());
        viewHolder.itemSpec.setText(counsellor.getArea());

        viewHolder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
                dialog.setMessage("Do you really want to call?");
                dialog.setTitle("Call?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phone = counsellor.getPhone();
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        mContext.startActivity(intent);
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        viewHolder.imgSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = counsellor.getPhone();
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null)));
            }
        });
        return convertView;
    }

    public void filter(String text){
        text=text.toLowerCase();
        temporaryArray.clear();

        if(text.trim().length()==0) {
            temporaryArray.addAll(permanentArray);
        }
        else {
            Log.d("SEARCH", "perma_array: "+permanentArray.size());
            for (Counsellor p:permanentArray) {//p.getName().toLowerCase().contains(text)||
                if(p.getLocation().toLowerCase().contains(text) ||
                        p.getArea().toLowerCase().contains(text) ) {
                    temporaryArray.add(p);
                }
            }
            Log.d("SEARCH","COUNT  "+temporaryArray.size());
        }
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView itemNames;
        TextView itemPhone;
        TextView itemLocation;
        TextView itemSpec;
        ImageView imgCall;
        ImageView imgSMS;

    }
}
