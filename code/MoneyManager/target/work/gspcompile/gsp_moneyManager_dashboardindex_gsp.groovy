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
printHtmlPart(11)
createClosureForHtmlPart(14, 2)
invokeTag('link','g',110,['controller':("Expense"),'action':("getExpenseReport"),'class':("LINK_BTN")],2)
printHtmlPart(15)
invokeTag('set','g',131,['var':("totBalance"),'value':(0)],-1)
printHtmlPart(1)
for( account in (userAccounts) ) {
printHtmlPart(1)
invokeTag('set','g',133,['var':("totBalance"),'value':(totBalance+account.currentBalance)],-1)
printHtmlPart(16)
expressionOut.print(account.accountName)
printHtmlPart(17)
expressionOut.print(account.accountType)
printHtmlPart(18)
expressionOut.print(account.currentBalance)
printHtmlPart(19)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',139,['controller':("Account"),'action':("get30DayStmt"),'params':([accountId:account.id ])],3)
printHtmlPart(19)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',140,['controller':("Account"),'action':("getThisMonthStmt"),'params':([accountId:account.id ])],3)
printHtmlPart(21)
}
printHtmlPart(22)
expressionOut.print(totBalance)
printHtmlPart(23)
for( vehicle in (vehicles) ) {
printHtmlPart(24)
expressionOut.print(vehicle.VehicleNumber)
printHtmlPart(17)
expressionOut.print(vehicle.engineType)
printHtmlPart(19)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',173,['controller':("Vehicle"),'action':("getMileage"),'params':([vehicle:vehicle.id ])],3)
printHtmlPart(21)
}
printHtmlPart(25)
for( loanLiability in (loanAndLiabilities) ) {
printHtmlPart(24)
expressionOut.print(loanLiability.date)
printHtmlPart(17)
expressionOut.print(loanLiability.type)
printHtmlPart(18)
expressionOut.print(loanLiability.description)
printHtmlPart(17)
expressionOut.print(loanLiability.fromToPerson)
printHtmlPart(17)
expressionOut.print(loanLiability.amount)
printHtmlPart(18)
expressionOut.print(loanLiability.plannedReturnDate.format('dd/MM/yyyy'))
printHtmlPart(21)
}
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',213,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1493584834348L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
