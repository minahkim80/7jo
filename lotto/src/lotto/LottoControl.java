package lotto;

public class LottoControl {

	public static void main(String[] args) {
		LottoManager lottoManager = new LottoManager();
		LottoPage lottoPage = new LottoPage(lottoManager);
		lottoPage.setVisible(true);

	}

}