package com.hodoroid.realmdemo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView name;
        TextView city;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardview);
            name = (TextView)itemView.findViewById(R.id.mMainLabel);
            city = (TextView)itemView.findViewById(R.id.mCityLabel);
        }
    }

    List<Person> persons;

    RVAdapter(List<Person> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_cell, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }
    public interface RecyclerViewClickListener
    {

        public void recyclerViewListClicked(View v, int position);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(persons.get(i).getName());
        personViewHolder.city.setText(persons.get(i).getCity());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
