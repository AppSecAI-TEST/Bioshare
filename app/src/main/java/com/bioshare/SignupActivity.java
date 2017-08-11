package com.bioshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bioshare.fargments.CompanySignup;
import com.bioshare.fargments.IndividualSignup;
import com.bioshare.fargments.OneFragment;
import com.bioshare.fargments.TwoFragment;

import java.util.ArrayList;
import java.util.List;


public class SignupActivity extends CustomActivity {

    private TextView backToLoginTextView;
    private Button signUpButton;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_individual);

        signUpButton = (Button) findViewById(R.id.signupIndividualSubmitButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, PhoneAuthActivity.class));
            }
        });

        backToLoginTextView = (TextView) findViewById(R.id.signUp_loginTextView);
        backToLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        viewPager = (ViewPager) findViewById(R.id.individualSignupPageViewer);
        setupViewPager(viewPager);
       /* TabLayout tabLayout=(TabLayout)findViewById(R.id.tabBar);
        tabLayout.setupWithViewPager(viewPager,true);*/

      /*  viewPager.setAdapter(new SignupActivity.SamplePagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabBar);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);*/

        tabLayout = (TabLayout) findViewById(R.id.tabBar);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }


    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("As Individual");
        //tabOne.setCompoundDrawablesWithIntrinsicBounds(0,0, 0,  R.drawable.wrong);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("As Company");
        // tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.wrong, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

    
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new IndividualSignup(), "As Individual");
        adapter.addFrag(new CompanySignup(), "As Company");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

/*    public class SamplePagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 2;
        private String tabTitles[] = new String[]{"As Individual", "As Company"};

        public SamplePagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return new IndividualSignup();
            } else {
                return new CompanySignup();
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }*/
}