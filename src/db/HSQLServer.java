package db;

import org.hsqldb.Server;

/**
 * 
 * @author Raj
 *
 */
public class HSQLServer
{
	private final static String DB_NAME = "MiniNetDB";
	private final static String DB_PATH = "file:MiniNetDB";
	private Server _hsqlServer = null;
	
	public HSQLServer()
	{
		_hsqlServer = new Server();
		_hsqlServer.setLogWriter(null);
		_hsqlServer.setSilent(true);
		_hsqlServer.setDatabaseName(0, DB_NAME);
		_hsqlServer.setDatabasePath(0, DB_PATH);
//		_hsqlServer.putPropertiesFromString("hsqldb.write_delay=false");
//		_hsqlServer.setNoSystemExit(true);
//		_hsqlServer.setPort(8080);
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
