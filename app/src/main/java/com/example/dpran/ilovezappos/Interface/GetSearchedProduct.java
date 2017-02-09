package com.example.dpran.ilovezappos.Interface;

/**
 * Created by dpran on 2/7/2017.
 */

import com.example.dpran.ilovezappos.PojoClasses.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GetSearchedProduct {
    @GET("Search?key=b743e26728e16b81da139182bb2094357c31d331")
    Call<Product> getUser(@Query("term") String username);
}
