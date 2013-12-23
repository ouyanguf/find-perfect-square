import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import scala.math._
class RemoteWorker extends Actor {
  def receive = {

    /* startNum means the number worker's task begin with.
       * taskNum means how much numbers need the worker work with.
       * sequenceLength means the length of consecutive numbers
       * The sum of k consecutive numbers'square begin with startNum is ai=k*(k+1)*(2k+1)/6+(startNum-1)(startNum+k)*k*/
    case (lengthSqr: Long, startNum: Long, taskNum: Long, sequenceLength: Long, boss: ActorRef) ⇒

      for (i ← startNum to (startNum + taskNum - 1)) {
        val ai = lengthSqr + (i - 1) * (i + sequenceLength) * sequenceLength
        val root = round(sqrt(ai))

        if (ai == root * root) {
          boss ! (root, i, sequenceLength) // finds a solution

        }
      }
      boss ! 1l // indicating finish

  }
}