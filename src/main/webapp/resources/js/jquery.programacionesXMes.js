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

function getTbodyMes(difMeses){
	var mes = moment().add(difMeses,'months'),
		dia = moment().add(difMeses,'months').startOf('month').startOf('isoWeek'),
		ultimoDia = moment().add(difMeses,'months').endOf('month').endOf('isoWeek'),
		$tbody = $('<tbody>');

	while (dia < ultimoDia) {
		var indice = dia.isoWeek() - moment().isoWeek() + (dia.year()-moment().year()) * moment().weeksInYear();
			$linea = $('<tr>')
				.append($('<th class="text-right">').text((indice > 0) ? '+' + indice : indice))
				.append($('<td>').text('S' + (dia.year()+'').substring(2) + ((dia.isoWeek()<10) ? '0' : '') + dia.isoWeek()));
		
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
	var $caption = $('<caption>').append($('<nav>')
		.append($('<ul class="pagination">')
			.append($('<li class="btnPrev">')
				.append($('<a aria-label="Previous">')
					.append($('<span aria-hidden="true">').text('<'))))
			.append($('<li class="btnNow">')
				.append($('<a>').text(moment().format('LL'))))
			.append($('<li class="btnNext">')
				.append($('<a aria-label="next">')
					.append($('<span aria-hidden="true">').text('>'))
					)
				)
			)
		);
	return $caption;
}

(function($){
	$.fn.extend({
		programacionesXMes : function(){
			moment.locale('es');
			return this.each(function(){
				var mesActivo = 0;
				var $table = $('<table class="table">')
					.append(getCaption())
					.append(getThead())
					.append(getTbodyMes(mesActivo));
				$table.find('.btnNext').click(function(){
					$table.find('tbody').remove();
					mesActivo++;
					$table.append(getTbodyMes(mesActivo));
				});
				$table.find('.btnPrev').click(function(){
					$table.find('tbody').remove();
					mesActivo--;
					$table.append(getTbodyMes(mesActivo));
				});
				$table.find('.btnNow').click(function(){
					$table.find('tbody').remove();
					$table.append(getTbodyMes(0));
				});

				$(this).append($table);
			});
		}
	});
})(jQuery)