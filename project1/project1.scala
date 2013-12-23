//imports
import akka.actor._
import akka.routing.RoundRobinRouter
import scala.math._
import scala.collection.mutable.ArrayBuffer

//Messages
sealed trait SqMessage
case object Find extends SqMessage
case class Work(start: Int, nrOfElements: Int, nrOfMessages: Int, workUnit: Int) extends SqMessage
case class Result(value: Int) extends SqMessage
case object Finish extends SqMessage
case class Answer(ans: ArrayBuffer[Int])

//Worker Actor
class Worker extends Actor {

  def receive = {

    case Work(start, nrOfElements, nrOfMessages, workUnit) =>
      val k = nrOfElements
      var end = start + workUnit - 1
      if (end > nrOfMessages)
        end = nrOfMessages
      for (x <- start to end) {
        var sum = k * x * x + k * (k - 1) * x + k * (k - 1) * (2 * k - 1) / 6
        var rt = sqrt(sum)
        if (rt.isValidInt)
          sender ! Result(x)
      }
      sender ! Finish
  }

}

//Master Actor
class Master(nrOfWorkers: Int, nrOfMessages: Int, nrOfElements: Int, listener: ActorRef) extends Actor {

  var ans = new ArrayBuffer[Int]()
  var nrOfFinish: Int = 0
  var nrOfResults: Int = 0
  var workUnit = (ceil(nrOfMessages.toDouble / nrOfWorkers.toDouble)).toInt

  if (nrOfMessages % nrOfWorkers == 0) {
    nrOfFinish = nrOfWorkers
  } else {
    var i = 1
    while (i <= nrOfMessages) {
      i += workUnit
      nrOfFinish += 1
    }
  }

  val workerRouter = context.actorOf(
    Props[Worker].withRouter(RoundRobinRouter(nrOfFinish)), name = "workerRouter")

  def receive = {
    case Find =>
      for (i <- 0 to nrOfFinish - 1)
        workerRouter ! Work(i * workUnit + 1, nrOfElements, nrOfMessages, workUnit)

    case Result(value) =>
      if (value != 0)
        ans += value

    case Finish =>
      nrOfResults += 1
      if (nrOfResults == nrOfFinish) {
        listener ! Answer(ans)
        context.stop(self)
      }
  }
}

//Listener Actor
class Listener extends Actor {
  def receive = {
    case Answer(ans) =>
      if (!ans.isEmpty) {
        for (i <- 0 to (ans.length - 1)) {
          println(ans(i))
        }
      } else {
        println("Perfect Square Not Found!")
      }
      context.system.shutdown()
  }
}

//Object with main
object project1 {
  def main(args: Array[String]) {
    if (args.length < 2) {
      println("Not Enough Argument!")
      return
    }
    if (args.length == 2) {
      find(nrOfWorkers = 2000, nrOfElements = args(1).toInt, nrOfMessages = args(0).toInt)
    } else {
      find(nrOfWorkers = args(2).toInt, nrOfElements = args(1).toInt, nrOfMessages = args(0).toInt)
    }

    def find(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {
      val system = ActorSystem("SqFindSystem")
      val listener = system.actorOf(Props[Listener], name = "listener")

      val master = system.actorOf(Props(new Master(
        nrOfWorkers, nrOfMessages, nrOfElements, listener)),
        name = "master")

      master ! Find
    }
  }
}

