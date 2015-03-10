package iacjeep.com.serjeep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class ViewJeepRoutes extends Activity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;

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
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        TextView myText = (TextView) view;

        Toast.makeText(this,"You Selected "+myText.getText(),Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> adapterView){

    }

    public void onClick_goBack(View view) {
        Intent goBackIntent = new Intent(this,Jeep.class);
        final int result = 1;
        startActivity(goBackIntent);
    }
}
