package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

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

	// 프로그램 동작
	public void run() {
		// 실제 버튼식 입력을 받기 때문에 변동 될 함수임
		int count = 0;
		while (count < 5) { // 5장 까지 구매 할 수 있으니까
			System.out.print("  Step" + (count + 1) + ". 번호 입력 : ");
			String numberString = scanner.nextLine();
			int[] numbers = new int[0];

			if (numberString.isEmpty()) {

			} else {
				// 사용자가 입력한 값을 스페이스바(빈칸) 기준으로 나눔
				String[] numberStrings = numberString.split(" ");
				numbers = Stream.of(numberStrings).mapToInt(Integer::parseInt).toArray();
			}

//			LottoTicket lottoTicket = createLottoTicket(numbers);
//
//			lottoTickets.add(lottoTicket);

			// System.out.println("lottoTicket : " + lottoTicket.getInputNumber());
			count++;
		}

// 		for (int i = 0; i < lottoTickets.size(); i++) {
// 			ArrayList<Integer> correctNumbers = checkLottoTicket(lottoTickets.get(i));
// 			// 요녀석으로 색을 바꾸게 해줄꺼임
// 			//색을 일부로 빨간색이나 파란샋으로 하고
// 			System.out.println("맞춘 번호 : " + correctNumbers);
// 		}
// 		for (int i = 0; i < lottoTickets.size(); i++) {
// 			ArrayList<Integer> correctNumbers = checkLottoTicket(lottoTickets.get(i));
// 			// 요녀석으로 색을 바꾸게 해줄꺼임
// 			//색을 일부로 빨간색이나 파란샋으로 하고
// 			System.out.println("맞춘 번호 : " + correctNumbers);
// 			for (int j = 0; j < correctNumbers.size(); j++) {
// 				if (correctNumbers.get(j) == winningNumbers.get(j)) {
// 					System.out.print("\033[31m"); // 빨간색으로 출력
// 				} else {
// 					System.out.print("\033[34m"); // 파란색으로 출력
// 				}
// 				System.out.print(correctNumbers.get(j) + " ");
// 			}
// 			System.out.println("\033[0m"); // 기본 색상으로 돌아가기
// 		}

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
		// 임의의 1등 추첨번호랑 보너스번호 7을 생성
		ArrayList<Integer> winnerNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

		return winnerNumbers;
	}

	public LottoTicket createLottoTicket(ArrayList<Integer> numbers) {

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
