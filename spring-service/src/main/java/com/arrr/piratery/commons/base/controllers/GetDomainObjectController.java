package com.arrr.piratery.commons.base.controllers;

import com.arrr.piratery.commons.base.services.GetDOMixin;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;


public interface GetDomainObjectController<PO extends PersistenceObject, DO extends DomainObject> extends
        BaseController {

    GetDOMixin<PO, DO> getService();

    String getContext();

    default URI toURI(DO domainObject) {
        return URI.create(BASE_PATH + "/" + getContext() + "/" + domainObject.getId());
    }

    @GetMapping()
    default Mono<ResponseEntity<Flux<DO>>> getAll() {
        return Mono.just(ResponseEntity.ok(getService().getAllDOs()));
    }

    @GetMapping("/{id}")
    default Mono<ResponseEntity<DO>> get(@PathVariable String id) {
        return getService().getDO(id).map(ResponseEntity::ok);
    }

}
