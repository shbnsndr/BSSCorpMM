import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_moneyManager_dashboardindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dashboard/index.gsp" }
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
if(true && (msg != null && msg.length() > 0)) {
printHtmlPart(5)
if(true && (msgFlag == "1")) {
printHtmlPart(6)
expressionOut.print(msg)
printHtmlPart(7)
}
else {
printHtmlPart(8)
expressionOut.print(msg)
printHtmlPart(7)
}
printHtmlPart(1)
}
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',104,['controller':("Expense"),'class':("LINK_BTN")],2)
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',106,['controller':("Account"),'class':("LINK_BTN")],2)
printHtmlPart(11)
createClosureForHtmlPart(13, 2)
invokeTag('link','g',108,['controller':("Vehicle"),'class':("LINK_BTN")],2)
printHtmlPart(14)
for( account in (userAccounts) ) {
printHtmlPart(15)
expressionOut.print(account.accountName)
printHtmlPart(16)
expressionOut.print(account.accountType)
printHtmlPart(17)
expressionOut.print(account.currentBalance)
printHtmlPart(18)
createClosureForHtmlPart(19, 3)
invokeTag('link','g',133,['controller':("Account"),'action':("get30DayStmt"),'params':([accountId:account.id ])],3)
printHtmlPart(18)
createClosureForHtmlPart(19, 3)
invokeTag('link','g',134,['controller':("Account"),'action':("getThisMonthStmt"),'params':([accountId:account.id ])],3)
printHtmlPart(20)
}
printHtmlPart(21)
for( vehicle in (vehicles) ) {
printHtmlPart(15)
expressionOut.print(vehicle.VehicleNumber)
printHtmlPart(16)
expressionOut.print(vehicle.engineType)
printHtmlPart(18)
createClosureForHtmlPart(19, 3)
invokeTag('link','g',160,['controller':("Vehicle"),'action':("getMileage"),'params':([vehicle:vehicle.id ])],3)
printHtmlPart(20)
}
printHtmlPart(22)
for( loanLiability in (loanAndLiabilities) ) {
printHtmlPart(15)
expressionOut.print(loanLiability.date)
printHtmlPart(16)
expressionOut.print(loanLiability.type)
printHtmlPart(17)
expressionOut.print(loanLiability.description)
printHtmlPart(16)
expressionOut.print(loanLiability.fromToPerson)
printHtmlPart(16)
expressionOut.print(loanLiability.amount)
printHtmlPart(17)
expressionOut.print(loanLiability.plannedReturnDate.format('dd/MM/yyyy'))
printHtmlPart(20)
}
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',200,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486056504373L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
