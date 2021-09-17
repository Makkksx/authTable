package com.task4.authTable.Security.OAuth2;

import com.task4.authTable.models.Status;
import com.task4.authTable.models.User;
import com.task4.authTable.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        User user = userRepository.findFirstByEmailAndClientName(oAuth2User.getEmail(),
                oAuth2User.getClientName());
        if ( user == null) {
            user = new User();
            user.setUsername(oAuth2User.getName());
            user.setClientName(oAuth2User.getClientName());
            user.setEmail(oAuth2User.getEmail());
            user.setStatus(Status.ACTIVE);
            user.setFirstVisit(LocalDateTime.now());
        }
        user.setLastVisit(LocalDateTime.now());
        userRepository.save(user);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
