package com.lk.netty.client.ui.listener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 创建分组右侧已选联系人列表监听事件
 * 2019年3月20日
 * likai
 */
public class GroupSelectedListListener implements ListSelectionListener {

	private JList<String> selectedList;
	
	public GroupSelectedListListener(JList<String> selectedList) {
		super();
		this.selectedList = selectedList;
	}

	/**
	 * 监听移除所选项
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {		
		//移除所选项
		int selectedIndex = selectedList.getSelectedIndex();
		if(selectedIndex < 0) {
			return;
		}
		DefaultComboBoxModel model = (DefaultComboBoxModel) selectedList.getModel();
		model.removeElementAt(selectedIndex);
	}

}
