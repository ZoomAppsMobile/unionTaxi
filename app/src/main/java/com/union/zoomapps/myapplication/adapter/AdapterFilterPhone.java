package com.union.zoomapps.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.di.modules.UnionFilterModule;
import com.union.zoomapps.myapplication.mvp.models.UnionFiterModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.CamcorderProfile.get;

/**
 * Created by Asus on 01.03.2018.
 */

public class AdapterFilterPhone extends RecyclerView.Adapter<AdapterFilterPhone.ViewHolder> {

    List<UnionFiterModel> unionFiterModels;

    public AdapterFilterPhone(List<UnionFiterModel> unionFiterModels) {
        this.unionFiterModels = unionFiterModels;
    }

    @Override
    public AdapterFilterPhone.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_filter_phone, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterFilterPhone.ViewHolder holder, int position) {
        holder.nameTaxi.setText(unionFiterModels.get(position).getAlias());
        holder.numbers_taxi.setText(unionFiterModels.get(position).getPhone());
        holder.adres_taxi.setText(unionFiterModels.get(position).getAdress());
    }

    @Override
    public int getItemCount() {
        return unionFiterModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameTaxi)
        TextView nameTaxi;
        @BindView(R.id.numbers_taxi)
        TextView numbers_taxi;

        @BindView(R.id.adres_taxi)
        TextView adres_taxi;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
