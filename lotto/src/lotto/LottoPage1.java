package lotto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LottoPage1 extends JFrame {

	private JPanel contentPane; // 페이지 배경
	private ArrayList<Integer> numbers; // 선택 번호 6개 저장하는 배열
	private ArrayList<JButton> buttons; // 1 ~ 45 번호 버튼 리스트
	private ArrayList<JPanel> tickets; // 선택한 로또 티켓 리스트
	private LottoManager lottoManager;
	private String choiceType;
	private JLabel lblTotal;

	public LottoPage1(LottoManager lottoManager) {
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
				Image image = toolkit.getImage("images/bg1.jpg");
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
				numbers.clear();
				for (int i = 0; i < 45; i++) {
					ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
					buttons.get(i).setIcon(imageIcon);
					buttons.get(i).putClientProperty("isChecked", "N");
				}
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
				JOptionPane.showMessageDialog(null, "선택 숫자를 제외한 나머지 숫자는 \n 자동으로 입력됩니다 ", "반자동 선택",
						JOptionPane.INFORMATION_MESSAGE);
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

					if (choiceType == "Auto") {
						JOptionPane.showMessageDialog(null, "원하시는 티켓 매수만큼 \n 등록 버튼을 눌러주세요", "자동 선택",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

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
		ImageIcon imagePlus = new ImageIcon("images/set.jpg");
		btnPlus.setIcon(imagePlus);
		btnPlus.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPlus.setBorderPainted(false);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choiceType == "Self") {
					if (numbers.size() < 6) {
						System.out.println("6개 번호를 선택해야 합니다.");
						JOptionPane.showMessageDialog(null, "6개 숫자를 모두 선택해야 합니다.", "숫자 확인",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

				}
				LottoTicket lottoTicket = lottoManager.createLottoTicket(choiceType, numbers);

				lottoManager.lottoTickets.add(lottoTicket);
				System.out.println("저장된 로또 티켓 수량 : " + lottoManager.lottoTickets.size());
				if (lottoManager.lottoTickets.size() > 5) {
					JOptionPane.showMessageDialog(null, "로또 구매는 5장까지 가능합니다.", "구매초과", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("5개 까지 등록 가능합니다 !!");
					return;
				}
				lblTotal.setText(lottoManager.lottoTickets.size() + ",000 원");

				int index = lottoManager.lottoTickets.size() - 1;

				System.out.println(lottoManager.lottoTickets.get(index).getNumbers());

				// tickets.get(index).setText(lottoManager.lottoTickets.get(index).getInputNumber().toString());
				// JPanel 내에 있는 모든 컴포넌트들을 가져옴
				// System.out.println("index : " + tickets.get(index));
				renderTickets();

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
		pnlChoice.setBounds(606, 93, 490, 339);
		pnlChoice.setOpaque(false);

		// 로또 티켓 리스트
		for (int i = 0; i < 5; i++) {
			JPanel choice = new JPanel();
			// choice.setSize(300, 300);
			// choice.setOpaque(false);
			choice.setBackground(Color.decode("#ffffff"));
			choice.setPreferredSize(new Dimension(460, 58));
			choice.setLayout(new FlowLayout(FlowLayout.LEFT, -10, 0));
			JLabel isAuto = new JLabel("   미지정       ");
			choice.add(isAuto);

			tickets.add(choice);
			// JLabel ticketNumbers = new JLabel("00 00 00 00 00 00");
			// choice.add(ticketNumbers);
			// ticketNumbers.setFont(new Font("굴림", Font.PLAIN, 22));
			RoundedLabel button_1 = new RoundedLabel(0);
			RoundedLabel button_2 = new RoundedLabel(0);
			RoundedLabel button_3 = new RoundedLabel(0);
			RoundedLabel button_4 = new RoundedLabel(0);
			RoundedLabel button_5 = new RoundedLabel(0);
			RoundedLabel button_6 = new RoundedLabel(0);
			choice.add(button_1);
			choice.add(button_2);
			choice.add(button_3);
			choice.add(button_4);
			choice.add(button_5);
			choice.add(button_6);

			// 삭제버튼
			JButton btnReset = new JButton();
			ImageIcon imageCancel = new ImageIcon("images/delete.jpg");
			ImageIcon resizedIcon = new ImageIcon(
					imageCancel.getImage().getScaledInstance(54, 42, java.awt.Image.SCALE_SMOOTH));
			btnReset.setIcon(resizedIcon);
			btnReset.setSize(54, 42);
			btnReset.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
			btnReset.setBorderPainted(false);
			btnReset.putClientProperty("index", i);
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Object source = e.getSource(); // 이벤트가 발생한 컴포넌트 가져오기
					JButton button = (JButton) source; // JButton으로 형변환
					int index = (Integer) button.getClientProperty("index");
					try {
						LottoTicket lottoTicket = lottoManager.getLottoTickets().get(index);
						System.out.println("lottoTicket : " + lottoTicket);

						lottoManager.getLottoTickets().remove(index);

						renderTickets();
					} catch (Exception error) {
						System.out.println("인덱스에 lottoTicket 없음");
					}
				}
			});

			choice.add(btnReset);

			pnlChoice.add(choice);
		}

		contentPane.add(pnlChoice);

		// 우측 하단 영역 ////////////////////////////////////////////////////////////

		lblTotal = new JLabel("     0 원");
		lblTotal.setForeground(Color.decode("#f57542"));
		lblTotal.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lblTotal.setBounds(798, 444, 147, 43);
		contentPane.add(lblTotal);

		// 구매하기 버튼 - 버튼 누르면 결과 확인으로 바뀜
		JButton btnPayment_1 = new JButton();
		ImageIcon imageBuy = new ImageIcon("images/buy.jpg");
		btnPayment_1.setIcon(imageBuy);
		btnPayment_1.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPayment_1.setBorderPainted(false);
		btnPayment_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPayment_1.setBounds(957, 435, 113, 64);
		btnPayment_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("구매하기 #############################################");

				for (int i = 0; i < lottoManager.lottoTickets.size(); i++) {
					ArrayList<Integer> correctNumbers = lottoManager.checkLottoTicket(lottoManager.lottoTickets.get(i));
					// 요녀석으로 색을 바꾸게 해줄꺼임
					System.out.println("맞춘 번호 : " + correctNumbers);
				}

				// 구매한 내역 초기화
				lottoManager.lottoTickets.clear();
				renderTickets();

				System.out.println("##################################################");
			}
		});
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

	private void renderTickets() {
		// 로또 티켓이 5개 까지 이므로..
		for (int i = 0; i < 5; i++) {
			Component[] components = tickets.get(i).getComponents();

			if (i < lottoManager.lottoTickets.size()) { // 번호 선택이 된 티켓 리스트이면
				// JButton만 선택하여 처리
				int buttonIndex = 0;
				for (Component component : components) {
					if (component instanceof RoundedLabel) {
						RoundedLabel button = (RoundedLabel) component;
						// JButton에 대한 작업 수행
						// System.out.println("button : " + button);
						// System.out.println("buttonIndex -> " + buttonIndex);
						button.setText(lottoManager.lottoTickets.get(i).getNumbers().get(buttonIndex).toString());
						buttonIndex++;
					} else if (component instanceof JLabel) {
						JLabel type = (JLabel) component;
						switch (lottoManager.lottoTickets.get(i).getType()) {
						case "Self":
							type.setText("   수   동       ");
							break;
						case "Auto":
							type.setText("   자   동       ");
							break;
						case "HalfSelf":
							type.setText("   반자동       ");
							break;
						default:
							type.setText("   미지정       ");
							break;
						}

					}
				}
			} else { // 선택전이면..
				// JButton만 선택하여 처리
				int buttonIndex = 0;
				for (Component component : components) {
					if (component instanceof RoundedLabel) {
						RoundedLabel button = (RoundedLabel) component;
						// JButton에 대한 작업 수행
						// System.out.println("button : " + button);
						// System.out.println("buttonIndex -> " + buttonIndex);
						button.setText("0");
						buttonIndex++;
					} else if (component instanceof JLabel) {
						JLabel type = (JLabel) component;
						type.setText("   미지정       ");
					}
				}
			}
		}
		if (lottoManager.lottoTickets.size() == 0) {
			lblTotal.setText(lottoManager.lottoTickets.size() + "  원");
		} else {

			lblTotal.setText(lottoManager.lottoTickets.size() + ",000 원");
		}
	}
}