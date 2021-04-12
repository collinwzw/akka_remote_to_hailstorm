import akka.actor.ActorSystem

object Application {
  def main(args: Array[String]): Unit = {
    val foo = ActorSystem("HailstormFrontend")
    foo.actorOf(RemoteActor.props())
  }
}