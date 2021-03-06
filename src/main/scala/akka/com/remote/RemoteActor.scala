import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.util.Failure
import scala.util.Success
import main.scala.akka.com.remote.HailstormStorageManager
import java.net.InetAddress
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
object RemoteActor {
  def props(): Props = Props(classOf[RemoteActor])
}

class RemoteActor extends Actor {
  val localhost: InetAddress = InetAddress.getLocalHost
  val localIpAddress: String = localhost.getHostAddress
  def receive = {
    case s: String => println(f"Received a reply: $s")
  }

  // connect to the remote actor system running on port 47000 and send its `$a` actor a message
  // this path looks so extremely fragile because it really is! don't worry though, we'll fix this in future examples
  context.system.actorSelection(s"akka.tcp://HailstormFrontend@$localIpAddress:3553/user/roxxfs").resolveOne()(10.seconds).onComplete(x => x match {
    case Success(ref: ActorRef) => {
      println(f"Located HailstormFrontend actor: $ref")
      ref ! "terminate"
      //ref ! "add,127.0.0.1,2556"
      //ref ! HailstormStorageManager.AddNode("127.0.0.1", 5000)
    }
    case Failure(t) => {
      System.err.println(f"Failed to locate the actor. Reason: $t")
      context.system.terminate()
    }
  })
}