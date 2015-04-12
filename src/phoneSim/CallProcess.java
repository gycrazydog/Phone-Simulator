package phoneSim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class CallProcess implements Runnable {
	String person = "";
	StartPhone sp = null;
	ObjectInputStream ois;
	public CallProcess(String secondOrder,StartPhone sp){
		this.person = secondOrder;
		this.sp = sp;
	}
	public void run() {
		if(this.sp.isCalling()==false){
			this.sp.setCalling(true);
			HashMap contact = null;
			try {
				ois = new ObjectInputStream(new FileInputStream("Contact.txt"));
				contact = (HashMap)ois.readObject();
				ois.close();
				if(contact.containsKey(this.person))
					System.out.println("Calling "+this.person+" (" +contact.get(this.person)+ ")");
				else
				{	
					System.out.println("Sorry,no such a person in contact list.");
					System.out.println("Please input your action(current credit: "+this.sp.getCredit()+" ):");
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			long starttime = System.currentTimeMillis();
			while(this.sp.isCalling()==true){
				if(this.sp.getCredit()<=0)
				{
					System.out.println("No enough credit,please charge!");
					System.out.println("Please input your action(current credit: "+this.sp.getCredit()+" ):");
					return;
				}
				long endtime = System.currentTimeMillis();
				if(endtime-starttime>10000){
					this.sp.setCredit(this.sp.getCredit()-2);
					System.out.println("calling,current credit: "+this.sp.getCredit());
					starttime = endtime;
				}
			}
			System.out.println("Hanging call to "+this.person+" (" +contact.get(this.person)+ ")");
		}
		else{
				System.out.println("Please Hang up current call first!");
		}
	}
}
