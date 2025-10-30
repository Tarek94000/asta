package fr.efrei.altn72.asta.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Pages publiques (pas besoin d’être connecté)
        if (uri.startsWith("/login") || uri.startsWith("/logout") || uri.startsWith("/error") || uri.startsWith("/css") || uri.startsWith("/js")) {
            return true;
        }

        // Si pas de session ou pas de prénom, redirige vers /login
        if (session == null || session.getAttribute("prenom") == null) {
            response.sendRedirect("/login");
            return false;
        }

        // Sinon, accès autorisé
        return true;
    }
}
