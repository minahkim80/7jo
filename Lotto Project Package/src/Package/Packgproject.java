package Package;

import java.util.*;
import java.util.Arrays;
import java.util.Scanner;

public class Packgproject {

	private static final int[] Array = null;

	public static void main1() {
		Scanner sc = new Scanner(System.in);
		ArrayList<int[]> listNumber = new ArrayList<>(); // 로또 구매 수량에 대한 숫자들을 담을 배열 생성
		// 로또 구매 수량
		char orderNumber = 'A'; // 맨 앞의 기호 초기값
		int[] outputNumber; // 출력 번호
		int[] winningNumber;
		 int[] BonusNumberCreate;
		 int[] bonusNum;// 당첨 번호
	
	
		System.out.println("[로또 발표]");
		// 당첨 번호 랜덤 생성
		winningNumber = lottoNumberCreate();
		int[] bonusNumber = BonusNumberCreate(winningNumber);
		

		int bonus = bonusNumber[0];
		
		
		for (int i = 0; i < winningNumber.length; i++) {
			if (i == 0) {
				System.out.printf(" \t%02d", winningNumber[i]);
			} else if (i < winningNumber.length - 1) {
				System.out.printf(",%02d", winningNumber[i]);
			} else {
				System.out.printf(",%02d, + %02d  \n",winningNumber[i],bonus);
				
			}
		
		}

		// 당첨 번호와 구매 로또 번호를 비교하여 숫자 일치 여부 판단
		System.out.println("[내 로또 결과]");
		for (int i = 0; i < listNumber.size(); i++) {
			int cnt = 0;
			outputNumber = listNumber.get(i);

			for (int j = 0; j < outputNumber.length; j++) {
				for (int k = 0; k < outputNumber.length; k++) {
					if (outputNumber[j] == winningNumber[k]) {
						cnt++;
					}
				}
				if (j == 0) {
					System.out.printf("%s\t%02d", orderNumber, outputNumber[j]);
				} else if (j < outputNumber.length - 1) {
					System.out.printf(",%02d", outputNumber[j]);
				} else {
					System.out.printf(",%02d => %d개 일치\n", outputNumber[j], cnt);
				}
			}
			if (i < listNumber.size() - 1) {
				orderNumber++;
			} else {
				orderNumber = 'A';
				System.out.println();
			}
		}
	}

	// 로또 번호 자동 생성
	public static int[] lottoNumberCreate() {
		int[] inputNumber = new int[6];

		for (int i = 0; i < inputNumber.length; i++) {
			inputNumber[i] = (int) (Math.random() * 50) + 1;
			// 증복 제거
			for (int j = 0; j < i; j++) {
				if (inputNumber[i] == inputNumber[j]) {
					i--;
					
					
				}
			}
		}
		Arrays.sort(inputNumber);
		return inputNumber;
	}
	public static int[] BonusNumberCreate(int[] inputNumber) {
		int[] bonusNum = new int[1];

		for (int i = 0; i < bonusNum.length; i++) {
			bonusNum[i] = (int) (Math.random() * 50) + 1;
			// 증복 제거
			for (int j = 0; j < i; j++) {
				if (bonusNum[i] == inputNumber[j]) {
					i--;
					
					
				}
			}
		}
		Arrays.sort(bonusNum);
		
		return bonusNum;
	}

	public static void main(String[] args) {
		main1(); 
				

	}

}
