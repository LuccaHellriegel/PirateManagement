package com.arrr.piratery.arch;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.properties.HasName.AndFullName.Predicates.fullNameMatching;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

public class ArchitectureTest {

  public final static JavaClasses importedClasses = new ClassFileImporter().importPackages(
      "com.arrr.piratery..");

  //TODO: this does not catch shit?
  @Test
  public void layers() {
    layeredArchitecture()
        .ignoreDependency(fullNameMatching("com.arrr.piratery.PirateryApplication"), alwaysTrue())

        .layer("BaseControllers").definedBy("(**).base.controllers")
        .layer("BaseMixins").definedBy("(**).base.mixins..")

        .layer("ApplicationPorts").definedBy("(**).ports.application")
        .layer("DomainPorts").definedBy("(**).ports.domain")
        .layer("DomainServices").definedBy("(**).services.domain")
        .layer("Domain").definedBy("(**).domain")

        .whereLayer("BaseControllers").mayOnlyBeAccessedByLayers("ApplicationPorts")
        .whereLayer("BaseMixins")
        .mayOnlyBeAccessedByLayers("BaseControllers", "ApplicationPorts", "DomainServices")

        .whereLayer("ApplicationPorts")
        .mayNotBeAccessedByAnyLayer()

        .whereLayer("DomainServices")
        .mayOnlyBeAccessedByLayers("ApplicationPorts")

        .whereLayer("DomainPorts")
        .mayOnlyBeAccessedByLayers("ApplicationPorts", "DomainServices")

        .check(importedClasses);
  }
}
