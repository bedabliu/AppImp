package bw.com.br.appImp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import bw.com.br.appImp.R;
import bw.com.br.appImp.adapter.UnitySelectAdapter;
import bw.com.br.appImp.model.UnidadeItem;

/**
 * Created by f9342808 on 18/09/15.
 */
public class FriendsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private UnitySelectAdapter adapter;
    private static String[] unidadesNomes = null;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unidadesNomes =  getActivity().getResources().getStringArray(R.array.unidades_nomes);

    }

    public static List<UnidadeItem> getData() {
        List<UnidadeItem> data = new ArrayList<>();


        // preparing navigation drawer items
        for (int i = 0; i < unidadesNomes.length; i++) {
            UnidadeItem item = new UnidadeItem();
            item.setTitle(unidadesNomes[i]);
            data.add(item);
        }
        return data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        getActivity().findViewById(R.id.fab_menu).setVisibility(View.VISIBLE);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new UnitySelectAdapter(getActivity(), getData());
        mRecyclerView.setAdapter(adapter);
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
