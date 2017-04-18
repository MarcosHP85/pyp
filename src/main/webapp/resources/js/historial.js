$(document).ready(function(){
//	$('.clickable-row').click( function(e){
//		self = $(this);
//		checkbox = self.find(':checkbox');
//		
//		if ( self.hasClass('active') ) {
//			self.removeClass('active');
//			checkbox.prop('checked',false);
//		} else {
//			self.addClass('active');
//			checkbox.prop('checked',true);
//		}
//	});
//	
//	$('.checkAll').click( function(e){
//		self = $(this);
//		checkbox = self.find(':checkbox');
//		
//		if ( self.hasClass('selectAll') ) {
//			self.removeClass('selectAll');
//			checkbox.prop('checked',false);
//			self.parent().parent().parent().find('.clickable-row').removeClass('active')
//				.find(':checkbox').prop('checked',false);
//		} else {
//			self.addClass('selectAll');
//			checkbox.prop('checked',true);
//			self.parent().parent().parent().find('.clickable-row').addClass('active')
//				.find(':checkbox').prop('checked',true);
//		}
//	});
	
	$('.checkAll').change( function(e){
		self = $(this);
		checkBoxs = $(':checkbox');
		
		if (self.prop('checked')) {
			checkBoxs.prop('checked',true);
		} else {
			checkBoxs.prop('checked',false);
		}
	});
	
	$('.checkByComponente').change( function(e){
		self = $(this);
		checkBoxs = $('input[name="ots"][data-componente='+ self.data('componente') +']');
		
		if (self.prop('checked')) {
			checkBoxs.prop('checked',true);
		} else {
			checkBoxs.prop('checked',false);
		}
	});
	
	$('.clickable-row').click( function(e){
		self = $(this);
		checkbox = self.find(':checkbox');
		
		if (e.target != checkbox.get(0)){
			if (checkbox.prop('checked')) {
				checkbox.prop('checked',false);
			} else {
				checkbox.prop('checked',true);
			}
		}
	});
	
	$(':checkbox').prop('checked',true);
});