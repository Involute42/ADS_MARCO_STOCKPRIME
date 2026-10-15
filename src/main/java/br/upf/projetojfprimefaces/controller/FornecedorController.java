package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.FornecedorEntity;
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
public class FornecedorController implements Serializable {
    @Inject
    private EstoqueStore store;
    private FornecedorEntity fornecedor = new FornecedorEntity();
    private FornecedorEntity selected;
    private String busca;

    public void prepararNovo() { fornecedor = new FornecedorEntity(); fornecedor.setAtivo(true); }

    public void salvar() {
        fornecedor.setId(store.nextFornecedorId());
        store.getFornecedores().add(fornecedor);
        mensagem("Fornecedor cadastrado com sucesso.");
        prepararNovo();
    }

    public void prepararEdicao() {
        if (selected != null) fornecedor = new FornecedorEntity(selected);
    }

    public void atualizar() {
        FornecedorEntity original = store.findFornecedor(fornecedor.getId());
        if (original != null) {
            original.setRazaoSocial(fornecedor.getRazaoSocial());
            original.setCnpj(fornecedor.getCnpj());
            original.setEmail(fornecedor.getEmail());
            original.setTelefone(fornecedor.getTelefone());
            original.setAtivo(fornecedor.isAtivo());
            mensagem("Fornecedor atualizado com sucesso.");
        }
    }

    public void salvarOuAtualizar() {
        if (fornecedor.getId() == 0) salvar(); else atualizar();
    }

    public void excluir() {
        if (selected == null) return;
        boolean emUso = store.getProdutos().stream().anyMatch(p -> p.getFornecedor() != null && p.getFornecedor().getId() == selected.getId());
        if (emUso) {
            erro("Não é possível excluir um fornecedor vinculado a produtos.");
            return;
        }
        store.getFornecedores().remove(selected);
        selected = null;
        mensagem("Fornecedor excluído com sucesso.");
    }

    public List<FornecedorEntity> getFornecedoresFiltrados() {
        if (busca == null || busca.trim().isEmpty()) return store.getFornecedores();
        String termo = busca.toLowerCase(Locale.ROOT);
        List<FornecedorEntity> out = new ArrayList<>();
        for (FornecedorEntity f : store.getFornecedores()) {
            if (f.getRazaoSocial().toLowerCase(Locale.ROOT).contains(termo) ||
                (f.getCnpj() != null && f.getCnpj().toLowerCase(Locale.ROOT).contains(termo))) out.add(f);
        }
        return out;
    }

    private void mensagem(String texto) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", texto)); }
    private void erro(String texto) { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", texto)); }
    public FornecedorEntity getFornecedor() { return fornecedor; }
    public void setFornecedor(FornecedorEntity fornecedor) { this.fornecedor = fornecedor; }
    public FornecedorEntity getSelected() { return selected; }
    public void setSelected(FornecedorEntity selected) { this.selected = selected; }
    public String getBusca() { return busca; }
    public void setBusca(String busca) { this.busca = busca; }
}
