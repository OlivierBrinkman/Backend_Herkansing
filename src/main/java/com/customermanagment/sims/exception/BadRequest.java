package com.customermanagment.sims.exception;
/**
 * Bad_Request Exception
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public class BadRequest extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * bad request
     */
    public BadRequest() {
        super("Request is not valid.");
    }

}