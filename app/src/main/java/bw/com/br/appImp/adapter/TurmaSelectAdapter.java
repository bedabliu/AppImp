package bw.com.br.appImp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import bw.com.br.appImp.ItemClickListener;
import bw.com.br.appImp.R;
import bw.com.br.appImp.activity.ClassFragment;
import bw.com.br.appImp.activity.HomeFragment;
import bw.com.br.appImp.activity.MainActivity;
import bw.com.br.appImp.activity.MinhasTurmasFragment;
import bw.com.br.appImp.model.Curso;
import bw.com.br.appImp.model.Turma;
import bw.com.br.appImp.utils.GlobalVar;

/**
 * Created by bedab on 21/09/2015.
 */
public class TurmaSelectAdapter extends RecyclerView.Adapter<TurmaSelectAdapter.MyViewHolder>  {

    List<Turma> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;
    private Fragment mFragment;
    private Bundle mBundle;

    public TurmaSelectAdapter(Context context, List<Turma> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void updateAll(List<Turma> lista){
        data = lista;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.turma_row, parent, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Teste", Toast.LENGTH_LONG).show();
//                fragmentJump();
//            }
//        });
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Turma current = data.get(position);
        holder.title.setText(current.getNomeTurma());
        if(GlobalVar.getInstance().hasTurma(current)){
            holder.mCardView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        }        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                builder.setTitle(current.getNomeTurma());
                builder.setMessage("Deseja adicionar a sua lista de cursos?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addCourseToSharedPrefs(current);
                        Toast.makeText(mContext, "Curso adicionado com sucesso!", Toast.LENGTH_LONG).show();
                        Fragment fragment = new MinhasTurmasFragment();
                        switchContent(R.id.container_body, fragment);
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
    }

    private void addCourseToSharedPrefs(Turma turma){
        SharedPreferences prefs = mContext.getSharedPreferences("meus_cursos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Curso curso;
        try {
            if(prefs.contains("cursos")) {
                curso = new Curso(new JSONObject(prefs.getString("cursos", null)));
            } else {
                curso = new Curso();
                curso.setNomeCurso("Meus Cursos");
            }
            curso.addTurma(turma);
            JSONObject cursoJson = new JSONObject();
            cursoJson.put("nome", curso.getNomeCurso());
            cursoJson.put("curso", curso.getTurmasJsonArray());
            editor.putString("cursos", cursoJson.toString());
            editor.commit();
            MainActivity mainActivity = (MainActivity) mContext;
            mainActivity.carregaVariavelGlobal();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fragmentJump(String url) {
        mFragment = new ClassFragment();
        mBundle = new Bundle();
        mBundle.putString("url", url);
        mFragment.setArguments(mBundle);
        switchContent(R.id.container_body, mFragment);
    }

    public void switchContent(int id, Fragment fragment) {
        if (mContext == null)
            return;
        if (mContext instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) mContext;
            Fragment frag = fragment;
            mainActivity.switchContent(id, frag);
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView title;
        private ItemClickListener clickListener;
        CardView mCardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.nome_campo);
            mCardView = (CardView) itemView.findViewById(R.id.cv);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getLayoutPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getLayoutPosition(), true);
            return true;
        }
    }
}
