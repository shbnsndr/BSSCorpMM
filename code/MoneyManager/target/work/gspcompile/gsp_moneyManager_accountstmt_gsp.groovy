import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_moneyManager_accountstmt_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/account/stmt.gsp" }
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
invokeTag('set','g',105,['var':("totExp"),'value':(0)],-1)
printHtmlPart(3)
for( expense in (expenses) ) {
printHtmlPart(5)
expressionOut.print(expense.createdDate.format('dd/MM/yyyy'))
printHtmlPart(6)
expressionOut.print(expense.txnType)
printHtmlPart(6)
expressionOut.print(expense.remarks)
printHtmlPart(7)
expressionOut.print(expense.amount)
printHtmlPart(8)
invokeTag('set','g',112,['var':("totExp"),'value':(totExp+expense.amount)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
expressionOut.print(totExp)
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',127,['controller':("Dashboard"),'class':("LINK_BTN")],2)
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',135,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1489080541773L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
