package com.benjaminpiouffle.podfler;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private PlantWatchersManager plantWatchersManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Init left drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Add plants to menu
        this.plantWatchersManager = new PlantWatchersManager(this);
        ListView drawerList = (ListView) findViewById(R.id.nav_plants_list);
        drawerList.setAdapter(new PlantWatcherAdapter(this, this.plantWatchersManager.getArray()));

        this.renderPlantWatcher(this.plantWatchersManager.getList().get(0));
    }

    private void renderPlantWatcher(PlantWatcher plantWatcher) {
        ((TextView) findViewById(R.id.plant_name)).setText(plantWatcher.getName());
        ((TextView) findViewById(R.id.value_air_humidity)).setText(String.valueOf(plantWatcher.getAirHumidity()));
        ((TextView) findViewById(R.id.value_air_temperature)).setText(String.valueOf(plantWatcher.getAirTemperature()));
        ((TextView) findViewById(R.id.value_ground_humidity)).setText(String.valueOf(plantWatcher.getGroundHumidity()));
        ((TextView) findViewById(R.id.value_luminosity)).setText(String.valueOf(plantWatcher.getLuminosity()));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
