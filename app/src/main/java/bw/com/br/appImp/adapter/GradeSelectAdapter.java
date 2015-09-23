package bw.com.br.appImp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import bw.com.br.appImp.ItemClickListener;
import bw.com.br.appImp.R;
import bw.com.br.appImp.activity.ClassFragment;
import bw.com.br.appImp.activity.MainActivity;
import bw.com.br.appImp.model.Aula;

/**
 * Created by bedab on 21/09/2015.
 */
public class GradeSelectAdapter extends RecyclerView.Adapter<GradeSelectAdapter.MyViewHolder> {

    List<Aula> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;
    private Fragment mFragment;
    private Bundle mBundle;

    public GradeSelectAdapter(Context context, List<Aula> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void updateAll(List<Aula> lista) {
        data = lista;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grade_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Aula current = data.get(position);
        if (current.getDia() != null)
            holder.data.setText(current.getDia().substring(0, 4));
        if (current.getMateria() != null)
            holder.materia.setText(current.getMateria());
        if (current.getProfessor() != null)
            holder.professor.setText(current.getProfessor());
        if (current.getAulasDadas() != null)
            holder.aulasDadas.setText(current.getAulasDadas());
        if (current.getAulasTotal() != null)
            holder.aulaTotal.setText(current.getAulasTotal());
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


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView data;
        TextView materia;
        TextView professor;
        TextView aulasDadas;
        TextView aulaTotal;

        private ItemClickListener clickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            data = (TextView) itemView.findViewById(R.id.data_grade);
            materia = (TextView) itemView.findViewById(R.id.materia_grade);
            professor = (TextView) itemView.findViewById(R.id.professor_grade);
            aulasDadas = (TextView) itemView.findViewById(R.id.aulas_dadas_grade);
            aulaTotal = (TextView) itemView.findViewById(R.id.aulas_total_grade);

        }

    }
}
