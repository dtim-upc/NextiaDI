package edu.upc.essi.dtim;


import edu.upc.essi.dtim.nextiadi.config.Namespaces;
import edu.upc.essi.dtim.nextiadi.config.Vocabulary;
import edu.upc.essi.dtim.NextiaCore.discovery.Alignment;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NextiaDI test")
public class NextiaDITest {

    String bi = "http://www.bicing.com/";
    String vi = "http://www.villo.com/";

    NextiaDI nextia;

    @BeforeEach
    void setUp() {
        nextia = new NextiaDI();
    }



    @Test
    void integrateDatatypeProperties_I1(){


        Alignment capacity = new Alignment(bi+"capacity", vi+"capacity", "capacity");
        Alignment name = new Alignment(bi+"name", vi+"name", "station_name");
        Alignment stationId =new Alignment(bi+"station_id", vi+"id", "station_id");

        List<Alignment> datatypes = new ArrayList<>();
        datatypes.add( name );
        datatypes.add( stationId );
        datatypes.add( capacity );
        datatypes.add( new Alignment(bi+"information", vi+"information", "info") );
        datatypes.add( new Alignment(bi+"year_of_purchase", vi+"purchase_year", "year_of_purchase") );
        datatypes.add( new Alignment(bi+"identifier", vi+"dataset_id", "dataset_id") );

        setBicingVilloModel();
        String integratedClass = Namespaces.G.val()+"Bike_station";
        nextia.graphO.add(integratedClass, RDF.type.getURI(), Vocabulary.IntegrationClass.val());
        nextia.graphO.add(bi+"Bicycle_Station", RDFS.subClassOf.getURI(), integratedClass);
        nextia.graphO.add(vi+"Bike_Station", RDFS.subClassOf.getURI(), integratedClass);

        long nStatements = nextia.graphO.getModel().size();
        assertNotEquals(0, nStatements);

        List<Alignment> unused = nextia.IntegrateDatatypeProperties(datatypes);

        assertEquals(3, unused.size());

        assertTrue(nextia.graphO.contains(name.getIriL(), RDF.type.getURI(), Vocabulary.IntegrationDProperty.val()));
        assertTrue(nextia.graphO.contains(capacity.getIriL(), RDF.type.getURI(), Vocabulary.IntegrationDProperty.val() ));
        assertTrue(nextia.graphO.contains(stationId.getIriL(), RDF.type.getURI(), Vocabulary.IntegrationDProperty.val() ));

        assertTrue(nextia.graphO.contains(name.getIriL(), RDFS.domain.getURI(), integratedClass));
        assertTrue(nextia.graphO.contains(capacity.getIriL(), RDFS.domain.getURI(), integratedClass ));
        assertTrue(nextia.graphO.contains(stationId.getIriL(), RDFS.domain.getURI(), integratedClass ));

        assertTrue(nextia.graphO.contains(name.getIriL(), RDFS.range.getURI(), XSD.xstring.getURI()));
        assertTrue(nextia.graphO.contains(capacity.getIriL(), RDFS.range.getURI(), XSD.xstring.getURI() ));
        assertTrue(nextia.graphO.contains(stationId.getIriL(), RDFS.range.getURI(), XSD.xstring.getURI() ));

        assertTrue(nextia.graphO.contains(name.getIriA(), RDFS.subPropertyOf.getURI(), name.getIriL()) );
        assertTrue(nextia.graphO.contains(name.getIriB(), RDFS.subPropertyOf.getURI(), name.getIriL()) );

        assertTrue(nextia.graphO.contains(capacity.getIriA(), RDFS.subPropertyOf.getURI(), capacity.getIriL()) );
        assertTrue(nextia.graphO.contains(capacity.getIriB(), RDFS.subPropertyOf.getURI(), capacity.getIriL()) );

        assertTrue(nextia.graphO.contains(stationId.getIriA(), RDFS.subPropertyOf.getURI(), stationId.getIriL()) );
        assertTrue(nextia.graphO.contains(stationId.getIriB(), RDFS.subPropertyOf.getURI(), stationId.getIriL()) );

        assertEquals(nStatements+15,  nextia.graphO.getModel().size());

//        nextia.graphO.write("/Users/javierflores/Documents/UPC_projects/nextia/src/test/resources/bicing_villo_I1.ttl", Lang.TURTLE);


    }

    @Test
    void integrateClass_I1(){



        Alignment bike_station = new Alignment(bi+"Bicycle_Station", vi+"Bike_Station", "Bike_station");

        List<Alignment> classes = new ArrayList<>();
        classes.add( bike_station );

        setBicingVilloModel();
        long nStatements = nextia.graphO.getModel().size();
        assertNotEquals(0, nStatements);

        nextia.IntegrateClasses(classes);

        assertTrue(nextia.graphO.contains(bike_station.getIriL(), RDF.type.getURI(), Vocabulary.IntegrationClass.val()) );
        assertTrue(nextia.graphO.contains(bike_station.getIriA(), RDFS.subClassOf.getURI(), bike_station.getIriL() ) );
        assertTrue(nextia.graphO.contains(bike_station.getIriB(), RDFS.subClassOf.getURI(), bike_station.getIriL() ) );

        assertEquals(nStatements+3,  nextia.graphO.getModel().size());


    }

    @Test
    void integrateTest_I1(){
        Model expected = ModelFactory.createDefaultModel();
        expected.read(getClass().getClassLoader().getResource("bicing_villo_I1.ttl").getPath());

        Model bicing = ModelFactory.createDefaultModel();
        bicing.read(getClass().getClassLoader().getResource("bicing.ttl").getPath());

        Model villo = ModelFactory.createDefaultModel();
        villo.read(getClass().getClassLoader().getResource("villo.ttl").getPath());

        Alignment bike_station = new Alignment(bi+"Bicycle_Station", vi+"Bike_Station", "Bike_station");

        List<Alignment> classes = new ArrayList<>();
        classes.add( bike_station );

        Alignment capacity = new Alignment(bi+"capacity", vi+"capacity", "capacity");
        Alignment name = new Alignment(bi+"name", vi+"name", "station_name");
        Alignment stationId =new Alignment(bi+"station_id", vi+"id", "station_id");

        List<Alignment> datatypes = new ArrayList<>();
        datatypes.add( name );
        datatypes.add( stationId );
        datatypes.add( capacity );
        datatypes.add( new Alignment(bi+"information", vi+"information", "info") );
        datatypes.add( new Alignment(bi+"year_of_purchase", vi+"purchase_year", "year_of_purchase") );
        datatypes.add( new Alignment(bi+"identifier", vi+"dataset_id", "dataset_id") );


        nextia.Integrate(bicing, villo, classes, datatypes, new ArrayList<>(), new ArrayList<>());


        System.out.println(nextia.graphO.getModel().size());
        System.out.println(expected.size());

       expected.isIsomorphicWith( nextia.graphO.getModel());

    }




    void setBicingVilloModel(){
        Model bicing = ModelFactory.createDefaultModel();
        bicing.read(getClass().getClassLoader().getResource("bicing.ttl").getPath(), "TURTLE");

        Model villo = ModelFactory.createDefaultModel();
        villo.read(getClass().getClassLoader().getResource("villo.ttl").getPath(), "TURTLE");

        nextia.graphO.setModel(bicing.union(villo));
    }

//    @Test
//    @DisplayName("Simple multiplication should work")
//    void testMultiply() {
//        assertEquals(20, calculator.multiply(4, 5),
//                "Regular multiplication should work");
//    }


}
