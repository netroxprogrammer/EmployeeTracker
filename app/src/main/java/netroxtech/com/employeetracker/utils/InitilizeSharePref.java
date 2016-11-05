package netroxtech.com.employeetracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import netroxtech.com.employeetracker.Models.Locations;
import netroxtech.com.employeetracker.Models.Users;

/**
 * Created by mac on 9/18/2016.
 */
public class InitilizeSharePref {
    SharedPreferences mPref;
    Context mcontext;
    public InitilizeSharePref(Context context){
        mcontext = context;
        mPref = mcontext.getSharedPreferences(Constants.MYPREFNAME,Context.MODE_PRIVATE);
  }

    public void saveUserLogin(Users users){
        SharedPreferences.Editor editor  = mPref.edit();
        editor.putString(Constants.ROLEPREF,users.getRole());
        editor.putString(Constants.USERNAMEPREF,users.getUserName());
        editor.commit();
    }
    public String getUserRole(){
        String role =  mPref.getString(Constants.ROLEPREF, null);
        return  role;
    }
    public void saveLocation(Locations locations){
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(Constants.LONGITUDEPREF,locations.getLongitude());
        editor.putString(Constants.LATITUDEPREF,locations.getLatitude());
        editor.putString(Constants.CURENTTIMEEPREF,locations.getCurrentTime());
        editor.commit();
    }
    public List<Locations> getLocationHistory(){
        ArrayList<Locations> arrayList = new ArrayList<>();
        Map<String, ?> allEntries = mPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Locations  locations = new Locations();
            if(entry.getKey().toString().equalsIgnoreCase(Constants.LONGITUDEPREF)){
                locations.setLongitude(entry.getValue().toString());
            }
            if(entry.getKey().toString().equalsIgnoreCase(Constants.LATITUDEPREF)){
                locations.setLongitude(entry.getValue().toString());
            }
            if(entry.getKey().toString().equalsIgnoreCase(Constants.CURENTTIMEEPREF)){
                locations.setLongitude(entry.getValue().toString());
            }
            arrayList.add(locations);
            Log.d(Constants.MYLOGS, entry.getKey() + ": " + entry.getValue().toString());
        }
        return arrayList;
    }

    public void saveDistance(String lati,String longi){

        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(Constants.OFFICELONGITUDEPREF,longi);
        editor.putString(Constants.OFFICELATITUDEPREF,lati);
        editor.commit();

    }

    public void removeData(String key){
        SharedPreferences.Editor editor = mPref.edit();
        editor.remove(key).commit();
    }

    public boolean isUserLogin(){
        String role =  mPref.getString(Constants.ROLEPREF, null);
        String userName =  mPref.getString(Constants.USERNAMEPREF, null);
        if(!role.equalsIgnoreCase("") ||  role != null && !userName.equalsIgnoreCase("") || userName!=null){
            return true;
        }
        return false;
    }
    public Locations getSaveOfficeLocation(){
        String latitude  = mPref.getString(Constants.OFFICELATITUDEPREF,null);
        String  longitude = mPref.getString(Constants.OFFICELONGITUDEPREF, null);
        Locations   locations = new Locations();
        locations.setLongitude(longitude);
        locations.setLatitude(latitude);
        return  locations;
    }
}
