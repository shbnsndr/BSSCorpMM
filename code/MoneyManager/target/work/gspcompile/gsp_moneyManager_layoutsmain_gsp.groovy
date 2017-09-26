import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_moneyManager_layoutsmain_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/main.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge,chrome=1")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',10,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',10,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(3)
expressionOut.print(assetPath(src: 'bss.png'))
printHtmlPart(4)
expressionOut.print(assetPath(src: 'apple-touch-icon.png'))
printHtmlPart(5)
expressionOut.print(assetPath(src: 'apple-touch-icon-retina.png'))
printHtmlPart(6)
invokeTag('stylesheet','asset',15,['src':("application.css")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',16,['src':("application.js")],-1)
printHtmlPart(1)
invokeTag('layoutHead','g',17,[:],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',18,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(assetPath(src: 'bss.png'))
printHtmlPart(9)
invokeTag('layoutBody','g',26,[:],-1)
printHtmlPart(10)
invokeTag('message','g',28,['code':("spinner.alt"),'default':("Loading&hellip;")],-1)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',29,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1499538927667L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
