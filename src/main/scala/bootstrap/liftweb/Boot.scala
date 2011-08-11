package bootstrap.liftweb

import net.liftweb._
import util._
import Props._
import Helpers._
import common._
import http._
import sitemap._
import Loc._
import mapper._

import code.snippet.Plot

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

   //JQPLOT dispatcher. 
   LiftRules.dispatch.append(Plot)
    
    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts
    
    // default resouce bundle
    LiftRules.resourceNames = "i18n/bundle" :: LiftRules.resourceNames 
    
  }

}
