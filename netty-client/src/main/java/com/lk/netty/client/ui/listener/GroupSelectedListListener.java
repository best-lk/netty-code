package com.lk.netty.client.ui.listener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ���������Ҳ���ѡ��ϵ���б�����¼�
 * 2019��3��20��
 * likai
 */
public class GroupSelectedListListener implements ListSelectionListener {

	private JList<String> selectedList;
	
	public GroupSelectedListListener(JList<String> selectedList) {
		super();
		this.selectedList = selectedList;
	}

	/**
	 * �����Ƴ���ѡ��
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {		
		//�Ƴ���ѡ��
		int selectedIndex = selectedList.getSelectedIndex();
		if(selectedIndex < 0) {
			return;
		}
		DefaultComboBoxModel model = (DefaultComboBoxModel) selectedList.getModel();
		model.removeElementAt(selectedIndex);
	}

}
