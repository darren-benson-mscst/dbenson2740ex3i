package dbenson2740ex3i;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ExamForm extends JFrame {

	private JPanel contentPane;
	private JLabel resultLabel;
	private JTextField answersInputTextField;
	private JButton btnPrevious;
	private JButton btnNext;
	private JList responsesList;
	private DefaultListModel responsesListModel;
	private JLabel labelRepNum;
	private DriverExam exam;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExamForm() {
		setTitle("dbenson Ex3i : Driver Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(10, 11, 78, 14);
		contentPane.add(lblResponses);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(132, 11, 46, 14);
		contentPane.add(lblResult);
		
		JList questNumList = new JList();
		questNumList.setForeground(Color.LIGHT_GRAY);
		questNumList.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		questNumList.setToolTipText("");
		questNumList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		questNumList.setEnabled(false);
		questNumList.setBounds(10, 34, 25, 184);
		contentPane.add(questNumList);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				do_responsesList_valueChanged(arg0);
			}
		});
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setBounds(45, 34, 32, 184);
		contentPane.add(responsesList);
		
		resultLabel = new JLabel("");
		lblResult.setLabelFor(resultLabel);
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setBounds(132, 37, 125, 25);
		contentPane.add(resultLabel);
		
		JButton btnPass = new JButton("Pass");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnPass_actionPerformed(arg0);
			}
		});
		btnPass.setBounds(132, 64, 111, 23);
		contentPane.add(btnPass);
		
		JButton btnCorrect = new JButton("Correct");
		btnCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCorrect_actionPerformed(e);
			}
		});
		btnCorrect.setBounds(132, 90, 111, 23);
		contentPane.add(btnCorrect);
		
		JButton btnIncorrect = new JButton("Incorrect");
		btnIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnIncorrect_actionPerformed(e);
			}
		});
		btnIncorrect.setBounds(132, 117, 111, 23);
		contentPane.add(btnIncorrect);
		
		JButton btnListIncorrect = new JButton("List Incorrect");
		btnListIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnListIncorrect_actionPerformed(e);
			}
		});
		btnListIncorrect.setBounds(132, 144, 111, 23);
		contentPane.add(btnListIncorrect);
		
		btnPrevious = new JButton("Prev");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPrevious_actionPerformed(e);
			}
		});
		btnPrevious.setEnabled(false);
		btnPrevious.setBounds(94, 219, 66, 23);
		contentPane.add(btnPrevious);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNext_actionPerformed(e);
			}
		});
		btnNext.setBounds(94, 247, 66, 23);
		contentPane.add(btnNext);
		
		answersInputTextField = new JTextField();
		answersInputTextField.setBounds(45, 229, 32, 24);
		contentPane.add(answersInputTextField);
		answersInputTextField.setColumns(10);
		
		labelRepNum = new JLabel("#0");
		labelRepNum.setLabelFor(answersInputTextField);
		labelRepNum.setBounds(10, 234, 25, 14);
		contentPane.add(labelRepNum);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		DriverExam exam = mapper.getDriverExam();
		this.responsesListModel = exam.getAnswers();
		responsesList.setModel(this.responsesListModel);
		
	}
	protected void do_btnPass_actionPerformed(ActionEvent arg0) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		}
		if (exam.passed()){
			resultLabel.setText("You Passed");
			 }
		else
			resultLabel.setText("You Failed");
		
	}
	protected void do_btnCorrect_actionPerformed(ActionEvent e) {
	}
	protected void do_btnIncorrect_actionPerformed(ActionEvent e) {
	}
	protected void do_btnListIncorrect_actionPerformed(ActionEvent e) {
	}
	protected void do_btnPrevious_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(answersInputTextField.getText().toUpperCase(), 
				responsesList.getSelectedIndex());
		responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
		labelRepNum.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		answersInputTextField.setText((String)responsesList.getSelectedValue());
		
		btnNext.setEnabled(true);
		if (responsesList.getSelectedIndex() == 0)
			btnPrevious.setEnabled(false);
		answersInputTextField.requestFocus();
	}
	protected void do_btnNext_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(
                answersInputTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
        labelRepNum.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        answersInputTextField.setText((String)responsesList.getSelectedValue());
        
        btnPrevious.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
            btnNext.setEnabled(false);
        answersInputTextField.requestFocus();
	}
	protected void do_responsesList_valueChanged(ListSelectionEvent arg0) {
		labelRepNum.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
		answersInputTextField.setText((String)responsesList.getSelectedValue());    

        btnPrevious.setEnabled(true);
        btnNext.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
            btnNext.setEnabled(false);
        if (responsesList.getSelectedIndex() == 0) 
            btnPrevious.setEnabled(false);
        answersInputTextField.requestFocus();        
	}
}
