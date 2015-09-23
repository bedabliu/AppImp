package bw.com.br.appImp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bedab on 23/09/2015.
 */
public class Grade {
    private String curso;
    private String ultimaAtualizacao;
    private String turma;
    private List<Aula> aulas = new ArrayList<Aula>();

    public Grade(JSONObject gradeJson){
        try {
            setCurso(gradeJson.getString("curso"));
            setUltimaAtualizacao(gradeJson.getJSONObject("ultimaAtualizacao").getString("dia"));
            setTurma(gradeJson.getString("turma"));
            JSONArray jsonArray = new JSONArray(gradeJson.getJSONArray("curso").toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                aulas.add(new Aula(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
}
