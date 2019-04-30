package by.it.mazniou.parserLogs.query;

import java.util.Set;

public interface QLQuery {
    Set<Object> execute(String query);
}