package com.example.takuya.myosmtest;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import static android.R.id.button1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("onCreate","start");

        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        //ボタンのリスナーを設定

        findViewById(R.id.button1).setOnClickListener(new OSMClickEvent());
        findViewById(R.id.button2).setOnClickListener(new OSMClickEvent());
        findViewById(R.id.button3).setOnClickListener(new OSMClickEvent());

        /*
        findViewById(R.id.button1).setOnClickListener(MyOSMEvent.getInstance());
        findViewById(R.id.button2).setOnClickListener(MyOSMEvent.getInstance());
        findViewById(R.id.button3).setOnClickListener(MyOSMEvent.getInstance());
        */

        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);


        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);


        Log.i("onCreate","finish");
    }

    public void onResume(){
        Log.i("onResume","start");
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        Log.i("onResume","finish");
    }

    /**
        画面更新時にViewにアクセスする必要がある為、イベントはインナークラスで実装
     */
    class OSMClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView optext = (TextView)findViewById(R.id.operationText);
            optext.setText("click : " + view.toString());

            switch (view.getId()) {
                case R.id.button1:
                    Log.d("debug","button1, Perform action on click");
                    button1();
                    break;
                case R.id.button2:
                    Log.d("debug","button2, Perform action on click");
                    button2();
                    break;
                case R.id.button3:
                    Log.d("debug","button3, Perform action on click");
                    button3();
                    break;
            }

        }

        private void button3(){
            //TODO: draw line with lat, long values
            Log.d("map","draw line with lat, long values");
        }

        private void button2(){
            //TODO: set current pos and place an icon
            Log.d("map","set current pos and place an icon");
        }

        /**
         * ECS office
         */
        private void button1(){
            MapView map = (MapView) findViewById(R.id.map);
            IMapController mapController = map.getController();
            //34.989155 137.005537
            GeoPoint startPoint = new GeoPoint(34.989155,137.005537);
            mapController.setZoom(18);
            mapController.setCenter(startPoint);

        }
    }
}



