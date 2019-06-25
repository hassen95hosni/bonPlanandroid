package com.example.bestoption.ADAPTERS;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestoption.R;
import com.example.bestoption.entity.Category;
import com.example.bestoption.entity.Plans;
import com.example.bestoption.interfaces.OnItemClickListener;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {


    private OnItemClickListener onItemClickListener;
    private List<Category> list ;

    public CategoriesAdapter(List<Category> list,OnItemClickListener onItemClickListener) {
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }
    private View.OnClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener,View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category,viewGroup,false);
        CategoriesAdapter.MyViewHolder mv = new CategoriesAdapter.MyViewHolder(v,onItemClickListener);
        return mv;
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.text.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public  static  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text ;
        OnItemClickListener onItemClickListener;
        public MyViewHolder(View v,OnItemClickListener onItemClickListener) {

            super(v);
            text = (TextView) v.findViewById(R.id.textView4);
            this.onItemClickListener=onItemClickListener;
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}
