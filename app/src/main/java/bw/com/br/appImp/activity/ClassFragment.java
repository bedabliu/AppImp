package bw.com.br.appImp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bw.com.br.appImp.R;
import bw.com.br.appImp.adapter.ClassSelectAdapter;
import bw.com.br.appImp.model.Curso;
import bw.com.br.appImp.model.Turma;
import bw.com.br.appImp.model.UnidadeItem;
import bw.com.br.appImp.utils.MySingleton;

/**
 * Created by f9342808 on 18/09/15.
 */
public class ClassFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ClassSelectAdapter adapter;
    private static String[] unidadesNomes = null;
    private static String[] unidadesUrl = null;
    private ProgressDialog loadingDg;

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unidadesNomes =  getActivity().getResources().getStringArray(R.array.unidades_nomes);
        unidadesUrl =  getActivity().getResources().getStringArray(R.array.unidades_url);

    }

    public static List<UnidadeItem> getData() {
        List<UnidadeItem> data = new ArrayList<>();

        // preparing navigation drawer items
        for (int i = 0; i < unidadesNomes.length; i++) {
            UnidadeItem item = new UnidadeItem();
            item.setTitle(unidadesNomes[i]);
            item.setUrl(unidadesUrl[i]);
            data.add(item);
        }
        return data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_class, container, false);
        final List<Curso> listaCursos = new ArrayList<Curso>();

        getActivity().findViewById(R.id.fab_menu).setVisibility(View.GONE);

        ((MainActivity)getActivity()).changeActionBarTitle("Turmas");

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new ClassSelectAdapter(getActivity(), listaCursos);
        mRecyclerView.setAdapter(adapter);

        String url = "http://undeadpixel.com.br:8585/imp/getCourses/" + getArguments().getString("url");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingDg.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response.getJSONArray("grupos").toString());
                    for(int i=0;i<jsonArray.length();i++){
                        listaCursos.add(new Curso(jsonArray.getJSONObject(i)));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                loadingDg.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext(), R.style.MyAlertDialogStyle);
                builder.setTitle("Erro");
                builder.setMessage("Ocorreu um erro ao consultar o servidor...");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getFragmentManager().popBackStackImmediate();
                    }
                });
                builder.show();
            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this.getContext()).addToRequestQueue(jsObjRequest);
        loadingDg = new ProgressDialog(rootView.getContext());
        loadingDg.setTitle("Consultando Servidor");
        loadingDg.setMessage("Por Favor Aguarde...");
        loadingDg.setCancelable(false);
        loadingDg.setIndeterminate(true);
        loadingDg.show();

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
