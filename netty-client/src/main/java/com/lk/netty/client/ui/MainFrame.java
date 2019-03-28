package com.lk.netty.client.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.lk.netty.client.ui.listener.GroupSelectedListListener;
import com.lk.netty.client.ui.listener.PersonalListListener;

/**
 *  ����������
 * 2019��3��15��
 * likai
 */
public class MainFrame {
	
	private final static Integer CONTANCT_PERSONAL = 0;
	private final static Integer CONTANCT_GROUP = 1;
	private final static Integer ADD_CONTANCT_GROUP = 2;
	//��ϵ��
	public static String[] personalData = new String[] {};
	//��ѡ��ϵ��
	public static String[] selectedData = new String[] {}; 
	//Ⱥ��
	public static String[] groupData = new String[] {};
	//��ϵ������ģ��
	public static DefaultComboBoxModel<String> personDataModel = new DefaultComboBoxModel(personalData);
	//Ⱥ������ģ��
	public static DefaultComboBoxModel<String> groupDataModel = new DefaultComboBoxModel(personalData);
	//��ѡ���б�����ģ��
	public static DefaultComboBoxModel<String> selectedDataModel = new DefaultComboBoxModel(selectedData);
	
	/**
	 * �������������
	 * 
	 * 2019��3��15��
	 * likai
	 */
	public static void mainFrameStartup() {
		JFrame frame = new JFrame("������");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ѡ�
		JTabbedPane tabPanel = new JTabbedPane();
		tabPanel.addTab("��ϵ��", createTextPanel(CONTANCT_PERSONAL));
		tabPanel.addTab("������", createTextPanel(CONTANCT_GROUP));
		tabPanel.addTab("����������", createAddContanctGroupPanel(ADD_CONTANCT_GROUP));
		frame.setContentPane(tabPanel);
		
		frame.setVisible(true);
	}

	/**
	 *   ����������
	 * @param addContanctGroup
	 * @return
	 * 2019��3��19��
	 * likai
	 */
	private static Component createAddContanctGroupPanel(Integer addContanctGroup) {
		JPanel addContanctGroupPanel = new JPanel();
		addContanctGroupPanel.setLayout(new BorderLayout());
		//�ϱ�����
		JPanel northPanel = new JPanel();
		JLabel groupNameLabel = new JLabel("����������");
		groupNameLabel.setBounds(20, 20, 50, 20);
		northPanel.add(groupNameLabel);
		JTextField groupNameField = new JTextField(20);
		groupNameField.setBounds(100, 20, 100, 20);
		northPanel.add(groupNameField);
		addContanctGroupPanel.add(northPanel, BorderLayout.NORTH);
		
		//�����ϵ��
		JScrollPane scrollPersonalPanel = new JScrollPane();
		addContanctGroupPanel.add(scrollPersonalPanel, BorderLayout.WEST);
		JList<String> personalList = new JList<>();
		scrollPersonalPanel.setPreferredSize(new Dimension(200, 400));
		scrollPersonalPanel.setViewportView(personalList);
		personalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		personalList.setModel(personDataModel);
		
		//�Ҳ�������ѡ��ϵ��
		JScrollPane selectedPanel = new JScrollPane();
		addContanctGroupPanel.add(selectedPanel, BorderLayout.EAST);
		JList<String> selectedList = new JList<>();
		selectedPanel.setPreferredSize(new Dimension(200, 400));
		selectedPanel.setViewportView(selectedList);
		selectedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedList.setModel(selectedDataModel);
		selectedList.addListSelectionListener(new GroupSelectedListListener(selectedList));
		personalList.addListSelectionListener(new PersonalListListener(personalList, selectedList));
		
		//�±�ȷ�ϰ�ť
		JPanel southPanel = new JPanel();
		JButton createGroupBtn = new JButton("ȷ��");
		createGroupBtn.setBounds(250, 500, 100, 50);
		southPanel.add(createGroupBtn);
		addContanctGroupPanel.add(southPanel, BorderLayout.SOUTH);
		return addContanctGroupPanel;
	}

	/**
	 * �������б����panel
	 * @param string
	 * @return
	 * 2019��3��18��
	 * likai
	 */
	private static Component createTextPanel(Integer type) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(2, 2));
		
		//�����ϵ��
		initContactePersonPanel(mainPanel, type);

		//�Ҳ�������
		initChatContentPanel(mainPanel);
        return mainPanel;
	}

	/**
	 * �Ҳ���������
	 * @param mainPanel
	 * 2019��3��15��
	 * likai
	 */
	private static void initChatContentPanel(JPanel mainPanel) {
		JPanel chatContentPanel = new JPanel(new BorderLayout(0, 5));
		//�ϱ�����������ʾ����
		JTextArea showAcceptContent = new JTextArea(20, 40);
		showAcceptContent.setEditable(false);
		chatContentPanel.add(showAcceptContent, BorderLayout.NORTH);
		//���Ͱ�ť
		JButton sendMessageBtn = new JButton("����");
		chatContentPanel.add(sendMessageBtn, BorderLayout.EAST);
		//�±߷�������
		JTextArea sendContent = new JTextArea(8, 40);
		chatContentPanel.add(sendContent, BorderLayout.SOUTH);
		mainPanel.add(chatContentPanel, BorderLayout.CENTER);
	}

	/**
	 * �����ϵ�˽���
	 * @param mainPanel
	 * 2019��3��15��
	 * likai
	 */
	private static void initContactePersonPanel(JPanel mainPanel, Integer type) {

		if(type.equals(CONTANCT_PERSONAL)) {  //�������ϵ��
			JScrollPane scrollPersonalPanel = new JScrollPane();
			scrollPersonalPanel.setPreferredSize(new Dimension(180, 550));
			mainPanel.add(scrollPersonalPanel, BorderLayout.WEST);
			JList<String> jList = new JList<>();
			scrollPersonalPanel.setViewportView(jList);
			jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jList.setModel(personDataModel);
		}else {  //�����������
			JScrollPane scrollGroupPanel = new JScrollPane();
			scrollGroupPanel.setPreferredSize(new Dimension(180, 550));
			mainPanel.add(scrollGroupPanel, BorderLayout.WEST);
			JList<String> jList = new JList<>();
			scrollGroupPanel.setViewportView(jList);
			jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jList.setModel(groupDataModel);
		}
	}
}
