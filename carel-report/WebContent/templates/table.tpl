
<table algin="center">
	<tr>
		<th rowspan="2">机种类型</th>
		<th colspan="2">焊接不良</th>
		<th colspan="2">置件不良</th>
		<th rowspan="2">零件不良</th>
		<th rowspan="2">误判</th>
	</tr>
	<tr>
		<th>PTH</th>
		<th>SMD</th>
		<th>PTH</th>
		<th>SMD</th>
	</tr>
	{{each data}}
	<tr>
		<td>{{$value.family}}</td>
		<td>{{$value.weldPth}}</td>
		<td>{{$value.weldSmd}}</td>
		<td>{{$value.positionPth}}</td>
		<td>{{$value.positionSmd}}</td>
		<td>{{$value.bad}}</td>
		<td>{{$value.ntf}}</td>
	</tr>
	{{/each}}
</table>
