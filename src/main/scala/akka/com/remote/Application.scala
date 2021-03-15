import akka.actor.ActorSystem

object Application {
  def main(args: Array[String]): Unit = {
    val foo = ActorSystem("Remote")
    foo.actorOf(RemoteActor.props())
  }
}