//package com.example.furniture_app.Database;
//
//import androidx.room.Embedded;
//import androidx.room.Relation;
//
//import java.util.List;
//
//public class OrderOfProducts {
//    @Embedded
//    private Order order;
//    @Relation(parentColumn = "orderId", entityColumn = "orderId", entity = Product.class)
//    private List<Product> listOfProducts;
//}
