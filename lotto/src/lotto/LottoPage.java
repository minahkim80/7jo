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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class LottoPage extends JFrame {

	private JPanel contentPane; // 페이지 배경
	private ArrayList<Integer> numbers; // 선택 번호 6개 저장하는 배열
	private ArrayList<JButton> buttons; // 1 ~ 45 번호 버튼 리스트
	private ArrayList<JPanel> tickets; // 선택한 로또 티켓 리스트
	private LottoManager lottoManager;
	private String choiceType; // 반자동 ,자동, 수동 선택 파일
	private JLabel lblTotal;
	private JPanel resultPanel;
	private ArrayList<JButton> btnResets = new ArrayList<>();
	private ArrayList<JButton> btnReNums = new ArrayList<>();
	private ArrayList<JButton> btnCopys = new ArrayList<>();

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

		setResizable(false);
		setBounds(100, 100, 1119, 569);

		// 배경 페이지 ////////////////////////////////////////////////////////////
		contentPane = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				Toolkit toolkit = Toolkit.getDefaultToolkit();

				Image image = toolkit.getImage("images/bg1.jpg");
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

///////////////왼쪽 버튼 세개 생성///////////////////////////////////////////////////
		JButton btnSelf = new JButton();//
		JButton btnAuot = new JButton();
		JButton btnSemiAuto = new JButton();

		// 수동선택 버튼
		btnSelf.putClientProperty("choiceType", "Self");
		ImageIcon imageSelf = new ImageIcon("images/self_1.jpg");
		btnSelf.setIcon(imageSelf);
		btnSelf.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnSelf.setBorderPainted(false);
		btnSelf.setFont(new Font("굴림", Font.PLAIN, 20));
		btnSelf.setBounds(40, 45, 173, 113);
		btnSelf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("수동 버튼 클릭 !!!");
				ImageIcon imageSelf = new ImageIcon("images/self_1.jpg");
				btnSelf.setIcon(imageSelf);
				ImageIcon imageAuto = new ImageIcon("images/random.jpg");
				btnAuot.setIcon(imageAuto);
				ImageIcon imageSemiAuto = new ImageIcon("images/hrandom.jpg");
				btnSemiAuto.setIcon(imageSemiAuto);

				choiceType = "Self";
			}
		});
		contentPane.add(btnSelf);

		// 자동선택 버튼 - 자동선택시 사용자 입력값이 0인 상태로 createLottoTicket()메소드 호출
		btnAuot.putClientProperty("choiceType", "Auto");
		ImageIcon imageAuto = new ImageIcon("images/random.jpg");
		btnAuot.setIcon(imageAuto);
		btnAuot.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnAuot.setBorderPainted(false);
		btnAuot.setFont(new Font("굴림", Font.PLAIN, 20));
		btnAuot.setBounds(40, 183, 173, 113);
		btnAuot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numbers.clear();
				for (int i = 0; i < 45; i++) {
					ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
					buttons.get(i).setIcon(imageIcon);
					buttons.get(i).putClientProperty("isChecked", "N");
				}
				System.out.println("자동 버튼 클릭 !!!");
				ImageIcon imageSelf = new ImageIcon("images/self.jpg");
				btnSelf.setIcon(imageSelf);
				ImageIcon imageAuto = new ImageIcon("images/random_1.jpg");
				btnAuot.setIcon(imageAuto);
				ImageIcon imageSemiAuto = new ImageIcon("images/hrandom.jpg");
				btnSemiAuto.setIcon(imageSemiAuto);

				choiceType = "Auto";
			}
		});
		contentPane.add(btnAuot);

		// 반자동 선택 버튼
		btnSemiAuto.putClientProperty("choiceType", "HalfSelf");
		ImageIcon imageSemiAuto = new ImageIcon("images/hrandom.jpg");
		btnSemiAuto.setIcon(imageSemiAuto);
		btnSemiAuto.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnSemiAuto.setBorderPainted(false);
		btnSemiAuto.setFont(new Font("굴림", Font.PLAIN, 20));
		btnSemiAuto.setBounds(40, 319, 173, 113);
		btnSemiAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("반자동 버튼 클릭 !!!");
				ImageIcon imageSelf = new ImageIcon("images/self.jpg");
				btnSelf.setIcon(imageSelf);
				ImageIcon imageAuto = new ImageIcon("images/random.jpg");
				btnAuot.setIcon(imageAuto);
				ImageIcon imageSemiAuto = new ImageIcon("images/hrandom_1.jpg");
				btnSemiAuto.setIcon(imageSemiAuto);

				choiceType = "HalfSelf";
			}
		});
		contentPane.add(btnSemiAuto);

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

					if (button.getClientProperty("isChecked").toString() == "N") {
						// 번호 6개 모두 골랐으면..
						if (numbers.size() >= 6) {
							System.out.println("6개 번호 모두 선택하셨습니다.");
							return;
						}
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

		// 등록버튼 Action시 우측화면에 번호 뿌려줌
		JButton btnPlus = new JButton();
		ImageIcon imagePlus = new ImageIcon("images/set.jpg");
		btnPlus.setIcon(imagePlus);
		btnPlus.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
		btnPlus.setBorderPainted(false);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lottoManager.lottoTickets.size() > 4) {
					JOptionPane.showMessageDialog(null, "로또 구매는 5장까지 가능합니다.", "구매초과", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("5개 까지 등록 가능합니다 !!");
					numbers.clear();

					for (int i = 0; i < 45; i++) {
						ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
						buttons.get(i).setIcon(imageIcon);
						buttons.get(i).putClientProperty("isChecked", "N");
					}

					btnPlus.setEnabled(false);
					for (int i = 0; i < 5; i++) {
						btnCopys.get(i).setEnabled(false);
					}

					return;
				}
				if (choiceType == "HalfSelf") {

					if (numbers.size() == 0) {
						JOptionPane.showMessageDialog(null, "번호를 1개 이상 선택하세요 ", "반자동 선택",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

				}
				if (choiceType == "Self") {
					if (numbers.size() < 6) {
						System.out.println("6개 번호를 선택해야 합니다.");
						JOptionPane.showMessageDialog(null, "6개 숫자를 모두 선택해야 합니다.", "숫자 확인",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

				}

				System.out.println("저장된 로또 티켓 수량 : " + lottoManager.lottoTickets.size());

				LottoTicket lottoTicket = lottoManager.createLottoTicket(choiceType, numbers);
				lottoManager.lottoTickets.add(lottoTicket);
				lblTotal.setText(lottoManager.lottoTickets.size() + ",000 원");

				int index = lottoManager.lottoTickets.size() - 1;

				System.out.println(lottoManager.lottoTickets.get(index).getNumbers());

				renderTickets();

				numbers.clear();

				for (int i = 0; i < 45; i++) {
					ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
					buttons.get(i).setIcon(imageIcon);
					buttons.get(i).putClientProperty("isChecked", "N");
				}

				// 버튼 일괄 활성화
				for (int i = 0; i < 5; i++) {
					Component[] components = tickets.get(i).getComponents();
					// JButton만 선택하여 처리
					int buttonIndex = 0;
					for (Component component : components) {
						if (component instanceof JButton) {
							JButton buttonInner = (JButton) component;
							buttonInner.setEnabled(true);
						}
					}
				}
			}
		});
		panel.add(btnPlus);

		// 우측 영역 ////////////////////////////////////////////////////////////
		JLabel lbl = new JLabel();
		buttons.get(0);

		JPanel pnlChoice = new JPanel();
		pnlChoice.setBounds(580, 93, 500, 339);
		pnlChoice.setOpaque(false);

		// 로또 티켓 리스트
		for (int i = 0; i < 5; i++) {
			JPanel choice = new JPanel();

			// choice.setSize(300, 300);
			// choice.setOpaque(false);
			choice.setBackground(Color.decode("#ffffff"));
			choice.setPreferredSize(new Dimension(500, 58));
			choice.setLayout(new FlowLayout(FlowLayout.LEFT, -10, 0));
			JLabel isAuto = new JLabel("   미지정       ");
			choice.add(isAuto);

			tickets.add(choice);

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

			JButton btnReset = new JButton();
			JButton btnReNum = new JButton();
			JButton btnCopy = new JButton();
			btnResets.add(btnReset);
			btnReNums.add(btnReNum);
			btnCopys.add(btnCopy);
			ImageIcon imageCancel = new ImageIcon("images/delete.jpg");
			ImageIcon resizedIcon = new ImageIcon(
					imageCancel.getImage().getScaledInstance(34, 39, java.awt.Image.SCALE_SMOOTH));
			btnReset.setIcon(resizedIcon);
			btnReset.setSize(34, 39);
			btnReset.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
			btnReset.setBorderPainted(false);
			btnReset.putClientProperty("index", i);
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnPlus.setEnabled(true);
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

					for (int i = 0; i < 5; i++) {
						btnCopys.get(i).setEnabled(true);
					}
				}
			});

			ImageIcon imageCancel1 = new ImageIcon("images/renum.jpg");
			ImageIcon resizedIcon1 = new ImageIcon(
					imageCancel1.getImage().getScaledInstance(34, 39, java.awt.Image.SCALE_SMOOTH));
			btnReNum.setIcon(resizedIcon1);
			btnReNum.setSize(34, 39);
			btnReNum.setContentAreaFilled(false); // 버튼의 기본 모양 숨기기
			btnReNum.setBorderPainted(false);
			btnReNum.putClientProperty("index", i);
			btnReNum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnPlus.setEnabled(true);
					Object source = e.getSource(); // 이벤트가 발생한 컴포넌트 가져오기
					JButton button = (JButton) source; // JButton으로 형변환
					ImageIcon imageSelf = new ImageIcon("images/self_1.jpg");
					btnSelf.setIcon(imageSelf);
					ImageIcon imageAuto = new ImageIcon("images/random.jpg");
					btnAuot.setIcon(imageAuto);
					ImageIcon imageSemiAuto = new ImageIcon("images/hrandom.jpg");
					btnSemiAuto.setIcon(imageSemiAuto);
					choiceType = "Self";

					// 버튼 비활성화
					int index = (Integer) button.getClientProperty("index");
					System.out.println("index : " + index);
					numbers.clear();
					ArrayList<Integer> lottoNumbers = lottoManager.getLottoTickets().get(index).numbers;
					System.out.println("lottoTicket1 : " + lottoNumbers);
					for (int i = 0; i < 45; i++) {
						if (lottoNumbers.contains(i + 1)) {
							ImageIcon imageIcon = new ImageIcon("images/btn_" + (i + 1) + ".jpg");
							buttons.get(i).setIcon(imageIcon);
							buttons.get(i).putClientProperty("isChecked", "Y");

							numbers.add(i + 1);
						} else {
							ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
							buttons.get(i).setIcon(imageIcon);
							buttons.get(i).putClientProperty("isChecked", "N");
						}
					}

					try {
						LottoTicket lottoTicket = lottoManager.getLottoTickets().get(index);
						System.out.println("lottoTicket : " + lottoTicket);

						lottoManager.getLottoTickets().remove(index);

						renderTickets();
					} catch (Exception error) {
						System.out.println("인덱스에 lottoTicket 없음");
					}

					// 버튼 일괄 비활성화
					for (int i = 0; i < 5; i++) {
						btnResets.get(i).setEnabled(false);
						btnReNums.get(i).setEnabled(false);
						btnCopys.get(i).setEnabled(false);
					}
				}
			});

			ImageIcon imageCancel2 = new ImageIcon("images/copy.jpg");
			ImageIcon resizedIcon2 = new ImageIcon(
					imageCancel2.getImage().getScaledInstance(34, 39, java.awt.Image.SCALE_SMOOTH));
			btnCopy.setIcon(resizedIcon2);
			btnCopy.setSize(34, 39);
			btnCopy.setContentAreaFilled(false);
			btnCopy.setBorderPainted(false);
			btnCopy.putClientProperty("index", i);

			btnCopy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (lottoManager.lottoTickets.size() > 4) {

						btnPlus.setEnabled(false);
						for (int i = 0; i < 5; i++) {
							btnCopys.get(i).setEnabled(false);
						}

						return;
					}
					Object source = e.getSource();
					JButton button = (JButton) source;
					int index = (Integer) button.getClientProperty("index");
					try {
						System.out.println("index : " + index);
						btnCopy.setEnabled(false);
						ArrayList<Integer> lottoNumbers = lottoManager.getLottoTickets().get(index).numbers;
						LottoTicket lottoTicket = lottoManager.createLottoTicket(choiceType, lottoNumbers);
						lottoManager.lottoTickets.add(lottoTicket);
						lblTotal.setText(lottoManager.lottoTickets.size() + ",000 원");

						renderTickets();
					} catch (Exception error) {
						System.out.println("인덱스에 lottoTicket 없음");
					}
				}
			});
			choice.add(btnReset);
			choice.add(btnReNum);
			choice.add(btnCopy);
			pnlChoice.add(choice);
		}

		contentPane.add(pnlChoice);

