import java.rmi.*;
import java.rmi.server.*;
//class that implements the remote interface
public class AddServerImpl extends UnicastRemoteObject 
implements AddServerIntf {
//constructor
public AddServerImpl() throws RemoteException {
}
//implement method declared in the interface
public int add(int d1, int d2) throws RemoteException {
 return d1 + d2; }
}
