function showGrid(id, duration){
  var speed = 900;
  var container =  $(id);  
  container.each(function() {   
    var elements = $(this).children();
    elements.each(function() {      
      var elementOffset = $(this).offset(); 
      var offset = elementOffset.left*0.75 + elementOffset.top;
      var delay = parseFloat(offset/speed).toFixed(2);
      $(this)
        .css("-webkit-transition-delay", delay+'s')
        .css("-o-transition-delay", delay+'s')
        .css("transition-delay", delay+'s')
        .removeClass('closed')
        .addClass('animated');
    });
  });
}

function closeGrid(id, duration){
	  var speed = 900;
	  var container =  $(id);  
	  container.each(function() {   
	    var elements = $(this).children();
	    elements.each(function() {      
	      var elementOffset = $(this).offset(); 
	      var offset = elementOffset.left*0.5 + elementOffset.top;
	      var delay = parseFloat(offset/speed).toFixed(2);
	      $(this)
	        .css("-webkit-transition-delay", delay+'s')
	        .css("-o-transition-delay", delay+'s')
	        .css("transition-delay", delay+'s')
	        .removeClass('animated')
	        .addClass('closed');
	    });
	  });
	}