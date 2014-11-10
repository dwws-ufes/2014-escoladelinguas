/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.util;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 *
 * @author Rafael
 */
public class MoedaDBPedia {

    private String nome;
    private String codigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static MoedaDBPedia getPaisesIdiomaEscolhido(RDFNode moedaResource) {

        MoedaDBPedia moeda = new MoedaDBPedia();

        String sparqlString
                = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX dbpprop: <http://dbpedia.org/property/>\n"
                + "SELECT distinct ?nome ?codigo \n"
                + "WHERE \n"
                + "{ \n"
                + "	<?resource> rdfs:label ?nome .\n"
                + "	<?resource> dbpprop:isoCode ?codigo .\n"
                + "FILTER (langMatches(lang(?nome), \"PT\"))\n"
                + "}";
        sparqlString = sparqlString.replace("?resource", moedaResource.asResource().getURI());

        System.out.println(sparqlString);

        Query query = QueryFactory.create(sparqlString);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(
                "http://dbpedia.org/sparql", query);

        ResultSet rs = qexec.execSelect();
        if (rs.hasNext()) {
            QuerySolution sol = rs.nextSolution();
            moeda.setNome(sol.get("nome").asLiteral().getString());
            moeda.setCodigo(sol.get("codigo").asLiteral().getString());
        }

        qexec.close();

        return moeda;
    }
}
