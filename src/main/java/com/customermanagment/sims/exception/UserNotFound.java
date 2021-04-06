package com.customermanagment.sims.exception;
/**
 * User_Not_Found Exception
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public class UserNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * user not found
     */
    public UserNotFound() {
        super("Cannot find specified user.");
    }

}
