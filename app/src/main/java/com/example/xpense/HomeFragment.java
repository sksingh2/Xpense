package com.example.xpense;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    DrawerLayout drawerLayout;
    BarChart barChart;
    PieChart pieChart;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Toolbar settings
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbarHome);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout,toolbar,R.string.nav_open
                ,R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
*/
        //PieChart settings
        pieChart = (PieChart)view.findViewById(R.id.PieChart);

        ArrayList<PieEntry> monthlyExpenses = new ArrayList();
        monthlyExpenses.add(new PieEntry(2000,"January"));
        monthlyExpenses.add(new PieEntry(2500,"February"));
        monthlyExpenses.add(new PieEntry(3000,"March"));
        monthlyExpenses.add(new PieEntry(2300,"April"));
        monthlyExpenses.add(new PieEntry(2600,"May"));
        monthlyExpenses.add(new PieEntry(1800,"June"));
        monthlyExpenses.add(new PieEntry(2200,"July"));

        PieDataSet pieDataSet = new PieDataSet(monthlyExpenses,"Monthly Expense");

        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Monthly Expenses");
        pieChart.animateXY(2000,2000);

        //BarChart settings
        barChart = (BarChart)view.findViewById(R.id.BarChart);

        ArrayList<BarEntry> expense = new ArrayList();
        expense.add(new BarEntry(1,220));
        expense.add(new BarEntry(2,180));
        expense.add(new BarEntry(3,150));
        expense.add(new BarEntry(4,200));
        expense.add(new BarEntry(5,280));


        BarDataSet barDataSet = new BarDataSet(expense, "Weekly Expenses");

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Expenses Weekly Detail");
        barChart.animateY(2000);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        return view;

    }

    // code used instead of back press
   /* @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
               if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK)
               {
                   return true;
               }
               return false;
            }

        });
    }
    */

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu , menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile)
        {
            Intent direct_Profile = new Intent(getActivity(), Profile_Page.class );
            startActivity(direct_Profile);
        }
        if (id == R.id.settings)
        {
            Intent direct_Profile = new Intent(getActivity(), Settings.class );
            startActivity(direct_Profile);
        }
        if (id == R.id.about)
        {

        }
        return true;
    }
}