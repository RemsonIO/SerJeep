package iacjeep.com.serjeep;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

public class Jeep extends Activity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    //
    public void onClick_viewJeepRoutes(View view){

    }
    //
    public void onClick_getJeepRoute(View view){
        Intent getIntent = new Intent(this,mapJeep.class);
        final int result = 1;
        startActivity(getIntent);
    }

    //
    public void onClick_sendRoute(View view) {


    }
    //
    public void onClick_close(View view) {
        finish();
    }
}
