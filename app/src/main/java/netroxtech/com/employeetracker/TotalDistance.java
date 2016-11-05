package netroxtech.com.employeetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import netroxtech.com.employeetracker.Models.Locations;
import netroxtech.com.employeetracker.utils.InitilizeSharePref;

public class TotalDistance extends AppCompatActivity {

    TextView latitude, longitude;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_distance);
        context = this;
        setXML();
    }
    public void setXML(){
        latitude = (TextView) findViewById(R.id.activity_totalDistance_latitude);
        latitude = (TextView) findViewById(R.id.activity_totalDistance_latitude);
    }
    public  void  LongLat(View v){
        Intent intent = new Intent(TotalDistance.this,TotalDistanceMap.class);
        startActivity(intent);
    }
    @Override
    public  void onResume() {

        super.onResume();
        Locations locations = new InitilizeSharePref(context).getSaveOfficeLocation();
        if (locations.getLatitude() != null && locations.getLongitude()!=null) {
            latitude.setText(locations.getLatitude());
            longitude.setText(locations.getLongitude());
        }
        else{
            latitude.setText("Latitude");
            longitude.setText("Longitude");

        }
    }
}
