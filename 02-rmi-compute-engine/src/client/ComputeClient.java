package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import contract.Compute;

import java.math.BigDecimal;

public class ComputeClient {

	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "ComputationServer";
			Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
			Compute comp = (Compute) registry.lookup(name);
			ComputePiTask task = new ComputePiTask(Integer.parseInt(args[2]));
			BigDecimal pi = comp.executeTask(task);
			System.out.println(pi);
		} catch (RemoteException e) {
			System.err.println("ComputePi exception:");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("Server object not registered with RMI Registry.");
			e.printStackTrace();
		}
	}
}