$(function(){

var surveyMapX= [];
var surveyMapY= [];
console.log(surveyMap)
for (let amount of surveyMap.values()) {
  surveyMapX.push(amount[0]);
  surveyMapY.push(amount[1]);
}
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Technology Popularity In India'
        },
        subtitle: {
            text: 'All Details is not true its a dummy Map'
        },
        xAxis: {
            categories: surveyMapX,
            crosshair: true
        },
        yAxis: {
            min: 0,
            max:1000,
            title: {
                text: 'Technology rating [in %]'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} K</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Technology',
            data: surveyMapY
        }]
    });
$("td").each(function(){
   let a = +$(this).text()/100;
   $(this).css('background-color', `rgba(152, 199, 108, ${a})`);
});
    });