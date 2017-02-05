import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='jquery-date-time-picker', version='0.2.0')
class gsp_jqueryDateTimePicker_popup_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/jquery-date-time-picker-0.2.0/grails-app/views/_popup.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('require','r',2,['module':(function=='datepicker' ? 'jquery-ui' : 'timepicker')],-1)
printHtmlPart(0)
invokeTag('set','g',3,['var':("inputId"),'value':(id.replace('.', '\\\\.').replace('[','\\\\[').replace(']', '\\\\]'))],-1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(inputId)
printHtmlPart(2)
expressionOut.print(function)
printHtmlPart(3)
expressionOut.print(pickerOptions)
printHtmlPart(4)
})
invokeTag('javascript','g',8,[:],1)
printHtmlPart(5)
expressionOut.print(name)
printHtmlPart(6)
expressionOut.print(id)
printHtmlPart(7)
if(true && (value)) {
printHtmlPart(8)
expressionOut.print(formatDate(date:value, format:valueFormat, timeZone:timeZone))
printHtmlPart(9)
}
printHtmlPart(10)
expressionOut.print(attrs)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1486307397156L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
