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
public class Curso implements Serializable{
    private String nomeCurso;
    private List<Turma> turmas = new ArrayList<Turma>();

    public Curso(){

    }

    public Curso(JSONObject curso){
        try {
            this.setNomeCurso(curso.getString("nome"));
            JSONArray jsonArray = new JSONArray(curso.getJSONArray("curso").toString());
            for(int i=0;i<jsonArray.length();i++){
                turmas.add(new Turma(jsonArray.getJSONObject(i)));
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
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public void setTurmas(JSONArray turmas) {
        for(int i=0;i<turmas.length();i++){
            try {
                this.turmas.add(new Turma(turmas.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTurma(Turma turma){
        this.turmas.add(turma);
    }

    public void removeTurma(Turma turma){
        this.turmas.remove(turma);
    }

    public JSONArray getTurmasJsonArray(){
        JSONArray array=new JSONArray();
        for(int i=0;i<turmas.size();i++){
            array.put(turmas.get(i));
        }
        return array;
    }
}
