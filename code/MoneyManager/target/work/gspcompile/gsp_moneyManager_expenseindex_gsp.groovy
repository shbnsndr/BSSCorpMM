import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_moneyManager_expenseindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/expense/index.gsp" }
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
invokeTag('captureHead','sitemesh',82,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
for( account in (userAccounts) ) {
printHtmlPart(6)
expressionOut.print(account.id)
printHtmlPart(7)
expressionOut.print(account.accountName)
printHtmlPart(8)
}
printHtmlPart(9)
for( vehicle in (vehicles) ) {
printHtmlPart(6)
expressionOut.print(vehicle.id)
printHtmlPart(7)
expressionOut.print(vehicle.VehicleNumber)
printHtmlPart(8)
}
printHtmlPart(10)
for( account in (userAccounts) ) {
printHtmlPart(6)
expressionOut.print(account.id)
printHtmlPart(7)
expressionOut.print(account.accountName)
printHtmlPart(8)
}
printHtmlPart(11)
for( loan in (loans) ) {
printHtmlPart(6)
expressionOut.print(loan.id)
printHtmlPart(7)
expressionOut.print(loan.description)
printHtmlPart(8)
}
printHtmlPart(12)
for( lended in (lendeds) ) {
printHtmlPart(6)
expressionOut.print(lended.id)
printHtmlPart(7)
expressionOut.print(lended.description)
printHtmlPart(8)
}
printHtmlPart(13)
createClosureForHtmlPart(14, 3)
invokeTag('link','g',410,['controller':("Dashboard"),'class':("LINK_BTN")],3)
printHtmlPart(15)
})
invokeTag('form','g',418,['controller':("Expense"),'action':("addExpense"),'onsubmit':("return validateExpense();")],2)
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',420,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486052256940L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
