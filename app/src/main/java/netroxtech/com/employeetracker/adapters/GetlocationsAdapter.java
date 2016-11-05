package netroxtech.com.employeetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import netroxtech.com.employeetracker.Models.Locations;
import netroxtech.com.employeetracker.R;

/**
 * Created by mac on 10/23/2016.
 */
public class GetlocationsAdapter extends BaseAdapter {
    List<Locations> locationsList;
    Context context;
    LayoutInflater mInfulate;
    public GetlocationsAdapter(Context context,List<Locations> locationsList){
        this.context  = context;
        this.locationsList = locationsList;
        mInfulate =  LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder  viewHolder  = new ViewHolder();
        if(view==null){
            view  = mInfulate.inflate(R.layout.view_locations,null);
            viewHolder.latitude  = (TextView) view.findViewById(R.id.latitude_view_location_txtView);
            viewHolder.longitude  = (TextView) view.findViewById(R.id.longitude_view_location_txtView);
            viewHolder.time  = (TextView) view.findViewById(R.id.time_view_location_txtView);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }
         Locations location = locationsList.get(i);
        viewHolder.latitude.setText(location.getLatitude());
        viewHolder.longitude.setText(location.getLongitude());
        viewHolder.time.setText(location.getCurrentTime());
        return view;
    }

    public  class ViewHolder{
        TextView latitude, longitude, time;

    }
}
