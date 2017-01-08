package com.baseactivityconcept;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baseactivityconcept.Utils.Common;
import com.baseactivityconcept.Utils.DrawerArrowDrawable;
import com.baseactivityconcept.Wrappers.MenuDrawer_Wrapper;

import java.util.ArrayList;

public class BaseActivity extends FragmentActivity
{
    public RelativeLayout mRelativeLayout;
    public FrameLayout actContent;
    public ListView navList;
    LinearLayout drawer_List;
    TextView text_emp_name;
    public MenuDrawerAdapter menuDrawerAdapter;
    public ArrayList<MenuDrawer_Wrapper> menuDrawerList = new ArrayList<>();
    public DrawerArrowDrawable drawerArrowDrawable;
    public ImageView drawer_indicator;
    public DrawerLayout drawer_layout;
    public float offset;
    public boolean flipped;
    LinearLayout drawer_indicator_LL;

    final String[] navDrawMenuItems = {"Search" , "Edit", "Logout", "Camera" ,"Timer"};
    final int[] navDrawMenuIcons = {R.drawable.search,
            R.drawable.edit,
            R.drawable.log_icon,
            R.drawable.camera1,
            R.drawable.duration_icon,

    };

    SharedPreferences _SP;
    SharedPreferences.Editor _EDITOR;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < navDrawMenuItems.length; i++)
        {
            menuDrawerList.add(new MenuDrawer_Wrapper(""+i,navDrawMenuItems[i], navDrawMenuIcons[i]));
        }

    }

    @Override
    public void setContentView(int layoutResID)

    {
        mRelativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        actContent = (FrameLayout) mRelativeLayout.findViewById(R.id.main);
        // set the drawer dialog_view as main content view of Activity.
        setContentView(mRelativeLayout);
        // add dialog_view of BaseActivities inside framelayout.i.e. frame_container
        getLayoutInflater().inflate(layoutResID, actContent, true);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // =========================================
        final Resources resources = getResources();
        drawerArrowDrawable = new DrawerArrowDrawable(resources);

        drawer_indicator = (ImageView) findViewById(R.id.drawer_indicator);
        drawerArrowDrawable.setStrokeColor(resources.getColor(android.R.color.white));
        drawer_indicator.setImageDrawable(drawerArrowDrawable);
        // =========================================

        text_emp_name=(TextView)findViewById(R.id.text_emp_name) ;
        _SP = getSharedPreferences(Common.TAG, MODE_PRIVATE);

        text_emp_name.setText(_SP.getString("empName",""));
        drawer_List=(LinearLayout)findViewById(R.id.drawer_List);
        navList = (ListView) findViewById(R.id.drawer_List1);
        menuDrawerAdapter = new MenuDrawerAdapter(BaseActivity.this, menuDrawerList);
        navList.setAdapter(menuDrawerAdapter);

        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent;
                //                Toast.makeText(getApplicationContext(),"position "+position,30).show();
                Log.i(Common.TAG,"drawer_position: "+position);
                switch (position)
                {
                    case 0:

                        intent = new Intent(BaseActivity.this, SearchActivity.class);
                        startActivityForResult(intent,Common.NORMAL);
                        //
                        break;

                    case 1:

                        intent = new Intent(BaseActivity.this, SearchActivity.class);
                        startActivityForResult(intent,Common.NORMAL);
                        //drawer_indicator.performClick();

                        break;
                    case 2:
                        intent = new Intent(BaseActivity.this, SearchActivity.class);
                        startActivityForResult(intent,Common.NORMAL);
                        //                        drawer_indicator.performClick();
                        break;

                    case 3:
                        intent = new Intent(BaseActivity.this, SearchActivity.class);
                        startActivityForResult(intent,Common.NORMAL);
                        //                        drawer_indicator.performClick();
                        break;
                    case 4:
                        intent = new Intent(BaseActivity.this, SearchActivity.class);
                        intent.putExtra("Type","Complaints");
                        startActivityForResult(intent,Common.NORMAL);
                        //                        drawer_indicator.performClick();
                        break;
                    case 5:
                        intent = new Intent(BaseActivity.this, SearchActivity.class);
                        intent.putExtra("Type","Suggestions");
                        startActivityForResult(intent,Common.NORMAL);
                        break;
                    case 6:
                        intent = new Intent(BaseActivity.this, SearchActivity.class);
                        startActivityForResult(intent,Common.NORMAL);
                        break;
                    case 7:

                        System.out.println("Logout clickedddddd ");
                        SharedPreferences.Editor editor = getSharedPreferences("my_pref", MODE_PRIVATE).edit();
                        editor.clear();
                        editor.commit();
                        setResult(Common.EXIT);
                        finish();
                        break;

                    default:
                        break;
                }

            }
        });


        initViews();
        setToViews();
        clickToViews();

    }



    public void setMenuDrawer(ArrayList<MenuDrawer_Wrapper> menuDrawerList, String email)
    {
        navList = (ListView) findViewById(R.id.drawer_List1);
        menuDrawerAdapter = new MenuDrawerAdapter(BaseActivity.this, menuDrawerList);
        navList.setAdapter(menuDrawerAdapter);
    }


    public void initViews()
    {

        _SP = getSharedPreferences(Common.TAG, MODE_PRIVATE);
        _EDITOR = _SP.edit();
        drawer_indicator_LL = (LinearLayout) findViewById(R.id.drawer_indicator_LL);

    }

    private void setToViews() {
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        if (drawer_layout.isDrawerVisible(Gravity.RIGHT))
        {
            drawer_layout.closeDrawer(drawer_List);
        }
        else
        {
            finish();
        }
    }

    public void clickToViews()
    {
        drawer_layout.addDrawerListener(new DrawerLayout.SimpleDrawerListener()
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                offset = slideOffset;
                // Sometimes slideOffset ends up so close to but not quite 1 or 0.
                if (slideOffset >= .995)
                {
                    flipped = true;
                    drawerArrowDrawable.setFlip(flipped);
                } else if (slideOffset <= .005)
                {
                    flipped = false;
                    drawerArrowDrawable.setFlip(flipped);
                }

                drawerArrowDrawable.setParameter(offset);
            }
        });

        drawer_indicator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (drawer_layout.isDrawerVisible(Gravity.RIGHT))
                {
                    drawer_layout.closeDrawer(drawer_List);
                }
                else
                {
                    drawer_layout.openDrawer(drawer_List);
                }
            }
        });

        drawer_indicator_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer_layout.isDrawerVisible(Gravity.RIGHT)) {
                    drawer_layout.closeDrawer(drawer_List);
                } else {
                    drawer_layout.openDrawer(drawer_List);
                }
            }
        });

    }


    public void NavigationPerformClick()
    {
        drawer_indicator.performClick();
    }

    private class MenuDrawerAdapter extends BaseAdapter
    {

        ArrayList<MenuDrawer_Wrapper> menuDrawerList;
        Context context;


        public MenuDrawerAdapter(Context context, ArrayList<MenuDrawer_Wrapper> menuDrawerList)
        {
            super();
            this.context = context;
            this.menuDrawerList = menuDrawerList;


        }

        @Override
        public int getCount()
        {
            return menuDrawerList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return position;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(BaseActivity.this);
            ViewHolder viewHolder;

            if (convertView == null)
            {
                viewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.row_menu_drawer, null);

                viewHolder.Title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.Icon = (ImageView) convertView.findViewById(R.id.icon);
                viewHolder.main_RL = (RelativeLayout)convertView.findViewById(R.id.main_RL);
                viewHolder.Icon.setVisibility(View.VISIBLE);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            MenuDrawer_Wrapper menuDrawerModel = menuDrawerList.get(position);
            //## Setup Data below
            String Name = menuDrawerModel.getName();
            String id = menuDrawerModel.getId();

            viewHolder.Title.setText(menuDrawerModel.getName());

            //            viewHolder.main_RL.setOnClickListener(new ClickToView(BaseActivity.this,position,id,Name));
            viewHolder.Icon.setImageResource(menuDrawerModel.getDrawable_icon());

            return convertView;

        }

        public class ViewHolder
        {
            TextView Title;
            ImageView Icon;
            RelativeLayout main_RL;
        }

    }


    protected class MenuDrawerModel
    {
        private String title;
        private int icon;
        public String Icon_url;

        public MenuDrawerModel(String title, int icon, String icon_url) {
            this.title = title;
            this.icon = icon;
            Icon_url = icon_url;
        }

        public MenuDrawerModel(String title, int icon)
        {
            super();
            this.title = title;
            this.icon = icon;
        }

        public MenuDrawerModel() {
            super();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon)
        {
            this.icon = icon;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Logout onActivityResult "+resultCode);
        if(resultCode==Common.EXIT)
        {
            setResult(Common.EXIT);
            finish();
        }
    }
}

