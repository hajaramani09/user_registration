package codingtechniques.Filter;

import java.io.IOException;
import java.util.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Transactional
@Component

@Order(1)
// Indique l'ordre d'exécution du filtre
public class JWTFilter extends OncePerRequestFilter {
	private static final Log LOG = LogFactory.getLog(JWTFilter.class);

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain)
			throws ServletException, IOException {

		Collections.list(req.getHeaderNames()).forEach((header -> {
			LOG.info("Header :{}={}" + header + req.getHeader(header));
		}));
		// Logique du filtre à cet endroit
		try {
			LOG.info("Démarrage d'une transaction pour demande : {}" + req.getRequestURI());
			// Logique du filtre à cet endroit
			chain.doFilter(req, res);
			LOG.info("Validation d'une transaction pour demande :{}" + req.getRequestURI());
			// Logique du filtre à cet endroit
			LOG.info("Demande de journalisation {}: " + req.getMethod() + req.getRequestURI());
			LOG.info("Réponse de journalisation : {}" + res.getContentType());
		} catch (Exception e) {
			LOG.error("Erreur dans le filtre", e);
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		chain.doFilter(req, res);

	}

}
