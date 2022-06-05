package com.arrr.piratery.commons.base.error;

import java.util.Collection;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseEntityError implements EntityError {

  protected final String entityType;
  protected final String entityTypeUpper;

  public BaseEntityError(String entityType) {
    if (entityType.endsWith("PO")) {
      entityType = entityType.substring(0, entityType.length() - 2);
    }

    this.entityType = entityType.substring(0, 1).toUpperCase() + entityType.substring(1);
    this.entityTypeUpper = entityType.toUpperCase();
  }

  public BaseEntityError(Class entityClass) {
    this(toEntityType(entityClass));
  }

  public static String toEntityType(Class entityClass) {
    return entityClass.getSimpleName();
  }

  @Override
  public EntityNotFoundException notFound(String entityId) {
    return new EntityNotFoundException(this, entityId);
  }

  @Override
  public EntityNotFoundException notFound(Collection<String> entityIds) {
    return new EntityNotFoundException(this, entityIds);
  }

  @Override
  public InvalidEntityException invalid() {
    return new InvalidEntityException(this);
  }

  @Override
  public InvalidEntityException invalid(String message) {
    return new InvalidEntityException(this, message);
  }

  @Override
  public DuplicateEntityException duplicate() {
    return new DuplicateEntityException(this);
  }

  public static class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(
        BaseEntityError entityError,
        String entityId) {
      super(entityError.entityTypeUpper + "_NOT_FOUND",
          entityError.entityType + " " + entityId + " could not be found.",
          HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(
        BaseEntityError entityError,
        Collection<String> entityIds) {
      super(entityError.entityType + "_NOT_FOUND",
          entityError.entityTypeUpper + "s could not be found: " + entityIds,
          HttpStatus.NOT_FOUND);
    }

  }

  public static class DuplicateEntityException extends BaseException {

    public DuplicateEntityException(
        BaseEntityError entityError) {
      super("DUPLICATE_" + entityError.entityTypeUpper,
          "Duplicate " + entityError.entityType
              + " detected. Either during a creation or update attempt.",
          HttpStatus.BAD_REQUEST);
    }

  }

  public static class InvalidEntityException extends BaseException {

    public InvalidEntityException(
        BaseEntityError entityError,
        String message) {
      super("INVALID_" + entityError.entityTypeUpper,
          "Invalid " + entityError.entityType
              + " detected. " + message,
          HttpStatus.BAD_REQUEST);
    }

    public InvalidEntityException(
        BaseEntityError entityError) {
      super("INVALID_" + entityError.entityTypeUpper,
          "Invalid " + entityError.entityType
              + " detected. ",
          HttpStatus.BAD_REQUEST);
    }

  }

}
