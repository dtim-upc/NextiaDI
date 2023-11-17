//package edu.upc.essi.dtim.nextiadi.jena;
//
//import edu.upc.essi.dtim.nextiadi;
//import edu.upc.essi.dtim.nextiadi.config.Vocabulary;
//import org.apache.jena.query.ResultSet;
//import org.apache.jena.rdf.model.Model;
//import org.apache.jena.rdf.model.ModelFactory;
//import org.apache.jena.rdf.model.RDFNode;
//import org.apache.jena.rdf.model.impl.PropertyImpl;
//import org.apache.jena.rdf.model.impl.ResourceImpl;
//import org.apache.jena.vocabulary.OWL;
//import org.apache.jena.vocabulary.RDF;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DisplayName("Graph operations test")
//class GraphTest {
//
//    Graph graph;
//    String prefix = "https://test.com/";
//
//    @BeforeEach
//    void setUp() {
//        graph = new Graph();
//    }
//
//
//    @DisplayName("add triple test")
//    @Test
//    void addTest() {
//        Model m = ModelFactory.createDefaultModel();
//        m.add(new ResourceImpl(prefix+"subject"), new PropertyImpl(prefix+"predicate"), new ResourceImpl(prefix+"object"));
//
//        graph.add(prefix+"subject", prefix+"predicate", prefix+"object");
//
//        assertEquals(1, graph.getModel().size());
//        assertTrue(m.isIsomorphicWith(graph.getModel()));
//
//    }
//
//    @Test
//    void deleteResourceTest() {
//        graph.loadModel(getClass().getClassLoader().getResource("bicing.ttl").getPath());
//
//        long nStatements = graph.getModel().size();
//        graph.deleteResource("http://www.bicing.com/Bicing_service");
//
//        assertNotEquals(0, nStatements);
//        assertTrue(nStatements > graph.getModel().size());
//
//        System.out.println(nStatements);
//        System.out.println(graph.getModel().size());
//    }
////
//    @Test
//    void deleteSubjectTest() {
//        graph.loadModel(getClass().getClassLoader().getResource("datasource1.ttl").getPath());
//        long nStatements = graph.getModel().size();
//
//        String query = "SELECT (count(*) AS ?total) WHERE { <http://data.europa.eu/s66#Organisation> ?p ?o. }";
//        Integer beforeStatements = Integer.valueOf(graph.runAQuery(query).next().getLiteral("total").getString());
//
//        graph.deleteSubject("http://data.europa.eu/s66#Organisation");
//
//        Integer afterStatements = Integer.valueOf(graph.runAQuery(query).next().getLiteral("total").getString());
//
//        assertNotEquals(0, beforeStatements);
//        assertEquals(0, afterStatements);
//
//    }
//
//    @Test
//    void deleteObjectTest() {
//
//        graph.loadModel(getClass().getClassLoader().getResource("datasource1.ttl").getPath());
//        long nStatements = graph.getModel().size();
//
//        String query = "SELECT (count(*) AS ?total) WHERE { ?s ?p <http://data.europa.eu/s66#Organisation>. }";
//        Integer beforeStatements = Integer.valueOf(graph.runAQuery(query).next().getLiteral("total").getString());
//
//        graph.deleteObject("http://data.europa.eu/s66#Organisation");
//
//        Integer afterStatements = Integer.valueOf(graph.runAQuery(query).next().getLiteral("total").getString());
//
//        assertNotEquals(0, beforeStatements);
//        assertEquals(0, afterStatements);
//    }
//
//    @Test
//    void generateMetaModelTest() {
//
//        Model expected = ModelFactory.createDefaultModel();
//        expected.add(new ResourceImpl(Vocabulary.IntegrationClass.val()), new PropertyImpl(RDF.type.getURI()), new ResourceImpl(OWL.Class.getURI()));
//        expected.add(new ResourceImpl(Vocabulary.IntegrationDProperty.val()), new PropertyImpl(RDF.type.getURI()), new ResourceImpl(OWL.Class.getURI()));
//        expected.add(new ResourceImpl(Vocabulary.IntegrationOProperty.val()), new PropertyImpl(RDF.type.getURI()), new ResourceImpl(OWL.Class.getURI()));
//
//        graph.generateMetaModel();
//
//        assertEquals(3, graph.getModel().size());
//        assertTrue(expected.isIsomorphicWith(graph.getModel()));
//
//    }
////
////    @Test
////    void replaceIntegratedClassTest() {
////
////        graph.loadModel(getClass().getClassLoader().getResource("bicing_villo.ttl").getPath());
////        String oldURI = "http://www.essi.upc.edu/dtim/ontology/Global/Bike_Station";
////        String newURI = "http://www.essi.upc.edu/dtim/ontology/Global/Bike_Station_new";
////        Boolean containsIntegrated = graph.contains(oldURI, RDF.type.getURI(), Vocabulary.IntegrationClass.val()  );
////
////        assertTrue(containsIntegrated);
////
////        graph.replaceIntegratedClass(newURI);
////
////
////
////    }
//////
////    @Test
////    void updateResourceNodeIRITest() {
////    }
////
////    @Test
////    void runAnUpdateQueryTest() {
////    }
////
////    @Test
////    void runAQueryTest() {
////    }
////
//    @Test
//    void isIntegratedClassTest() {
//        graph.loadModel(getClass().getClassLoader().getResource("bicing_villo.ttl").getPath());
//        assertTrue(graph.isIntegratedClass("http://www.essi.upc.edu/dtim/ontology/Global/Bike_Station"));
//    }
//
//    @Test
//    void isNotIntegratedClassTest() {
//        graph.loadModel(getClass().getClassLoader().getResource("bicing_villo.ttl").getPath());
//        assertTrue(!graph.isIntegratedClass("http://www.bicing.com/Bicycle_info"));
//    }
//
//    @Test
//    void isIntegratedDatatypePropertyTest() {
//        graph.loadModel(getClass().getClassLoader().getResource("bicing_villo.ttl").getPath());
//        assertTrue(graph.isIntegratedDatatypeProperty("http://www.essi.upc.edu/dtim/ontology/Global/station_id"));
//    }
//
////    @Test
////    void isIntegratedObjectPropertyTest() {
////    }
////
////    @Test
////    void getFlexibleRangeTest() {
////    }
////
////    @Test
////    void getRangeTest() {
////    }
////
////    @Test
////    void getDomainTest() {
////    }
////
////    @Test
////    void loadModelTest() {
////    }
////
////    @Test
////    void setModelTest() {
////    }
////
////    @Test
////    void getModelTest() {
////    }
//}