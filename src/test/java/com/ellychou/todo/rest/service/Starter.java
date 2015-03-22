package com.ellychou.todo.rest.service;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author Shan Zhou
 */
public class Starter {
    private static final Logger log = Logger.getLogger(Starter.class);


    public static void main(String[] args) throws Exception {
        int timeout = 60 * 60 * 1000;

        Server server = new Server();
        SocketConnector connector = new SocketConnector();

        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(timeout);
        connector.setSoLingerTime(-1);
        connector.setPort(8888);
        server.addConnector(connector);

        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setWar("src/main/webapp");


        server.setHandler(bb);

        try {
            log.info(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
            log.info(">>> STOPPING EMBEDDED JETTY SERVER");
            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
