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
invokeTag('link','g',114,['controller':("Dashboard"),'class':("LINK_BTN")],3)
printHtmlPart(7)
if(true && (session.isAdmin == "Y")) {
printHtmlPart(3)
createClosureForHtmlPart(8, 4)
invokeTag('link','g',122,['controller':("Expense"),'action':("uploadReportToGoogle"),'class':("LINK_BTN")],4)
printHtmlPart(3)
}
printHtmlPart(9)
})
invokeTag('form','g',132,['controller':("Expense"),'action':("getExpenseReport")],2)
printHtmlPart(10)
invokeTag('set','g',154,['var':("totExp"),'value':(0)],-1)
printHtmlPart(11)
for( expense in (expenses) ) {
printHtmlPart(12)
expressionOut.print(expense.createdDate.format('dd/MM/yyyy'))
printHtmlPart(13)
expressionOut.print(expense.userAccount.accountName)
printHtmlPart(13)
expressionOut.print(expense.userAccount.accountType)
printHtmlPart(13)
expressionOut.print(expense.txnType)
printHtmlPart(13)
expressionOut.print(expense.remarks)
printHtmlPart(14)
expressionOut.print(expense.amount)
printHtmlPart(15)
invokeTag('set','g',165,['var':("totExp"),'value':(totExp+expense.amount)],-1)
printHtmlPart(16)
}
printHtmlPart(17)
expressionOut.print(totExp)
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',185,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1492275361658L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
