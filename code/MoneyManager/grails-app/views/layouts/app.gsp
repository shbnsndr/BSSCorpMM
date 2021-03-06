<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>BSS Corp - Money Manager</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'bss.png')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
	</head>
	<body>
		<div id="grailsLogo" role="banner" class="banner">
		<table><tr>
		<td><img alt="logo" src="${assetPath(src: 'bss.png') }" height="100px" width="100px"></td>
		<td class="company_name">BSS Corp - Money Manager</td>
		<td><img alt="" src="${assetPath(src: 'gold-coins-and-bars.png') }" height="100px" width="250px"></td>
		</tr></table>
		</div>
		<table class="W_100 WELCOME_BANNER">
		<tr><td class="L_ALIGN">
		<table><tr><td>Hi</td><td>${session.name }</td><td></table>
		</td>
		<td class="R_ALIGN">
		<table class="W_100">
		<tr><td class="R_ALIGN">
		<g:link controller="Login" action="logout"><label class="label_logout">Logout</label></g:link>
		</td></tr>
		</table>
		</td></tr>
		</table>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
</html>
