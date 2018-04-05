package com.example.root.auditorium;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class admin_only extends AppCompatActivity {



    private static final String TAG = "admin_only";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    Toolbar toolbar;
    private FloatingActionButton f1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_only);

        toolbar = findViewById(R.id.toolbar);

        //floating button definition
         f1 = (FloatingActionButton)findViewById(R.id.fab);

        setSupportActionBar(toolbar);


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Setup the ViewPager with the sections adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        //declare the tablayout
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.user1);
        tabLayout.getTabAt(1).setIcon(R.drawable.auditorium);
        tabLayout.getTabAt(2).setIcon(R.drawable.request);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = tabLayout.getSelectedTabPosition();

                switch(position) {
                    case 0:
                        Intent a = new Intent(admin_only.this, add_user.class);
                        startActivity(a);
                        break;
                    case 1:
                        Intent b = new Intent(admin_only.this, add_audi.class);
                        startActivity(b);
                        break;
                    case 2:
                        Intent c = new Intent(admin_only.this, req_form.class);
                        startActivity(c);
                        break;
                }

            }
        });


    }


    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new view_user(),"Users");
       adapter.addFragment(new view_audi(),"Auditoriums");
       adapter.addFragment(new view_requests(),"Requests");
       viewPager.setAdapter(adapter);
   }


    //to show and add the menu options in current activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.admin_menu,menu);
       return super.onCreateOptionsMenu(menu);
    }

    //to make the options in the menu workable
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.change_pass_admin:
                Intent i = new Intent(admin_only.this, change_password.class);
                startActivity(i);
                break;
            case R.id.logout_admin:
                Intent j = new Intent(admin_only.this, login.class);
                startActivity(j);
                break;
            case R.id.del_audi:
                Intent k = new Intent(admin_only.this, del_audi.class);
                startActivity(k);
                break;

        }
        return true;
    }
}
