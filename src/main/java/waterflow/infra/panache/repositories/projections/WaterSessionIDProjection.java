package waterflow.infra.panache.repositories.projections;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record WaterSessionIDProjection(String id) { }
