<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="app"/>
		
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	
	</head>
	<body>
	
	
	<table>
	
	<tr><td class="L_ALIGN PAGE_HDR">
	View Mileage
	</td></tr>
	
	<tr><td>
		
	<table class="DATA_TBL">
	<thead>
	<tr>
	<th class="DATA_TBL_TH">Date</th>
	<th class="DATA_TBL_TH">Fuel Type</th>
	<th class="DATA_TBL_TH">Fuel Price</th>
	<th class="DATA_TBL_TH">Litres Purchased</th>
	<th class="DATA_TBL_TH">Total Cost</th>
	<th class="DATA_TBL_TH">KM Before</th>
	<th class="DATA_TBL_TH">KM After</th>
	<th class="DATA_TBL_TH">KM Run</th>
	<th class="DATA_TBL_TH">Mileage</th>
	</tr>
	</thead>
	<tbody>
	<g:each var="m" in="${vm }">
	<tr>
	<td class="DATA_TBL_TD_STR">${m.infliatedDate.format('dd/MM/yyyy') }</td>
	<td class="DATA_TBL_TD_STR">${vehicle.engineType }</td>
	<td class="DATA_TBL_TD_STR">${m.pricePerLitre.round(2) }</td>
	<td class="DATA_TBL_TD_NUM">${m.litresPurchased.round(2) }</td>
	<td class="DATA_TBL_TD_STR">${m.cost}</td>
	<td class="DATA_TBL_TD_STR">${m.kmBeforeInflation }</td>
	<td class="DATA_TBL_TD_NUM">${m.kmAfterInflation }</td>
	<td class="DATA_TBL_TD_STR">${m.kmRun }</td>
	<td class="DATA_TBL_TD_NUM">${m.millageAchieved.round(2) }</td>
	</tr>
	</g:each>
	</tbody>
	</table>
	
	</td></tr>
	
	<tr><td class="EMPTY_H10P"></td></tr>
	
	<tr>
	<td><g:link controller="Dashboard" class="LINK_BTN">Back to dashboard</g:link></td>
	</tr>


		
	
	</table>
	
	</body>
	</html>