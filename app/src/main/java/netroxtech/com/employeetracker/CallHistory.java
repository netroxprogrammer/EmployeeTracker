package netroxtech.com.employeetracker;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;


import java.util.ArrayList;
import java.util.List;

import netroxtech.com.employeetracker.fragments.InCommingCallFragment;
import netroxtech.com.employeetracker.fragments.MissCallsFragment;
import netroxtech.com.employeetracker.fragments.OutGoingCallsFragment;

public class CallHistory extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_history);
        setXML();
    }
    public void setXML(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_incommingcall);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_outgoong);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_misscall);
    }
    public void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter =  new ViewPagerAdapter(getSupportFragmentManager());
      adapter.addFragment(new InCommingCallFragment(), "inComing");
      adapter.addFragment(new OutGoingCallsFragment(), "outGoing");
      adapter.addFragment(new MissCallsFragment(), "MissCalls");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private  final List<Fragment> mFragmentList = new ArrayList<>();
        private  final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentTitleList.size();
        }
        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTitleList.get(position);
        }
    }
}
