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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    ListView prof_list;
    String [] list_item = {"Bills", "Cosmetic", "Gifts", "Grocies", "Medicine", "Party", "Payback", "Project", "Rent", "Repair",
            "Snacks", "Stationary", "Subscription"};
    ArrayAdapter converter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment



        prof_list = (ListView) view.findViewById(R.id.prof_list);
        converter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, list_item);
        prof_list.setAdapter(converter);


        //toolbar settings
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbarPersonal);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_personal , menu);
    }


    Fragment fragment;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings)
        {
            fragment = new Settings_fragment();
        }
        if (id == R.id.about)
        {

            fragment = new About_fragment();
        }
        if (id == R.id.search_p)
        {
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setQueryHint("Type here to search");

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    converter.getFilter().filter(newText);
                    return false;
                }
            });
        }
        if (id == R.id.add_p)
        {
            Toast.makeText(getActivity(), "This is add message", Toast.LENGTH_SHORT).show();
        }

        getParentFragmentManager().beginTransaction().replace(R.id.containerNavPersonal,fragment).commit();
        return true;
    }
}