////////////전체 초기화 ////////////////////////////////////////////////////////////
		JButton btnResetAll = new JButton();
		btnResetAll.setBounds(982, 37, 87, 41);
		btnResetAll.setContentAreaFilled(false);
		btnResetAll.setBorderPainted(false);
		ImageIcon imageCancel = new ImageIcon("images/resetAll.jpg");
		btnResetAll.setIcon(imageCancel);
		btnResetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numbers.clear();

				for (int i = 0; i < 45; i++) {
					ImageIcon imageIcon = new ImageIcon("images/btn" + (i + 1) + ".jpg");
					buttons.get(i).setIcon(imageIcon);
					buttons.get(i).putClientProperty("isChecked", "N");
				}

				lottoManager.lottoTickets.clear();
				renderTickets();
				btnPlus.setEnabled(true);
				for (int i = 0; i < 5; i++) {
					btnCopys.get(i).setEnabled(true);
					btnResets.get(i).setEnabled(true);
					btnReNums.get(i).setEnabled(true);
					btnCopys.get(i).setEnabled(true);
				}

			}
		});

		contentPane.add(btnResetAll);

		setVisible(true);
///////////합계 금액 text 출력부분 /////////////////////////////////////////////////
		lblTotal = new JLabel("     0 원");
		lblTotal.setForeground(Color.decode("#f57542"));
		lblTotal.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lblTotal.setBounds(630, 444, 147, 43);
		contentPane.add(lblTotal);

