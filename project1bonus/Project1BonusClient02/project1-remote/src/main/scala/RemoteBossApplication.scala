import akka.kernel.Bootable
import akka.actor.{ Props, Actor, ActorSystem }
import com.typesafe.config.ConfigFactory

class RemoteBossApplication extends Bootable {
  //#setup
  val system = ActorSystem("RemoteBossApplication",
    ConfigFactory.load.getConfig("listen"))

  //#setup

  def startup() {
  }

  def shutdown() {
    system.shutdown()
  }
}

object RemoteBossApp {
  def main(args: Array[String]) {
    new RemoteBossApplication()
    println("I am the remote boss! I am waiting for messages...")
  }
}