package br.com.sseemitter.model;

public class Emissor {

    String idTag;
    String valorTag;

    public Emissor(String idTag) {
        this.idTag = idTag;
    }

    public String getIdTag() {
        return idTag;
    }

    public String getValorTag() {
        return valorTag;
    }

    public void setValorTag(String valorTag) {
        this.valorTag = valorTag;
    }
}
