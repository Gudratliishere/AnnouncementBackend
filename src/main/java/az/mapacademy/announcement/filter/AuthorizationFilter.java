package az.mapacademy.announcement.filter;

import az.mapacademy.announcement.dto.BaseResponse;
import az.mapacademy.announcement.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * @author : Dunay Gudratli
 * @since : 27.03.2025
 **/
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final MessageSource messageSource;

    private static final Set<String> SERVLET_PATHS = Set.of("/admin/auth",
            "/customer/auth",
            "/customer/file",
            "/customer/intro-page",
            "/customer/customer-profession",
            "/customer/default-file",
            "/v3/api-docs",
            "/swagger-ui");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
//        for (String path : SERVLET_PATHS)
//            if (request.getServletPath().startsWith(path)) {
//                filterChain.doFilter(request, response);
//                return;
//            }

        final String authorizationHeader = request.getHeader("Authorization");//Bearer tokensdfds
        final String prefix = "Bearer ";

        if (authorizationHeader == null || !authorizationHeader.startsWith(prefix)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    getBaseResponse("Authorization header is invalid"));
        } else {
            String token = authorizationHeader.substring(prefix.length());//tokensfdsf
            try {
                if (jwtService.isAccessTokenValid(token)) {
                    String username = jwtService.extractUsername(token);

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                username, null, null);
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        filterChain.doFilter(request, response);
                        return;
                    }
                }
            } catch (ExpiredJwtException ex) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),
                        getBaseResponse(ex.getMessage()));
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),
                    getBaseResponse("JWT token is invalid"));
        }
    }

    private BaseResponse<Void> getBaseResponse(String message) {
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(message);
        return baseResponse;
    }
}
