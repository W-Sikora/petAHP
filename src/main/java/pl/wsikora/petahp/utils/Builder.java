package pl.wsikora.petahp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Builder<T> {
    private final List<Consumer<T>> operations;
    private final T object;

    protected Builder(T object) {
        this.object = object;
        this.operations = new ArrayList<>();
    }

    protected void add(Consumer<T> operation) {
        operations.add(operation);
    }

    public T build() {
        operations.forEach(operation -> operation.accept(object));
        return object;
    }

}
