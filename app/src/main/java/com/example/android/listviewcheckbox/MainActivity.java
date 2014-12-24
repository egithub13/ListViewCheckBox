package com.example.android.listviewcheckbox;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new checkAdapter(this));
    }
}

class SingleRow{

    String product;
    String description;
    //CheckBox checkBox;
    SingleRow(String product,String description){
        this.product = product;
        this.description = description;
    }
}

class checkAdapter extends BaseAdapter{

    Context context;
    ArrayList<SingleRow> list;

    checkAdapter(Context c){

        context = c;
        list = new ArrayList<SingleRow>();

        Resources resources = c.getResources();
        String[]product = resources.getStringArray(R.array.product);
        String[]description = resources.getStringArray(R.array.description);
        for(int i=0;i<12;i++){
            list.add(new SingleRow(product[i],description[i]));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row,parent,false);
        final TextView product = (TextView) row.findViewById(R.id.textViewManufacturer);
        final TextView description = (TextView) row.findViewById(R.id.textViewDescription);
        CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkBox);

        SingleRow temp = list.get(position);
        product.setText(temp.product);
        description.setText(temp.description);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox temp = (CheckBox) v;
                String prod = product.getText().toString();
                String desc = description.getText().toString();
                if(temp.isChecked()){
                    temp.setText(R.string.checked);
                    Toast.makeText(context,"Added: "+ prod + "\n"+desc,Toast.LENGTH_SHORT).show();
                }
                else
                    temp.setText(R.string.unChecked);
            }
        });

        return row;
    }
}

