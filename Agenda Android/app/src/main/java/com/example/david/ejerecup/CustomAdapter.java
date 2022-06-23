package com.example.david.ejerecup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    protected ArrayList<Contacto> conList;
    private Context context;


    public CustomAdapter(ArrayList<Contacto> list, Context cont)
    {
        this.conList = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.conList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.conList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;


        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.elemento, null);

            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.no_cargar_v);
            holder.phone = (TextView)convertView.findViewById(R.id.ph_cargar_v);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Contacto cnt = conList.get(position);
        holder.name.setText(cnt.getNombre());
        holder.phone.setText(cnt.getTelefono());

        return convertView;
    }

    private static class ViewHolder{

        public TextView name;
        public TextView phone;

    }




}
