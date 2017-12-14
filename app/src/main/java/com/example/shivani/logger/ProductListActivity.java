package com.example.shivani.logger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Intent.ACTION_VIEW;

public class ProductListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int[] mResources = {R.drawable.img_one, R.drawable.img_two, R.drawable.img_four};
    private ViewPager viewPager;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 4000;
    private LinearLayout yourLayout;


    int[] group1 = {R.drawable.fb, R.drawable.gplus, R.drawable.twit, R.drawable.instagram, R.drawable.link,
            R.drawable.snapchat, R.drawable.reddit, R.drawable.pinterest, R.drawable.tumblr, R.drawable.viber,
            R.drawable.myspace, R.drawable.vimeo, R.drawable.flickr, R.drawable.vine, R.drawable.digg, R.drawable.vk};

    int[] group2 = {R.drawable.amazon, R.drawable.flipkart, R.drawable.ebay, R.drawable.snapdeal, R.drawable.shopclues};
    int[] group3 = {R.drawable.news, R.drawable.bbc, R.drawable.nyt, R.drawable.cnn, R.drawable.usatoday, R.drawable.huffington,
            R.drawable.toi, R.drawable.hindu, R.drawable.ht};
    int[] group4 = {R.drawable.paytm, R.drawable.paypal};
    int[] group5 = {R.drawable.wikipedia, R.drawable.blogger, R.drawable.git, R.drawable.wordpress, R.drawable.stackoverflow,
            R.drawable.quora, R.drawable.nasa, R.drawable.msn};
    int[] group6 = {R.drawable.youtube, R.drawable.netflix, R.drawable.rssfeed, R.drawable.skype, R.drawable.soundcloud, R.drawable.saavn,
            R.drawable.google, R.drawable.drive, R.drawable.dropbox};
    int[][] grp={group1,group2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textHeading = (TextView) findViewById(R.id.heading1);
        textHeading.setTypeface(Typeface.createFromAsset(getAssets(), "font/BebasNeue.otf"));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        CustomPagerAdapter adapter = new CustomPagerAdapter(this);
        viewPager.setAdapter(adapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == mResources.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread 
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        yourLayout = (LinearLayout)findViewById(R.id.linearScroll1);
        getScrollLayout();
        
    }
    private void getScrollLayout(){
        for (int i = 0; i < group1.length; i++) {
            RelativeLayout relativeLayout=new RelativeLayout(this);
            relativeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.edit_text_background));
            relativeLayout.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(200,250);
            layoutParams.setMargins(5,5,5,5);
            relativeLayout.setLayoutParams(layoutParams);

            ImageView image = new ImageView(getApplicationContext());
            image.setId(R.id.oneimg);
            // image.setBackground(ContextCompat.getDrawable(this,R.drawable.edit_text_background));
            RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(80,80);
            imageParams.setMargins(10,10,10,10);
            imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            image.setLayoutParams(imageParams);
            image.setImageResource(group1[i]);

            TextView textName=new TextView(this);
            textName.setId(R.id.onetitle);
            textName.setText(getResources().getStringArray(R.array.group_one_titles)[i]);
            textName.setTypeface(Typeface.createFromAsset(getAssets(),"font/BebasNeue.otf"));
            textName.setTextColor(ContextCompat.getColor(this,R.color.lightgrey));
            textName.setTextSize(18);
            textName.setGravity(Gravity.CENTER);

            RelativeLayout.LayoutParams titleParams= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            titleParams.addRule(RelativeLayout.BELOW,R.id.oneimg);
            titleParams.setMargins(0,10,0,10);
            titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textName.setLayoutParams(titleParams);


            View view=new View(this);
            view.setBackgroundColor(ContextCompat.getColor(this,R.color.greywhite));
            RelativeLayout.LayoutParams viewParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,2);
            viewParams.addRule(RelativeLayout.BELOW,R.id.onetitle);
            viewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            view.setLayoutParams(viewParams);


            ImageView imageDelete=new ImageView(this);
            imageDelete.setId(R.id.del);
            imageDelete.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.delete));
            RelativeLayout.LayoutParams deleteParams = new RelativeLayout.LayoutParams(30,30);
            deleteParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            deleteParams.addRule(RelativeLayout.BELOW,R.id.onetitle);
            deleteParams.setMargins(35,20,0,0);
            imageDelete.setLayoutParams(deleteParams);


            ImageView imageAdd = new ImageView(this);
            imageAdd.setId(R.id.add);
            imageAdd.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.add_fav));
            RelativeLayout.LayoutParams addParams = new RelativeLayout.LayoutParams(30,30);
            addParams.addRule(RelativeLayout.BELOW, R.id.onetitle);
            addParams.addRule(RelativeLayout.RIGHT_OF,R.id.del);
            addParams.setMargins(50,20,0,0);
            imageAdd.setLayoutParams(addParams);
            relativeLayout.addView(image);
            relativeLayout.addView(textName);
            relativeLayout.addView(view);
            relativeLayout.addView(imageDelete);
            relativeLayout.addView(imageAdd);


            yourLayout.addView(relativeLayout);
        }


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch(item.getItemId()) {
            case R.id.request_news:
 
                String[] email = new String[]{"abc@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Bug Report");
                intent.putExtra(Intent.EXTRA_TEXT, "I want to share my views on this application as follows :");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            case R.id.rate:

                String appPackage = this.getPackageName();
                Intent intent2 = new Intent(
                        ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id="
                                + appPackage));
                this.startActivity(intent2);

            case R.id.contact:

                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT,
                            getApplicationContext().getResources()
                                    .getString(R.string.app_name));
                    String sAux = getApplicationContext()
                            .getResources()
                            .getString(
                                    R.string.sharesuggestion);
                    sAux = sAux
                            + "https://play.google.com/store/apps/details?id="
                            + getPackageName() + " \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i,
                            getApplicationContext().getResources()
                                    .getString(R.string.sug)));
                } catch (Exception e) { // e.toString();
                }
                return true;


            case R.id.share:

                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT,
                            getApplicationContext().getResources()
                                    .getString(R.string.app_name));
                    String sAux = getApplicationContext()
                            .getResources()
                            .getString(
                                    R.string.sharesuggestion);
                    sAux = sAux
                            + "https://play.google.com/store/apps/details?id="
                            + getPackageName() + " \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i,
                            getApplicationContext().getResources()
                                    .getString(R.string.sug)));
                } catch (Exception e) { // e.toString();
                }
                return true;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class CustomPagerAdapter extends PagerAdapter {

       

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.viewPagerImage);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
