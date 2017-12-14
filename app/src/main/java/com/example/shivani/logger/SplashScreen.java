package com.example.shivani.logger;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        textView = (TextView) findViewById(R.id.appText);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/BebasNeue.otf"));

        String styledText = "<font color='#1565c0'><b>Crazy</b></font> Logger";
        textView.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SplashScreen.this, textView, ViewCompat.getTransitionName(textView));
                startActivity(mainIntent, options.toBundle());
            }
        }, 3000);


    }
    
    
    
    
    
    
    
    
    
    
    
      /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(enterTransition());
            getWindow().setSharedElementReturnTransition(returnTransition());
        }*/ //add in onCreate()
  /*  private Transition enterTransition() {
        ChangeBounds bounds = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            bounds = new ChangeBounds();
            bounds.setDuration(2000);
        }
      

        return bounds;
    }

    private Transition returnTransition() {
        ChangeBounds bounds = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            bounds = new ChangeBounds();
            bounds.setInterpolator(new DecelerateInterpolator());
//            textView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
            bounds.setDuration(2000);
        }
      

        return bounds;
    }*/
}
