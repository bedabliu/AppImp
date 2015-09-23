package bw.com.br.appImp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bedab on 21/09/2015.
 */
public class UnidadeItem{
    private boolean showNotify;
    private String title;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UnidadeItem() {

    }

    public UnidadeItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
