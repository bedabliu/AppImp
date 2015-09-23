package bw.com.br.appImp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by bedab on 22/09/2015.
 */
public class Turma implements Serializable{
    private String nomeTurma;
    private String urlTurma;

    public Turma(JSONObject turma){
        try {
            this.setNomeTurma(turma.getString("nome"));
            this.setUrlTurma(turma.getString("url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getUrlTurma() {
        return urlTurma;
    }

    public void setUrlTurma(String urlTurma) {
        this.urlTurma = urlTurma;
    }
}
