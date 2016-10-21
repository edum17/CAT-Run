package com.pes.catrun;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class CursesActivity extends AppCompatActivity{

    class Cursa {
        String nom;
        String distancia;
        String data;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curses);
        //HttpClient httpClient = new HttpClient();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialup_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.materialup_viewpager);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                if(tab.getPosition() == 0) fab.setImageResource(R.drawable.close_envelope);
                if(tab.getPosition() == 1) fab.setImageResource(R.drawable.add);
            }
        });

        //String result="";
        //rellenaLista(result);
    }

    public void rellenaLista(String result)
    {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialup_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.materialup_viewpager);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                if(tab.getPosition() == 0) fab.setImageResource(R.drawable.close_envelope);
                if(tab.getPosition() == 1) fab.setImageResource(R.drawable.add);
            }
        });
    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, com.pes.catrun.CursesActivity.class));
    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return MaterialUpConceptFakePage.newInstance();
                case 1:
                    return MaterialUpConceptFakePage2.newInstance();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Curses oficials";
                case 1:
                    return "Curses custom";
            }
            return "";
        }
    }

}