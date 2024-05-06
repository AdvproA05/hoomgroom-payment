package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

public interface Command<T> {
    T execute(T payload);
}