/////////////결과보기 버튼 생성////////////////////////////////////////////////////
		JButton btnResult = new JButton();
		ImageIcon imageResult = new ImageIcon("images/result.jpg");
		btnResult.setIcon(imageResult);
		btnResult.setContentAreaFilled(false);
		btnResult.setBorderPainted(false);
		btnResult.setEnabled(false);
		btnResult.setBounds(957, 435, 113, 64);
/////////////구매하기 버튼 생성////////////////////////////////////////////////////
		JButton btnBuy = new JButton();
		ImageIcon imageBuy = new ImageIcon("images/buy.jpg");
		btnBuy.setIcon(imageBuy);
		btnBuy.setContentAreaFilled(false);
		btnBuy.setBorderPainted(false);
		btnBuy.setBounds(838, 435, 113, 64);
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("결과보기 #############################################");
				btnResult.setEnabled(false);
				// 당첨 번호 제외하고 0으로 바꾸기
				for (int i = 0; i < lottoManager.lottoTickets.size(); i++) {
					Component[] components = tickets.get(i).getComponents();
					// JButton만 선택하여 처리
					int buttonIndex = 0;
					for (Component component : components) {
						if (component instanceof RoundedLabel) {
							RoundedLabel button = (RoundedLabel) component;
							// 당첨 번호가 아니면..
							if (!lottoManager
									.isCorrectNumber(lottoManager.lottoTickets.get(i).getNumbers().get(buttonIndex))) {
								button.setText("0");
							}

							buttonIndex++;
						} else if (component instanceof JLabel) {
							int correctNumbers = lottoManager.checkLottoTicket(lottoManager.lottoTickets.get(i));
							System.out.println(correctNumbers + "등 !!");

							JLabel type = (JLabel) component;
							switch (correctNumbers) {
							case 1:
								type.setText("   에러       ");
								break;
							case 2:
								type.setText("   2 등    ");
								break;
							case 3:
								type.setText("   3 등     ");
								break;
							case 4:
								type.setText("   4 등     ");
								break;
							case 5:
								type.setText("   5 등      ");
								break;
							default:
								type.setText("   꽝 !!    ");

								break;
							}
						}
					}
				}

				resultPanel.setVisible(true);

				// 타이머 설정
				int delay = 7000; // 7초 후에 닫히도록 설정
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						resultPanel.setVisible(false); // JPanel을 숨김

						// 구매한 내역 초기화
						lottoManager.lottoTickets.clear();
						renderTickets();

						btnResetList.setEnabled(true);
						btnPlus.setEnabled(true);
						btnResetAll.setEnabled(true);
						btnBuy.setEnabled(true);
						for (int i = 0; i < 5; i++) {
							btnResets.get(i).setEnabled(true);
							btnReNums.get(i).setEnabled(true);
							btnCopys.get(i).setEnabled(true);
						}

					}
				};
				Timer timer = new Timer(delay, taskPerformer);
				timer.setRepeats(false); // 타이머를 한 번만 실행하도록 설정
				// 타이머 시작
				timer.start();
				System.out.println("##################################################");
			}
		});
		contentPane.add(btnResult);

