package br.com.OS.enums;

public enum EnumPrioridade {

    Baixa("Baixa"),
    Media("Média"),
    Alta("Alta");

    private String descricao;

    EnumPrioridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}