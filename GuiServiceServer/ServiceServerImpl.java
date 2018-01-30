import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer{
	
	HashMap serviceList;
	
	public ServiceServerImpl() throws RemoteException{
		setUpServices();
	}
	
	private void setUpServices(){
		
		serviceList = new HashMap();
		serviceList.put("Dice Rolling Service", new DiceService());
		serviceList.put("Day of the Week Service", new DayOfTheWeekService());
		
	}
}