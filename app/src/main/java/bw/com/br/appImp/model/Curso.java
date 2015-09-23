package bw.com.br.appImp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bedab on 22/09/2015.
 */
public class Curso implements Serializable {
    private String nomeCurso;
    private List<Turma> mTurmas = new ArrayList<Turma>();

    public Curso() {

    }

    public Curso(JSONObject curso) {
        try {
            this.setNomeCurso(curso.getString("nome"));
            JSONArray jsonArray = new JSONArray(curso.getJSONArray("curso").toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                mTurmas.add(new Turma(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public List<Turma> getTurmas() {
        return mTurmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.mTurmas = turmas;
    }

    public void setTurmas(JSONArray turmas) {
        for (int i = 0; i < turmas.length(); i++) {
            try {
                this.mTurmas.add(new Turma(turmas.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasTurma(Turma turma){
        boolean achou = false;
        for(Turma turmas : mTurmas){
            if(turma.getNomeTurma().equals(turmas.getNomeTurma()) && turma.getUrlTurma().equals(turmas.getUrlTurma())){
                achou = true;
            }
        }
        return achou;
    }

    public void addTurma(Turma turma) {
        if (!hasTurma(turma))
            this.mTurmas.add(turma);
    }

    public void removeTurma(Turma turma) {
        for(Turma turmas : mTurmas){
            if(turma.getNomeTurma().equals(turmas.getNomeTurma()) && turma.getUrlTurma().equals(turmas.getUrlTurma())){
                mTurmas.remove(turmas);
            }
        }
    }

    public JSONArray getTurmasJsonArray() {
        JSONArray array = new JSONArray();
        for (int i = 0; i < mTurmas.size(); i++) {
            JSONObject turmaJSON = new JSONObject();
            try {
                turmaJSON.put("nome", mTurmas.get(i).getNomeTurma());
                turmaJSON.put("url", mTurmas.get(i).getUrlTurma());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(turmaJSON);
        }
        return array;
    }

}
