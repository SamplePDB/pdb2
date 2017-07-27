package com.reachuson.app.pdb2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.reachuson.app.pdb2.R.layout.activity_retail;

/**
 * Created by root on 27/7/17.
 */

public class list_item extends ArrayAdapter<String> {
    private Activity context;
    private List<String> result;

    public list_item(Activity context, List<String> result){
        super(context,activity_retail,result);
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inf = context.getLayoutInflater();
        View viewlistitem = inf.inflate(R.layout.list_item,null,true) ;
        TextView brand = viewlistitem.findViewById(R.id.label);
        TextView name = viewlistitem.findViewById(R.id.name);
        TextView mrp = viewlistitem.findViewById(R.id.mrp);
        TextView price = viewlistitem.findViewById(R.id.price);
        String res = result.get(position);
        brand.setText(res);
        //name.setText(res.getName());
        //mrp.setText(res.getMrp());
       // price.setText(res.getprice());
        return viewlistitem;
    }
}
