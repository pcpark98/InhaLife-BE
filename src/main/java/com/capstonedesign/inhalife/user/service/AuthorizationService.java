package com.capstonedesign.inhalife.user.service;

import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.exception.ExpiredSessionException;
import com.capstonedesign.inhalife.user.exception.InsufficientPrivilegesException;
import com.capstonedesign.inhalife.user.exception.MissingCookieException;
import com.capstonedesign.inhalife.user.repository.UserRepository;
import com.capstonedesign.inhalife.user.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorizationService {

    public static void checkSession(Long userId) {
        String sessionCookie = SessionUtil.getSessionCookie();
        if(sessionCookie == null) throw new MissingCookieException();
        else if(SessionUtil.getAttribute(sessionCookie) == null) throw new ExpiredSessionException();
        else if(userId != Long.parseLong(SessionUtil.getAttribute(sessionCookie))) throw new InsufficientPrivilegesException();
    }
}
