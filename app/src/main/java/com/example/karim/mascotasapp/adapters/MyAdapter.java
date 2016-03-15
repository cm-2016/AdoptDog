package com.example.karim.mascotasapp.adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karim.mascotasapp.R;
import com.example.karim.mascotasapp.api.models.Dog;

import java.util.ArrayList;

/**
 * Created by Karim on 15/03/16.
 */
public class MyAdapter extends ArrayAdapter<Dog> {

    private final Context context;
    private final ArrayList<Dog> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Dog> itemsArrayList) {

        super(context, R.layout.rowlayout, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        // 3. Get the two text view from the rowView
        TextView lblName = (TextView) rowView.findViewById(R.id.name);
        TextView lblBreed = (TextView) rowView.findViewById(R.id.breed);
        ImageView img = (ImageView) rowView.findViewById(R.id.img);

        // 4. Set the text for textView
        lblName.setText(itemsArrayList.get(position).getName());
        lblBreed.setText(itemsArrayList.get(position).getBreed());
        img.setImageResource(R.drawable.dog);

        // 5. retrn rowView
        return rowView;
    }
}
