package com.example.catbreeds.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catbreeds.Database;
import com.example.catbreeds.MainActivity;
import com.example.catbreeds.adapter.BreedAdapter;
import com.example.catbreeds.model.BreedList;
import com.google.gson.Gson;
import com.example.catbreeds.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.example.catbreeds.Database.getCatById;


public class BreedRecyclerFragment extends Fragment  {

    public static List<BreedList> breedLists = new ArrayList<BreedList>();

    private RecyclerView recyclerView;
    private final BreedAdapter breedAdapter =new BreedAdapter();

    private SearchView searchView;
    private String query;


    public BreedRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_breed_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        getCatList();

        Thread thread = new Thread(){
            @Override
            public void run (){
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("Thread is running\n Current list size: " + breedLists.size());
                                breedAdapter.setData(breedLists);
                                recyclerView.setAdapter(breedAdapter);
                            }
                        });
                    }
                }catch (Exception e){
                    e. printStackTrace();
                }
            }
        };

        thread.start();

        searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }

    public void getCatList(){
        // final String url = "http://api.thecatapi.com/v1/breeds?api_key=ed8ed6e6-1f2b-4f2e-b488-9751478c3d0a";
        final String url = "https://api.thecatapi.com/v1/images/search?breed_ids=beng";

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                BreedList[] objectArray = gson.fromJson(response, BreedList[].class);
                breedLists = Arrays.asList(objectArray);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request error message: " + error);
                Toast.makeText(getContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    /*
    public void getCatId(String query) {
        Database.catSearch.clear();
        final String url = "https://api.thecatapi.com/v1/breeds/search?q=" + query;

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        getCatById(id);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void getCatById(String catId) {
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + catId;
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { Database.addCatSearch(response);
            requestQueue.stop();
            }
        };


                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        requestQueue.stop();
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
                requestQueue.add(stringRequest);


            }

     */
}
