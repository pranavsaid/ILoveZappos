package com.example.dpran.ilovezappos.Model;

/**
 * Created by dpran on 2/7/2017.
 */

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dpran.ilovezappos.R;
import com.example.dpran.ilovezappos.ViewModel.MainViewModel;
import com.example.dpran.ilovezappos.API.ZapposAdapter;
import com.example.dpran.ilovezappos.Interface.GetSearchedProduct;

import com.example.dpran.ilovezappos.PojoClasses.Product;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import android.content.Context;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZapposModel {

    private GetSearchedProduct Zappos;
    private MainViewModel viewModel;


    public ZapposModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
        this.Zappos =  ZapposAdapter.createService(GetSearchedProduct.class);

    }

    public void getUser(String username) {
        //binding.username.getText().toString()
        Call call = Zappos.getUser(username);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Response<Product> response) {
                Product model = response.body();

                if (model.getResults() == null) {
                    //404 or the response cannot be converted to User.

                        viewModel.setText("Your Search doesn't match with any product at Zappos");
                    viewModel.setPb(false);
                } else {

                    //200
                    viewModel.setText("" + model.getResults().get(0).getProductName());
                    viewModel.setPrice(model.getResults().get(0).getPrice());
                    viewModel.setDiscount("Discount :"+ model.getResults().get(0).getPercentOff());
                    viewModel.setImageURL((model.getResults().get(0).getThumbnailImageUrl()).replace("-t-THUMBNAIL","-p-MULTIVIEW"));
                    viewModel.setBrand(model.getResults().get(0).getBrandName());
                    viewModel.setProductURL("View this product on Zappos \n"+model.getResults().get(0).getProductUrl()+"");
                }
                //viewModel.setPb(false);
            }

            @Override
            public void onFailure(Throwable t) {
                viewModel.setText("t = " + t.getMessage());
                //viewModel.setPb(false);
            }
        });
    }

}
