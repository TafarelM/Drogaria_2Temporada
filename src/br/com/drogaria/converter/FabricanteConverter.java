package br.com.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

@FacesConverter("fabricanteConverter")
public class FabricanteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try {
			int id = Integer.parseInt(valor);
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			Fabricante fabricante =  fabricanteDAO.buscarPorId(id);
			
			return fabricante;
		} catch (RuntimeException ex) {
			return null;
		}		
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try {
			Fabricante fabricante = (Fabricante) objeto;
			String  id = Integer.toString(fabricante.getId());
			
			return id;
		} catch (RuntimeException ex) {
			return null;
		}
		
	}
	
}
