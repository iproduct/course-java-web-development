package contract;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
	<T> T executeTask(Task<T> task) throws RemoteException;
}
