package db;

import org.hsqldb.HsqlException;
import org.hsqldb.Server;

/**
 * 
 * @author Raj
 *
 */
public class HSQLServer
{
	private final static String DB_NAME = "MiniNetDB";
	private final static String DB_PATH = "file:MYDB";
	private Server _hsqlServer = null;
	
	public HSQLServer()
	{
		_hsqlServer = new Server();
		_hsqlServer.setLogWriter(null);
		_hsqlServer.setSilent(true);
		_hsqlServer.setDatabaseName(0, DB_NAME);
		_hsqlServer.setDatabasePath(0, DB_PATH);
	}
	
	public void start()
	{
		_hsqlServer.start();
	}
	
	public void stop()
	{	
		if (_hsqlServer != null) {
            _hsqlServer.stop();
        }else {
        	System.out.println("Server not found");
        }
	}
	
	public Server getServer()
	{
		return _hsqlServer;
	}
}
