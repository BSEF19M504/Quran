package com.example.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class StartingActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        String [] lang = {"English","Urdu"};
        String [] engNames = {"Dr Mohsin Khan","Mufti Taqi Usmani"};
        String [] urduNames = {"Fateh Muhammad Jalandhri","Mehmood-ul-Hassan"};

        Spinner spinner1 = (Spinner) navigationView.getMenu().findItem(R.id.translate_lang).getActionView();
        Spinner spinner2 = (Spinner) navigationView.getMenu().findItem(R.id.translate_author).getActionView();
        spinner1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, lang));
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                    spinner2.setAdapter(new ArrayAdapter<String>(StartingActivity.this,android.R.layout.simple_spinner_dropdown_item, engNames));
                else if(position == 1)
                    spinner2.setAdapter(new ArrayAdapter<String>(StartingActivity.this,android.R.layout.simple_spinner_dropdown_item, urduNames));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Intent intent;
                String translate = Integer.toString(spinner2.getSelectedItemPosition()) + spinner1.getSelectedItemPosition();
                int num = Integer.parseInt(translate,2);
                switch (menuItem.getItemId())
                {
                    case R.id.surah_list:
                        intent = new Intent(StartingActivity.this, MainActivity.class);
                        intent.putExtra("key","Surah");
                        intent.putExtra("translate",num);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.parah_list:
                        intent = new Intent(StartingActivity.this, MainActivity.class);
                        intent.putExtra("key","Parah");
                        intent.putExtra("translate",num);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.surah_info:

                        //Toast.makeText(StartingActivity.this, Integer.toString(num), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(),"Laptop is clicked",Toast.LENGTH_LONG).show();
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
            Toast.makeText(getApplicationContext(),"Start", Toast.LENGTH_LONG).show();

        }
        else
        {
            super.onBackPressed();
            Toast.makeText(getApplicationContext(),"End",Toast.LENGTH_LONG).show();
        }
    }
}