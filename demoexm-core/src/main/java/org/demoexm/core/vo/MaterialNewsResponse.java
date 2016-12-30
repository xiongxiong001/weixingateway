package org.demoexm.core.vo;

import java.util.List;

public class MaterialNewsResponse  extends ResponseVo{

	/**
	 * 素材类型
	 */
	private String materialType;
	
	/**
	 *图文信息
	 */
	private List<MaterialNewsVo> newsMaterials;

	public List<MaterialNewsVo> getNewsMaterials() {
		return newsMaterials;
	}

	public void setNewsMaterials(List<MaterialNewsVo> newsMaterials) {
		this.newsMaterials = newsMaterials;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	 
	
}
