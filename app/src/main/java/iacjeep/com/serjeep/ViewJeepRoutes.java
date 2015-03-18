package iacjeep.com.serjeep;



import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;


public class ViewJeepRoutes extends FragmentActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_jeep_routes);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter= ArrayAdapter.createFromResource(this,R.array.makati_transit,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.animateCamera(CameraUpdateFactory.zoomBy(16));
                mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
            }
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        TextView myText = (TextView) view;

        Toast.makeText(this,"You Selected "+myText.getText(),Toast.LENGTH_SHORT).show();


        if(myText.getText().equals("BelAir-Washington")){
            LatLng posFirst = new LatLng (14.5601515,121.0116654);
            LatLng posSecond = new LatLng  (14.5575996,121.0241638);

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(posFirst,16)));

            mMap.addMarker(new MarkerOptions().position(posFirst).title("Point A"));
            mMap.addMarker(new MarkerOptions().position(posSecond).title("Point B"));

            GMapV2Direction md = new GMapV2Direction();

            Document doc = md.getDocument(posFirst, posSecond, GMapV2Direction.MODE_DRIVING);
            ArrayList<LatLng> directionPoint = md.getDirection(doc);
            PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);

            for(int i = 0 ; i < directionPoint.size() ; i++) {
                rectLine.add(directionPoint.get(i));
            }
            mMap.addPolyline(rectLine);

        }



    }



    public void onNothingSelected(AdapterView<?> adapterView){

    }


    public void onClick_goBack(View view) {
        Intent goBackIntent = new Intent(this,Jeep.class);
        final int result = 1;
        this.finishActivity(result);
        startActivity(goBackIntent);
    }
}
