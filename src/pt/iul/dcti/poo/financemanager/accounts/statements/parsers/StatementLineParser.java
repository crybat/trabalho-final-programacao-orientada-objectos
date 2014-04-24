package pt.iul.dcti.poo.financemanager.accounts.statements.parsers;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;

public interface StatementLineParser<T> {

    public StatementLine parseStatementLine(T line) throws Exception;

}
