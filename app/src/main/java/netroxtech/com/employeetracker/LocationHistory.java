package netroxtech.com.employeetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import netroxtech.com.employeetracker.Models.Locations;
import netroxtech.com.employeetracker.adapters.GetlocationsAdapter;
import netroxtech.com.employeetracker.utils.InitilizeSharePref;

public class LocationHistory extends AppCompatActivity {

     Context context;
     ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_history);
        listView = (ListView)findViewById(R.id.locationHosoty_activity_locationhistory_listView);
        context = this;
        setXML();
    }
    public void setLocationHistory(){
    List<Locations> locationHistory =new   InitilizeSharePref(context).getLocationHistory();
        ListAdapter adapter= new GetlocationsAdapter(context,locationHistory);
        listView.setAdapter(adapter);
    }
    public  void  setXML(){
        setLocationHistory();
    }

}
