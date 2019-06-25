package com.example.bestoption.ADAPTERS;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestoption.R;
import com.example.bestoption.entity.Plans;
import com.example.bestoption.interfaces.OnItemClickListener;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Plans> list ;
    private OnItemClickListener onItemClickListener;
    public MyAdapter(List<Plans> lists,OnItemClickListener onItemClickListener) {
        list= lists;
        this.onItemClickListener = onItemClickListener;
    }
    private View.OnClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener,View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offre_info,viewGroup,false);
        MyViewHolder mv = new MyViewHolder(v,onItemClickListener);
        return mv;
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int i) {
myViewHolder.text.setText(list.get(i).getName());
myViewHolder.description.setText((list.get(i).getDescriptionCourt()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text ;
        public TextView description;
        OnItemClickListener onItemClickListener;
    public MyViewHolder(View v,OnItemClickListener onItemClickListener) {

        super(v);
        text = (TextView) v.findViewById(R.id.textView3);
        description = (TextView) v.findViewById(R.id.textView30);
        this.onItemClickListener=onItemClickListener;
        v.setOnClickListener(this);

    }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


}
