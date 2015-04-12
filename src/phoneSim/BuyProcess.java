package phoneSim;

public class BuyProcess implements Runnable {
	StartPhone phonepro = null;
	long addition = 0;
	public BuyProcess(long addition,StartPhone sp){
		this.addition = addition;
		this.phonepro = sp;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.phonepro.setCredit(this.phonepro.getCredit()+this.addition);
		System.out.println("Purchase "+this.addition+" ,current credit: "+this.phonepro.getCredit());
		System.out.println("Please input your action(current credit: "+this.phonepro.getCredit()+" ):");
	}

}
