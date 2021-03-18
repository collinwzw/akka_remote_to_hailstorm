package main.scala.akka.com.remote

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import scala.language.postfixOps
import java.sql.{Array => _, _}
object HailstormStorageManager {

  val name: String = "roxxfs"
  val INVALID_CHARS_MAP =
    Map(
      "/" -> "!"
    )
  val INVALID_CHARS_REVERSE_MAP =
    INVALID_CHARS_MAP.map(_.swap)

  def props(fileMappingDb: String, clearOnInit: Boolean): Props = Props(classOf[HailstormStorageManager], fileMappingDb, clearOnInit)

  def encodePath(path: String): String = java.util.UUID.nameUUIDFromBytes(path.getBytes).toString

  sealed trait StorageCommands

  sealed trait StorageResponse

  case class OpenFile(path: String) extends StorageCommands

  case class AddNode(hostname: String, port: Int) extends StorageResponse

  case class RemoveNode(hostname: String, port: Int) extends StorageResponse

  case class CloseFile(path: String) extends StorageCommands

  case class RenameFile(path: String, newPath: String) extends StorageCommands

  case class DeleteFile(path: String) extends StorageCommands

  case class ListFiles(path: String) extends StorageCommands

  case class FileOpened(ref: ActorRef) extends StorageResponse

  case class FilesList(ls: List[String]) extends StorageResponse

  case object FileClosed extends StorageResponse

  case object FileRenamed extends StorageResponse

  case object FileDeleted extends StorageResponse

  case class FileCreate(path: String, uuid: String) extends StorageCommands

  case object FileCreated extends StorageResponse

  case class GetMetadata(path: String) extends StorageCommands

  case class FileMetadata(path: String, uuid: String) extends StorageResponse

  /*def encodePath(path: String): String = {
    var ret = path
    for((old, _new) <- INVALID_CHARS_MAP) {
      ret = ret.replace(old, _new)
    }
    ret
  }

  def decodePath(path: String): String = {
    var ret = path
    for((old, _new) <- INVALID_CHARS_REVERSE_MAP) {
      ret = ret.replace(old, _new)
    }
    ret
  }*/

}



class HailstormStorageManager(fileMappingDb: String, clearOnInit: Boolean) extends Actor {

  import HailstormStorageManager._


  override def receive: Receive = {
    case _ =>


  }
}