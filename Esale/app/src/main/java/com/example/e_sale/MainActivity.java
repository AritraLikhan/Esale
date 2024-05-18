package com.example.e_sale;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.e_sale.ui.home.HomeFragment;
import com.example.e_sale.ui.login.LoginFragment;
import com.example.e_sale.ui.profile.ProfileFragment;
import com.example.e_sale.ui.reg.RegFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        drawerLayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // bottomNavigationView = findViewById(R.id.bottom_navigation);
    //    navController = Navigation.findNavController(this, R.id.main_fragment);
     //   NavigationUI.setupWithNavController(bottomNavigationView,navController);

        // Set the navigation item selected listener
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null)
        {
            // Profile fragment = Profile.newInstance(dataToSend);
            getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }



//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = findViewById(R.id.my_drawer);
//        NavigationView navigationview = findViewById(R.id.navigation_view);
//        navigationview.setNavigationItemSelectedListener(this);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(toggle.onOptionsItemSelected(item)) return true;

        return super.onOptionsItemSelected(item);
    }


 //   @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem loginItem = menu.findItem(R.id.login);
//        MenuItem logoutItem = menu.findItem(R.id.logout);
//
//        if (isUserLoggedIn()) { // Replace this with your actual check
//            // If user is logged in, hide login option and show logout option
//            loginItem.setVisible(false);
//            logoutItem.setVisible(true);
//        } else {
//            // If user is not logged in, show login option and hide logout option
//            loginItem.setVisible(true);
//            logoutItem.setVisible(false);
//        }
//
//        return super.onPrepareOptionsMenu(menu);
//    }


    private boolean isUserLoggedIn() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser != null;
    }


    @SuppressLint("NonConstantResourceId")

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.pdf) {
            Toast.makeText(this, "pdf", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://learntocodewith.me/posts/programming-books/"));
            startActivity(i);
        }
        else if (menuItem.getItemId() == R.id.cmp) {
//            Intent i=new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse("https://www.programiz.com/python-programming/online-compiler"));
//            startActivity(i);


            RegFragment fragment = RegFragment.newInstance("");
            getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer,fragment).commit();
        }else if (menuItem.getItemId() == R.id.home) {
            // Code to handle the "About" item selection
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            startActivity(intent);

            HomeFragment fragment = HomeFragment.newInstance("","");
            getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer,fragment).commit();

        }

        else if (menuItem.getItemId() == R.id.login) {
           LoginFragment fragment = LoginFragment.newInstance("","");
            getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer,fragment).commit();

        }

        else if (menuItem.getItemId() == R.id.pro) {

            if(FirebaseAuth.getInstance().getCurrentUser()!=null)
            {
                ProfileFragment fragment =  ProfileFragment.newInstance("","");
                getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer,fragment).commit();
            }
            else
            {
                LoginFragment fragment =  LoginFragment.newInstance("","");
                getSupportFragmentManager().beginTransaction().replace(R.id.idFragContainer,fragment).commit();
            }

        }

        else if (menuItem.getItemId() == R.id.exit) {
            Toast.makeText(this, "Exited App Successfully", Toast.LENGTH_SHORT).show();
            finishAffinity();
            return true;
        }
        drawerLayout.closeDrawers();
        return true;
    }

    public void replaceFragment(Fragment fragment) {
     getSupportFragmentManager().beginTransaction()
         .replace(R.id.idFragContainer, fragment)
         .commit();
 }


}

