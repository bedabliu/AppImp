package bw.com.br.appImp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bw.com.br.appImp.R;
import bw.com.br.appImp.adapter.MinhaTurmaSelectAdapter;
import bw.com.br.appImp.adapter.TurmaSelectAdapter;
import bw.com.br.appImp.model.Curso;
import bw.com.br.appImp.model.Turma;

/**
 * Created by f9342808 on 18/09/15.
 */
public class MinhasTurmasFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MinhaTurmaSelectAdapter adapter;

    public MinhasTurmasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_minhas_turmas, container, false);
        final List<Turma> listaTurmas = new ArrayList<Turma>();

        listaTurmas.addAll(((Curso) getArguments().getSerializable("turmas")).getTurmas());
        getActivity().findViewById(R.id.fab_menu).setVisibility(View.GONE);

        ((MainActivity)getActivity()).changeActionBarTitle("Codigo da Turma");

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new MinhaTurmaSelectAdapter(getActivity(), listaTurmas);
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
