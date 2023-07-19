//package com.project.accomatch.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class LogoutService implements LogoutHandler {
//
//    @Override
//    public void logout(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Authentication authentication
//    ) {
//        final String authHeader = request.getHeader("Authorization");
//        String jwt = null;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        // Extract the JWT token from the Authorization header
//        jwt = authHeader.substring(7);
//
//        // Clear the security context
//        SecurityContextHolder.clearContext();
//    }
//}