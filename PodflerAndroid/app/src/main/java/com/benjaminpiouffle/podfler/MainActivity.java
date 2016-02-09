package com.benjaminpiouffle.podfler;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private PlantWatchersManager plantWatchersManager;
    private String plantAddedMessage = null;
    private PlantWatcherAdapter plantWatcherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init plants
        this.plantWatchersManager = new PlantWatchersManager(this);

        // Init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Init left drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Add plants to menu
        buildDrawerMenu();

        if (this.plantWatchersManager.getList().size() > 0)
            this.renderPlantWatcher(this.plantWatchersManager.getList().get(0));
    }

    private void buildDrawerMenu() {
        ListView drawerList = (ListView) findViewById(R.id.nav_plants_list);
        this.plantWatcherAdapter = new PlantWatcherAdapter(this, this.plantWatchersManager.getArray());
        drawerList.setOnItemClickListener((parent, view, position, id) -> {
            ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
            if (this.plantWatchersManager.getList().size() > 0)
                this.renderPlantWatcher(this.plantWatchersManager.getList().get(position));
            this.plantWatcherAdapter.setSelected(position);
            drawerList.setAdapter(this.plantWatcherAdapter);
        });
        drawerList.setAdapter(this.plantWatcherAdapter);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        return false;
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        this.closeDrawer(GravityCompat.START);
//        drawerList.setSelection(0);

//        return super.onNavigationItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_add_plant:
                Intent intent = new Intent(this, AddPlantActivity.class);
                this.startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle addPlantForm = data.getExtras();
                String plantName = addPlantForm.getString("plantName");
                this.plantWatchersManager.addPlant(plantName, addPlantForm.getString("plantIP"));
                this.plantAddedMessage = String.format("Plant %s successfully added", plantName);
            }
            if (resultCode == RESULT_CANCELED) {
                this.plantAddedMessage = "Error : Can't add this plant";
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (this.plantAddedMessage != null) {
            Toast.makeText(this, this.plantAddedMessage, Toast.LENGTH_SHORT).show();
            this.plantAddedMessage = null;
            buildDrawerMenu();
        }
    }
}
