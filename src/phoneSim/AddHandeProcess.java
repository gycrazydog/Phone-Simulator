package phoneSim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class AddHandeProcess implements Runnable {
	String so = "";
	String to = "";
	public AddHandeProcess(String secondOrder,String thirdOrder){
		this.so = secondOrder;
		this.to = thirdOrder;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("Contact.txt"));
			HashMap contact = (HashMap)ois.readObject();
			contact.put(this.so,this.to);
			ois.close();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Contact.txt"));
            oos.writeObject(contact);  
            oos.flush();
            oos.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
