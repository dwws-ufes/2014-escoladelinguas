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
public class CapitalDBPedia {

    private String nome;
    private String resumo;
    private Integer populacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public static CapitalDBPedia getPaisesIdiomaEscolhido(RDFNode capitalResource) {

        CapitalDBPedia capital = new CapitalDBPedia();

        String sparqlString
                = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "PREFIX : <http://dbpedia.org/resources/>\n"
                + "PREFIX d: <http://dbpedia.org/ontology/> \n"
                + "SELECT distinct ?nome ?resumo ?populacao \n"
                + "WHERE \n"
                + "{ \n"
                + "	<?resource> rdfs:label ?nome .\n"
                + "	<?resource> rdfs:comment ?resumo .\n"
                + "FILTER (langMatches(lang(?nome), \"PT\"))\n"
                + "FILTER (langMatches(lang(?resumo), \"PT\"))\n"
                + "OPTIONAL { <?resource> d:populationTotal ?populacao }\n"                
                + "}";
        sparqlString = sparqlString.replace("?resource", capitalResource.asResource().getURI());
        
        System.out.println(sparqlString);
        
        Query query = QueryFactory.create(sparqlString);        

        QueryExecution qexec = QueryExecutionFactory.sparqlService(
                "http://dbpedia.org/sparql", query);

        ResultSet rs = qexec.execSelect();
        if (rs.hasNext()) {
            QuerySolution sol = rs.nextSolution();            
            capital.setNome(sol.get("nome").asLiteral().getString());
            capital.setResumo(sol.get("resumo").asLiteral().getString());
            capital.setPopulacao(sol.get("populacao") == null ? null : sol.get("populacao").asLiteral().getInt());
        }

        qexec.close();

        return capital;
    }

}
