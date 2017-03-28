
package com.example.assets.activity;


import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assets.R;
import com.example.assets.fragment.AccountFragment;
import com.example.assets.fragment.ChartFragment;
import com.example.assets.fragment.CommunityFragment;
import com.example.assets.fragment.DetailFragment;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DetailFragment detailFragment;//用于展示收支明细Fragment
    private AccountFragment accountFragment;// 用于展示账户的Fragment
    private ChartFragment chartFragment;//用于展示图表的Fragment
    private CommunityFragment communityFragment;//用于展示社区的Fragment
    private FragmentManager fragmentManager;//用于对Fragment进行管理

    @BindView(R.id.detail_layout)
    View detailLayout;//收支明细界面布局

    @BindView(R.id.account_layout)
    View accountLayout;//账户界面布局

    @BindView(R.id.chart_layout)
    View chartLayout;//图表界面布局

    @BindView(R.id.community_layout)
    View communityLayout;//社区界面布局

    @BindView(R.id.detail_text)
    TextView detailText;//在Tab布局上显示明细标题的控件

    @BindView(R.id.account_text)
    TextView accountText;//在Tab布局上显示账户标题的控件

    @BindView(R.id.chart_text)
    TextView chartText;// 在Tab布局上显示图表标题的控件

    @BindView(R.id.community_text)
    TextView communityText;//在Tab布局上显示社区标题的控件

    @BindView(R.id.detail_image)
    ImageView detailImage;//在Tab布局上显示明细图片的控件

    @BindView(R.id.account_image)
    ImageView accountImage;//在Tab布局上显示账户图片的控件

    @BindView(R.id.chart_image)
    ImageView chartImage;//在Tab布局上显示图表图片的控件

    @BindView(R.id.community_image)
    ImageView communityImage;//在Tab布局上显示社区图片的控件

    @BindView(R.id.toolbar)
    Toolbar toolbar; //Toolbar控件

    @BindView(R.id.add_image)
    ImageView addImage;


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout; //获取DrawerLayout实例

    @BindView(R.id.navigationview)
    NavigationView navView; //获取Navigation实例

    private ArrayAdapter<String> adapter;
    private DialogPlus dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);
        ButterKnife.bind(this);
        SQLiteDatabase db = LitePal.getDatabase();//创建数据库
        setSupportActionBar(toolbar);//让ToolBar的外观与ActionBar一致
        //得到ActionBar实例，虽然这个ActionBar具体是有ToolBar实现的
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);//设置一个导航按钮图标
        }
        //将plan菜单设置为默认选项
        navView.setCheckedItem(R.id.nav_plan);
        //菜单项点击事件
        navView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawer(navView);
                return true;
            }
        });

        fragmentManager = getSupportFragmentManager();  //获取FragmengtManager对象
        setTabSelection(0);// 第一次启动时选中第0个tab
        adapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_list_item_1, new String[]{"收入", "支出"});
        createBottomDialog();
        listenViews();
    }


    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */

    private void listenViews() {

        detailLayout.setOnClickListener(this);
        accountLayout.setOnClickListener(this);
        chartLayout.setOnClickListener(this);
        communityLayout.setOnClickListener(this);
        addImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_layout:
                // 当点击了明细tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.account_layout:
                // 当点击了账户tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.chart_layout:
                // 当点击了图表tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.community_layout:
                // 当点击了社区tab时，选中第4个tab
                setTabSelection(3);
                break;
            case R.id.add_image:
                dialog.show();
            default:
                break;
        }
    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     * 每个tab页对应的下标。0表示明细，1表示账户，2表示图表，3表示社区。
     */
    private void setTabSelection(int index) {
        clearSelection(); // 每次选中之前先清楚掉上次的选中状态
        FragmentTransaction transaction = fragmentManager.beginTransaction(); // 开启一个Fragment事务
        hideFragments(transaction); // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        Resources res = getResources();
        switch (index) {
            case 0:
                detailText.setTextColor(res.getColor(R.color.tab_selected));
                if (detailFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    detailFragment = new DetailFragment();
                    transaction.add(R.id.content, detailFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(detailFragment);
                }
                break;
            case 1:
                accountText.setTextColor(res.getColor(R.color.tab_selected));
                if (accountFragment == null) {
                    accountFragment = new AccountFragment();
                    transaction.add(R.id.content, accountFragment);
                } else {
                    transaction.show(accountFragment);
                }
                break;
            case 2:
                chartText.setTextColor(res.getColor(R.color.tab_selected));
                if (chartFragment == null) {
                    chartFragment = new ChartFragment();
                    transaction.add(R.id.content, chartFragment);
                } else {
                    transaction.show(chartFragment);
                }
                break;
            case 3:
            default:
                communityText.setTextColor(res.getColor(R.color.tab_selected));
                if (communityFragment == null) {
                    communityFragment = new CommunityFragment();
                    transaction.add(R.id.content, communityFragment);
                } else {
                    transaction.show(communityFragment);
                }
                break;
        }
        transaction.commit();
    }


    /**
     * 清除掉所有的选中状态。
     */

    private void clearSelection() {
        detailText.setTextColor(Color.parseColor("#82858b"));
        accountText.setTextColor(Color.parseColor("#82858b"));
        chartText.setTextColor(Color.parseColor("#82858b"));
        communityText.setTextColor(Color.parseColor("#82858b"));
    }


    /**
     * 将所有的Fragment都置为隐藏状态。
     */

    private void hideFragments(FragmentTransaction transaction) {
        if (detailFragment != null) {
            transaction.hide(detailFragment);
        }
        if (accountFragment != null) {
            transaction.hide(accountFragment);
        }
        if (chartFragment != null) {
            transaction.hide(chartFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
    }

    /**
     * 让菜单显示出来
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * 菜单项的单击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);//让滑动菜单显示出来
                break;
            default:

        }
        return true;
    }


    /**
     * 从底部弹出一个对话框
     */
    public void createBottomDialog() {
        dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ListHolder())//使用ListView作为默认的content holder(默认)
                .setGravity(Gravity.BOTTOM) //设置对话框的位置
                .setCancelable(true)//对话框是可取消的
                .setAdapter(adapter)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int
                            position) {
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this, IncomeActivity.class);
                            startActivity(intent);
                        }
                        if (position == 1) {
                            Intent intent = new Intent(MainActivity.this, OutcomeActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                .setExpanded(true)
                .create();
    }
}


