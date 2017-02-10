package com.example.dpran.ilovezappos.ViewModel;

/**
 * Created by dpran on 2/8/2017.
 */

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.dpran.ilovezappos.BR;
import com.example.dpran.ilovezappos.R;
import com.squareup.picasso.Picasso;

import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ImageView;

public class MainViewModel extends BaseObservable{

    private String text;
    private String brand;
    private String price;
    private String discount;
    private String imageURL;
    private String productURL;
    private String fabColor;
    private boolean pb;
    private boolean addedtocart;
    ImageView img;

    @Bindable
    public ImageView getImageView() {
        return img;
    }
    @Bindable
    public String getText() {
        return text;
    }

    @Bindable
    public String getBrand() {
        return brand;
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    @Bindable
    public String getDiscount() {
        return discount;
    }

    @Bindable
    public String getImageURL() {
        return imageURL;
    }

    @Bindable
    public String getFabColor() {
        return fabColor;
    }

    @Bindable
    public String getProductURL() {
        return productURL;
    }

    @Bindable
    public boolean isPb() {
        return pb;
    }

    @Bindable
    public boolean isAddedtocart() {
        return addedtocart;
    }

    public void setImageView(ImageView img) {
         this.img = img;
    }
    public void setPb(boolean pb) {
        this.pb = pb;
        notifyPropertyChanged(BR.pb);
    }

    public void setAddedtocart(boolean addedtocart) {
        this.addedtocart = addedtocart;
        notifyPropertyChanged(BR.addedtocart);
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
       }

    public void setBrand(String brand) {
        this.brand = brand;
        notifyPropertyChanged(BR.brand);
   }
    public void setFabColor(String fabColor) {
        this.fabColor = fabColor;
        notifyPropertyChanged(BR.fabColor);
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    public void setDiscount(String discount) {
        this.discount = discount;
        notifyPropertyChanged(BR.discount);
    }


    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        Picasso.with(getImageView().getContext()).load(imageURL).into(getImageView());
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
        notifyPropertyChanged(BR.productURL);
    }
}
