package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.ProdutoEntity;
import br.upf.projetojfprimefaces.store.EstoqueStore;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.math.BigDecimal;
import java.util.List;

@Named
@RequestScoped
public class DashboardController {
    @Inject
    private EstoqueStore store;

    public int getTotalProdutos() { return store.getProdutos().size(); }
    public int getTotalCategorias() { return store.getCategorias().size(); }
    public int getTotalFornecedores() { return store.getFornecedores().size(); }
    public int getProdutosEstoqueBaixo() { return store.getProdutosEstoqueBaixo().size(); }
    public List<ProdutoEntity> getListaEstoqueBaixo() { return store.getProdutosEstoqueBaixo(); }
    public int getTotalItensEstoque() { return store.getProdutos().stream().mapToInt(ProdutoEntity::getEstoqueAtual).sum(); }

    public BigDecimal getValorTotalEstoque() {
        return store.getProdutos().stream()
                .filter(produto -> produto.getPreco() != null)
                .map(produto -> produto.getPreco().multiply(BigDecimal.valueOf(produto.getEstoqueAtual())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
