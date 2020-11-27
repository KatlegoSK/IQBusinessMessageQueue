import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.*;
public class Produce {
	
  /*set up a que for the receiver */
  private final static String QUEUE_NAME = "messaging";
  public static void main(String[] argv) throws Exception {
	  
	 String  rabbitmqserver, rabbitmquser, rabbitmqpassword, rabbitmqport, queueprefix;

	 	//To define system environment variables
	    Map<String, String> env = System.getenv();
		
	    //These variables are defined or configured in Class debug settings
		rabbitmqserver = env.get("rabbitmqserver");
		rabbitmquser = env.get("rabbitmquser");
		rabbitmqpassword = env.get("rabbitmqpassword");
		rabbitmqport = env.get("rabbitmqport");
		queueprefix = (( queueprefix = env.get("queueprefix")) != null) ? queueprefix : "";
     
	 ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost(rabbitmqserver);
	    	factory.setHost(rabbitmqserver);
			factory.setPort(Integer.parseInt(rabbitmqport));
			factory.setUsername(rabbitmquser);
			factory.setPassword(rabbitmqpassword);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            /*Data to Send*/
            
            System.out.println("Please Enter your name:");
            Scanner in = new Scanner(System.in);   
            String name = in.nextLine();   
            
            String message = "Hello my name is,"+name;
            
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
  }
  
	
}