package com.softniac.connectfour.core.security.api;

import com.softniac.connectfour.core.model.UserAccount;

public interface SecurityContextService {

    public UserAccount getCurrentUserAccount();

}
