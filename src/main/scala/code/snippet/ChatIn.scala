/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.snippet

import net.liftweb._
import http._
import actor._
import util._
import Helpers._
import scala.xml.NodeSeq

import js._
import JsCmds._
import JE._
import code.comet.ChatServer

class ChatIn {
  def render(xml:NodeSeq) = {
    bind(
      "chatin",
      xml,
      "input" -> SHtml.text("", s => {
          ChatServer ! s
          SetValById("chat_in", "")
        }
      ) % ("id" -> "chat_in"),
      "submitme" -> SHtml.submit("submit me", ()=>{})
    )
  }
}
