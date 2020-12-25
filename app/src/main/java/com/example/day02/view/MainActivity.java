package com.example.day02.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.example.day02.R;

import com.example.day02.adapter.NewAdapter;
import com.example.day02.contract.MainContract;
import com.example.day02.modle.bean.Banbean;
import com.example.day02.modle.bean.Chbean;
import com.example.day02.modle.bean.Homebean;
import com.example.day02.modle.bean.UserBean;
import com.example.day02.persent.MainPresenterImpl;
import com.example.day02.util.PoiOverlay;
import com.example.day02.util.WalkRouteOverlay;
import com.example.mvp.base.BaseActivity;
import com.example.mvp.net.URLConstant;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.UserDictionary.Words.APP_ID;


public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.IMainView, PoiSearch.OnPoiSearchListener, RouteSearch.OnRouteSearchListener {

    private String url="exam2003/anewslist.json";
    private EditText editZh;
    private EditText editMm;
    private Button button;
    List<UserBean.NewsBean> use;

    //在微博开发平台为应用申请的App Key
    private static final String APP_KY = "3340613290";
    //在微博开放平台设置的授权回调页
    private static final String REDIRECT_URL = "http://www.sina.com";
    //在微博开放平台为应用申请的高级权限
    private static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";


    private IWBAPI mWBAPI;


    private MapView mMapView;
    AMap aMap;
    private EditText editQuery;
    private Button btn;
    private TextView text;
    private String check;
    private androidx.recyclerview.widget.RecyclerView recy;
    private ArrayList<PoiItem> pois;
    private NewAdapter adapter;

    private Button btna;
    private LatLonPoint mStartPoint;
    private LatLonPoint mEndPoint;
    private Button btnWb;
    private Button btnQq;
    private Tencent mtencent;
    private Button btnYm;

    @Override
    protected void initData() {

                presenter.login(URLConstant.NEWLIST);
                presenter.loginban(URLConstant.NEWBAN);
                presenter.loginCh(URLConstant.NEWCH);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        btnYm = (Button) findViewById(R.id.btn_ym);
        recy = (RecyclerView) findViewById(R.id.recy);
        editQuery = (EditText) findViewById(R.id.edit_query);
        btn = (Button) findViewById(R.id.btn);
        btna = (Button) findViewById(R.id.btna);
        btnWb = (Button) findViewById(R.id.btn_wb);
        btnQq = (Button) findViewById(R.id.btn_qq);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //初始化地图对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //定位
        initMap();
        //button搜索poi
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = editQuery.getText().toString();

                initJS();
            }
        });
        //步行路线规划
        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = pois.get(0).getLatLonPoint().getLatitude();
                double longitude = pois.get(0).getLatLonPoint().getLongitude();
                //起点，116.335891,39.942295
                mStartPoint = new LatLonPoint(40.136901,116.208744);
                //终点，116.481288,39.995576
                mEndPoint = new LatLonPoint( latitude,longitude);
                if (latitude!=0.0&&longitude!=0.0){
                    initBx();
                }else {
                    Toast.makeText(MainActivity.this, "终点没有选择", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ///微博分享按钮  简陋版
        btnWb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSdk();
            }
        });
        //！！QQ分享         简陋版
        btnQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initQQ();
            }
        });

        btnYm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

    }




    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenterImpl getPresenter() {
        return new MainPresenterImpl();
    }



    @Override
    public void getBan(Banbean banbean) {
        Log.i("TTT", "getBan: "+banbean.toString());
    }

    @Override
    public void getData(UserBean string) {


    }

    @Override
    public void getCh(Chbean chbean) {
        Log.i("TTT", "getCh: "+chbean.toString());
    }

    @Override
    public void gethome(Homebean homebean) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
    //定位
    private void initMap() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。



        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。

    }
    //检索
    private void initJS() {
        PoiSearch.Query query = new PoiSearch.Query(check, "", "北京");
        query.setPageSize(10);

        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);

        poiSearch.searchPOIAsyn();
    }

    //检索监听
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        pois = poiResult.getPois();

          adapter = new NewAdapter(this, pois);
          recy.setLayoutManager(new LinearLayoutManager(this));
          recy.setAdapter(adapter);
          adapter.notifyDataSetChanged();
        Log.i("TTT", "onPoiSearched: "+pois.get(0).getLatLonPoint().toString());
        PoiOverlay poiOverlay = new PoiOverlay(aMap, pois);
        poiOverlay.removeFromMap();
        poiOverlay.addToMap();
        poiOverlay.zoomToSpan();

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
    //步行
    private void initBx() {

        RouteSearch routeSearch = new RouteSearch(this);
        //设置数据回调监听
        routeSearch.setRouteSearchListener(this);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WALK_DEFAULT);
        routeSearch.calculateWalkRouteAsyn(query);//开始算路
    }
    //数据点听回调方法
    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

        WalkRouteResult mWalkRouteResult = walkRouteResult;
         WalkPath walkPath = mWalkRouteResult.getPaths().get(0);
        WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(
                this, aMap, walkPath,
                mWalkRouteResult.getStartPos(),
                mWalkRouteResult.getTargetPos());
        walkRouteOverlay.removeFromMap();
        walkRouteOverlay.addToMap();
        walkRouteOverlay.zoomToSpan();
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
        //初始化 微博分享sdk
    private void initSdk() {
        AuthInfo authInfo = new AuthInfo(this, APP_KY, REDIRECT_URL, SCOPE);
        mWBAPI = WBAPIFactory.createWBAPI(this);
        mWBAPI.registerApp(this, authInfo);
        WeiboMultiMessage message = new WeiboMultiMessage();

        TextObject textObject = new TextObject();
        String text = "我正在使用微博客户端发博器分享文字。";

        // 分享文字
            text = "约德尔教练--给墨迹！";

        textObject.text = text;
        message.textObject = textObject;
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
        imageObject.setImageData(bitmap);
        message.imageObject = imageObject;
        mWBAPI.shareMessage(message,!text.isEmpty());
    }
    // ਫሿWbShareCallbackളݗ҅឴ړݐՁᕮຎ̶
    private class ShareCallback implements WbShareCallback {
        @Override
        public void onComplete() {
            Toast.makeText(MainActivity.this, "分享成功",Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onError(UiError error) {
            Toast.makeText(MainActivity.this, "分享失败" + error.errorMessage, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mWBAPI!=null){
            mWBAPI.doResultIntent(data,new ShareCallback());
        }
    }

    //QQQ
    private void initQQ() {
        mtencent = Tencent.createInstance("1111336280", this.getApplicationContext());
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "十年春去秋来");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "千年王朝更替");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "https://v26-dy.ixigua.com/caae71375538c2e5197d7e13a70ba26d/5fe0aea9/video/tos/cn/tos-cn-ve-15/d78f8e65f066420eb241e79116a9b10f/?a=1128&br=12528&bt=4176&cd=0%7C0%7C0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&l=202012212118040101980650774A186A6C&lr=&mime_type=video_mp4&qs=0&rc=Mzw8ZDQzdzp2eTMzZmkzM0ApOmdkNztmZmVnN2c4OTRkZmdmX2lgMmJfZzZfLS1iLS9zczAxMl4tL19fYGBiLzExX2M6Yw%3D%3D&vl=&vr=");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试应用222222");

        mtencent.shareToQQ(MainActivity.this, params, new BaseUiListener());


    }

    //内部类
   class BaseUiListener implements IUiListener {

        protected void doComplete(JSONObject values) {
            Log.i("UUU", "doComplete: "+values);
        }


        @Override
        public void onComplete(Object o) {
            Log.i("CCC", "onComplete: "+o.toString());
        }

        @Override
        public void onError(com.tencent.tauth.UiError uiError) {
            /*showResult("onError:", "code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail);*/
            Log.i("HHH", "onError: "+uiError.toString());
        }

        @Override
        public void onCancel() {
            Log.i("YYY", "onCancel: ");
        }

        @Override
        public void onWarning(int i) {

        }
    }


}