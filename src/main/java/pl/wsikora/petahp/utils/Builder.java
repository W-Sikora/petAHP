package pl.wsikora.petahp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Builder<T> {

    protected final List<Consumer<T>> operations;

    protected Builder() {
        this.operations = new ArrayList<>();
    }

    public T build() {
        T object = formObject();
        operations.forEach(operation -> operation.accept(object));
        return object;
    }

    protected abstract T formObject();

}
