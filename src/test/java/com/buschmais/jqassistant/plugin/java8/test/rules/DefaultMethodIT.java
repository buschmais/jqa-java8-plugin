package com.buschmais.jqassistant.plugin.java8.test.rules;

import static com.buschmais.jqassistant.core.analysis.api.Result.Status.SUCCESS;
import static com.buschmais.jqassistant.plugin.java.test.matcher.MethodDescriptorMatcher.methodDescriptor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.buschmais.jqassistant.core.analysis.api.AnalysisException;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import com.buschmais.jqassistant.plugin.java8.test.set.rules.DefaultMethod;

/**
 * Tests for the concept java8:DefaultMethod.
 */
public class DefaultMethodIT extends AbstractJavaPluginIT {

    /**
     * Verifies the concept "java8:DefaultMethod".
     * 
     * @throws java.io.IOException
     *             If the test fails.
     * @throws com.buschmais.jqassistant.core.analysis.api.AnalysisException
     *             If the test fails.
     */
    @Test
    public void defaultMethod() throws Exception {
        scanClasses(DefaultMethod.class);
        assertThat(applyConcept("java8:DefaultMethod").getStatus(), equalTo(SUCCESS));
        store.beginTransaction();
        TestResult result = query("MATCH (m:Method:Default) RETURN m");
        assertThat(result.getRows().size(), equalTo(1));
        assertThat(result.getColumn("m"), hasItem(methodDescriptor(DefaultMethod.class, "add", int.class, int.class)));
        store.commitTransaction();
    }
}
