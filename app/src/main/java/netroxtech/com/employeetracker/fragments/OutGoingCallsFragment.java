package netroxtech.com.employeetracker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import netroxtech.com.employeetracker.R;
import netroxtech.com.employeetracker.adapters.CallAdapter;
import netroxtech.com.employeetracker.utils.ApplicationFunctions;

public class OutGoingCallsFragment extends Fragment {
    ListView outGoingCallList;
    public OutGoingCallsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_out_going_calls, container, false);
        setXML(v);
        return v;
    }
    public void setXML(View  view){
        outGoingCallList = (ListView)view.findViewById(R.id.outgoingcall_framgmentIncoming);
        setAllInComingCalls();
    }
    public void setAllInComingCalls(){
        ApplicationFunctions aps = new ApplicationFunctions(getActivity());
        ListAdapter adapter = new CallAdapter(getActivity(), aps.getCallLogs(2));
        outGoingCallList.setAdapter(adapter);

    }

}
