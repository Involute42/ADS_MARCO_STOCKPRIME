package br.upf.projetojfprimefaces.entity;

import java.io.Serializable;

public class UsuarioEntity implements Serializable {
    private String nome;
    private String email;
    private String senha;
    private boolean administrador;

    public UsuarioEntity() {}

    public UsuarioEntity(String nome, String email, String senha, boolean administrador) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public boolean isAdministrador() { return administrador; }
    public void setAdministrador(boolean administrador) { this.administrador = administrador; }
}
