package com.union.zoomapps.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.union.zoomapps.myapplication.R;
import com.union.zoomapps.myapplication.mvp.models.CityModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 01.03.2018.
 */

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> implements Filterable {

    private List<CityModel> movieList;
    private List<CityModel> movieListFiltered;
    //private Context context;


    public CityAdapter(List<CityModel> movieList) {
        this.movieList = movieList;
        if(this.movieList == null){
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            notifyItemChanged(0, movieListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return CityAdapter.this.movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return CityAdapter.this.movieList.get(oldItemPosition).getName() == movieList.get(newItemPosition).getName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    CityModel cityModel = CityAdapter.this.movieList.get(oldItemPosition);

                    CityModel oldMovie = movieList.get(newItemPosition);

                    return cityModel.getName() == oldMovie.getName() ;
                }
            });
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_city,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityAdapter.MyViewHolder holder, int position) {
        try{
            holder.name_city.setText(movieListFiltered.get(position).getName());
            holder.id_city.setText(movieListFiltered.get(position).getId() + "");
            holder.city_lat.setText(movieListFiltered.get(position).getLat().toString());
            holder.city_lot.setText(movieListFiltered.get(position).getLon().toString());
        }catch (Exception ex){
            Log.e("CityAdapter", "Вожможен null в onBindViewHolder");
        }

    }

    @Override
    public int getItemCount() {

        if(movieList != null){
            return movieListFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    movieListFiltered = movieList;
                } else {
                    List<CityModel> filteredList = new ArrayList<>();

                    for (CityModel movie : movieList) {


                      if (movie.getName().toLowerCase().contains(charString.toLowerCase())) {


                             filteredList.add(movie);
                        }

                    }
                    movieListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<CityModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_city)
        TextView name_city;

        @BindView(R.id.city_lat)
        TextView city_lat;

        @BindView(R.id.city_lot)
        TextView city_lot;

        @BindView(R.id.id_city)
        TextView id_city;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

//public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> implements Filterable {
//    private List<CityModel> cityModels;
//
//    public CityAdapter(List<CityModel> cityModels) {
//        this.cityModels = cityModels;
//    }
//
//    @Override
//    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_city, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
//        holder.name_city.setText(cityModels.get(position).getName());
//        holder.city_lat.setText(cityModels.get(position).getLat().toString());
//        holder.city_lot.setText(cityModels.get(position).getLon().toString());
//
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return cityModels.size();
//    }
//
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    cityModels = cityModels;
//                } else {
//                    List<CityModel> filteredList = new ArrayList<>();
//                    for (CityModel movie : cityModels) {
//                        if (movie.getName().toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(movie);
//                        }
//                    }
//                    cityModels = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = cityModels;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                cityModels = (ArrayList<CityModel>) filterResults.values;
//
//                notifyDataSetChanged();
//            }
//        };
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.name_city)
//        TextView name_city;
//
//        @BindView(R.id.city_lat)
//        TextView city_lat;
//
//        @BindView(R.id.city_lot)
//        TextView city_lot;
//
//        @BindView(R.id.id_city)
//        TextView ids_city;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
//}
