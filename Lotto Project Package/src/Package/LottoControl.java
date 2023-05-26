package Package;

public class LottoControl {
//여기서는 실행만 시킬 예정인데.. 뷰에서 필요한 메소드들을 만들게 될지도 모르겠음... 

	public static void main(String[] args) {

		LottoManager lottoManager = new LottoManager();
		new MyFrame();
		lottoManager.run();

	}

}