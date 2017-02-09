package com.example.dpran.ilovezappos;

import android.databinding.DataBindingUtil;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dpran.ilovezappos.R;
import com.example.dpran.ilovezappos.databinding.ActivityMainBinding;
import com.example.dpran.ilovezappos.Model.ZapposModel;
import com.example.dpran.ilovezappos.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ZapposModel model;
    ImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(viewModel = new MainViewModel());
        model = new ZapposModel(viewModel);
        view = (ImageView) this.findViewById(R.id.product_details_carousel);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ImageView img = (ImageView) v.findViewById(R.id.product_details_carousel);
                viewModel.setImageView(view);
                viewModel.setPb(true);
                model.getUser(binding.username.getText().toString());
            }
        });
    }

}
