package br.upf.projetojfprimefaces.entity;

import java.io.Serializable;
import java.util.Objects;

public class FornecedorEntity implements Serializable {
    private int id;
    private String razaoSocial;
    private String cnpj;
    private String email;
    private String telefone;
    private boolean ativo = true;

    public FornecedorEntity() {}

    public FornecedorEntity(int id, String razaoSocial, String cnpj, String email, String telefone, boolean ativo) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    public FornecedorEntity(FornecedorEntity other) {
        this(other.id, other.razaoSocial, other.cnpj, other.email, other.telefone, other.ativo);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FornecedorEntity)) return false;
        FornecedorEntity that = (FornecedorEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
