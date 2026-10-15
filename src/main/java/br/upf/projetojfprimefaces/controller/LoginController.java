package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.UsuarioEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    private UsuarioEntity usuario;
    private boolean autenticado;

    @PostConstruct
    public void init() {
        usuario = new UsuarioEntity();
    }

    public String validarLogin() {
        if ("admin@estoque.com".equalsIgnoreCase(usuario.getEmail()) && "123".equals(usuario.getSenha())) {
            usuario = new UsuarioEntity("Administrador", "admin@estoque.com", null, true);
            autenticado = true;
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getSessionMap().put("usuarioLogado", usuario);
            return "/index.xhtml?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login", "E-mail ou senha incorretos."));
        return null;
    }

    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
    }

    public UsuarioEntity getUsuario() { return usuario; }
    public void setUsuario(UsuarioEntity usuario) { this.usuario = usuario; }
    public boolean isAutenticado() { return autenticado; }
}
