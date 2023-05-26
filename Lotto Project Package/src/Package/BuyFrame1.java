package Package;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BuyFrame1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyFrame1 frame = new BuyFrame1();
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
	public BuyFrame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlLottoNum = new JPanel();
		pnlLottoNum.setBounds(198, 25, 248, 249);
		contentPane.add(pnlLottoNum);
		pnlLottoNum.setLayout(new GridLayout(9, 5));

		// 45개의 로또 번호 버튼배열입니다
		ArrayList<JButton> buttons = new ArrayList<>();

		for (int i = 1; i <= 45; i++) {
			JButton button = new JButton(Integer.toString(i));
			buttons.add(button);
			pnlLottoNum.add(button);
		}

		ArrayList<JPanel> tickets = new ArrayList<>();

		JPanel pnlChoice = new JPanel();
		pnlChoice.setBounds(501, 20, 353, 295);
		contentPane.add(pnlChoice);

		for (int i = 0; i < 5; i++) {
			JPanel choice = new JPanel();
			pnlChoice.add(choice);
			JLabel isAuto = new JLabel("수동");
			choice.add(isAuto);
			JLabel number = new JLabel("13 25 26 28 32 35");
			choice.add(number);
			number.setFont(new Font("굴림", Font.PLAIN, 22));
			JButton btnReset = new JButton("초기화");
			choice.add(btnReset);
			tickets.add(choice);

		}

		JLabel lblTotal = new JLabel("***** 원");
		lblTotal.setForeground(Color.GRAY);
		lblTotal.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lblTotal.setBounds(608, 345, 113, 23);
		contentPane.add(lblTotal);

		JPanel panel = new JPanel();
		panel.setBounds(171, 284, 303, 33);
		contentPane.add(panel);

		JButton btnAutoPlus = new JButton("자동버튼");
		btnAutoPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnAutoPlus);

		JButton btnResetList = new JButton("선택 초기화");
		btnResetList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnResetList);

		JButton btnPlus = new JButton("등록");
		panel.add(btnPlus);

		JButton btnPayment = new JButton("자동 선택");
		btnPayment.setBounds(34, 25, 132, 104);
		contentPane.add(btnPayment);
		btnPayment.setFont(new Font("굴림", Font.PLAIN, 20));

		JButton btnPayment_1 = new JButton("결제하기");
		btnPayment_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment_1.setBounds(733, 316, 95, 59);
		contentPane.add(btnPayment_1);

		JButton btnPayment_2 = new JButton("수동 선택");
		btnPayment_2.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment_2.setBounds(34, 152, 132, 104);
		contentPane.add(btnPayment_2);

		JButton btnPayment_2_1 = new JButton("반자동 선택");
		btnPayment_2_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment_2_1.setBounds(34, 277, 132, 98);
		contentPane.add(btnPayment_2_1);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(379, 342, 67, 33);
		contentPane.add(btnNewButton);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(294, 344, 67, 31);
		contentPane.add(comboBox);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		setVisible(true);
	}
}