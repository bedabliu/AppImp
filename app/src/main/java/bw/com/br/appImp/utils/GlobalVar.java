package bw.com.br.appImp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import bw.com.br.appImp.model.Curso;
import bw.com.br.appImp.model.Turma;

/**
 * Created by bedab on 22/09/2015.
 */
public class GlobalVar {

    private static GlobalVar instance;

    private Curso mCurso;

    public GlobalVar() {
    }

    public void setCurso(Curso curso) {
        this.mCurso = curso;
    }

    public Curso getCurso() {
        return this.mCurso;
    }

    public boolean hasTurma(Turma turma) {
        boolean achou = false;
        if (this.mCurso != null) {
            for (Turma turmas : this.mCurso.getTurmas()) {
                if (turma.getNomeTurma().equals(turmas.getNomeTurma()) && turma.getUrlTurma().equals(turmas.getUrlTurma())) {
                    achou = true;
                }
            }
        }
        return achou;
    }

    public static synchronized GlobalVar getInstance() {
        if (instance == null) {
            instance = new GlobalVar();
        }
        return instance;
    }


}
