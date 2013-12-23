import akka.kernel.Bootable
import com.typesafe.config.ConfigFactory
import scala.util.Random
import akka.actor._

class ServerApplication extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreation"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

  def doSomething(numRange: Long, sequenceLength: Long) = {
    localActor ! (numRange, sequenceLength)
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}

//#actor
class ServerActor(remoteActor: ActorRef) extends Actor {
  var doneRemote: Int = 0

  def receive = {
    case (numRange: Long, sequenceLength: Long) ⇒
      remoteActor ! (1l, numRange, sequenceLength, 4, self)
    case (root: Long, startOfSolu: Long, seLeng: Long) ⇒
      //println("" + root + "^2 = " + (for (i ← startOfSolu to (startOfSolu + seLeng - 1)) yield "" + i + "^2").mkString("+"))
      println(startOfSolu)
    case (fin: Long) ⇒
      {
        doneRemote += 1
        //println(doneRemote)
        if (doneRemote == 1) {
          println("All done !!!!!!!!!!!")
          sys.exit()
        }
      }
  }
}

//#actor

object ServerApp {
  def main(args: Array[String]) {
    val app = new ServerApplication
    println("Server Application Started...")
    app.doSomething(40l, 24l)
  }
}
