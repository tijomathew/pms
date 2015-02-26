/**
 * @author Bilahari 
 */
 
var myLayout;
$(document).ready(function () {
	myLayout = $('body').layout({
		center__paneSelector:	".outer-center"
	,	west__paneSelector:		".outer-west"
	,	east__paneSelector:		".outer-east"
	,	west__size:				200
	,	east__size:				125
	,	spacing_open:			8  // ALL panes
	,	spacing_closed:			8 // ALL panes
	//,	north__spacing_open:	0
	//,	south__spacing_open:	0
	,	north__maxSize:			200
	,	south__maxSize:			100
	,	togglerLength_open:			50

		// MIDDLE-LAYOUT (child of outer-center-pane)
	,	center__childOptions: {
			center__paneSelector:	".middle-center"
		,	west__paneSelector:		".middle-west"
		,	east__paneSelector:		".middle-east"
		,	west__size:				100
		,	east__size:				100
		,	spacing_open:			8  // ALL panes
		,	spacing_closed:			8 // ALL panes

			// INNER-LAYOUT (child of middle-center-pane)
		,	center__childOptions: {
				center__paneSelector:	".inner-center"
			,	west__paneSelector:		".inner-west"
			,	east__paneSelector:		".inner-east"
			,	west__size:				75
			,	east__size:				75
			,	spacing_open:			8  // ALL panes
			,	spacing_closed:			8  // ALL panes
			,	west__spacing_closed:	12
			,	east__spacing_closed:	12
				,	south: {
						size:					150							
					}
			}
		}
		,	north: {
					size:					"auto"
				,	spacing_open:			0
				,	closable:				false
				,	resizable:				false
				}
		
	});

	$(".openProfile").bind('click', function(){
		$('.profile').toggle();
	});
	$('#leftmenuAccordion').accordion({heightStyle: "content"});
	
	var tabTitle = $( "#tab_title" ),
			tabContent = $( "#tab_content" ),
			tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>X</span></li>",
			tabCounter = 2;

		var tabs = $( "#tabs" ).tabs();

		// modal dialog init: custom buttons and a "close" callback resetting the form inside
		var dialog = $( "#dialog" ).dialog({
			autoOpen: false,
			modal: true,
			buttons: {
				Add: function() {
					addTab();
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				form[ 0 ].reset();
			}
		});

		// addTab form: calls addTab function on submit and closes the dialog
		var form = dialog.find( "form" ).submit(function( event ) {
			addTab();
			dialog.dialog( "close" );
			event.preventDefault();
		});

		// actual addTab function: adds new tab using the input from the form above
		function addTab() {
			var label = tabTitle.val() || "Tab " + tabCounter,
				id = "tabs-" + tabCounter,
				li = $( tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) ),
				tabContentHtml = tabContent.val() || "Tab " + tabCounter + " content.";

			tabs.find( ".ui-tabs-nav" ).append( li );
			tabs.append( "<div id='" + id + "'><p>" + tabContentHtml + "</p></div>" );
			tabs.tabs( "refresh" );
			tabCounter++;
		}

		// addTab button: just opens the dialog
		$( "#leftmenuAccordion ul li" ).click(function() {
				dialog.dialog( "open" );
			});

		// close icon: removing the tab on click
		tabs.delegate( "span.ui-icon-close", "click", function() {
			var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
			$( "#" + panelId ).remove();
			tabs.tabs( "refresh" );
		});

		tabs.bind( "keyup", function( event ) {
			if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
				var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
				$( "#" + panelId ).remove();
				tabs.tabs( "refresh" );
			}
		});		
		
		/*Tree starts*/
		$("#tree1").treeview();
		$("#tree2").treeview();
		$('.filetree .subnodes li').bind('click', function(){
			$(this).parents('.filetree').find('li.active').removeClass('active');
			$(this).addClass('active');
		});
		/*Tree ends*/	
		/*userSettings*/
		$('#userSettings').bind('click', function(e){
			e.preventDefault();
			$('.userSettings').toggle();			
		});
});