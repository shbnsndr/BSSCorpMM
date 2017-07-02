import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_moneyManager_forexindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/forex/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',134,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
if(true && (msg != null && msg.length() > 0 && msgFlag == "0")) {
printHtmlPart(6)
expressionOut.print(msg)
printHtmlPart(7)
}
printHtmlPart(8)
expressionOut.print(date)
printHtmlPart(9)
for( unit in (units) ) {
printHtmlPart(4)
if(true && (unit.currencyUnit == fromCurr)) {
printHtmlPart(10)
expressionOut.print(unit.currencyUnit)
printHtmlPart(11)
expressionOut.print(unit.currencyUnit)
printHtmlPart(12)
}
else {
printHtmlPart(10)
expressionOut.print(unit.currencyUnit)
printHtmlPart(13)
expressionOut.print(unit.currencyUnit)
printHtmlPart(12)
}
printHtmlPart(4)
}
printHtmlPart(14)
for( unit in (units) ) {
printHtmlPart(4)
if(true && (unit.currencyUnit == toCurr)) {
printHtmlPart(10)
expressionOut.print(unit.currencyUnit)
printHtmlPart(11)
expressionOut.print(unit.currencyUnit)
printHtmlPart(12)
}
else {
printHtmlPart(10)
expressionOut.print(unit.currencyUnit)
printHtmlPart(13)
expressionOut.print(unit.currencyUnit)
printHtmlPart(12)
}
printHtmlPart(4)
}
printHtmlPart(15)
expressionOut.print(exchangeRate)
printHtmlPart(16)
invokeTag('set','g',237,['var':("i"),'value':(1)],-1)
printHtmlPart(4)
if(true && (denominations != null)) {
printHtmlPart(17)
expressionOut.print(denominations.size())
printHtmlPart(18)
}
printHtmlPart(4)
for( denomination in (denominations) ) {
printHtmlPart(19)
expressionOut.print("denominationUnit_"+i)
printHtmlPart(20)
expressionOut.print("denominationUnit_"+i)
printHtmlPart(21)
expressionOut.print(denomination.denominationUnit)
printHtmlPart(22)
expressionOut.print("denominationNos_"+i)
printHtmlPart(20)
expressionOut.print("denominationNos_"+i)
printHtmlPart(23)
expressionOut.print(i)
printHtmlPart(24)
expressionOut.print("denominationValue_"+i)
printHtmlPart(20)
expressionOut.print("denominationValue_"+i)
printHtmlPart(25)
expressionOut.print("denominationForexRate_"+i)
printHtmlPart(20)
expressionOut.print("denominationForexRate_"+i)
printHtmlPart(23)
expressionOut.print(i)
printHtmlPart(26)
expressionOut.print("denominationForexValue_"+i)
printHtmlPart(20)
expressionOut.print("denominationForexValue_"+i)
printHtmlPart(27)
expressionOut.print(denomination.comments)
printHtmlPart(28)
expressionOut.print(exchangeRate)
printHtmlPart(29)
expressionOut.print("denominationOnlineValue_"+i)
printHtmlPart(20)
expressionOut.print("denominationOnlineValue_"+i)
printHtmlPart(30)
expressionOut.print("denominationProfit_"+i)
printHtmlPart(20)
expressionOut.print("denominationProfit_"+i)
printHtmlPart(30)
expressionOut.print("denominationProfitPerUnit_"+i)
printHtmlPart(20)
expressionOut.print("denominationProfitPerUnit_"+i)
printHtmlPart(31)
invokeTag('set','g',258,['var':("i"),'value':(i+1)],-1)
printHtmlPart(4)
}
printHtmlPart(32)
expressionOut.print(exchangeRate)
printHtmlPart(33)
})
invokeTag('form','g',312,['controller':("Forex"),'action':("calculator"),'onsubmit':("return validateInputs('strDate', 'fromCurrency&&toCurrency', 'jsErrorMsg');")],2)
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',312,[:],1)
printHtmlPart(34)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1498457212444L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
