package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import contract.Compute;
import contract.Task;

public class ComputationServer implements Compute {

	@Override
	public <T> T executeTask(Task<T> task) throws RemoteException {
		return task.execute();
	}

	public static void main(String[] args) {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.createRegistry(2020);
//			Registry registry = LocateRegistry.getRegistry();
			ComputationServer server = new ComputationServer();
			Compute stub = (Compute) UnicastRemoteObject.exportObject(server, 0);
			registry.rebind("ComputationService", stub);
			System.out.println("ComputationService started successfully.");
		} catch (Exception e) {
			System.err.println("ComputationServer exception:");
			e.printStackTrace();
		}
	}

}
