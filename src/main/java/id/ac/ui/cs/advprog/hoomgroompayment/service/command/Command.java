package id.ac.ui.cs.advprog.hoomgroompayment.service.command;

import java.util.concurrent.CompletableFuture;

public interface Command<T> {
    CompletableFuture<T> execute(T payload);
}