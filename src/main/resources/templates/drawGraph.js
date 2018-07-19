function generateBothGraphs(firstCanvas, secondCanvas) {
    drawGraph("status",firstCanvas);
    drawGraph("delay",secondCanvas);
}

function drawGraph(url, elementId) {
    $.ajax({
        type: "get",
        url: "/getData/" + url,
        contentType: "application/json; charset=utf-8",
        updateTargetId: elementId,
        dataType: "json",
        success:
            function (result) {
                console.log(result);
                var ctx1 = document.getElementById(elementId).getContext("2d");
                var startingData = {
                    type: 'line',
                    data: {
                        labels: result.map(a => a.requestUrl),
                        datasets: [{
                            label: "Common Responses",
                            backgroundColor: "rgba(75,192,192,0.4)",
                            borderWidth: 2,
                            data: result.map(a => a.responseTime)
                        }]
                    },
                    options: {
                        title: {
                            display: true,
                            text: "Graphic"
                        },
                        responsive: true,
                        maintainAspectRatio: true
                    }
                }
                var myChart = new Chart(ctx1, startingData);

                // setInterval(function () {
                //     updLineChart(url, myChart)
                // }, frequency);

            },
        error: function OnErrorCall_(repo) { alert("Woops something went wrong, pls try later !"); }
    });
}