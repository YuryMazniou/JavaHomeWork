package by.it.mazniou.pattern.proxy;

import by.it.mazniou.pattern.proxy.connectors.Connector;
import by.it.mazniou.pattern.proxy.connectors.SecurityProxyConnector;
import by.it.mazniou.pattern.proxy.connectors.SimpleConnector;

/*
Security Proxy
*/
public class Solution {
    public static void main(String[] args) {
        Connector securityProxyConnector = new SecurityProxyConnector("google.com");
        Connector simpleConnector = new SimpleConnector("javarush.ru");

        System.out.println("Connecting with SimpleConnector...");
        simpleConnector.connect();

        System.out.println("----------------------------------------------------");

        System.out.println("Connecting with SecurityProxyConnector...");
        securityProxyConnector.connect();
    }
}
