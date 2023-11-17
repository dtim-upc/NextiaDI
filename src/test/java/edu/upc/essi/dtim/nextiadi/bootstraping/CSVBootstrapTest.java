package edu.upc.essi.dtim.nextiadi.bootstraping;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVBootstrapTest {

    @Test
    void bootstrapSchemaTest() throws IOException {

        CSVBootstrap csv = new CSVBootstrap();

        Model result = csv.bootstrapSchema("file:///cities.csv", "name",getClass().getClassLoader().getResource("bootstraping/cities.csv").getPath());

        Model expected = ModelFactory.createDefaultModel();
        expected.read(getClass().getClassLoader()
                .getResource("bootstraping/cities.ttl").getPath());

        assertTrue(result.isIsomorphicWith(expected));
        assertEquals(result.size(), expected.size());
        assertTrue(expected.size()>0);

    }

}