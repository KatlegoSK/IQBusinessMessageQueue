
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receiver {
	private final static String QUEUE_NAME = "messaging";

	public static void main(String[] argv) throws Exception {
		String rabbitmqserver, rabbitmquser, rabbitmqpassword, rabbitmqport, queueprefix;

		// To define system environment variables
		Map<String, String> env = System.getenv();

		// These variables are defined or configured in Class debug settings
		rabbitmqserver = env.get("rabbitmqserver");
		rabbitmquser = env.get("rabbitmquser");
		rabbitmqpassword = env.get("rabbitmqpassword");
		rabbitmqport = env.get("rabbitmqport");
		queueprefix = ((queueprefix = env.get("queueprefix")) != null) ? queueprefix : "";

		ConnectionFactory factory = new ConnectionFactory();

		factory.setHost(rabbitmqserver);
		factory.setHost(rabbitmqserver);
		factory.setHost(rabbitmqserver);
		factory.setPort(Integer.parseInt(rabbitmqport));
		factory.setUsername(rabbitmquser);
		factory.setPassword(rabbitmqpassword);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] PING ...Waiting...127.0.0.1");
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			
			String[] userDetails = message.split(",");
			
			System.out.println(" Hello " + userDetails[1] + ", I am your father!!!!!");
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
		});
	}
}