package br.com.uniamerica.api.entity;

public enum TipoAtendimento {
    PARTICULAR("Particular"), CONVENIO("Convenio");

    private String descricao;

    TipoAtendimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}