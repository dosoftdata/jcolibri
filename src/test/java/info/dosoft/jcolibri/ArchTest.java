package info.dosoft.jcolibri;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("info.dosoft.jcolibri");

        noClasses()
            .that()
            .resideInAnyPackage("info.dosoft.jcolibri.service..")
            .or()
            .resideInAnyPackage("info.dosoft.jcolibri.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..info.dosoft.jcolibri.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
