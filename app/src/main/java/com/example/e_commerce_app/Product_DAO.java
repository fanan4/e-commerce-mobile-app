package com.example.e_commerce_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface Product_DAO {
     @Insert
     void addProduct(Product product);

     @Query("SELECT * FROM product")
     LiveData<List<Product>> getAllProduct();

     @Query("SELECT * FROM product")
     List<Product> getAllProducts();

     @Query("DELETE  FROM product")
     void deleteProduct();
     @Update
     void updateProduct(Product product);
     @Query("SELECT * FROM product WHERE id=:productId")
     Product getProductById(int productId);
     @Query("SELECT * FROM product WHERE Categorie=:categorie")
     List<Product> getProductByCategorie(String categorie);
     @Query("SELECT * FROM product WHERE name Like '%' || :name || '%'")
     List<Product> getSimilarProduct(String name);
    @Query("SELECT * from product WHERE cart=:cart")
     List<Product> getCartProduct(boolean cart);
    @Query("SELECT id FROM product WHERE cart=:cart")
     List<Integer> getProductsID(boolean cart);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     long addAdress(Address address);
    @Query("SELECT * FROM Address WHERE id=:id")
    Address getAdressById(int id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addOrder(Order order);
    @Query("SELECT * FROM `Order` WHERE id=:id")
    Order getOrderById(int id);

}
