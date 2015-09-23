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
import bw.com.br.appImp.activity.TurmasFragment;
import bw.com.br.appImp.model.Curso;
import bw.com.br.appImp.model.Turma;

/**
 * Created by bedab on 21/09/2015.
 */
public class ClassSelectAdapter extends RecyclerView.Adapter<ClassSelectAdapter.MyViewHolder>  {

    List<Curso> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;
    private Fragment mFragment;
    private Bundle mBundle;

    public ClassSelectAdapter(Context context, List<Curso> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void updateAll(List<Curso> lista){
        data = lista;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.unidade_row, parent, false);
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
        final Curso current = data.get(position);
        holder.title.setText(current.getNomeCurso());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(mContext, "#" + position + " - " + current.getNomeCurso() + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "#" + position + " - " + current.getNomeCurso(), Toast.LENGTH_SHORT).show();
                    fragmentJump(current);
                }
            }
        });
    }

    private void fragmentJump(Curso curso) {
        mFragment = new TurmasFragment();
        mBundle = new Bundle();
        mBundle.putSerializable("turmas", curso);
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

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.nome_unidade);
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
