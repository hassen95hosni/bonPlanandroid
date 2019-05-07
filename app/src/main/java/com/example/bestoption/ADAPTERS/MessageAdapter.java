package com.example.bestoption.ADAPTERS;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestoption.R;
import com.example.bestoption.entity.Message;
import com.example.bestoption.entity.Plans;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private List<Message> list ;

    public MessageAdapter(List<Message> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message,viewGroup,false);
        MessageAdapter.MyViewHolder mv = new MessageAdapter.MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.text.setText(list.get(i).getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView text ;
        public MyViewHolder(View v) {

            super(v);
            text = (TextView) v.findViewById(R.id.textView9);

        }

    }


}
