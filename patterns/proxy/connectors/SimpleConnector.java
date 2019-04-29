package by.it.mazniou.pattern.proxy.connectors;

public class SimpleConnector implements Connector {
    private String resourceString;

    public SimpleConnector(String resourceString) {
        this.resourceString = resourceString;
    }

    @Override
    public void connect() {
        System.out.println("Successfully connected to " + resourceString);
    }
}
