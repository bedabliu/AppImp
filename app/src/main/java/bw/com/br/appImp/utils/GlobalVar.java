package bw.com.br.appImp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import bw.com.br.appImp.model.Curso;

/**
 * Created by bedab on 22/09/2015.
 */
public class GlobalVar {

    private static GlobalVar instance;

    private Curso mCurso;

    public GlobalVar(){}

    public void setCurso(Curso curso){
        this.mCurso = curso;
    }

    public Curso getCurso(){
        return this.mCurso;
    }

    public static synchronized GlobalVar getInstance(){
        if(instance==null){
            instance=new GlobalVar();
        }
        return instance;
    }


}
