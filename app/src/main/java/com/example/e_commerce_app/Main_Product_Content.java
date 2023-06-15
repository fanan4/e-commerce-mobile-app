package com.example.e_commerce_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main_Product_Content extends Fragment {
    DB_E_COMMERCE MyDb;
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView,recyclerViewPopular,recyclerViewUserPoint,recyclerViewReview;
    Adapter_ProductList adapter_productList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_main__product__content,container,false);
          initView(view);
          initBottomNavView();

          MyDb=DB_E_COMMERCE.getInstance(getActivity());
          //MyDb.getProduct_DAO().deleteProduct();
          /*
          ArrayList<Review> reviewss=new ArrayList<>();
          reviewss.add(new Review(1,"it is a greate product","12-10-2022"));
          MyDb.getProduct_DAO().addProduct(new Product("cafe","greate product","https://img.freepik.com/photos-gratuite/tasse-cafe-haricots-fond-blanc_1232-2000.jpg?w=2000",12,30,reviewss,"Food",false));
          MyDb.getProduct_DAO().addProduct(new Product("Meiji","greate product","https://www.meiji.com/global/food/common/img/food_top_country_tab/Singapore/singapore_01.jpg",12,30,reviewss,"Food",false));
          MyDb.getProduct_DAO().addProduct(new Product("sode","greate product","https://cdn4.vectorstock.com/i/1000x1000/49/28/fresh-soda-vector-4844928.jpg",12,30,reviewss,"Drink",false));
          MyDb.getProduct_DAO().addProduct(new Product("sucre","greate product","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwhOaMYGnfyOOhN9BQbOITVhT_J5JbmbBYpA&usqp=CAU",12,30,reviewss,"Cleanser",false));
          MyDb.getProduct_DAO().addProduct(new Product("Red Bull","greate product","https://cpimg.tistatic.com/06164971/b/4/ORIGINAL-Red-Bull-250-Ml-Energy-Drink-Red-Bull-250-Ml-Energy-Drink-Fresh-Stock-Wholesale-Redbull-For-Sale.jpg",20,45,reviewss,"Nuts",false));*/

            LiveData<List<Product>>  products= MyDb.getProduct_DAO().getAllProduct();

           initRecyclerView(products);


         return view;
    }
    public void setAdapter(List<Product> products,RecyclerView recyclerView){
        adapter_productList.setProductsList(products);
        recyclerView.setAdapter(adapter_productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
    }
    public void initBottomNavView(){
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.searchIcon:
                        Intent intent=new Intent(getActivity(),Search_Activity.class);
                        getActivity().startActivity(intent);
                        break;
                    case R.id.cartbottom:
                        Intent intent1=new Intent(getActivity(),CartActivity.class);
                        getActivity().startActivity(intent1);
                        break;
                    default: break;
                }
                return false;
            }
        });
    }
    public void initRecyclerView(LiveData<List<Product>> products){
        //handle the recycler view for newest Items
        products.observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                /*for (Product p:products){
                    System.out.println(p.toString()+"\n");
                }*/
                comparatorNewItem(products);
                setAdapter(products,recyclerView);
                //handle the recylcer view for popular items
                comparatorPopular(products);
                setAdapter(products,recyclerViewPopular);
                //handle the recycler view for userPoint items
                compartorUserPoint(products);
                setAdapter(products,recyclerViewUserPoint);
            }
        });

    }
    public void initView(View view){
        bottomNavigationView=view.findViewById(R.id.bottomNavigation);
        recyclerView=view.findViewById(R.id.recyclNewItem);
        recyclerViewPopular=view.findViewById(R.id.recyclPopularItem);
        recyclerViewUserPoint=view.findViewById(R.id.userPoint);
        adapter_productList=new Adapter_ProductList(getActivity());
    }
    public void comparatorNewItem(List<Product> products){
        if(null!=products){
            //create a comparator to sort to new items
            Comparator<Product> newItemComparator=new Comparator<Product>() {
                @Override
                public int compare(Product product, Product t1) {
                    return product.getId() - t1.getId();
                }
            };
            Comparator<Product> reverseComparator=Collections.reverseOrder(newItemComparator);
            Collections.sort(products,reverseComparator);

        }
    }
    public void comparatorPopular(List<Product> products){
        Comparator<Product> comparatorPopular=new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return product.getPopularityPoint()- t1.getPopularityPoint();
            }
        };
        Comparator<Product> reverseComparator1=Collections.reverseOrder(comparatorPopular);
        Collections.sort(products,reverseComparator1);
    }
    public void compartorUserPoint(List<Product> products){
        Comparator<Product> comparatorUserPoint=new Comparator<Product>() {
            @Override
            public int compare(Product product, Product t1) {
                return product.getUserPoint()-t1.getUserPoint();
            }
        };
        Comparator<Product> reverseComparator3=Collections.reverseOrder(comparatorUserPoint);
        Collections.sort(products,reverseComparator3);
    }
}