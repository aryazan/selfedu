let updateTime = 5000;

function drawGraph(elementId) {
    $.ajax({
        type: "get",
        url: "/getData/" + document.getElementById(elementId).getAttribute("data-request"),
        dataType: "json",
        success:
            function (result) {
                let canvas = document.getElementById(elementId);
                let requestType = canvas.getAttribute("data-request");
                let ctx1 = canvas.getContext("2d");
                let chartData = {
                    type: 'line',
                    data: {
                        labels: result.map(a => a.requestUrl),
                        datasets: [{
                            label: "Response time",
                            backgroundColor: "rgba(75,192,192,0.4)",
                            data: result.map(a => a.responseTime)
                        }]
                    },
                    options: {
                        title: {
                            display: true,
                            text: "Requests chart for type: " + requestType
                        },
                        scales: {
                            xAxes: [{
                                display: false,
                                scaleLabel: {
                                    display: true
                                }
                            }],
                            yAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                }
                            }]
                        },
                        responsive: true,
                        maintainAspectRatio: true
                    }
                };
                let chartObj = new Chart(ctx1, chartData);

                setInterval(function () {
                    updateChart(chartObj, requestType)
                }, updateTime);

            },
        error: function errorCallGenerating() {
            alert("Error during chart creation");
        }
    });
}

function updateChart(chart, param) {
    $.ajax({
        type: "get",
        url: "/getData/" + param,
        dataType: "json",
        success:
            function (result) {
                let data = result.map(a => a.responseTime);
                chart.data.labels = result.map(a => a.requestUrl);
                chart.data.datasets[0].data = data;
                chart.update();

            },
        error: function errorCallUpdate() {
            alert("Error during chart updating");
        }
    });
}