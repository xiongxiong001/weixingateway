package org.demoexm.core.vo;

import java.io.Serializable;
import java.util.List;

public class MenuVo implements Serializable{
	
	private List<MenuButtonVo> button;	//一级菜单数组，个数应为1~3个

	public List<MenuButtonVo> getButton() {
		return button;
	}

	public void setButton(List<MenuButtonVo> button) {
		this.button = button;
	}
	

}
