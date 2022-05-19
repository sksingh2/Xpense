package com.example.xpense;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendFragment extends Fragment {

    ListView listView;
    String[] names = {"Akash", "Meghana", "Sunitha", "Ramesh", "Owais","Shivam","Apoorva","Nandi","Durjoy",
    "Oskar","Chetan","Karan Raj","Mounjo"};
    ArrayAdapter arrayAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FriendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendFragment newInstance(String param1, String param2) {
        FriendFragment fragment = new FriendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);

        //Toolbar settings
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbarFriends);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        //SearchView list
        listView = (ListView) view.findViewById(R.id.friendsList);
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1 ,names);
        listView.setAdapter(arrayAdapter);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.inflater.inflate(R.menu.toolbar_menu , menu);layout.fragment_friend, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_friends , menu);
    }

    Fragment fragment;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.settings)
        {
            fragment = new Settings_fragment();
            getParentFragmentManager().beginTransaction().replace(R.id.containerNavFriend,fragment).commit();
        }
        if (id == R.id.about)
        {
            fragment = new About_fragment();
            getParentFragmentManager().beginTransaction().replace(R.id.containerNavFriend,fragment).commit();
        }
        if (id == R.id.search_f)
        {
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setQueryHint("Type here to search ");

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    arrayAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        }
        if (id == R.id.add_f)
        {
            Toast.makeText(getActivity(), "This is add message", Toast.LENGTH_SHORT).show();
        }


        return true;
    }
}