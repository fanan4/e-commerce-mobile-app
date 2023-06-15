package com.example.e_commerce_app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Database(entities = {Product.class,Address.class,Order.class},version = 8)
public abstract class DB_E_COMMERCE extends RoomDatabase {
    public final static  String DB_NAME="e_commerce_db";
    private static  DB_E_COMMERCE db_e_commerce;
    public  abstract Product_DAO getProduct_DAO();
    public static synchronized DB_E_COMMERCE getInstance(Context context){
        if(db_e_commerce==null) {
             db_e_commerce= Room.databaseBuilder(context,DB_E_COMMERCE.class,DB_NAME)
                     .fallbackToDestructiveMigration()
                     .addCallback(callback)
                     .allowMainThreadQueries()
                     .build();
        }
        return db_e_commerce;
    }
         static RoomDatabase.Callback callback=new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                new asyncTask(db_e_commerce).execute();
                super.onCreate(db);
            }
        };
    public static class  asyncTask extends AsyncTask<Void,Void,Void>{
        public Product_DAO product_dao;

        public asyncTask(DB_E_COMMERCE db_e_commerce) {
            product_dao = db_e_commerce.getProduct_DAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
             ArrayList<Review> reviewss=new ArrayList<>();
             reviewss.add(new Review(1,"it is a greate product","12-10-2022"));
             product_dao.addProduct(new Product("cafe","greate product","https://img.freepik.com/photos-gratuite/tasse-cafe-haricots-fond-blanc_1232-2000.jpg?w=2000",12,30,reviewss,"Food",false));
             product_dao.addProduct(new Product("Meiji","greate product","https://www.meiji.com/global/food/common/img/food_top_country_tab/Singapore/singapore_01.jpg",12,30,reviewss,"Food",false));
             product_dao.addProduct(new Product("sode","greate product","https://cdn4.vectorstock.com/i/1000x1000/49/28/fresh-soda-vector-4844928.jpg",12,30,reviewss,"Drink",false));
             product_dao.addProduct(new Product("sucre","greate product","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwhOaMYGnfyOOhN9BQbOITVhT_J5JbmbBYpA&usqp=CAU",12,30,reviewss,"Cleanser",false));
             product_dao.addProduct(new Product("Red Bull","greate product","https://cpimg.tistatic.com/06164971/b/4/ORIGINAL-Red-Bull-250-Ml-Energy-Drink-Red-Bull-250-Ml-Energy-Drink-Fresh-Stock-Wholesale-Redbull-For-Sale.jpg",20,45,reviewss,"Nuts",false));
            return null;
        }
    }
}
