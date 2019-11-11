package com.example.catbreeds;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.catbreeds.fragment.BreedRecyclerFragment;
import com.example.catbreeds.fragment.FavoriteRecyclerFragment;
import com.example.catbreeds.R;
import com.example.catbreeds.fragment.BreedRecyclerFragment;
import com.example.catbreeds.fragment.FavoriteRecyclerFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

// Notice the Activity implements the interface OnFragmentInteractionListener, meaning this Activity
// MUST have the method defined by the interface (see bottom), and if it does then this Activity
// can be considered an OnFragmentInteractionListener; it listens for Fragment interaction.
// This is only relevant to ProfileFragment in this app (because ArticleRecyclerFragment has nothing
// to listen to).
public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // I want there to be a Fragment in the slot from the start
        Fragment fragment = new BreedRecyclerFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // menuItem = the item on the bottom nav view that was selected
                // The id's here belong to the items in the menu of the BottomnNavigationView
                // The menu is chunked out as bottom_nav_menu.xml in the res > menu folder
                if (menuItem.getItemId() == R.id.nav_search) {
                    Fragment fragment = new BreedRecyclerFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_favorites) {
                    Fragment fragment = new FavoriteRecyclerFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });


    }

    /**
     * Helper method to change the fragment displayed in the activity. We put this here so we don't
     * have to repeat the code every time we want to saw
     * @param fragment: instance of the fragment to go into the slot
     */
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        System.out.println("Fragment changed");
        fragmentTransaction.commit();
    }
}