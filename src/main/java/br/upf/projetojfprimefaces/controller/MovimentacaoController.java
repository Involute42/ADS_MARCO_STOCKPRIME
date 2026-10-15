package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.MovimentacaoEntity;
import br.upf.projetojfprimefaces.entity.ProdutoEntity;
import br.upf.projetojfprimefaces.enums.StatusProduto;
import br.upf.projetojfprimefaces.enums.TipoMovimentacao;
import br.upf.projetojfprimefaces.store.EstoqueStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class MovimentacaoController implements Serializable {
    @Inject
    private EstoqueStore store;
    private Integer produtoId;
    private TipoMovimentacao tipo = TipoMovimentacao.ENTRADA;
    private int quantidade = 1;
    private String observacao;

    public void registrar() {
        ProdutoEntity produto = produtoId == null ? null : store.findProduto(produtoId);
        if (produto == null) {
            mensagem(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione um produto.");
            return;
        }
        if (quantidade <= 0) {
            mensagem(FacesMessage.SEVERITY_WARN, "Atenção", "A quantidade deve ser maior que zero.");
            return;
        }
        if (tipo == TipoMovimentacao.SAIDA && produto.getEstoqueAtual() < quantidade) {
            mensagem(FacesMessage.SEVERITY_ERROR, "Estoque insuficiente", "A saída é maior que o saldo atual do produto.");
            return;
        }

        if (tipo == TipoMovimentacao.ENTRADA) produto.setEstoqueAtual(produto.getEstoqueAtual() + quantidade);
        else produto.setEstoqueAtual(produto.getEstoqueAtual() - quantidade);

        store.getMovimentacoes().add(0, new MovimentacaoEntity(store.nextMovimentacaoId(), produto, tipo, quantidade, new Date(), observacao));
        mensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Movimentação registrada. Novo saldo: " + produto.getEstoqueAtual());
        produtoId = null;
        tipo = TipoMovimentacao.ENTRADA;
        quantidade = 1;
        observacao = null;
    }

    public List<ProdutoEntity> getProdutos() { return store.getProdutos(); }
    public List<MovimentacaoEntity> getMovimentacoes() { return store.getMovimentacoes(); }
    public TipoMovimentacao[] getTipos() { return TipoMovimentacao.values(); }
    private void mensagem(FacesMessage.Severity severidade, String titulo, String texto) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidade, titulo, texto));
    }
    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
    public TipoMovimentacao getTipo() { return tipo; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}
