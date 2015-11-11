package com.buschmais.jqassistant.plugin.java8.test.rules;

import static com.buschmais.jqassistant.core.analysis.api.Result.Status.SUCCESS;
import static com.buschmais.jqassistant.plugin.java.test.matcher.TypeDescriptorMatcher.typeDescriptor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.buschmais.jqassistant.core.analysis.api.AnalysisException;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import com.buschmais.jqassistant.plugin.java8.test.set.rules.FunctionalInterface;

/**
 * Tests for the concept java8:FunctionalInterface.
 */
public class FunctionalInterfaceIT extends AbstractJavaPluginIT {

    /**
     * Verifies the concept "java8:FunctionalInterface".
     * 
     * @throws java.io.IOException
     *             If the test fails.
     * @throws com.buschmais.jqassistant.core.analysis.api.AnalysisException
     *             If the test fails.
     */
    @Test
    public void functionalInterface() throws Exception {
        scanClasses(com.buschmais.jqassistant.plugin.java8.test.set.rules.FunctionalInterface.class);
        assertThat(applyConcept("java8:FunctionalInterface").getStatus(), equalTo(SUCCESS));
        store.beginTransaction();
        TestResult result = query("MATCH (fi:Type:FunctionalInterface) RETURN fi");
        assertThat(result.getColumn("fi"), hasItem(typeDescriptor(FunctionalInterface.class)));
        store.commitTransaction();
    }
}
