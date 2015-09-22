package bw.com.br.appImp.model;

/**
 * Created by bedab on 21/09/2015.
 */
public class UnidadeItem {
    private boolean showNotify;
    private String title;


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
