package com.union.zoomapps.myapplication.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.mvp.models.ModelYandex;
import com.union.zoomapps.myapplication.mvp.models.UberModel;
import com.union.zoomapps.myapplication.mvp.models.UberPrices;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 03.03.2018.
 */

public class UberAdapter  extends RecyclerView.Adapter<UberAdapter.ViewHolder> {
    private UberModel uberModel;
    private Context context;

    public UberAdapter(UberModel uberModel, Context context) {
        this.uberModel = uberModel;
        this.context = context;
    }

    @Override
    public UberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_list, parent, false);
        return new UberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UberAdapter.ViewHolder holder, int position) {
        UberPrices uberData = uberModel.getPrices().get(position);
//        uberModel.getPrices().get(0).getEstimate().toString()
        holder.priceTaxi.setText(  uberData.getHighEstimate() + uberData.getLowEstimate() / 2 + " " + uberData.getCurrencyCode().toString());
        holder.nameTaxtPrice.setText(uberModel.getPrices().get(position).getDisplayName());
        holder.price_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.uber_icon));

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.nameTaxtPrice)
        TextView nameTaxtPrice;

        @Nullable
        @BindView(R.id.priceTaxi)
        TextView priceTaxi;

        @BindView(R.id.price_icon)
        ImageView price_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
