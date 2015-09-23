package bw.com.br.appImp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bedab on 23/09/2015.
 */
public class Aula {
    private String materia;
    private String professor;
    private String dia;
    private String aulasDadas;
    private String aulasTotal;

    public Aula(JSONObject aulaJson) {
        try {
            if (aulaJson.has("materia"))
                this.setMateria(aulaJson.getString("materia"));
            if (aulaJson.has("professor"))
                this.setProfessor(aulaJson.getString("professor"));
            if (aulaJson.has("diaDaSemana")){
                String dataCompleta =  aulaJson.getString("diaDaSemana");
                String[] dataDividida = dataCompleta.split("/");
                String dataFormatada = dataDividida[1].trim() + dataDividida[0].trim();
                this.setDia(dataFormatada);
            }
            if (aulaJson.has("aulasDadas"))
                this.setAulasDadas(aulaJson.getString("aulasDadas"));
            if (aulaJson.has("aulasTotal"))
                this.setAulasTotal(aulaJson.getString("aulasTotal"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getAulasDadas() {
        return aulasDadas;
    }

    public void setAulasDadas(String aulasDadas) {
        this.aulasDadas = aulasDadas;
    }

    public String getAulasTotal() {
        return aulasTotal;
    }

    public void setAulasTotal(String aulasTotal) {
        this.aulasTotal = aulasTotal;
    }
}
