package com.example.dellpc.facts.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.dellpc.facts.R;
import com.example.dellpc.facts.model.FactsList;
import com.example.dellpc.facts.util.Global;

import java.util.List;


/**
 * Created by Balamithra on 2/11/2018.
 */

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.MyViewHolder> {

    LayoutInflater inflater;
    Context context;
    private List<FactsList> factsLists;
    public FactsAdapter(Context context,List<FactsList> factsLists) {
        this.context = context;
        this.factsLists=factsLists;
        this.inflater = LayoutInflater.from(this.context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FactsAdapter.MyViewHolder(this.inflater.inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        FactsList facts=factsLists.get(position);
        holder.tv_head_title.setText(facts.getTitle());
        holder.tv_sub_title.setText(facts.getDescription());
        try {
            if(facts.getImageHref()!=null) {
                Glide.with(this.context).load(facts.getImageHref()).asBitmap().override(150, 150) .fitCenter().into(new BitmapImageViewTarget(holder.img_pic) {
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(FactsAdapter.this.context.getResources(), resource);
                        circularBitmapDrawable.setCircular(false);
                        holder.img_pic.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }else
                Glide.clear(holder.img_pic);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return factsLists.size();
    }


    public class MyViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tv_head_title;
        TextView tv_sub_title;
        CardView cv_layout;
        ImageView img_pic;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.cv_layout=(CardView)itemView.findViewById(R.id.cv_layout);
            this.tv_head_title=(TextView)itemView.findViewById(R.id.head_title);
            this.tv_sub_title=(TextView)itemView.findViewById(R.id.sub_title);
            this.img_pic=(ImageView)itemView.findViewById(R.id.img_pic);
            this.cv_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Global.showToastShort(FactsAdapter.this.context, "Clicked");
        }

        @Override
        public boolean onLongClick(View view) {
            Global.showToastShort(FactsAdapter.this.context, "Long Click");
            return false;
        }
    }
}
