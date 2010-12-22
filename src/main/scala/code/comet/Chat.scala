package code.comet

import net.liftweb._
import http._
import actor._
import util._
import Helpers._
import scala.xml.NodeSeq

object ChatServer extends LiftActor with ListenerManager {
  private var msgs = List("welcome")

  def createUpdate = msgs

  override def lowPriority = {
    case s: String => msgs ::= s; updateListeners()
  }
}

class Chat extends CometActor with CometListener {
  private var msgs: List[String] = Nil

  def registerWith = ChatServer

  override def lowPriority = {
    case m: List[String] => msgs = m; reRender(false)
  }

  def doNothing() = {1}

  def render = {
    bind(
      "chat",
      "line"   -> lines _
    )
  }

  private def lines(xml: NodeSeq): NodeSeq =
    msgs.reverse.flatMap(m => bind("chat", xml, "msg" -> m))
}
