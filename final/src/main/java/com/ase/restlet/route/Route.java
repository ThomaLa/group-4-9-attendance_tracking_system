package com.ase.restlet.route;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.ase.restlet.resource.impl.StudentServerResource;

public class Route extends Application{ 
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/student/{studentEmail}",StudentServerResource.class );
        return router;
    }
}