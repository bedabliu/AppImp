package bw.com.br.appImp.activity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import bw.com.br.appImp.R;
import bw.com.br.appImp.utils.GlobalVar;

/**
 * Created by f9342808 on 18/09/15.
 */
public class HomeFragment extends Fragment {

    private GlobalVar global = GlobalVar.getInstance();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

//        TextView text = (TextView) rootView.findViewById(R.id.label);
        ImageView logo1 = (ImageView) rootView.findViewById(R.id.logo1);
        logo1.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.left_to_right));

        ImageView logo2 = (ImageView) rootView.findViewById(R.id.logo2);
        logo2.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_to_left));

        ImageView logo3 = (ImageView) rootView.findViewById(R.id.logo3);
        logo3.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.top_to_bottom));

        getActivity().findViewById(R.id.fab_menu).setVisibility(View.GONE);

//        if(global.getCurso() != null) {
//            if (global.getCurso().getTurmas().size() > 0) {
//                Fragment fg = new MinhasTurmasFragment();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("turmas", global.getCurso());
//                fg.setArguments(bundle);
//                ((MainActivity) getActivity()).switchContent(R.id.container_body, fg);
//            }
//        }

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
