package gov.nist.toolkit.simulators.fhir.validators

import org.hl7.fhir.dstu3.model.Bundle
import org.hl7.fhir.dstu3.model.Identifier
import org.hl7.fhir.dstu3.model.Resource
import org.hl7.fhir.dstu3.model.StringType

/**
 *
 */
class BundleFullUrlValidator implements BaseValidator {
    Bundle.BundleEntryComponent component
    String identifierSource

    BundleFullUrlValidator(Bundle.BundleEntryComponent component, String identifierSource) {
        this.component = component
        this.identifierSource = identifierSource
    }

    @Override
    void validate() {
        Resource resource = component.resource
        String id = resource.getIdElement()
        String fullUrl = component.fullUrl
        if (id && fullUrl && component.fullUrl != id)
            throw new MismatchException(
                    'Resource Id does not match Bundle fullUrl',
                    'http://hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl',
                    fullUrl,
                    id,
                    null,
                    identifierSource
            )
    }
}