package br.upf.projetojfprimefaces.enums;

public enum StatusProduto {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    StatusProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
