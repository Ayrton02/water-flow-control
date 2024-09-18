package domain.entities;

import core.BaseEntity;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;
import domain.valueobjects.Volume;
import domain.valueobjects.WaterFlow;

public abstract class WaterPump<T extends Volume<T>> extends BaseEntity {
    private WaterFlow<T> waterFlow;
    private WaterContainer<T> container;
    private WaterSource<T> source;
    protected WaterPump(
            ID id,
            WaterFlow<T> waterFlow,
            WaterContainer<T> container,
            WaterSource<T> source
    ) {
        super(id);
        this.waterFlow = waterFlow;
        this.source = source;
        this.container = container;
    }

    protected WaterPump(
            WaterFlow<T> waterFlow,
            WaterContainer<T> container,
            WaterSource<T> source
    ) {
        super(UUID.generate());
        this.waterFlow = waterFlow;
        this.source = source;
        this.container = container;
    }

}
