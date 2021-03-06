package edu.upc.essi.dtim.nextiadi.bootstraping;

import edu.upc.essi.dtim.nextiadi.config.DataSourceVocabulary;
import edu.upc.essi.dtim.nextiadi.jena.Graph;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class DataSource {

    protected Graph Σ;

    protected String wrapper;
    //List of pairs, where left is the IRI in the graph and right is the attribute in the wrapper (this will create sameAs edges)
    protected List<Pair<String,String>> sourceAttributes;

    protected List<Pair<String,String>> attributes;
    protected List<Pair<String,String>> lateralViews;
    protected String id = "";

    protected Map<String, String> prefixes;

    DataSource() {
        init();
    }

    protected void setPrefixes(){
        prefixes.put("nextiaSchema", DataSourceVocabulary.Schema.val());
        prefixes.put("nextiaDataSource", DataSourceVocabulary.DataSource.val() +"/");
        prefixes.put("rdf", RDF.getURI());
        prefixes.put("rdfs", RDFS.getURI());
        prefixes.put("xsd", XSD.getURI());
    }

    protected void setPrefixesID(String id){
        prefixes.put("nextiaSchema", DataSourceVocabulary.Schema.val()+id+"/");
    }

    protected void init(){
        Σ = new Graph();
        prefixes = new HashMap<>();
        setPrefixes();
        sourceAttributes = Lists.newArrayList();

        attributes = Lists.newArrayList();
        lateralViews = Lists.newArrayList();
        id = "";
    }

    public void addBasicMetaData(String name, String path, String ds){
        Σ.add( ds , RDF.type.getURI(),  DataSourceVocabulary.DataSource.val() );
        Σ.addLiteral( ds , DataSourceVocabulary.HAS_PATH.val(), path);
        Σ.addLiteral( ds , RDFS.label.getURI(),  name );
    }

}
