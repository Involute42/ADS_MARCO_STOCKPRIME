package br.upf.projetojfprimefaces.entity;

import br.upf.projetojfprimefaces.enums.TipoMovimentacao;
import java.io.Serializable;
import java.util.Date;

public class MovimentacaoEntity implements Serializable {
    private int id;
    private ProdutoEntity produto;
    private TipoMovimentacao tipo;
    private int quantidade;
    private Date data;
    private String observacao;

    public MovimentacaoEntity() {}

    public MovimentacaoEntity(int id, ProdutoEntity produto, TipoMovimentacao tipo, int quantidade, Date data, String observacao) {
        this.id = id;
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
        this.observacao = observacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public ProdutoEntity getProduto() { return produto; }
    public void setProduto(ProdutoEntity produto) { this.produto = produto; }
    public TipoMovimentacao getTipo() { return tipo; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
