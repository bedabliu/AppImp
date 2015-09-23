package bw.com.br.appImp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    List<Aula> listaDeAulas = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;
    private Fragment mFragment;
    private Bundle mBundle;

    public GradeSelectAdapter(Context context, List<Aula> listaDeAulas) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.listaDeAulas = listaDeAulas;
        Collections.sort(listaDeAulas, new CustomComparator());
    }

    public void delete(int position) {
        listaDeAulas.remove(position);
        notifyItemRemoved(position);
    }

    public void updateAll(List<Aula> lista) {
        listaDeAulas = lista;
        notifyDataSetChanged();
    }

    public void organizaGrade(){
        Collections.sort(this.listaDeAulas, new CustomComparator());
    }

    public class CustomComparator implements Comparator<Aula> {
        @Override
        public int compare(Aula aula1, Aula aula2) {
            Integer dataAula1 = Integer.parseInt(aula1.getDia());
            Integer dataAula2 = Integer.parseInt(aula2.getDia());
            return dataAula1 > dataAula2 ? -1 : dataAula1 < dataAula2 ? 1 : 0;
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grade_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Aula current = listaDeAulas.get(position);
        String dataHoje = android.text.format.DateFormat.format("MMdd", new java.util.Date()).toString();
        holder.data.setText("");
        holder.diaDaSemana.setText("");
        holder.materia.setText("");
        holder.professor.setText("");
        holder.aulasDadas.setText("");
        holder.aulaTotal.setText("");
        if (current.getDia() != null) {
            SimpleDateFormat format = new SimpleDateFormat("MMdd");
            SimpleDateFormat formatDayOfWeek = new SimpleDateFormat("EEEE");
            try {
                Date date = format.parse(current.getDia());
                holder.diaDaSemana.setText(formatDayOfWeek.format(date));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (dataHoje.equals(current.getDia())) {
//                holder.mCardView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
            }
            holder.data.setText(current.getDia().substring(2) + "/" + current.getDia().substring(0, 2));
        }
        if (current.getMateria() != null)
            holder.materia.setText(current.getMateria());
        if (current.getProfessor() != null) {
            holder.professor.setText(current.getProfessor());
        } else {
//            holder.mCardView.setBackgroundColor(mContext.getResources().getColor(R.color.half_black));
        }
        if (current.getAulasDadas() != null)
            holder.aulasDadas.setText(current.getAulasDadas().substring(0, 1));
        if (current.getAulasTotal() != null)
            holder.aulaTotal.setText(current.getAulasTotal().split(" ")[0]);
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
        return listaDeAulas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView data;
        TextView diaDaSemana;
        TextView materia;
        TextView professor;
        TextView aulasDadas;
        TextView aulaTotal;
        CardView mCardView;

        private ItemClickListener clickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv);
            data = (TextView) itemView.findViewById(R.id.data_grade);
            diaDaSemana = (TextView) itemView.findViewById(R.id.dia_da_semana_grade);
            materia = (TextView) itemView.findViewById(R.id.materia_grade);
            professor = (TextView) itemView.findViewById(R.id.professor_grade);
            aulasDadas = (TextView) itemView.findViewById(R.id.aulas_dadas_grade);
            aulaTotal = (TextView) itemView.findViewById(R.id.aulas_total_grade);

        }

    }
}
