package c4q.nyc.bookstore;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddsFragment extends Fragment {

    View v;
    FrameLayout frameContainer;
    TextView closeAdd;
    public AddsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_adds, container, false);
        frameContainer=(FrameLayout)v.findViewById(R.id.frameContainer);
        closeAdd=(TextView)v.findViewById(R.id.closeAdd);
        closeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADDS==", "onClick: ADDS CLoCKED");
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frameContainer)).commit();
                // getActivity().finish();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }


}
