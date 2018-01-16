package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HARNY on 9/27/2017.
 */

public class WordAdapter  extends ArrayAdapter<Word> {
    private int mcolors;

    public WordAdapter(Activity context, int colorRescourceId, ArrayList<Word> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, colorRescourceId, words);
        mcolors = colorRescourceId;


    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }

        Word currentWord = getItem(position);
        TextView listview = (TextView)listItemView.findViewById(R.id.lutti);
        listview.setText(currentWord.getmMiwokTranslation());


        TextView englishWord = (TextView)listItemView.findViewById(R.id.one);
        englishWord.setText(currentWord.getmDefaultTranslation());




        ImageView avatar = (ImageView)listItemView.findViewById(R.id.avatar);

        if(currentWord.hasImage()== true){
            avatar.setImageResource(currentWord.getmResourceId());

        } else {avatar.setVisibility(View.GONE);}

        //how to change each background color of the activity

        View container = listItemView.findViewById(R.id.textContainer);
        int color  = ContextCompat.getColor(getContext(),mcolors);
        container.setBackgroundColor(color);

        return listItemView;
        //return super.getView(position, convertView, parent);


    }
}


