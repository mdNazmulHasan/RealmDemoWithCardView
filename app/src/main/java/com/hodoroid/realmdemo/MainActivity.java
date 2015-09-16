package com.hodoroid.realmdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import android.support.v7.widget.CardView;


public class MainActivity extends Activity {

    ArrayList<Person> persons = new ArrayList<>();
    CardView mCardView;
    /*private MyListAdapter mAdapter;*/
    private RecyclerView rv;
    RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.rv);
        loadData();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Person p = persons.get(position);
                        Intent intent = new Intent(MainActivity.this, EditActivity.class);
                        intent.putExtra("person", p.getId());
                        startActivity(intent);
                    }
                })
        );

    }

    public void loadData() {
        Realm realm = Realm.getInstance(this);
        RealmResults<Person> query = realm.where(Person.class)
                .findAll();
        for (Person p : query) {
            persons.add(p);
        }
    }

    public void add(View view) {
        Realm realm = Realm.getInstance(getApplicationContext());
        realm.beginTransaction();
        Person p = realm.createObject(Person.class);
        p.setCity("New City");
        p.setName("New Guy");
        p.setId(UUID.randomUUID().toString());
        realm.commitTransaction();
        persons.add(p);
        adapter.notifyDataSetChanged();
    }
}
