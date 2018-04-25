package com.example.georgewang.hw2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Item> myDataset = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            Item item = new Item();
            item.setCheck(false);
            item.setText(i + "");
            myDataset.add(item);
        }
        Hw2Adapter myAdapter = new Hw2Adapter(myDataset);
        RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
        RecyclerView.Adapter myAdapter = mList.getAdapter();
        Hw2Adapter mA = (Hw2Adapter) mList.getAdapter();
        List<Item> mData = mA.mData;
        String num = mA.getSelected();
        Toast.makeText(this, "You select "+num, Toast.LENGTH_SHORT).show();
        return true;
    }

    public class Hw2Adapter extends RecyclerView.Adapter<Hw2Adapter.ViewHolder> {
        private List<Item> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public CheckBox mCheckBox;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.info_text);
                mCheckBox = (CheckBox) v.findViewById(R.id.info_chcekbox);
            }
        }

        public Hw2Adapter(List<Item> data) {
            mData = data;
        }



        public String getSelected(){
            StringBuilder stringBuilder = new StringBuilder();
            for (Item number : mData) {
                if (number.isCheck()) {
                    if (stringBuilder.length() > 0)
                        stringBuilder.append(", ");
                    stringBuilder.append(number.getText());
                }
            }

            return stringBuilder.toString();
        }
        @Override
        public Hw2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Item item = mData.get(position);
            holder.mTextView.setText(item.getText());
            holder.mCheckBox.setChecked(item.isCheck());
            holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean b = ((CheckBox) view).isChecked();
                    holder.mCheckBox.setChecked(b);
                    mData.get(position).setCheck(b);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

    }
    private static class Item{
        String text;
        boolean check;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }
    }
}





