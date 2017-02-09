package com.example.dpran.ilovezappos;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
        ((EditText)findViewById(R.id.username)).setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        // Identifier of the action. This will be either the identifier you supplied,
                        // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
                        if (actionId == EditorInfo.IME_ACTION_SEARCH
                                || actionId == EditorInfo.IME_ACTION_DONE
                                || event.getAction() == KeyEvent.ACTION_DOWN
                                && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            viewModel.setImageView(view);
                            viewModel.setPb(true);
                            model.getUser(binding.username.getText().toString());
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            return true;
                        }
                        // Return true if you have consumed the action, else false.
                        return false;
                    }
                });
    }

}
