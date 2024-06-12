package com.example.uddd_nhom14.entity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uddd_nhom14.R;

import java.util.ArrayList;

public class quanLyPhieuAdapter extends ArrayAdapter<itemListQLP> {
    Activity context;
    int idLayout;
    ArrayList<itemListQLP> myList;

    public quanLyPhieuAdapter( Activity context, int idLayout, ArrayList<itemListQLP> myList) {
        super(context, idLayout, myList);
        this.context = context;
        this.idLayout = idLayout;
        this.myList = myList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater myflacter = context.getLayoutInflater();
        convertView = myflacter.inflate(idLayout, null);
        itemListQLP myItem = myList.get(position);
        TextView stt = convertView.findViewById(R.id.txtSTT);
        stt.setText(myItem.getStt());
        TextView maPhieu = convertView.findViewById(R.id.txtMaPhieu);
        maPhieu.setText(myItem.getMaPhieu());
        TextView tenPhieu = convertView.findViewById(R.id.txtTenPhieu);
        tenPhieu.setText(myItem.getTenPhieu());
        TextView trangThai = convertView.findViewById(R.id.txtTrangThai);
        trangThai.setText(myItem.getTrangThai());

        //đặt màu cho trạng thái
        if (myItem.getTrangThai().equals("Đã duyệt")) {
            trangThai.setTextColor(context.getResources().getColor(R.color.approved_color));
        } else if (myItem.getTrangThai().equals("Không duyệt")) {
            trangThai.setTextColor(context.getResources().getColor(R.color.pending_color));
        } else {
            // Default text color for other statuses
            trangThai.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        // Đặt màu nền cho các dòng
        if (position == 0) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.first_row_color)); // Màu đỏ cho dòng đầu tiên
        } else if (position % 2 == 0) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.even_row_color)); // Màu vàng cho các dòng chẵn
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.odd_row_color)); // Màu xanh cho các dòng lẻ
        }
        return convertView;
    }
}
