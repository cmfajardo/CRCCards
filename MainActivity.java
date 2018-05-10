package edu.utep.cs.cs4330.crccards;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CRCDatabaseHelper mDatabaseHelper;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int count = 1;
    private EditText className, res1, res2, res3, collab1, collab2, collab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = new CRCDatabaseHelper(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        className = (EditText)viewPager.findViewById(R.id.classNameText);
        res1 = (EditText)viewPager.findViewById(R.id.resp1Text);
        res2 = (EditText)findViewById(R.id.resp2Text);
        res3 = (EditText)findViewById(R.id.resp3Text);
        collab1=(EditText)findViewById(R.id.collab1Text);
        collab2=(EditText)findViewById(R.id.collab2Text);
        collab3=(EditText)findViewById(R.id.collab3Text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_newCard:
                newCard();
                return true;
            case R.id.action_deleteCard:
                deleteCard();
                return true;
            case R.id.action_saveCard:
                saveCard();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //add new card to the end of the tab layout
    private void newCard(){
        count++;
        setupViewPager(viewPager);
    }

    //remove the last card from the tab layout
    private void deleteCard(){
        count--;
        setupViewPager(viewPager);
    }

    //add card to database
    private void saveCard(){
//        mDatabaseHelper.addData(className.getText().toString(), res1.getText().toString(),
//                res2.getText().toString(), res3.getText().toString(), collab1.getText().toString(),
//                collab2.getText().toString(), collab3.getText().toString());
        toast("not implmented Class To Database");
    }

    //create a new fragment and repaint the tab layout
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i=0; i<count; i++){
            crcCard fView = new crcCard();
            adapter.addFrag(fView,"CLASS " + i);
        }
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        //lists for the tab layout and fragment layout
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
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


