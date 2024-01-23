package decorator;

import model.Providers;

public class ProvidersDecoratorImplementation implements ProvidersDecorator{
    private final Providers providers;

    public ProvidersDecoratorImplementation(Providers providers) {
        this.providers = providers;
    }

    @Override
    public String displayInfo() {
        return "Decorated Information: " + providers.toString();
    }
}

