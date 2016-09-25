package netroxtech.com.employeetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import netroxtech.com.employeetracker.Models.CallLogs;
import netroxtech.com.employeetracker.R;

/**
 * Created by mac on 9/25/2016.
 */
public class CallAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mlayoutInfulator;
    List<CallLogs> callLogsList;
    public CallAdapter(Context context, List<CallLogs> calllogsL){
        this.context =  context;
        callLogsList = calllogsL;
        mlayoutInfulator =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return callLogsList.size();
    }

    @Override
    public Object getItem(int i) {
        return callLogsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view==null){
            view = mlayoutInfulator.inflate(R.layout.calllog_list,null);
            viewHolder.mobile =  (TextView)view.findViewById(R.id.mobile_calllog_list);
            viewHolder.type =  (TextView)view.findViewById(R.id.callTytpe_calllog_list);
            viewHolder.date =  (TextView)view.findViewById(R.id.date_calllog_list);
            viewHolder.duration =  (TextView)view.findViewById(R.id.duration_calllog_list);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        CallLogs logs = callLogsList.get(i);
        viewHolder.mobile.setText("Mobile: "+logs.getPhoneNumber());
        viewHolder.date.setText("Time "+logs.getCallDate());
        viewHolder.duration.setText("Duration: "+logs.getCallDuration());
        viewHolder.type.setText("Type: "+logs.getCallType());


        return view;
    }
    class  ViewHolder{
        TextView mobile, type,date,duration;
    }
}
