package com.example.catbreeds;

import com.example.catbreeds.model.BreedList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class Database {

    public static HashMap<String, BreedList> catSearch = new HashMap<>();

    public static BreedList getCatById(String id){
        return catSearch.get(id);
    }

    public static void addCatSearch(String json){
    Gson gson =new Gson();
    Type listType = new TypeToken<List<BreedList>>(){}.getType();
    List <BreedList> catList  =gson.fromJson(json,listType);
    for (int i =0; i< catList.size(); i++){
        BreedList cat =catList.get(i);
        catSearch.put(cat.getBreeds().get(0).getId(),cat);
    }
}
}
