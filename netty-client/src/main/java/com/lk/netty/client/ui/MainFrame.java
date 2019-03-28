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
 *  聊天主界面
 * 2019年3月15日
 * likai
 */
public class MainFrame {
	
	private final static Integer CONTANCT_PERSONAL = 0;
	private final static Integer CONTANCT_GROUP = 1;
	private final static Integer ADD_CONTANCT_GROUP = 2;
	//联系人
	public static String[] personalData = new String[] {};
	//已选联系人
	public static String[] selectedData = new String[] {}; 
	//群组
	public static String[] groupData = new String[] {};
	//联系人数据模型
	public static DefaultComboBoxModel<String> personDataModel = new DefaultComboBoxModel(personalData);
	//群组数据模型
	public static DefaultComboBoxModel<String> groupDataModel = new DefaultComboBoxModel(personalData);
	//已选人列表数据模型
	public static DefaultComboBoxModel<String> selectedDataModel = new DefaultComboBoxModel(selectedData);
	
	/**
	 * 主界面启动入口
	 * 
	 * 2019年3月15日
	 * likai
	 */
	public static void mainFrameStartup() {
		JFrame frame = new JFrame("聊天室");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//选项卡
		JTabbedPane tabPanel = new JTabbedPane();
		tabPanel.addTab("联系人", createTextPanel(CONTANCT_PERSONAL));
		tabPanel.addTab("讨论组", createTextPanel(CONTANCT_GROUP));
		tabPanel.addTab("新增讨论组", createAddContanctGroupPanel(ADD_CONTANCT_GROUP));
		frame.setContentPane(tabPanel);
		
		frame.setVisible(true);
	}

	/**
	 *   创建讨论组
	 * @param addContanctGroup
	 * @return
	 * 2019年3月19日
	 * likai
	 */
	private static Component createAddContanctGroupPanel(Integer addContanctGroup) {
		JPanel addContanctGroupPanel = new JPanel();
		addContanctGroupPanel.setLayout(new BorderLayout());
		//上边组名
		JPanel northPanel = new JPanel();
		JLabel groupNameLabel = new JLabel("讨论组名：");
		groupNameLabel.setBounds(20, 20, 50, 20);
		northPanel.add(groupNameLabel);
		JTextField groupNameField = new JTextField(20);
		groupNameField.setBounds(100, 20, 100, 20);
		northPanel.add(groupNameField);
		addContanctGroupPanel.add(northPanel, BorderLayout.NORTH);
		
		//左侧联系人
		JScrollPane scrollPersonalPanel = new JScrollPane();
		addContanctGroupPanel.add(scrollPersonalPanel, BorderLayout.WEST);
		JList<String> personalList = new JList<>();
		scrollPersonalPanel.setPreferredSize(new Dimension(200, 400));
		scrollPersonalPanel.setViewportView(personalList);
		personalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		personalList.setModel(personDataModel);
		
		//右侧组内已选联系人
		JScrollPane selectedPanel = new JScrollPane();
		addContanctGroupPanel.add(selectedPanel, BorderLayout.EAST);
		JList<String> selectedList = new JList<>();
		selectedPanel.setPreferredSize(new Dimension(200, 400));
		selectedPanel.setViewportView(selectedList);
		selectedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedList.setModel(selectedDataModel);
		selectedList.addListSelectionListener(new GroupSelectedListListener(selectedList));
		personalList.addListSelectionListener(new PersonalListListener(personalList, selectedList));
		
		//下边确认按钮
		JPanel southPanel = new JPanel();
		JButton createGroupBtn = new JButton("确定");
		createGroupBtn.setBounds(250, 500, 100, 50);
		southPanel.add(createGroupBtn);
		addContanctGroupPanel.add(southPanel, BorderLayout.SOUTH);
		return addContanctGroupPanel;
	}

	/**
	 * 创建带有标题的panel
	 * @param string
	 * @return
	 * 2019年3月18日
	 * likai
	 */
	private static Component createTextPanel(Integer type) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(2, 2));
		
		//左侧联系人
		initContactePersonPanel(mainPanel, type);

		//右侧聊天板块
		initChatContentPanel(mainPanel);
        return mainPanel;
	}

	/**
	 * 右侧聊天内容
	 * @param mainPanel
	 * 2019年3月15日
	 * likai
	 */
	private static void initChatContentPanel(JPanel mainPanel) {
		JPanel chatContentPanel = new JPanel(new BorderLayout(0, 5));
		//上边聊天内容显示区域
		JTextArea showAcceptContent = new JTextArea(20, 40);
		showAcceptContent.setEditable(false);
		chatContentPanel.add(showAcceptContent, BorderLayout.NORTH);
		//发送按钮
		JButton sendMessageBtn = new JButton("发送");
		chatContentPanel.add(sendMessageBtn, BorderLayout.EAST);
		//下边发送内容
		JTextArea sendContent = new JTextArea(8, 40);
		chatContentPanel.add(sendContent, BorderLayout.SOUTH);
		mainPanel.add(chatContentPanel, BorderLayout.CENTER);
	}

	/**
	 * 左边联系人界面
	 * @param mainPanel
	 * 2019年3月15日
	 * likai
	 */
	private static void initContactePersonPanel(JPanel mainPanel, Integer type) {

		if(type.equals(CONTANCT_PERSONAL)) {  //如果是联系人
			JScrollPane scrollPersonalPanel = new JScrollPane();
			scrollPersonalPanel.setPreferredSize(new Dimension(180, 550));
			mainPanel.add(scrollPersonalPanel, BorderLayout.WEST);
			JList<String> jList = new JList<>();
			scrollPersonalPanel.setViewportView(jList);
			jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jList.setModel(personDataModel);
		}else {  //如果是讨论组
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
