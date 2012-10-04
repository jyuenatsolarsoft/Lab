package com.cms.gwt.fs.client.model.material;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

public class ServiceDetailMaterialList extends FSModel {

	private List<ServiceDetailMaterial> materials = new ArrayList<ServiceDetailMaterial>();
	
	public void add(ServiceDetailMaterial material) {
		
		materials.add(material);		
	}
	
	public List<ServiceDetailMaterial> getMaterials()
	{
		return materials;
	}	
	
}	
