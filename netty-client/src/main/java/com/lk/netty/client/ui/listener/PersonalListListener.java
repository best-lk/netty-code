package com.lk.netty.client.ui.listener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * �������������ϵ���б����
 * 2019��3��20��
 * likai
 */
public class PersonalListListener implements ListSelectionListener {

	//�����ϵ���б�
	private JList<String> personalList;
	
	//�Ҳ���ѡ���б�
	private JList<String> selectedList;
	
	//��¼�Ѿ�ѡ�����
	private Map<String, String> selectedItem = new HashMap<String, String>();
	
	public PersonalListListener(JList<String> personalList, JList<String> selectedList) {
		super();
		this.personalList = personalList;
		this.selectedList = selectedList;
	}

	/**
	 * ��ϵ���б�����ѡ����ϵ���б����
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//1����ȡ��ϵ���б�ѡ��
		String selectValue = personalList.getSelectedValue();
		if(!selectedItem.containsKey(selectValue)) {			
			DefaultComboBoxModel model = (DefaultComboBoxModel) selectedList.getModel();
			model.addElement(selectValue);
			selectedItem.put(selectValue, selectValue);
		}
	}

}
