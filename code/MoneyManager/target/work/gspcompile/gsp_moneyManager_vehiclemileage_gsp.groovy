import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_moneyManager_vehiclemileage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/vehicle/mileage.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("app")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',83,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
for( m in (vm) ) {
printHtmlPart(5)
expressionOut.print(m.infliatedDate.format('dd/MM/yyyy'))
printHtmlPart(6)
expressionOut.print(vehicle.engineType)
printHtmlPart(6)
expressionOut.print(m.pricePerLitre.round(2))
printHtmlPart(7)
expressionOut.print(m.litresPurchased.round(2))
printHtmlPart(6)
expressionOut.print(m.cost)
printHtmlPart(6)
expressionOut.print(m.kmBeforeInflation)
printHtmlPart(7)
expressionOut.print(m.kmAfterInflation)
printHtmlPart(6)
expressionOut.print(m.kmRun)
printHtmlPart(7)
expressionOut.print(m.millageAchieved.round(2))
printHtmlPart(8)
}
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',131,['controller':("Dashboard"),'class':("LINK_BTN")],2)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',139,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486057597545L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
