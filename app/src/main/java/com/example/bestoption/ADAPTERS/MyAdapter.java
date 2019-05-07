package com.example.bestoption.ADAPTERS;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestoption.R;
import com.example.bestoption.entity.Plans;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Plans> list ;
    public MyAdapter(List<Plans> lists) {
        list= lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offre_info,viewGroup,false);
        MyViewHolder mv = new MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int i) {
myViewHolder.text.setText(list.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView text ;
    public MyViewHolder(View v) {

        super(v);
        text = (TextView) v.findViewById(R.id.textView3);

    }

}


}
