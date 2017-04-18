function getThead(){
	return $('<thead>').append($('<tr>')
		.append($('<th class="col-md-1 text-right">').text('#'))
		.append($('<th class="col-md-4">').text('Semana'))
		.append($('<th class="active">').text('Lu'))
		.append($('<th class="active">').text('Ma'))
		.append($('<th class="active">').text('Mi'))
		.append($('<th class="active">').text('Ju'))
		.append($('<th class="active">').text('Vi'))
		.append($('<th>').text('Sa'))
		.append($('<th>').text('Do'))
	);
}

function getTbodyMes(difMeses,estado){
	var mes = moment().add(difMeses,'months'),
		dia = moment().add(difMeses,'months').startOf('month').startOf('isoWeek'),
		ultimoDia = moment().add(difMeses,'months').endOf('month').endOf('isoWeek'),
		$tbody = $('<tbody>');

	while (dia < ultimoDia) {
		var indice = dia.isoWeek() - moment().isoWeek() + (dia.year()-moment().year()) * moment().weeksInYear(); // Falla si se adelanta mas de un año
			semana = (dia.year()+'').substring(2) + ((dia.isoWeek()<10) ? '0' : '') + dia.isoWeek();
			$linea = $('<tr>')
				.append($('<th class="text-right">').text((indice > 0) ? '+' + indice : indice))
				.append($('<td>').append($('<a>').text('S' + semana).attr('href','./programacion/editar?semana='+semana+'&estado='+estado)));
		
		for (var j = 0; j < 7; j++) {
			var tmp = $('<td>').text(dia.date());
			$linea.append((dia.month() != mes.month())||(j > 4) ? tmp : tmp.addClass('active'));
			dia.add(1,'days');
		}
		$tbody.append($linea);
	}
	return $tbody;
}

function getCaption(){
	var $caption = $('<caption>').append($('<div class="row">').append($('<div class="col-md-5">')
			.append($('<p class="mes-en-vista">').text(moment().format('MMMM YYYY'))))
		.append($('<nav>')
			.append($('<ul class="pagination">')
				.append($('<li class="btnPrev">')
					.append($('<a aria-label="Previous">')
						.append($('<span aria-hidden="true">').text('<'))))
				.append($('<li class="btnNow">')
					.append($('<a>').text(moment().format('ll'))))
				.append($('<li class="btnNext">')
					.append($('<a aria-label="next">')
						.append($('<span aria-hidden="true">').text('>'))
						)
					)
				)
			)
		);
	return $caption;
}

(function($){
	$.fn.extend({
		programacionesXMes : function(options){
			var self = this;
			var defaults = {
					planta : 2000,
					estado : 'normal'
			};
			options = $.extend(defaults, options);
			
			moment.locale('es');
			self.mesActivo = 0;
			self.tabla = $('<table class="table">')
				.append(getCaption())
				.append(getThead())
				.append(getTbodyMes(self.mesActivo,options.estado));
			
			return self.each(function(){
				console.log('hola '+options.estado);
				
				self.tabla.find('.btnNext').click(function(){
					self.tabla.find('tbody').remove();
					self.mesActivo++;
					self.tabla.find('.mes-en-vista').text(moment().add(self.mesActivo,'months').format('MMMM YYYY'));
					self.tabla.append(getTbodyMes(self.mesActivo,options.estado));
				});
				self.tabla.find('.btnPrev').click(function(){
					self.tabla.find('tbody').remove();
					self.mesActivo--;
					self.tabla.find('.mes-en-vista').text(moment().add(self.mesActivo,'months').format('MMMM YYYY'));
					self.tabla.append(getTbodyMes(self.mesActivo,options.estado));
				});
				self.tabla.find('.btnNow').click(function(){
					self.tabla.find('tbody').remove();
					self.mesActivo=0;
					self.tabla.find('.mes-en-vista').text(moment().format('MMMM YYYY'));
					self.tabla.append(getTbodyMes(0));
				});

				$(self).append(self.tabla);
			});
		}
	});
})(jQuery)