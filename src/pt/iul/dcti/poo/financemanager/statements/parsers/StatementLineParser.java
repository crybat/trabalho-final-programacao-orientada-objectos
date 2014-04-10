package pt.iul.dcti.poo.financemanager.statements.parsers;

import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public interface StatementLineParser<T> {

    public StatementLine parseStatementLine(T line) throws Exception;

}
