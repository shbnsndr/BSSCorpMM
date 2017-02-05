import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_moneyManager_expensereport_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/expense/report.gsp" }
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
createTagBody(2, {->
printHtmlPart(5)
createClosureForHtmlPart(6, 3)
invokeTag('link','g',110,['controller':("Dashboard"),'class':("LINK_BTN")],3)
printHtmlPart(7)
})
invokeTag('form','g',115,['controller':("Expense"),'action':("getExpenseReport")],2)
printHtmlPart(8)
for( expense in (expenses) ) {
printHtmlPart(9)
expressionOut.print(expense.createdDate.format('dd/MM/yyyy'))
printHtmlPart(10)
expressionOut.print(expense.userAccount.accountName)
printHtmlPart(10)
expressionOut.print(expense.userAccount.accountType)
printHtmlPart(10)
expressionOut.print(expense.txnType)
printHtmlPart(10)
expressionOut.print(expense.remarks)
printHtmlPart(11)
expressionOut.print(expense.amount)
printHtmlPart(12)
}
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',155,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486309035721L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
