package edu.upc.essi.dtim.nextiadi.bootstraping;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONBootstrapTest {


    @Test
    void bootstrapSchemaTest() throws FileNotFoundException {




        JSONBootstrap json = new JSONBootstrap();

        Model result = json.bootstrapSchema("file:///bikes.json", getClass().getClassLoader()
                .getResource("bootstraping/bikes.json").getPath());
//        json.write("/Users/javierflores/Documents/UPC_projects/nuupdi/src/test/resources/bootstraping/bikes2.ttl", "TLL");

        Model expected = ModelFactory.createDefaultModel();
        expected.read(getClass().getClassLoader()
                .getResource("bootstraping/bikes.ttl").getPath());

        System.out.println(expected.listSubjects().toList().size());

        for( Resource r : expected.listSubjects().toList() ) {
            for (Statement statement :  r.listProperties().toList()){

                System.out.println(statement.toString());

            }
            System.out.println(r + " - "+ r.listProperties().toList().size());
        }

//        result.listStatements().forEachRemaining(x -> {
//            if(!expected.contains(x.getSubject(), x.getPredicate(), x.getObject())){
//                System.out.println(x.toString());
//            }
//        });
//        System.out.println("---");
//        expected.listStatements().forEachRemaining(x -> {
//            if(!result.contains(x.getSubject(), x.getPredicate(), x.getObject())){
//                System.out.println(x.toString());
//            }
//        });

        assertTrue(result.isIsomorphicWith(expected));
        assertEquals(result.size(), expected.size());
        assertTrue(expected.size()>0);


    }

}