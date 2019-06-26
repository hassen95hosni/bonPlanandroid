package com.example.bestoption.ADAPTERS;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bestoption.R;
import com.example.bestoption.design.customfonts.MyTextView;
import com.example.bestoption.entity.Message;
import com.example.bestoption.entity.Plans;
import com.example.bestoption.interfaces.OnItemClickListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private List<Message> list ;
    private OnItemClickListener onItemClickListener;

    public MessageAdapter(List<Message> list,OnItemClickListener onItemClickListener) {
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
    public MessageAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message,viewGroup,false);
        MessageAdapter.MyViewHolder mv = new MessageAdapter.MyViewHolder(v,onItemClickListener);
        return mv;
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MyViewHolder myViewHolder, int i) {
        if(list.get(i).getMe()){
            myViewHolder.textme.setText(list.get(i).getMessage());
            myViewHolder.textnotme.setText("");
            myViewHolder.notmebox.setVisibility(View.GONE);
            myViewHolder.mebox.setVisibility(View.VISIBLE);
            myViewHolder.image.setVisibility(View.GONE);
            myViewHolder.notmelayout.setVisibility(View.GONE);

        }
        else {
            myViewHolder.textnotme.setText(list.get(i).getMessage());
            myViewHolder.textme.setText("");
            myViewHolder.mebox.setVisibility(View.GONE);
            myViewHolder.notmebox.setVisibility(View.VISIBLE);
            myViewHolder.melayout.removeAllViews();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public MyTextView textnotme ;
        public MyTextView textme ;
        public LinearLayout notmebox ;
        public LinearLayout mebox ;
        public LinearLayout notmelayout ;
        public LinearLayout melayout ;

        public CircularImageView image;
        OnItemClickListener onItemClickListener;
        public MyViewHolder(View v,OnItemClickListener onItemClickListener) {

            super(v);
            image=(CircularImageView)v.findViewById(R.id.photo);
            textnotme = (MyTextView) v.findViewById(R.id.notme);
            textme = (MyTextView) v.findViewById(R.id.metext);
            notmebox = (LinearLayout) v.findViewById(R.id.notmebox);
            mebox = (LinearLayout) v.findViewById(R.id.mebox);
            notmelayout = (LinearLayout) v.findViewById(R.id.notmelayout);
            melayout = (LinearLayout) v.findViewById(R.id.melayout);
            this.onItemClickListener=onItemClickListener;
            v.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


}



