package com.lk.netty.client.ui.listener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 创建分组左侧联系人列表监听
 * 2019年3月20日
 * likai
 */
public class PersonalListListener implements ListSelectionListener {

	//左侧联系人列表
	private JList<String> personalList;
	
	//右侧已选择列表
	private JList<String> selectedList;
	
	//记录已经选择的项
	private Map<String, String> selectedItem = new HashMap<String, String>();
	
	public PersonalListListener(JList<String> personalList, JList<String> selectedList) {
		super();
		this.personalList = personalList;
		this.selectedList = selectedList;
	}

	/**
	 * 联系人列表往已选择联系人列表添加
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//1：获取联系人列表选项
		String selectValue = personalList.getSelectedValue();
		if(!selectedItem.containsKey(selectValue)) {			
			DefaultComboBoxModel model = (DefaultComboBoxModel) selectedList.getModel();
			model.addElement(selectValue);
			selectedItem.put(selectValue, selectValue);
		}
	}

}
