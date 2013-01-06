package code.comet

import net.liftmodules.amqp._
import com.rabbitmq.client._

object Rabbit {

  val factory = new ConnectionFactory(new ConnectionParameters)

  val host = "127.0.0.1"
  val port = 5672
  val exchange = "lift.chat"
  val routing = ""
  val durable = true

  object RemoteSend extends AMQPSender[String](factory, host, port, exchange, routing) {
    def configure(channel: Channel) = channel.exchangeDeclare(exchange, "fanout", durable)
  }

  object RemoteReceiver extends AMQPDispatcher[String](factory, host, port) {
    def configure(channel: Channel) = {
      channel.exchangeDeclare(exchange, "fanout", durable)
      val queueName = channel.queueDeclare().getQueue()
      channel.queueBind(queueName, exchange, routing)
      channel.basicConsume(queueName, false, new SerializedConsumer(channel, this))
    }
  }

}
