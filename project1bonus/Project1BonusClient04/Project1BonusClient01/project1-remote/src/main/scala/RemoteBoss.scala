import akka.actor.Actor
import akka.actor.ActorSystem
import scala.math._
import akka.actor.Props
import com.typesafe.config.ConfigFactory
import akka.actor.ActorRef

class RemoteBoss extends Actor {
  var done: Int = 0
  var actorsNum: Int = 0
  var timeBoss: Long = 0l
  var serverBoss: ActorRef = null
  def receive = {
    case (numStart: Long, numRange: Long, sequenceLength: Long, actorNum: Int, server: ActorRef) ⇒
      println("This is from RemoteBoss.scala")
	  
	  val lengthSqr = sequenceLength * (sequenceLength + 1) * (2 * sequenceLength + 1) / 6
      actorsNum += actorNum
      for (i ← 1 to actorsNum) { // initializing workers and assign subtasks
        val systemworker = ActorSystem("Remoteclient")
        val worker = systemworker.actorOf(Props[RemoteWorker])
        worker ! (lengthSqr, numStart + (i - 1) * (numRange - numStart + 1) / actorsNum, (numRange - numStart + 1) / actorsNum, sequenceLength, self)
      }

      serverBoss = server
      println("I need to calculate from "+numStart+" to "+numRange)
        println("Here is the answers:")

    case (root: Long, startOfSolu: Long, seLeng: Long) ⇒ // receive a solution
      // val path="akka.tcp://Remoteserver@127.0.0.1:2552/user/ServerBoss"
      // val serverbossInstance=context.actorSelection(path)
      //val system=ActorSystem("Remoteclient",ConfigFactory.load("clientToserverBoss"))
      //val serverbossInstance=system.actorOf(Props(classOf[ServerBoss]),"serverbossInstance")
      //serverbossInstance! (root,startOfSolu,seLeng,1l)
    println(startOfSolu)
      serverBoss ! (root, startOfSolu, seLeng,1l)

    case (fin: Long) ⇒ // record the number of finished workers
      done += 1
      if (done >= actorsNum) {
        // val system=ActorSystem("Remoteclient",ConfigFactory.load("clientToserverBoss"))
        // val serverbossInstance=system.actorOf(Props(classOf[ServerBoss]),"serverbossInstance")
        serverBoss ! ((System.nanoTime() - timeBoss))
        println("I am done")
   //  context.system.shutdown()
		
      }

  }
}