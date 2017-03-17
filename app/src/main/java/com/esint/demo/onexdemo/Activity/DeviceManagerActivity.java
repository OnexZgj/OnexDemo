package com.esint.demo.onexdemo.Activity;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.esint.demo.onexdemo.Common.Common;
import com.esint.demo.onexdemo.R;
import com.esint.demo.onexdemo.view.PullToRefreshListView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Linsa on 2017/3/16:10:29.
 * des: 设备管理页面
 */
public class DeviceManagerActivity extends AppCompatActivity {

    private static final String TAG = "sss";
    @InjectView(R.id.iv_am_add)
    ImageView ivAmAdd;
    @InjectView(R.id.iv_am_scan)
    ImageView ivAmScan;
    @InjectView(R.id.iv_am_setting)
    ImageView ivAmSetting;
    @InjectView(R.id.iv_am_search)
    ImageView ivAmSearch;
    @InjectView(R.id.lv_am_lists)
    PullToRefreshListView lvAmLists;
    @InjectView(R.id.sp_adm_type)
    Spinner spAdmType;
    @InjectView(R.id.sp_adm_entry_name)
    Spinner spAdmEntryName;
    @InjectView(R.id.sp_adm_producer)
    Spinner spAdmProducer;
    @InjectView(R.id.sp_adm_supply)
    Spinner spAdmSupply;
    @InjectView(R.id.sp_adm_room)
    Spinner spAdmRoom;
    @InjectView(R.id.tv_adm_time1)
    TextView tvAdmTime1;
    @InjectView(R.id.tv_adm_time2)
    TextView tvAdmTime2;
    @InjectView(R.id.btn_adm_ok)
    Button btnAdmOk;
    @InjectView(R.id.ll_container_pop)
    LinearLayout llContainerPop;
    @InjectView(R.id.swl_adm_refresh)
    SwipeRefreshLayout swlAdmRefresh;
    /**
     * 设置是否显示了动画
     */
    private boolean isShow;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manager);
        ButterKnife.inject(this);

        initData();

        initListener();
    }

    private void initListener() {

        swlAdmRefresh.setColorSchemeColors(Color.RED,Color.GREEN);

        swlAdmRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DeviceManagerActivity.this, "下拉刷新了", Toast.LENGTH_SHORT).show();
                        swlAdmRefresh.setEnabled(false);
                    }
                }, 2000);
            }
        });
    }

    private void initData() {
        lvAmLists.setOnRefreshListener(new PullToRefreshListView.onRefreshListener() {
            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DeviceManagerActivity.this, "加载了更多", Toast.LENGTH_SHORT).show();
                        lvAmLists.finish();
                    }
                }, 2000);
            }
        });
        lvAmLists.setAdapter(new ProductAdapter());
    }


    @OnClick({R.id.iv_am_add, R.id.iv_am_scan, R.id.iv_am_setting, R.id.iv_am_search, R.id.tv_adm_time1, R.id.tv_adm_time2, R.id.btn_adm_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_am_add:
                break;
            case R.id.iv_am_scan:
                startActivityForResult(new Intent(DeviceManagerActivity.this, CaptureActivity.class), Common.REQUEST_SCAN);
                break;
            case R.id.iv_am_setting:
                break;
            case R.id.iv_am_search:
                if (isShow) {
                    showExitAnimation(llContainerPop);
                } else {
                    showEnterAnimation(llContainerPop);
                }
                break;
//                String text = et_text.getText().toString();
//                if (text.equals("")) {
//                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
//                } else {
//                    Bitmap QRCode = EncodingUtils.createQRCode(text, 500, 500,
//                            cb_logo.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) : null);
//                    iv_show.setImageBitmap(QRCode);
//                }
            case R.id.tv_adm_time1:

                calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(DeviceManagerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvAdmTime1.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.tv_adm_time2:
                calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(DeviceManagerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvAdmTime2.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.btn_adm_ok:
                break;
        }
    }

    /**
     * 退出的动画
     */
    private void showExitAnimation(LinearLayout llContainerPop) {
        int width = llContainerPop.getWidth();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(llContainerPop, "translationX", 0, width);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        isShow = false;
    }


    /**
     * 执行弹出的动画
     *
     * @param llContainerPop
     */
    private void showEnterAnimation(LinearLayout llContainerPop) {
        llContainerPop.setVisibility(View.VISIBLE);
        int width = llContainerPop.getWidth();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(llContainerPop, "translationX", width, 0);
        objectAnimator.setDuration(300);
        objectAnimator.start();
        isShow = true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Common.REQUEST_SCAN) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
                //进行跳转到webActivity的页面

                Intent intent = new Intent(DeviceManagerActivity.this, WebViewActivity.class);
                intent.putExtra(Common.EXTRA_URL, result);
                startActivity(intent);
                Log.i(TAG, "onActivityResult: " + result);
            }
        }
    }
}
