// ? WIP
var options = {
    series: [{
        name: 'masters',
        data: [6257000, 8328000, 5752000, 7256000, 6581000, 9452000, 4142145, 3681550, 3550000]
    }, {
        name: 'omega',
        data: [6328000, 6320000, 5605000, 6461000, 6581335, 8500000, 5103000, 5504000, 5015000]

    },  {
        name: 'elites',
        data: [3533000, 6173000, 3225000, 4876000, 3683000, 5320000, 2689700, 2836000, 2987000]
    },{
        name: 'delta',
        data: [3158000, 3318000, 2816592, 2820000, 2350002, 2725000, 2126000, 2168200, 2172000]
    },{
        name: 'alpha',
        data: [1718000, 2001000, 1708000, 1955000, 2072113, 3395000, 1676000, 1222000, 1782000]
    }],
    chart: {
        height: 350,
        type: 'area'
    },
    colors:['#ff1300',
        '#1ee9e2',
        '#ff8700',
        '#a1fc09',
        '#e011ff'],
    dataLabels: {
        enabled: false
    },
    stroke: {
        curve: 'smooth'
    },
    xaxis: {
        type: 'date',
        categories: [
            "14th Jan",
            "11th Feb",
            "11th Mar",
            "8th Apr",
            "5th May",
            "2nd Jun",
            "7th Jul",
            "28th Jul",
            "18th Aug"
        ],
        labels: {
            style: {
                colors: '#ffffff'
            }
        }
    },
    yaxis:{
        labels: {
            style: {
                colors: '#ffffff'
            }
        }
    },
    tooltip: {
        theme: 'dark',
        x: {
            format: 'dd/MM/yy'
        }
    }
};

var clubsrepchart = new ApexCharts(document.querySelector("#clubsrepchart"), options);
clubsrepchart.render();

const k = document.getElementById('playertotalcars').textContent.trim();
const n = document.getElementById('totalcars').textContent.trim();
const divisionValue = k / n;

var optionsgcc = {
    chart: {
        type: 'radialBar',
    },
    series: [divisionValue * 100],
    labels: ['Cars'],
    plotOptions: {
        radialBar: {
            hollow: {
                margin: 15,
                size: '70%',
            },
            dataLabels: {
                name: {
                    show: true,
                    fontSize: '22px',
                    fontWeight: '600',
                },
                value: {
                    show: true,
                    fontSize: '16px',
                    fontWeight: '400',
                    offsetY: 10,
                    formatter: function(val) {
                        return k + '/' + n + ' cars';
                    },
                    color: "#FFFFFF"
                }
            }
        }
    },
    colors: ['#FF0000']
};

var optionsgcc2 = {
    chart: {
        type: 'radialBar',
        width: '300px',
        height: '300px',
    },
    series: [divisionValue * 100],
    labels: ['Cars'],
    plotOptions: {
        radialBar: {
            hollow: {
                margin: 15,
                size: '70%',
            },
            dataLabels: {
                name: {
                    show: true,
                    fontSize: '22px',
                    fontWeight: '600',
                },
                value: {
                    show: true,
                    fontSize: '16px',
                    fontWeight: '400',
                    offsetY: 10,
                    formatter: function(val) {
                        return k + '/' + n + ' cars';
                    }
                }
            }
        }
    }
};

var chart = new ApexCharts(document.querySelector("#garagecount"), optionsgcc);
chart.render();

var chart = new ApexCharts(document.querySelector("#garagecount2"), optionsgcc2);
chart.render();

var chart = new ApexCharts(document.querySelector("#garagecount3"), optionsgcc2);
chart.render();