package br.upf.projetojfprimefaces.entity;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaEntity implements Serializable {
    private int id;
    private String nome;
    private String descricao;
    private boolean ativa = true;

    public CategoriaEntity() {}

    public CategoriaEntity(int id, String nome, String descricao, boolean ativa) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativa = ativa;
    }

    public CategoriaEntity(CategoriaEntity other) {
        this(other.id, other.nome, other.descricao, other.ativa);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaEntity)) return false;
        CategoriaEntity that = (CategoriaEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
