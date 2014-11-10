/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.util;

import br.ufes.edl.enums.Idioma;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class PaisDBPedia implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String resumo;
    private String urlBandeira;
    private Integer populacao;
    private RDFNode resCapital;
    private RDFNode resMoeda;

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

    public String getUrlBandeira() {
        return urlBandeira;
    }

    public void setUrlBandeira(String urlBandeira) {
        this.urlBandeira = urlBandeira;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }
    
    public RDFNode getResCapital() {
        return resCapital;
    }

    public void setResCapital(RDFNode resCapital) {
        this.resCapital = resCapital;
    }

    public RDFNode getResMoeda() {
        return resMoeda;
    }

    public void setResMoeda(RDFNode resMoeda) {
        this.resMoeda = resMoeda;
    }

    public static List<PaisDBPedia> getPaisesIdiomaEscolhido(String idioma) {

        List<PaisDBPedia> listPaisesDBPedia = new ArrayList<>();

        String yagoSpokenLanguage;

        if (idioma.equals(Idioma.ALEMAO.getLabel())) {
            yagoSpokenLanguage = RDFUtil.GERMAN_SPOKEN_COUNTRIES;
        } else if (idioma.equals(Idioma.ESPANHOL.getLabel())) {
            yagoSpokenLanguage = RDFUtil.SPANISH_SPOKEN_COUNTRIES;
        } else if (idioma.equals(Idioma.FRANCES.getLabel())) {
            yagoSpokenLanguage = RDFUtil.FRENCH_SPOKEN_COUNTRIES;
        } else if (idioma.equals(Idioma.INGLES.getLabel())) {
            yagoSpokenLanguage = RDFUtil.ENGLISH_SPOKEN_COUNTRIES;
        } else if (idioma.equals(Idioma.ITALIANO.getLabel())) {
            yagoSpokenLanguage = RDFUtil.ITALIAN_SPOKEN_COUNTRIES;
        } else if (idioma.equals(Idioma.PORTUGUES.getLabel())) {
            yagoSpokenLanguage = RDFUtil.PORTUGUESE_SPOKEN_COUNTRIES;
        } else {
            yagoSpokenLanguage = RDFUtil.PORTUGUESE_SPOKEN_COUNTRIES;
        }

        String sparql
                = "PREFIX schema: <http://schema.org/>\n"
                + "PREFIX yago: <http://dbpedia.org/class/yago/>\n"
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>\n"
                + "PREFIX dbpprop: <http://dbpedia.org/property/>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT DISTINCT ?nome ?resumo ?bandeira ?populacao ?capital ?moeda\n"
                + "WHERE {\n"
                + "   ?res rdf:type dbpedia-owl:Place;\n"
                + "   rdf:type dbpedia-owl:Country;\n"
                + "   rdf:type %s;\n"
                + "   rdfs:label ?nome;\n"
                + "   rdfs:comment ?resumo;\n"
                + "   dbpedia-owl:thumbnail ?bandeira;\n"
                + "   dbpedia-owl:capital ?capital;\n"
                + "   dbpedia-owl:currency ?moeda   \n"
                + "   FILTER (langMatches(lang(?resumo), \"PT\"))   	    \n"
                + "   FILTER (langMatches(lang(?nome), \"PT\"))\n"
                + "   OPTIONAL { ?res dbpprop:populationCensus ?populacao }\n"
                + "} ORDER BY ?nome ";
        String queryString = String.format(sparql, yagoSpokenLanguage);

        System.out.println(queryString);

        Query query = QueryFactory.create(queryString);

        QueryExecution qexec = QueryExecutionFactory.sparqlService(
                "http://dbpedia.org/sparql", query);

        ResultSet rs = qexec.execSelect();
        while (rs.hasNext()) {
            QuerySolution sol = rs.nextSolution();
            PaisDBPedia pais = new PaisDBPedia();
            pais.setNome(sol.get("nome").asLiteral().getString());
            pais.setResumo(sol.get("resumo").asLiteral().getString());
            pais.setUrlBandeira(sol.get("bandeira").asResource().getURI());
            try {
                pais.setPopulacao(sol.get("populacao") == null ? null : sol.get("populacao").asLiteral().getInt());                    
			} catch (Exception ex) {
                pais.setPopulacao(null);
            }
            pais.setResCapital(sol.get("capital"));
            pais.setResMoeda(sol.get("moeda"));
            listPaisesDBPedia.add(pais);
        }
        qexec.close();

        return listPaisesDBPedia;
    }

   
}
