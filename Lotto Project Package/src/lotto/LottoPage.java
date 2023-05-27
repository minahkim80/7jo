package lotto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LottoPage extends JFrame {

	private JPanel contentPane; // 페이지 배경
	private ArrayList<Integer> numbers; // 선택 번호 6개 저장하는 배열
	private ArrayList<JButton> buttons; // 1 ~ 45 번호 버튼 리스트
	private ArrayList<JLabel> tickets; // 선택한 로또 티켓 리스트
	private LottoManager lottoManager;
	private String choiceType;

	public LottoPage(LottoManager lottoManager) {
		// 회원이 입력하는 로또 번호 6개
		numbers = new ArrayList<>();
		// 45개의 로또 번호 버튼 배열
		buttons = new ArrayList<>();
		// 선택한 로또 티켓 배열
		tickets = new ArrayList<>();
		// 로또 관리자 클래스
		this.lottoManager = lottoManager;
		// 선택 타입
		choiceType = "Self"; // Self, Auto, HalfSelf

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1119, 569);

		// 배경 페이지 ////////////////////////////////////////////////////////////
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Toolkit을 사용하여 Image 객체 생성
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Image image = toolkit.getImage("images/bg.jpg");
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnPayment_2 = new JButton();
		JButton btnPayment = new JButton();
		JButton btnPayment_2_1 = new JButton();

		// 수동선택 버튼
		btnPayment_2.putClientProperty("choiceType", "Self");
		ImageIcon imageSelf = new ImageIcon("images/self_1.jpg");
		btnPayment_2.setIcon(imageSelf);
		btnPayment_2.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPayment_2.setBorderPainted(false);
		btnPayment_2.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment_2.setBounds(40, 45, 173, 113);
		btnPayment_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("수동 버튼 클릭 !!!");
				ImageIcon imageSelf = new ImageIcon("images/self_1.jpg");
				btnPayment_2.setIcon(imageSelf);
				ImageIcon imagePayment = new ImageIcon("images/random.jpg");
				btnPayment.setIcon(imagePayment);
				ImageIcon imageHalfRandom = new ImageIcon("images/hrandom.jpg");
				btnPayment_2_1.setIcon(imageHalfRandom);

				choiceType = "Self";
			}
		});
		contentPane.add(btnPayment_2);

		// 자동선택 버튼 - 자동선택시 사용자 입력값이 0인 상태로 createLottoTicket()메소드 호출
		btnPayment.putClientProperty("choiceType", "Auto");
		ImageIcon imagePayment = new ImageIcon("images/random.jpg");
		btnPayment.setIcon(imagePayment);
		btnPayment.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPayment.setBorderPainted(false);
		btnPayment.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment.setBounds(40, 183, 173, 113);
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("자동 버튼 클릭 !!!");
				ImageIcon imageSelf = new ImageIcon("images/self.jpg");
				btnPayment_2.setIcon(imageSelf);
				ImageIcon imagePayment = new ImageIcon("images/random_1.jpg");
				btnPayment.setIcon(imagePayment);
				ImageIcon imageHalfRandom = new ImageIcon("images/hrandom.jpg");
				btnPayment_2_1.setIcon(imageHalfRandom);

				choiceType = "Auto";
			}
		});
		contentPane.add(btnPayment);

		// 반자동 선택 버튼
		btnPayment_2_1.putClientProperty("choiceType", "HalfSelf");
		ImageIcon imageHalfRandom = new ImageIcon("images/hrandom.jpg");
		btnPayment_2_1.setIcon(imageHalfRandom);
		btnPayment_2_1.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPayment_2_1.setBorderPainted(false);
		btnPayment_2_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment_2_1.setBounds(40, 319, 173, 113);
		btnPayment_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("반자동 버튼 클릭 !!!");
				ImageIcon imageSelf = new ImageIcon("images/self.jpg");
				btnPayment_2.setIcon(imageSelf);
				ImageIcon imagePayment = new ImageIcon("images/random.jpg");
				btnPayment.setIcon(imagePayment);
				ImageIcon imageHalfRandom = new ImageIcon("images/hrandom_1.jpg");
				btnPayment_2_1.setIcon(imageHalfRandom);

				choiceType = "HalfSelf";
			}
		});
		contentPane.add(btnPayment_2_1);

		// 버튼 선택 영역 ////////////////////////////////////////////////////////////
		JPanel pnlLottoNum = new JPanel();
		pnlLottoNum.setBounds(261, 126, 243, 287);
		pnlLottoNum.setLayout(new GridLayout(9, 5));
		pnlLottoNum.setOpaque(false);

		// 1 ~ 45 선택 버튼
		for (int i = 0; i < 45; i++) {
			buttons.add(new JButton());
			buttons.get(i).putClientProperty("number", Integer.toString(i + 1));
			buttons.get(i).putClientProperty("isChecked", "N");
			buttons.get(i).setSize(29, 30);
			ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
			buttons.get(i).setIcon(imageIcon);
			buttons.get(i).setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
			buttons.get(i).setBorderPainted(false);

			buttons.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object source = e.getSource(); // 이벤트가 발생한 컴포넌트 가져오기
					JButton button = (JButton) source; // JButton으로 형변환
					String buttonText = button.getClientProperty("number").toString(); // 버튼의 텍스트 값 가져오기

					if (numbers.size() >= 6) {
						System.out.println("6개 번호 모두 선택하셨습니다.");
						return;
					}

					if (button.getClientProperty("isChecked").toString() == "N") {
						ImageIcon imageIcon = new ImageIcon("images/btn_" + buttonText + ".jpg");
						button.setIcon(imageIcon);
						button.putClientProperty("isChecked", "Y");
					} else {
						ImageIcon imageIcon = new ImageIcon("images/btn" + buttonText + ".jpg");
						button.setIcon(imageIcon);
						button.putClientProperty("isChecked", "N");
					}

					System.out.println("ActionEvent : " + buttonText);
					inputNumbers(Integer.parseInt(buttonText));
				}
			});

			pnlLottoNum.add(buttons.get(i));
		}
		contentPane.add(pnlLottoNum);

		// 초기화, 선택 완료 버튼 영역 ////////////////////////////////////////////////////////////
		JPanel panel = new JPanel();
		panel.setBounds(253, 444, 269, 64);
		Color customColor = new Color(255, 255, 255); // 빨간색 (R: 255, G: 0, B: 0)
		panel.setBackground(customColor);
		contentPane.add(panel);

		// 초기화 버튼
		JButton btnResetList = new JButton();
		ImageIcon imageReset = new ImageIcon("images/reset.jpg");
		btnResetList.setIcon(imageReset);
		btnResetList.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnResetList.setBorderPainted(false);
		btnResetList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numbers.clear();

				for (int i = 0; i < 45; i++) {
					ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
					buttons.get(i).setIcon(imageIcon);
					buttons.get(i).putClientProperty("isChecked", "N");
				}
			}
		});
		panel.add(btnResetList);

		// 구매 확인 버튼 - 배열에 입력된 6개의 값을 우측에 뿌려줌
		JButton btnPlus = new JButton();
		ImageIcon imagePlus = new ImageIcon("images/conform.jpg");
		btnPlus.setIcon(imagePlus);
		btnPlus.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPlus.setBorderPainted(false);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LottoTicket lottoTicket = lottoManager.createLottoTicket(numbers);

				lottoManager.lottoTickets.add(lottoTicket);
				System.out.println(lottoManager.lottoTickets.get(0).getInputNumber());

				tickets.get(0).setText(lottoManager.lottoTickets.get(0).getInputNumber().toString());

				numbers.clear();

				for (int i = 0; i < 45; i++) {
					ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
					buttons.get(i).setIcon(imageIcon);
					buttons.get(i).putClientProperty("isChecked", "N");
				}
			}
		});
		panel.add(btnPlus);

		// 우측 영역 ////////////////////////////////////////////////////////////
		JLabel lbl = new JLabel();
		buttons.get(0);

		JPanel pnlChoice = new JPanel();
		pnlChoice.setBounds(540, 92, 439, 320);
		pnlChoice.setOpaque(false);

		// 로또 티켓 리스트
		for (int i = 0; i < 5; i++) {
			JPanel choice = new JPanel();
			choice.setOpaque(false);
			pnlChoice.add(choice);
			JLabel isAuto = new JLabel("미지정");
			choice.add(isAuto);

			JLabel ticketNumbers = new JLabel("00 00 00 00 00 00");
			choice.add(ticketNumbers);
			ticketNumbers.setFont(new Font("굴림", Font.PLAIN, 22));
			// 초기화 버튼 - 삭제 버튼으로 변경 예정
			JButton btnReset = new JButton();
			ImageIcon imageCancel = new ImageIcon("images/reset.jpg");
			btnReset.setIcon(imageCancel);
			btnReset.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
			btnReset.setBorderPainted(false);
			choice.add(btnReset);

			tickets.add(ticketNumbers);
		}

		contentPane.add(pnlChoice);

		// 우측 하단 영역 ////////////////////////////////////////////////////////////

		// 구매 금액
		JLabel lblTotal = new JLabel("***** 원");
		lblTotal.setForeground(Color.ORANGE);
		lblTotal.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lblTotal.setBounds(800, 454, 147, 43);
		contentPane.add(lblTotal);

		// 구매하기 버튼 - 버튼 누르면 결과 확인으로 바뀜
		JButton btnPayment_1 = new JButton();
		ImageIcon imageBuy = new ImageIcon("images/buy.jpg");
		btnPayment_1.setIcon(imageBuy);
		btnPayment_1.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPayment_1.setBorderPainted(false);
		btnPayment_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment_1.setBounds(957, 435, 113, 64);
		contentPane.add(btnPayment_1);

		setVisible(true);
	}

	private void inputNumbers(int number) {
		int index = numbers.indexOf(number);
		if (index > -1) {
			numbers.remove(index);
		} else {
			numbers.add(number);
		}

		System.out.println(numbers);
	}

}