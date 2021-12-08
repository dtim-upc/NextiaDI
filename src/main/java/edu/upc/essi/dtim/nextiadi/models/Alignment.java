package edu.upc.essi.dtim.nextiadi.models;

import edu.upc.essi.dtim.nextiadi.config.Namespaces;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Alignment implements Serializable {

    String iriA;
    String iriB;
    String l;
    String type;
    Boolean identifier;
//    String score?

    public Alignment(){

    }

    public Alignment(String iriA, String iriB, String l) {
        this.iriA = iriA;
        this.iriB = iriB;
        this.l = l;
    }

    public Alignment(String iriA, String iriB, String l, String type) {
        this.iriA = iriA;
        this.iriB = iriB;
        this.l = l;
        this.type = type;
    }

    public Alignment(String iriA, String iriB, String l, String type, Boolean identifier) {
        this.iriA = iriA;
        this.iriB = iriB;
        this.l = l;
        this.type = type;
        this.identifier = identifier;
    }


    public String getIriL() {
        return Namespaces.NextiaDI.val() + l;
    }
}
