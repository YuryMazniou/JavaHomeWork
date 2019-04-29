package by.it.mazniou.pattern.proxy.connectors;


import by.it.mazniou.pattern.proxy.security.SecurityChecker;
import by.it.mazniou.pattern.proxy.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector {
    private String resourceString;
    private SecurityChecker securityChecker=new SecurityCheckerImpl();
    private SimpleConnector simpleConnector;

    public SecurityProxyConnector(String resourceString) {
        this.resourceString = resourceString;
        simpleConnector=new SimpleConnector(resourceString);
    }

    @Override
    public void connect() {
        if(securityChecker.performSecurityCheck()){
            simpleConnector.connect();
        }
    }
}
