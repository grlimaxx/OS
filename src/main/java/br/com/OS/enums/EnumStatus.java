package br.com.OS.enums;

public enum EnumStatus {

    Aberto("Aberto"),
    Fechado("Fechado");

    private String descricao;

    EnumStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}
