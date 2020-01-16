package netdemo;

import java.net.*;
import java.io.*;

public class ParseUrl {
    public static void main(String[] args) throws Exception {

        URL aURL = new URL("http://trayan@example.com:80/docs/books/tutorial"
                           + "/index.html?name=networking&mode=edit#DOWNLOADING");

        System.out.println("protocol = " + aURL.getProtocol());
        System.out.println("authority = " + aURL.getAuthority());
        System.out.println("host = " + aURL.getHost());
        System.out.println("port = " + aURL.getPort());
        System.out.println("path = " + aURL.getPath());
        System.out.println("query = " + aURL.getQuery());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("ref = " + aURL.getRef());
    }
}
