package phoneSim;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;


public class StartPhone {
	private long credit = 20;
	private boolean calling = false;
	HashMap contact = new HashMap();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StartPhone sp = new StartPhone();
		Scanner in = new Scanner(System.in);
		System.out.println("Please input your action(current credit: "+sp.credit+" ):");
		while(true){
			String orderStr = in.next();
			if(orderStr.compareTo("buy")==0){
				long money = in.nextInt();
				Thread t = new Thread(new BuyProcess(money,sp));
				t.start();
			}
			else if(orderStr.compareTo("add")==0){
				String secondOrder = in.next();
				String thirdOrder = in.next();
				Thread t = new Thread(new AddHandeProcess(secondOrder,thirdOrder));
				t.start();
			}
			else if(orderStr.compareTo("call")==0){
				String secondOrder = in.next();
				Thread t = new Thread(new CallProcess(secondOrder,sp));
				t.start();
			}
			else if(orderStr.compareTo("hangup")==0){
				sp.calling = false;
				System.out.println("Please input your action(current credit: "+sp.credit+" ):");
			}
			else if(orderStr.compareTo("sms")==0){
				String secondOrder = in.next();
				Thread t = new Thread(new SMSProcess(secondOrder,sp));
				t.start();
			}
			else
			System.out.println("Invalid order,please check your input.");
		}
	}
	public long getCredit() {
		return credit;
	}
	public void setCredit(long credit) {
		this.credit = credit;
	}
	public boolean isCalling() {
		return calling;
	}
	public void setCalling(boolean calling) {
		this.calling = calling;
	}
}
