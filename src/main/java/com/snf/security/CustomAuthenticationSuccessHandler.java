package com.snf.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.snf.enums.TipoUsuario;
import com.snf.model.Usuario;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	static final Logger log = Logger.getLogger(CustomAuthenticationSuccessHandler.class);

	private static final String PATH_PAGINA_INICIAL_CAIXA = "/pages/home/inicio.xhtml";
	private static final String PATH_PAGINA_INICIAL_GERENTE = "/pages/home/inicioGerente.xhtml";
	private static final String PATH_PAGINA_LOGIN = "/login.xhtml";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		if (response.isCommitted()) {
			return;
		}

		try {
			Usuario usuarioLogado = (Usuario) auth.getDetails();
			String homePage = getHomePage(usuarioLogado);
			getRedirectStrategy().sendRedirect(request, response, homePage);
		} catch (Exception e) {
			log.error(e.toString());
			return;
		}

	}

	public String getHomePage(Usuario usuario) throws IOException {
		switch (usuario != null ? usuario.getTipo() : TipoUsuario.INEXISTENTE) {
		case FUNCIONARIO:
			return PATH_PAGINA_INICIAL_CAIXA;
		case CAIXA:
			return PATH_PAGINA_INICIAL_CAIXA;
		case GERENTE:
			return PATH_PAGINA_INICIAL_GERENTE;
		default:
			return PATH_PAGINA_LOGIN;
		}
	}

}
