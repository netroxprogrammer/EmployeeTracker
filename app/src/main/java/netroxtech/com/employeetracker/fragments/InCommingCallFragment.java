package netroxtech.com.employeetracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import netroxtech.com.employeetracker.Models.CallLogs;
import netroxtech.com.employeetracker.R;
import netroxtech.com.employeetracker.adapters.CallAdapter;
import netroxtech.com.employeetracker.utils.ApplicationFunctions;

public class InCommingCallFragment extends Fragment {
    ListView inComingList;


    public InCommingCallFragment() {
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
        View v = inflater.inflate(R.layout.fragment_in_comming_call, container, false);
        setXML(v);
        return v;
    }
    public void setXML(View  view){
        inComingList = (ListView)view.findViewById(R.id.incommingcall_framgmentIncoming);
        setAllInComingCalls();
    }
    public void setAllInComingCalls(){
        ApplicationFunctions  aps = new ApplicationFunctions(getActivity());
        ListAdapter adapter = new CallAdapter(getActivity(), aps.getCallLogs(1));
          inComingList.setAdapter(adapter);

    }
}