package pl.wsikora.petahp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class BuilderTemplate<T> {
    private final List<Consumer<T>> operations;
    private final T object;

    protected BuilderTemplate(T object) {
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
