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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;

import iacjeep.com.serjeep.GMapV2Direction.*;

public class ViewJeepRoutes extends FragmentActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    GoogleMapOptions options = new GoogleMapOptions().liteMode(true);

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
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        TextView myText = (TextView) view;

        Toast.makeText(this,"You Selected "+myText.getText(),Toast.LENGTH_SHORT).show();

        GMapV2Direction gMapDirection = new GMapV2Direction();

        if(myText.getText() == "BelAir-Washington"){

            LatLng posFirst = new LatLng (14.5601515,121.0116654);
            LatLng posSecond = new LatLng  (14.5575996,121.0241638);
            drawDirection(posFirst, posSecond);
        }



    }

    public void drawDirection(LatLng fromPosition, LatLng toPosition){

        GMapV2Direction gMapDirection = new GMapV2Direction();

        Document doc = gMapDirection.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
        ArrayList<LatLng> directionPoint = gMapDirection.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
        for(int i = 0 ; i < directionPoint.size() ; i++) {
            rectLine.add(directionPoint.get(i));
        }

        mMap.addPolyline(rectLine);
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
