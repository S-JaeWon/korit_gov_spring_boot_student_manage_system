package com.korit.student_manage_system.security.filter;

import com.korit.student_manage_system.entity.User;
import com.korit.student_manage_system.repository.UserRepository;
import com.korit.student_manage_system.security.jwt.JwtUtils;
import com.korit.student_manage_system.security.model.Principal;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        List<String> methods =List.of("POST", "PUT", "PATCH", "DELETE");
        if (!methods.contains(request.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String uri = request.getRequestURI();
        if (uri.startsWith("/auth/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String authorization = request.getHeader("Authorization");

        if (jwtUtils.isBearer(authorization)) {
            String accessToken = jwtUtils.removeBearer(authorization);

            try {
                Claims claims = jwtUtils.getClaims(accessToken);
                Integer userId = Integer.parseInt(claims.getId());

                userRepository.foundByUserId(userId).ifPresent(user -> {
                    Principal principal = Principal.builder()
                            .userId(user.getUserId())
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .age(user.getAge())
                            .userRoles(user.getUserRoles())
                            .build();

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
                // 사용자를 못 찾으면 그냥 인증 안 된 상태로 두고, 나중에 Security가 401/403 처리

            } catch (io.jsonwebtoken.JwtException e) {
                // 토큰이 만료/조작/형식 오류 등인 경우
                e.printStackTrace(); // 또는 log.warn("JWT 인증 실패", e);
            } catch (NumberFormatException e) {
                // claims.getId() 가 숫자가 아닐 때
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
