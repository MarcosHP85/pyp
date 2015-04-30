function getThead(){
	return $('<thead>').append($('<tr>')
		.append($('<th class="text-right">').text('#'))
		.append($('<th>').text('Semana'))
		.append($('<th class="active">').text('L'))
		.append($('<th class="active">').text('M'))
		.append($('<th class="active">').text('X'))
		.append($('<th class="active">').text('J'))
		.append($('<th class="active">').text('V'))
		.append($('<th>').text('S'))
		.append($('<th>').text('D'))
	);
}

function getTbodyMes(mes){
	var difMeses = mes - moment().month(),
		dia = moment().add(difMeses,'months').startOf('month').startOf('isoWeek'),
		ultimoDia = moment().add(difMeses,'months').endOf('month').endOf('isoWeek'),
		actualWeek = moment().isoWeek(),
		$tbody = $('<tbody>');

	while (dia < ultimoDia) {
		var indice = dia.isoWeek() - actualWeek,
			$linea = $('<tr>')
				.append($('<th class="text-right">').text((indice > 0) ? '+' + indice : indice))
				.append($('<td>').text('S' + (dia.year()+'').substring(2) + dia.isoWeek()));
		
		for (var j = 0; j < 7; j++) {
			var tmp = $('<td>').text(dia.date());
			$linea.append((dia.month() != mes)||(j > 4) ? tmp : tmp.addClass('active'));
			dia.add(1,'days');
		}
		$tbody.append($linea);
	}
	return $tbody;
}

function getCaption(){
	var $caption = $('<caption>').append($('<nav>')
		.append($('<ul class="pagination">')
			.append($('<li class="disabled">')
				.append($('<a href="#" aria-label="Previous">')
					.append($('<span aria-hidden="true">').text('<'))))
			.append($('<li class="disabled">')
				.append($('<a href="#">').text(moment().format('LL'))))
			.append($('<li class="next">')
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
				var mesActivo = moment().month();
				var $table = $('<table class="table">')
					.append(getCaption())
					.append(getThead())
					.append(getTbodyMes(mesActivo));
				$table.find('.next').click(function(){
					$table.find('tbody').remove();
					mesActivo++;
					$table.append(getTbodyMes(mesActivo));
				});

				$(this).append($table);
			});
		}
	});
})(jQuery)