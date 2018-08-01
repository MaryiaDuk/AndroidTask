package com.example.masha.androidapplication.Dz6.Person;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.masha.androidapplication.Dz3.CircleImageTransformation;
import com.example.masha.androidapplication.Dz6.Singleton;
import com.example.masha.androidapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Holder.CustomClickListener customClickListener;
    private List<Person> personList = Singleton.getState().getPeople().getPeople();
    private List<Person> filteredList = new ArrayList<>();
    private PersonFilter personFilter;
    private boolean flag = false;


    public PersonAdapter(Holder.CustomClickListener customClickListener) {
        this.customClickListener = customClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item, parent, false);

        return new Holder(view, customClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Person person;
        if (flag == false) {
            person = personList.get(position);
        } else {
            person = filteredList.get(position);
        }
        Holder itemHolder = (Holder) holder;

        itemHolder.name.setText(person.getName());
        itemHolder.surname.setText(person.getSurname());
        Picasso.get().load(person.getPhoto()).
                transform(new CircleImageTransformation(150)).into(itemHolder.userPhoto);
    }

    @Override
    public int getItemCount() {
        return flag == false ? personList.size() : filteredList.size();
    }

    @Override
    public Filter getFilter() {
        if (personFilter == null) {
            personFilter = new PersonFilter(this, personList);
        }
        return personFilter;
    }

    private class PersonFilter extends Filter {

        private PersonAdapter adapter;
        private List<Person> personList;
        private List<Person> filtredList;

        public PersonFilter(PersonAdapter adapter, List<Person> people) {
            this.adapter = adapter;
            this.personList = people;
            this.filtredList = new ArrayList<>();
            flag = true;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filtredList.clear();
            FilterResults filterResults = new FilterResults();
            if (constraint.length() == 0) {
                filtredList.addAll(personList);
            } else {
                String filterPattern = constraint.toString().trim();
                for (final Person person : personList) {
                    if (person.getName().contains(filterPattern)||
                            person.getSurname().toLowerCase().contains(filterPattern)) {
                        filtredList.add(person);
                    }
                }
            }
            filterResults.values = filtredList;
            filterResults.count = filtredList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filteredList.clear();
            adapter.filteredList.addAll((Collection<? extends Person>) results.values);
            adapter.notifyDataSetChanged();
        }
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name, surname;
        private ImageView userPhoto;
        private LinearLayout linearLayout;

        private CustomClickListener clickListener;

        public Holder(View itemView, CustomClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;

            name = itemView.findViewById(R.id.personName);
            surname = itemView.findViewById(R.id.personSurname);
            userPhoto = itemView.findViewById(R.id.personImage);
            linearLayout = itemView.findViewById(R.id.clickLayout);

            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onUserClickListener(getAdapterPosition());
            }
        }

        public interface CustomClickListener {
            void onUserClickListener(int position);
        }
    }

}
