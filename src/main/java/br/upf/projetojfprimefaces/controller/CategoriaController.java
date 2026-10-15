package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.CategoriaEntity;
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
public class CategoriaController implements Serializable {
    @Inject
    private EstoqueStore store;
    private CategoriaEntity categoria = new CategoriaEntity();
    private CategoriaEntity selected;
    private String busca;

    public void prepararNovo() { categoria = new CategoriaEntity(); categoria.setAtiva(true); }

    public void salvar() {
        categoria.setId(store.nextCategoriaId());
        store.getCategorias().add(categoria);
        mensagem("Categoria cadastrada com sucesso.");
        prepararNovo();
    }

    public void prepararEdicao() {
        if (selected != null) categoria = new CategoriaEntity(selected);
    }

    public void atualizar() {
        CategoriaEntity original = store.findCategoria(categoria.getId());
        if (original != null) {
            original.setNome(categoria.getNome());
            original.setDescricao(categoria.getDescricao());
            original.setAtiva(categoria.isAtiva());
            mensagem("Categoria atualizada com sucesso.");
        }
    }

    public void salvarOuAtualizar() {
        if (categoria.getId() == 0) salvar(); else atualizar();
    }

    public void excluir() {
        if (selected == null) return;
        boolean emUso = store.getProdutos().stream().anyMatch(p -> p.getCategoria() != null && p.getCategoria().getId() == selected.getId());
        if (emUso) {
            erro("Não é possível excluir uma categoria vinculada a produtos.");
            return;
        }
        store.getCategorias().remove(selected);
        selected = null;
        mensagem("Categoria excluída com sucesso.");
    }

    public List<CategoriaEntity> getCategoriasFiltradas() {
        if (busca == null || busca.trim().isEmpty()) return store.getCategorias();
        String termo = busca.toLowerCase(Locale.ROOT);
        List<CategoriaEntity> out = new ArrayList<>();
        for (CategoriaEntity c : store.getCategorias()) {
            if (c.getNome().toLowerCase(Locale.ROOT).contains(termo) ||
                (c.getDescricao() != null && c.getDescricao().toLowerCase(Locale.ROOT).contains(termo))) out.add(c);
        }
        return out;
    }

    private void mensagem(String texto) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", texto)); }
    private void erro(String texto) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", texto)); }
    public CategoriaEntity getCategoria() { return categoria; }
    public void setCategoria(CategoriaEntity categoria) { this.categoria = categoria; }
    public CategoriaEntity getSelected() { return selected; }
    public void setSelected(CategoriaEntity selected) { this.selected = selected; }
    public String getBusca() { return busca; }
    public void setBusca(String busca) { this.busca = busca; }
}
