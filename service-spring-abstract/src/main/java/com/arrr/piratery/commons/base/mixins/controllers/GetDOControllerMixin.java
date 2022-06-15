package com.arrr.piratery.commons.base.mixins.controllers;

import com.arrr.piratery.commons.base.controllers.BaseController;
import com.arrr.piratery.commons.base.mixins.norm.GetDOMixin;
import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface GetDOControllerMixin<PO extends Entity, DO extends Entity> extends
    BaseController {

    GetDOMixin<PO, DO> getService();

    default Mono<ResponseEntity<Flux<DO>>> getAll() {
        return Mono.just(ResponseEntity.ok(getService().getAllDOs()));
    }

    default Mono<ResponseEntity<DO>> get(@PathVariable String id) {
        return getService().getDO(id).map(ResponseEntity::ok);
    }

}
