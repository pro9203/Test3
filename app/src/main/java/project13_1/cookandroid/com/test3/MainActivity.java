package project13_1.cookandroid.com.test3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView txLat, txLon, txAlt, txstate;
    public Button btmap, btloc;
    public LocationManager locationMan;
    public MyLocationListener myLocationLx;
    public double latitude,longitude,altitude; //위치정보 저장할 변수들

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txAlt = (TextView) findViewById(R.id.txAlt);
        txLat = (TextView) findViewById(R.id.txLat);
        txLon = (TextView) findViewById(R.id.txLon);
        txstate = (TextView) findViewById(R.id.txstate);
        btloc = (Button) findViewById(R.id.btloc);
        btmap = (Button) findViewById(R.id.btmap);

        locationMan = (LocationManager) getSystemService(LOCATION_SERVICE); //서비스 형태이기 때문에 getService로 접근함

        myLocationLx = new MyLocationListener(); //새로 정의하는거라 new로 정의하고 내가 관리를 해주어야 함

        long minTime = 1000; //1초에 한번씩 정보를 가져옴
        float minDistance = 0; //얼마만큼 위치를 벗어나면 정보를 가져오는지
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //사용자가 permition을 거부하였을떄 어떻게 할지 해주는 것
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, myLocationLx); //내가 만든 locationlistener를 LocationManager에 붙여주는 과정

        btloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitude = myLocationLx.latitude; //정보를 가져오기z
                longitude = myLocationLx.longitude; //정보를 가져오기
                altitude = myLocationLx.altitude; //정보를 가져오기

                txLat.setText(String.format("%g", latitude)); //Text정보를 (문자열로)포맷을 맞추어 최신화 해주기 (%g는 소수점을 없애줌)
                txLon.setText(String.format("%g", longitude));
                txAlt.setText(String.format("%g", altitude));
            }
        });

        btmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:%g.%g?z=17",latitude,longitude)));
                //구글맵을 띄어주기 위한 인텐트 부분!
                //Uri가 복잡하기떄문에 parse로 쉽게 불러오기

                startActivity(intent);
            }
        });
    }
}
