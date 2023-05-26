package Package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class LottoManager {
	Scanner scanner;

	LottoManager() {
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		ArrayList<LottoTicket> lottoTickets = new ArrayList<>();

		int count = 0;
		while (count < 5) {
			System.out.print("  Step" + (count + 1) + ". 번호 입력 : ");
			String numberString = scanner.nextLine();
			int[] numbers = new int[0];

			if (numberString.isEmpty()) {

			} else {
				String[] numberStrings = numberString.split(" ");
				numbers = Stream.of(numberStrings).mapToInt(Integer::parseInt).toArray();
			}
			// System.out.println("length : " + numbers.length);

			LottoTicket lottoTicket = createLottoTicket(numbers);

			lottoTickets.add(lottoTicket);

			System.out.println("lottoTicket : " + lottoTicket.getInputNumber());
			count++;
		}

		for (int i = 0; i < lottoTickets.size(); i++) {
			ArrayList<Integer> correctNumbers = checkLottoTicket(lottoTickets.get(i));
			System.out.println("맞춘 번호 : " + correctNumbers);
		}

		// 화면에출력하는애.화면에좀뿌려봐(lottoTickets, correctNumbers);
	}

	public ArrayList<Integer> checkLottoTicket(LottoTicket lottoTicket) {
		ArrayList<Integer> winnerNumber = getWinnerNumbers();
		int bonusNumber = winnerNumber.get(6);
		winnerNumber.remove(6);

		ArrayList<Integer> numbers = lottoTicket.getInputNumber();
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

		// 당첨 번호와 구매 로또 번호를 비교하여 숫자 일치 여부 판단
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
			break;
		}

		return correctNumbers;
	}

	public ArrayList<Integer> getWinnerNumbers() {
		ArrayList<Integer> winnerNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

		return winnerNumbers;
	}

	public LottoTicket createLottoTicket(int[] numbers) {
		ArrayList<Integer> inputNumber = new ArrayList<>();

		for (int i = 0; i < numbers.length; i++) {
			inputNumber.add(numbers[i]);
		}

//		for (int i = 0; i < 6 - numbers.length; i++) {
//			int number = (int) (Math.random() * 45) + 1;
//			inputNumber.add(number);
//		}
		while (inputNumber.size() < 6) {
			int number = (int) (Math.random() * 45) + 1;
			if (!inputNumber.contains(number)) {
				inputNumber.add(number);
			}
		}

		Collections.sort(inputNumber);

		return new LottoTicket(inputNumber);
	}

	public LottoTicket[] createLottoTicketList(LottoTicket[] tickets) {

		return tickets;
	}
}

class LottoTicket {
	ArrayList<Integer> inputNumbers = new ArrayList<>();

	LottoTicket(ArrayList<Integer> numbers) {
		inputNumbers = numbers;
	}

	public ArrayList<Integer> getInputNumber() {
		return inputNumbers;
	}

	public void setInputNumber(ArrayList<Integer> inputNumbers) {
		this.inputNumbers = inputNumbers;
	}
}