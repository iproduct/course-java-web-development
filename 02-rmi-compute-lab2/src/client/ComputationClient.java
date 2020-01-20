package client;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import contract.Compute;

public class ComputationClient {

	public static void main(String[] args) {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
			Compute service = (Compute) registry.lookup("ComputationService");
			BigDecimal result = service.executeTask(new ComputePiTask(Integer.parseInt(args[2])));
			System.out.println("Remote computation result is: " + result.toString());
		} catch (RemoteException e) {
			System.err.println("RMI remote execution error:");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("Service ComputationService does not exist at RMI registry:");
			e.printStackTrace();
		}

	}

}
