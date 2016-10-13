package netroxtech.com.employeetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import netroxtech.com.employeetracker.Models.PlayCalls;
import netroxtech.com.employeetracker.R;

/**
 * Created by mac on 9/30/2016.
 */
public class PlayCallAdapters extends BaseAdapter {
    private LayoutInflater mInflute;
    private Context mconetxt;
    private List<PlayCalls> playCallsList;
    public PlayCallAdapters(Context context, List<PlayCalls>  calls){
        this.mconetxt = context;
        this.playCallsList = calls;
        mInflute = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return playCallsList.size();
    }

    @Override
    public Object getItem(int i) {
        return  playCallsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
          if(view == null){
              view = mInflute.inflate(R.layout.play_call_list, null);
              holder.playListName = (TextView) view.findViewById(R.id.play_call_logs_name_text);
              holder.playListPath = (TextView) view.findViewById(R.id.play_call_logs_path_text);
              holder.playListTime = (TextView) view.findViewById(R.id.play_call_logs_time_text);
              view.setTag(holder);
          }
        else{
              holder = (ViewHolder) view.getTag();
          }
        PlayCalls playCalls = playCallsList.get(i);
        holder.playListName.setText(playCalls.getName());
        holder.playListTime.setText(playCalls.getTime());
        holder.playListPath.setText(playCalls.getAdddres());
        return view;
    }
    public class ViewHolder{
        public TextView playListName, playListPath, playListTime;
    }
}
