package br.upf.projetojfprimefaces.filter;

import br.upf.projetojfprimefaces.entity.UsuarioEntity;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AutenticacaoFilter", urlPatterns = {"/faces/*"})
public class AutenticacaoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        boolean paginaLogin = uri.endsWith("/faces/login.xhtml") || uri.endsWith("/faces/");
        HttpSession session = req.getSession(false);
        UsuarioEntity usuario = session == null ? null : (UsuarioEntity) session.getAttribute("usuarioLogado");
        boolean administrador = usuario != null && usuario.isAdministrador();

        if (paginaLogin || administrador) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
        }
    }

    @Override
    public void destroy() {}
}
