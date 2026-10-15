package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.CategoriaEntity;
import br.upf.projetojfprimefaces.entity.FornecedorEntity;
import br.upf.projetojfprimefaces.entity.ProdutoEntity;
import br.upf.projetojfprimefaces.enums.StatusProduto;
import br.upf.projetojfprimefaces.store.EstoqueStore;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Named
@SessionScoped
public class ProdutoController implements Serializable {
    @Inject
    private EstoqueStore store;
    private ProdutoEntity produto = new ProdutoEntity();
    private ProdutoEntity selected;
    private Integer categoriaId;
    private Integer fornecedorId;
    private String busca;

    public void prepararNovo() {
        produto = new ProdutoEntity();
        produto.setStatus(StatusProduto.ATIVO);
        categoriaId = null;
        fornecedorId = null;
    }

    public void salvar() {
        vincularRelacionamentos();
        produto.setId(store.nextProdutoId());
        store.getProdutos().add(produto);
        mensagem("Produto cadastrado com sucesso.");
        prepararNovo();
    }

    public void prepararEdicao() {
        if (selected != null) {
            produto = new ProdutoEntity(selected);
            categoriaId = selected.getCategoria() == null ? null : selected.getCategoria().getId();
            fornecedorId = selected.getFornecedor() == null ? null : selected.getFornecedor().getId();
        }
    }

    public void atualizar() {
        vincularRelacionamentos();
        ProdutoEntity original = store.findProduto(produto.getId());
        if (original != null) {
            original.setCodigo(produto.getCodigo());
            original.setNome(produto.getNome());
            original.setDescricao(produto.getDescricao());
            original.setPreco(produto.getPreco());
            original.setEstoqueAtual(produto.getEstoqueAtual());
            original.setEstoqueMinimo(produto.getEstoqueMinimo());
            original.setStatus(produto.getStatus());
            original.setCategoria(produto.getCategoria());
            original.setFornecedor(produto.getFornecedor());
            mensagem("Produto atualizado com sucesso.");
        }
    }

    public void salvarOuAtualizar() {
        if (produto.getId() == 0) salvar(); else atualizar();
    }

    public void excluir() {
        if (selected == null) return;
        store.getProdutos().remove(selected);
        selected = null;
        mensagem("Produto excluído com sucesso.");
    }

    private void vincularRelacionamentos() {
        CategoriaEntity categoria = categoriaId == null ? null : store.findCategoria(categoriaId);
        FornecedorEntity fornecedor = fornecedorId == null ? null : store.findFornecedor(fornecedorId);
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);
    }

    public List<ProdutoEntity> getProdutosFiltrados() {
        if (busca == null || busca.trim().isEmpty()) return store.getProdutos();
        String termo = busca.toLowerCase(Locale.ROOT);
        List<ProdutoEntity> out = new ArrayList<>();
        for (ProdutoEntity p : store.getProdutos()) {
            if (p.getNome().toLowerCase(Locale.ROOT).contains(termo) ||
                p.getCodigo().toLowerCase(Locale.ROOT).contains(termo) ||
                (p.getCategoria() != null && p.getCategoria().getNome().toLowerCase(Locale.ROOT).contains(termo))) out.add(p);
        }
        return out;
    }

    public List<CategoriaEntity> getCategorias() { return store.getCategorias(); }
    public List<FornecedorEntity> getFornecedores() { return store.getFornecedores(); }
    public StatusProduto[] getStatusDisponiveis() { return StatusProduto.values(); }
    private void mensagem(String texto) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", texto)); }
    public ProdutoEntity getProduto() { return produto; }
    public void setProduto(ProdutoEntity produto) { this.produto = produto; }
    public ProdutoEntity getSelected() { return selected; }
    public void setSelected(ProdutoEntity selected) { this.selected = selected; }
    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
    public Integer getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Integer fornecedorId) { this.fornecedorId = fornecedorId; }
    public String getBusca() { return busca; }
    public void setBusca(String busca) { this.busca = busca; }
}
