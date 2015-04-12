package phoneSim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class SMSProcess implements Runnable {
	StartPhone sp = null;
	String person = "";
	public SMSProcess(String secondOrder,StartPhone sp){
		this.sp = sp;
		this.person = secondOrder;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ObjectInputStream ois;
		if(this.sp.getCredit()<=0)
		{
			System.out.println("No enough credit,please charge!");
			System.out.println("Please input your action(current credit: "+this.sp.getCredit()+" ):");
			return;
		}
		try {
			ois = new ObjectInputStream(new FileInputStream("Contact.txt"));
			HashMap contact = (HashMap)ois.readObject();
			ois.close();
			if(contact.containsKey(this.person))
			{	
				this.sp.setCredit(this.sp.getCredit()-1);
				System.out.println("sent Hello To "+this.person+" (" +contact.get(this.person)+ ")");
			}
			else
				System.out.println("Sorry,no such a person in contact list.");
			System.out.println("Please input your action(current credit: "+this.sp.getCredit()+" ):");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
