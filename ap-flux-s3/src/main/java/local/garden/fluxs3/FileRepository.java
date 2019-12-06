package local.garden.fluxs3;

import reactor.core.publisher.Mono;

public interface FileRepository {
    public Mono<Void> save(FileInfo info);
}