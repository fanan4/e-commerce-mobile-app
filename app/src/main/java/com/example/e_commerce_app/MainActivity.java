package com.example.e_commerce_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     DrawerLayout drawer;
     MaterialToolbar toolbar;
     NavigationView navigationView;
     public  static  DB_E_COMMERCE myDB;
     public static final String CAT_LIST="CAT_LIST";
     public static final String CALL_ACTIVITY="CALL_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataBase();
        initView();

        //
        setSupportActionBar(toolbar);

        //create the toggle inside the toolbar

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_open,R.string.drawer_close);

        //make the toggle as the listenner for the navigation drawer

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content,new Main_Product_Content());
        transaction.commit();

        //create an onClick listenner for the nevigation drawer items

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.categorie:
                        System.out.println("ctegorie selected");
                        DialogCatList dialogCatList=new DialogCatList();
                        Bundle bundle=new Bundle();
                        bundle.putStringArrayList(CAT_LIST,getCategories());
                        bundle.putString(CALL_ACTIVITY,"Main_Activity");
                        dialogCatList.setArguments(bundle);
                        dialogCatList.show(getSupportFragmentManager(),"cat");
                        break;
                }
                return false;
            }
        });

        /*add the fragment to the main activity
        creating a transaction to handle the fragment*/





    }
    public void initView(){
         drawer=findViewById(R.id.drawer);
         toolbar=findViewById(R.id.tool_bar);
         navigationView=findViewById(R.id.navigation_drawer);

    }
    public void initDataBase(){
        myDB=DB_E_COMMERCE.getInstance(this);
    }
      public ArrayList<String> getCategories(){
          List<Product> products = myDB.getProduct_DAO().getAllProducts();
          ArrayList<String> categorieList=new ArrayList<>();

                  System.out.println("hello i on changeddeeeeeedddd");
                  if(null!=products){
                      for(Product p:products){
                          boolean doesExist=false;
                          for(String s: categorieList){
                              if(p.getCategorie().equals(s)){
                                  doesExist=true;
                                  break;
                              }
                          }
                          if(!doesExist){
                              categorieList.add(p.getCategorie());
                          }

                      }
                  }

            if( null!=categorieList ){
                System.out.println("categorie listttttttttttttttttttttt"+categorieList.toString());
                return categorieList;
            }
            return null;
    }
}