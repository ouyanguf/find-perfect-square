import akka.kernel.Bootable
import com.typesafe.config.ConfigFactory
import scala.util.Random
import akka.actor._
class ServerApplicationone extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationone"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

  def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}
class ServerApplicationtwo extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationtwo"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

  def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}
class ServerApplicationthree extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationthree"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

  def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}
class ServerApplicationfour extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationfour"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

 def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}
class ServerApplicationfive extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationfive"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

 def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}
class ServerApplicationsix extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationsix"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

  def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}
class ServerApplicationseven extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationseven"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

  def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
  }
  //#setup

  def startup() {

  }

  def shutdown() {
    system.shutdown()
  }
}
class ServerApplicationeight extends Bootable {
  //#setup
  var computerNum = 1;
  val system =
    ActorSystem("SuperBossApplication", ConfigFactory.load.getConfig("remotecreationeight"))
  val remoteActor = system.actorOf(Props[RemoteBoss], name = "RemoteBoss")
  val localActor = system.actorOf(Props(classOf[ServerActor], remoteActor),
    name = "ServerActor")

  def doSomething(numStart:Long,numRange: Long, sequenceLength: Long) = {
    localActor ! (numStart,numRange, sequenceLength)
   
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
    case (numStart:Long,numRange: Long, sequenceLength: Long) =>
      remoteActor ! (numStart, numRange, sequenceLength, 100, self)
    case (root: Long, startOfSolu: Long, seLeng: Long,signal:Long) =>
      //println("" + root + "^2 = " + (for (i <- startOfSolu to (startOfSolu + seLeng - 1)) yield "" + i + "^2").mkString("+"))
      println(startOfSolu)
    case (fin: Long) =>
      {
        doneRemote += 1
        //println(doneRemote)
        if (doneRemote == 1) {
          println("One computer done")
          sys.exit()
        }
      }
  }
}

//#actor

object ServerApp {
  def main(args: Array[String]) {
    val app_1 = new ServerApplicationone
   val app_2=new ServerApplicationtwo
    val app_3 = new ServerApplicationthree
   val app_4=new ServerApplicationfour
    val app_5 = new ServerApplicationfive
   val app_6=new ServerApplicationsix
    val app_7 = new ServerApplicationseven
   val app_8=new ServerApplicationeight
    println("Server Application Started...")
    val numRange=100000000l
    val sequenceLength=24l
    app_1.doSomething(1l,numRange/8, sequenceLength)
    app_2.doSomething(1+numRange/8,numRange/8*2 ,sequenceLength)
    app_3.doSomething(1+numRange/8*2,numRange/8*3 ,sequenceLength)
    app_4.doSomething(1+numRange/8*3,numRange/8*4 ,sequenceLength)
    app_5.doSomething(1+numRange/8*4,numRange/8*5 ,sequenceLength)
    app_6.doSomething(1+numRange/8*5,numRange/8*6 ,sequenceLength)
    app_7.doSomething(1+numRange/8*6,numRange/8*7 ,sequenceLength)
    app_8.doSomething(1+numRange/8*7,numRange ,sequenceLength)
    println("I Got The Answers:    ")
  }
}