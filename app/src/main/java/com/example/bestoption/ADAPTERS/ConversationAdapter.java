package com.example.bestoption.ADAPTERS;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestoption.R;
import com.example.bestoption.entity.Conversation;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder> {

    private List<Conversation> list ;

    public ConversationAdapter(List<Conversation> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ConversationAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.conversation,viewGroup,false);
        ConversationAdapter.MyViewHolder mv = new ConversationAdapter.MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(ConversationAdapter.MyViewHolder myViewHolder, int i) {
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
            text = (TextView) v.findViewById(R.id.textView6);

        }

    }
}