/////////////구매 버튼 클릭시////////////////////////////////////////////////////	
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("구매하기 #############################################");
				JOptionPane.showMessageDialog(null, "구매가 완료 되었습니다", "구매 완료", JOptionPane.INFORMATION_MESSAGE);
				lblTotal.setText("      0원");
				btnResult.setEnabled(true);
				for (int j = 0; j < 5; j++) {
					btnResetAll.setEnabled(false);
					btnResets.get(j).setEnabled(false);
					btnReNums.get(j).setEnabled(false);
					btnCopys.get(j).setEnabled(false);
				}
				btnBuy.setEnabled(false);
				btnPlus.setEnabled(false);
				btnResetList.setEnabled(false);
				System.out.println("##################################################");
			}
		});
		contentPane.add(btnBuy);

//////////////// 오른쪽 추첨 결과 영역 ////////////////////////////////////////////////////////////
		resultPanel = new JPanel();
		resultPanel.setBounds(28, 19, 498, 488);
		resultPanel.setBackground(Color.decode("#ffffff"));
		resultPanel.setVisible(false);

		String imagePath = "images/resultMain.jpg";
		ImageIcon imageIcon = new ImageIcon(imagePath);
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		resultPanel.add(imageLabel);

		contentPane.add(resultPanel);
		contentPane.setComponentZOrder(resultPanel, 0);

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

///////////////티켓이 좌측에 볼 모양으로 뿌려지게 하는 부분////////////////////////////////////////////
	private void renderTickets() {
		// 로또 티켓이 5개 까지 이므로..
		for (int i = 0; i < 5; i++) {
			Component[] components = tickets.get(i).getComponents();

			if (i < lottoManager.lottoTickets.size()) { // 번호 선택이 된 티켓 리스트이면
				int buttonIndex = 0;
				for (Component component : components) {
					if (component instanceof RoundedLabel) {
						RoundedLabel button = (RoundedLabel) component;

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
			} else { // 선택전이면 기본 설정 출력
				int buttonIndex = 0;
				for (Component component : components) {
					if (component instanceof RoundedLabel) {
						RoundedLabel button = (RoundedLabel) component;
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