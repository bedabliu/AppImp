package bw.com.br.appImp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import bw.com.br.appImp.R;
import bw.com.br.appImp.model.UnidadeItem;

/**
 * Created by bedab on 21/09/2015.
 */
public class UnitySelectAdapter extends RecyclerView.Adapter<UnitySelectAdapter.MyViewHolder>  {

    List<UnidadeItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public UnitySelectAdapter(Context context, List<UnidadeItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.unidade_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UnidadeItem current = data.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.nome_unidade);
        }
    }
}
