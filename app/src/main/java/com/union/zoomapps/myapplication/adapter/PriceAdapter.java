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
import com.union.zoomapps.myapplication.mvp.models.CityModel;
import com.union.zoomapps.myapplication.mvp.models.ModelYandex;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 02.03.2018.
 */

public class PriceAdapter  extends RecyclerView.Adapter<PriceAdapter.ViewHolder> {
    private ModelYandex modelYandexes;
    private Context context;

    public PriceAdapter(ModelYandex modelYandexes, Context context) {
        this.modelYandexes = modelYandexes;
        this.context = context;
    }

    @Override
    public PriceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_list, parent, false);
        return new PriceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PriceAdapter.ViewHolder holder, int position) {
        holder.priceTaxi.setText(modelYandexes.getServiceLevels().get(0).getPrice().toString());
        holder.nameTaxtPrice.setText("yandex");
        holder.price_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.yandex));
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
