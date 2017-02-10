package com.example.dpran.ilovezappos;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dpran.ilovezappos.R;
import com.example.dpran.ilovezappos.databinding.ActivityMainBinding;
import com.example.dpran.ilovezappos.Model.ZapposModel;
import com.example.dpran.ilovezappos.ViewModel.MainViewModel;

import static android.graphics.Color.parseColor;
import static com.example.dpran.ilovezappos.R.color.full_cart;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ZapposModel model;
    private boolean isaddedtocart;
    ImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(viewModel = new MainViewModel());
        model = new ZapposModel(viewModel);
        view = (ImageView) this.findViewById(R.id.product_details_carousel);
        viewModel.setAddedtocart(false);
        isaddedtocart = false;
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
       binding.AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.AddToCart);
                v.startAnimation(animAlpha);
                if(isaddedtocart == false)
                {
                    Toast.makeText(getApplicationContext(), "Wohoo!! Added to Cart", Toast.LENGTH_SHORT).show();
                    viewModel.setAddedtocart(true);
                    isaddedtocart = true;
                    fab.setBackgroundTintList(ColorStateList.valueOf(parseColor("#ffff4444")));
                    vibe.vibrate(100);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Removed from Cart :(", Toast.LENGTH_SHORT).show();
                    viewModel.setAddedtocart(false);
                    isaddedtocart = false;
                    fab.setBackgroundTintList(ColorStateList.valueOf(parseColor("#ffd6d7d7")));
                }
            }
        });
        TextView zapposlink = (TextView) findViewById(R.id.product_URL);
        zapposlink.setMovementMethod(LinkMovementMethod.getInstance());
        zapposlink.setClickable(true);
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

        ((EditText)findViewById(R.id.username)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        findViewById(R.id.mainLayout).requestFocus();

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
