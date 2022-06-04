package com.arrr.piratery.commons.base.error;

import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.util.Collection;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseEntityError<E extends PersistenceObject> implements EntityError<E> {

  protected final String entityType;
  protected final String entityTypeUpper;

  public BaseEntityError(String entityType) {
    if (entityType.endsWith("PO")) {
      entityType = entityType.substring(0, entityType.length() - 2);
    }

    this.entityType = entityType.substring(0, 1).toUpperCase() + entityType.substring(1);
    this.entityTypeUpper = entityType.toUpperCase();
  }

  public static <E extends PersistenceObject> BaseEntityError<E> forClass(Class<E> entityClass) {
    return new BaseEntityError<>(entityClass.getSimpleName());
  }

  public EntityNotFoundException notFound(String entityId) {
    return new EntityNotFoundException(this, entityId);
  }

  public EntityNotFoundException notFound(Collection<String> entityIds) {
    return new EntityNotFoundException(this, entityIds);
  }

  public InvalidEntityException invalid(E entity) {
    return new InvalidEntityException(this, entity.toString());
  }

  public DuplicateEntityException duplicate() {
    return new DuplicateEntityException(this);
  }

  public static class EntityNotFoundException extends BaseException {

    public <E extends PersistenceObject> EntityNotFoundException(
        BaseEntityError<E> entityError,
        String entityId) {
      super(entityError.entityTypeUpper + "_NOT_FOUND",
          entityError.entityType + " " + entityId + " could not be found.",
          HttpStatus.NOT_FOUND);
    }

    public <E extends PersistenceObject> EntityNotFoundException(
        BaseEntityError<E> entityError,
        Collection<String> entityIds) {
      super(entityError.entityType + "_NOT_FOUND",
          entityError.entityTypeUpper + "s could not be found: " + entityIds,
          HttpStatus.NOT_FOUND);
    }

  }

  public static class DuplicateEntityException extends BaseException {

    public <E extends PersistenceObject> DuplicateEntityException(
        BaseEntityError<E> entityError) {
      super("DUPLICATE_" + entityError.entityTypeUpper,
          "Duplicate " + entityError.entityType
              + " detected. Either during a creation or update attempt.",
          HttpStatus.BAD_REQUEST);
    }

  }

  public static class InvalidEntityException extends BaseException {

    public <E extends PersistenceObject> InvalidEntityException(
        BaseEntityError<E> entityError,
        String entity) {
      super("INVALID_" + entityError.entityTypeUpper,
          "Invalid " + entityError.entityType
              + " detected. Either during a creation or update attempt. Entity: " + entity,
          HttpStatus.BAD_REQUEST);
    }

  }

}
