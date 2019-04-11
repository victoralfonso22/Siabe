function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}

function creaAutocompletar(element,_url,minLength){
	//alert(element);
	$("#"+element).autocomplete({
	 
	/*source: function( request, response ) {
	
		$.getJSON(_url, {
            term: extractLast(request.term)
        }, response);	
		*/
	source: function( request, response ) {	
	$.ajax({
	url:_url ,
	dataType: "json",
	data: {
	term: request.term
	},	
	success: function( data ) {
		response($.map(data, function (item) {
            //console.log(value);
            return {
            	label: item.carrera + " - " +item.region,
            	value: item.idCarrera
            };
        }));
    }
	});
	
	
	},
	/*search: function () {
        // custom minLength
        var term = extractLast(this.value);
        if (term.length < 1) {
            return false;
        }
    },*/
    focus: function () {
        // prevent value inserted on focus
        return false;
    },
    select: function (event, ui) {
        //var terms = split(this.value);
        // remove the current input
        //terms.pop();
        // add the selected item
        //terms.push(ui.item.label);
        //alert("label "+ui.item.label);
        //alert("value "+ui.item.value);
        // add placeholder to get the comma-and-space at the end
        //terms.push("");
        this.value = ui.item.label;
 
        return false;
    },
	
	minLength: minLength

	});
}



