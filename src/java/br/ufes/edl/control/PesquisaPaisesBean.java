/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.edl.control;

import br.ufes.edl.enums.Idioma;
import br.ufes.edl.util.CapitalDBPedia;
import br.ufes.edl.util.MoedaDBPedia;
import br.ufes.edl.util.PaisDBPedia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Rafael
 */
@Named
@SessionScoped
public class PesquisaPaisesBean implements Serializable {
	private static final long serialVersionUID = 1L;

    private List<PaisDBPedia> listPaisDBPedia = new ArrayList<>();
    
    private Idioma selectedIdioma = null;
    
    private PaisDBPedia selectedPais = new PaisDBPedia();
    
    private CapitalDBPedia selectedCapital = new CapitalDBPedia();
    
    private MoedaDBPedia selectedMoeda = new MoedaDBPedia();

    public List<PaisDBPedia> getListPaisDBPedia() {
        return listPaisDBPedia;
    }

    public Idioma getSelectedIdioma() {
        return selectedIdioma;
    }

    public void setSelectedIdioma(Idioma selectedIdioma) {
        this.selectedIdioma = selectedIdioma;
    }
    
    public void setListPaisDBPedia(List<PaisDBPedia> listPaisDBPedia) {
        this.listPaisDBPedia = listPaisDBPedia;
    }
    
    public PaisDBPedia getSelectedPais() {
        return selectedPais;
    }

    public void setSelectedPais(PaisDBPedia selectedPais) {
        this.selectedPais = selectedPais;
    }
    
    public CapitalDBPedia getSelectedCapital() {
        return selectedCapital;
    }

    public void setSelectedCapital(CapitalDBPedia selectedCapital) {
        this.selectedCapital = selectedCapital;
    }

    public MoedaDBPedia getSelectedMoeda() {
        return selectedMoeda;
    }

    public void setSelectedMoeda(MoedaDBPedia selectedMoeda) {
        this.selectedMoeda = selectedMoeda;
    }
    
    public Idioma[] getIdiomas() {
        return Idioma.values();
    }    
    
    public void onIdiomaChange() {
        if (getSelectedIdioma() != null && !getSelectedIdioma().getLabel().equals(""))
            this.setListPaisDBPedia(PaisDBPedia.getPaisesIdiomaEscolhido(getSelectedIdioma().getLabel()));
        else
            this.setListPaisDBPedia(null);
    }   
    
    public void onClickPais(PaisDBPedia pais) {
        this.setSelectedPais(pais);
        this.setSelectedCapital(CapitalDBPedia.getPaisesIdiomaEscolhido(this.getSelectedPais().getResCapital()));        
        this.setSelectedMoeda(MoedaDBPedia.getPaisesIdiomaEscolhido(this.getSelectedPais().getResMoeda()));
    }
}
