package com.example.catbreeds.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catbreeds.MainActivity;
import com.example.catbreeds.adapter.BreedAdapter;
import com.example.catbreeds.adapter.FavAdapter;
import com.example.catbreeds.model.BreedList;
import com.google.gson.Gson;
import com.example.catbreeds.R;


public class FavoriteRecyclerFragment extends Fragment {


    private RecyclerView recyclerView;

    public FavoriteRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        FavAdapter favAdapter = new FavAdapter();

        // This part is converting the JSON string into a TopStoriesResponse object, because we
        // have written the TopStoriesResponse class to match the structure of the JSON.
        // Within the TopStoriesResponse object ("topStoriesResponse"), it has the field "results",
        // which is an ArrayList<Article>.
        //
        // Because my Article class is also written to match how one article is represented in the
        // JSON, Gson will also automatically populate the "results" ArrayList with Article objects
        // using the data from the JSON.
        //
        // Thus, when I access topStoriesResponse.results, I get an ArrayList of Articles.
        // I can then give this to my recyclerView adapter.

        /*Gson gson = new Gson();
        String jsonString = FakeAPI.getMostViewedStoriesJsonString();
        BreedList breedList = gson.fromJson(jsonString, BreedList.class);
        FavAdapter.setData(breedList.results);

        // We have reworked FakeDatabase to act as a place to store these Articles, such that we
        // can access them via their ID. This will allow our intents to the DetailView to keep
        // functioning.
        FakeDatabase.saveArticlesToFakeDatabase(BreedList.results);

        recyclerView.setAdapter(FavAdapter);

        return view;
    }

    // This is just an example of a way that the Fragment can communicate with the parent Activity.
    // Specifically, this is using a method belonging to the parent.

             @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
        parent.showCoolMessage("cool (from BreedRecyclerFragment onResume)");
    }

         */

        return view;
    }

}


