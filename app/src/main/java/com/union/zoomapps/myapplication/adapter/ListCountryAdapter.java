package com.union.zoomapps.myapplication.adapter;

import android.support.v7.util.DiffUtil;
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
import com.union.zoomapps.myapplication.mvp.models.CountryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 28.02.2018.
 */

public class ListCountryAdapter extends RecyclerView.Adapter<ListCountryAdapter.MyViewHolder> implements Filterable {
    private List<CountryModel> movieList;
    private List<CountryModel> movieListFiltered;
    //private Context context;


    public ListCountryAdapter(List<CountryModel> movieList) {
        this.movieList = movieList;
        if(this.movieList == null){
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            notifyItemChanged(0, movieListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ListCountryAdapter.this.movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ListCountryAdapter.this.movieList.get(oldItemPosition).getName() == movieList.get(newItemPosition).getName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    CountryModel cityModel = ListCountryAdapter.this.movieList.get(oldItemPosition);

                    CountryModel oldMovie = movieList.get(newItemPosition);

                    return cityModel.getName() == oldMovie.getName() ;
                }
            });
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ListCountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country ,parent,false);
        return new ListCountryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try{
            holder.name_country.setText(movieListFiltered.get(position).getName());
            holder.id_country.setText(movieListFiltered.get(position).getId() + "");
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
                    List<CountryModel> filteredList = new ArrayList<>();

                    for (CountryModel movie : movieList) {


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
                movieListFiltered = (ArrayList<CountryModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_country)
        TextView name_country;


        @BindView(R.id.id_country)
        TextView id_country;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

