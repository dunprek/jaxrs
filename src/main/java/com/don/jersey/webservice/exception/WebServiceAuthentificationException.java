package com.don.jersey.webservice.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

class WebServiceAuthetificationException extends WebApplicationException
{
    private static final long serialVersionUID = 2965837849358623718L;

    public WebServiceAuthetificationException(String msg)
    {
        super(Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized, " + msg).build());
    }

}
