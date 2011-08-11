package code.snippet

import scala.xml.NodeSeq

import net.liftweb._
import common._
import http._
import SHtml._
import util.Helpers._
   import net.liftweb.json.JsonAST._

import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JsonAST._


object Plot extends RestHelper with Loggable {

  serve { case JsonGet("plot"  :: Nil, _) => currentUsergraph }

  private def currentUsergraph: Box[JValue] = {
   
      def pairs:List[JValue] = (1 to 10 map { x =>  JArray(List(JInt(x),JInt(x * x) ))}).toList
      
      Full(JArray(List(JArray(pairs))))
  }

}
