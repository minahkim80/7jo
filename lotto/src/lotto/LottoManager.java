package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class LottoManager {
	Scanner scanner;
	ArrayList<LottoTicket> lottoTickets;

	LottoManager() {
		this.scanner = new Scanner(System.in);
		lottoTickets = new ArrayList<>();
	}

	public ArrayList<LottoTicket> getLottoTickets() {
		return lottoTickets;
	}

	public void setLottoTickets(ArrayList<LottoTicket> lottoTickets) {
		this.lottoTickets = lottoTickets;
	}

	public int checkLottoTicket(LottoTicket lottoTicket) {
		ArrayList<Integer> winnerNumber = getWinnerNumbers();
		int bonusNumber = winnerNumber.get(6);
		winnerNumber.remove(6);

		ArrayList<Integer> numbers = lottoTicket.getNumbers();
		System.out.println(" 찍은 번호 : " + numbers);

		for (int i = 0; i < winnerNumber.size(); i++) {
			if (i == 0) {
				System.out.printf(" \t%02d", winnerNumber.get(i));
			} else if (i < winnerNumber.size() - 1) {
				System.out.printf(",%02d", winnerNumber.get(i));
			} else {
				System.out.printf(",%02d + %02d  \n", winnerNumber.get(i), bonusNumber);
			}
		}

		ArrayList<Integer> correctNumbers = new ArrayList<>();

		// 당첨 번호와 구매 로또 번호를 비교하여 숫자 일치 여부
		System.out.println("[내 로또 결과]");
		for (int i = 0; i < numbers.size(); i++) {
			if (winnerNumber.contains(numbers.get(i))) {
				correctNumbers.add(numbers.get(i));
			}
		}

		int result = 0;

		switch (correctNumbers.size()) {
		case 0:
		case 1:
		case 2:
			System.out.println("꽝!");

			break;

		case 3:
			System.out.println("5등!");
			result = 5;
			break;

		case 4:
			System.out.println("4등!");
			result = 4;
			break;

		case 5:
			if (numbers.contains(bonusNumber)) {
				System.out.println("2등!");
				result = 2;
			} else {
				System.out.println("3등!");
				result = 3;
			}
			break;

		case 6:
			System.out.println("1등!");
			result = 1;
			// JOptionPane.showConfirmDialog(null, "1등이 될꺼같냐?", "에러발생",
			// JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			throw new UnsupportedOperationException("될꺼 같냐?");

		// break;
		}

		return result;
	}

	public ArrayList<Integer> getWinnerNumbers() {
		// 임의의 1등 추첨번호랑 보너스번호 7을 생성
		ArrayList<Integer> winnerNumbers = new ArrayList<>(Arrays.asList(5, 10, 15, 20, 25, 30, 35));

		return winnerNumbers;
	}

	public boolean isCorrectNumber(int number) {
		ArrayList<Integer> correctNumbers = getWinnerNumbers();

		for (int i = 0; i < 6; i++) {
			if (correctNumbers.indexOf(number) > -1)
				return true;
		}

		return false;
	}

	public LottoTicket createLottoTicket(String type, ArrayList<Integer> numbers) {

		// 선택한 값을 받아서 숫자 6개의 로또 티켓을 생성함 입력값이 없으면 자동, 입력값이 6개이면 수동이 되는거임
		ArrayList<Integer> inputNumber = new ArrayList<>();

		for (int i = 0; i < numbers.size(); i++) {
			inputNumber.add(numbers.get(i));
		}

		while (inputNumber.size() < 6) {
			int number = (int) (Math.random() * 45) + 1;
			if (!inputNumber.contains(number)) {
				inputNumber.add(number);
			}
		}

		Collections.sort(inputNumber);

		return new LottoTicket(type, inputNumber);
	}

	public LottoTicket[] createLottoTicketList(LottoTicket[] tickets) {

		return tickets;
	}
}

class LottoTicket {
	String type;
	ArrayList<Integer> numbers = new ArrayList<>();

	LottoTicket(String type, ArrayList<Integer> numbers) {
		this.type = type;
		this.numbers = numbers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(ArrayList<Integer> numbers) {
		this.numbers = numbers;
	}
}