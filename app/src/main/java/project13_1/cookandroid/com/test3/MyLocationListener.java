package project13_1.cookandroid.com.test3;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by USER on 2016-03-07.
 */
public class MyLocationListener implements LocationListener {
    //Implements 는 직접만든 calss이기 때문에 함수가 정의가 안되어있어서 오버라이드를 해주어야함

    public double latitude,longitude,altitude;

    @Override
    public void onLocationChanged(Location location) { //위치가 변경되었을때 호출되는 부분
        latitude = location.getLatitude(); //고도 정보 받아오기
        longitude = location.getLongitude(); //경도 정보 받아오기
        altitude = location.getAltitude(); //위도 정보 받아오기
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
