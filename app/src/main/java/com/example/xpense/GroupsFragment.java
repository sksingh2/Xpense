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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupsFragment extends Fragment {

    ListView listView;
    String[] groupName = {"Xpense","Legends","Andi Mandi Sandi","Pakodi"};
    ArrayAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GroupsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupsFragment newInstance(String param1, String param2) {
        GroupsFragment fragment = new GroupsFragment();
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
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        //Toolbar settings
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar_Groups);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        //Search View code settings
        listView = (ListView) view.findViewById(R.id.groupList);
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,groupName);
        listView.setAdapter(adapter);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.inflater.inflate(R.menu.toolbar_menu , menu);layout.fragment_friend, container, false);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_groups , menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings)
        {
            Intent direct_Profile = new Intent(getActivity(), Settings.class );
            startActivity(direct_Profile);
        }
        if (id == R.id.about)
        {
            Intent direct_Profile = new Intent(getActivity(), About_Page.class );
            startActivity(direct_Profile);
        }
        if (id == R.id.search_g)
        {
            SearchView searchview = (SearchView) item.getActionView();
            searchview.setQueryHint("Type here to Search");

            searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
        }
        if (id == R.id.add_g)
        {
            Toast.makeText(getActivity(), "This is add message", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}