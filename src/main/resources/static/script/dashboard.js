document.addEventListener("DOMContentLoaded", function () {
    // 서버로부터 데이터를 fetch로 가져옴
    fetch('/api/production-data')
        .then(response => response.json())
        .then(data => {
            // 데이터를 확인하기 위해 콘솔에 출력
            console.log("서버로부터 받은 데이터:", data);

            // mainDate와 production 값을 배열로 분리
            const mainDates = data.map(item => item.mainDate);  // X축: 날짜
            const productionValues = data.map(item => item.production);  // Y축: 생산량 합계

            // 데이터가 비어있는지 확인
            if (mainDates.length === 0 || productionValues.length === 0) {
                console.error('데이터가 없습니다.');
                return;
            }

            // Chart.js로 차트 그리기
            const ctx = document.getElementById('productionChart').getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: mainDates, // X축: 날짜
                    datasets: [{
                        label: '날짜별 생산량 합계', // 차트 제목
                        data: productionValues, // Y축: 생산량 합계 데이터
                        borderColor: '#007bff', // 선 색상
                        backgroundColor: 'rgba(0, 123, 255, 0.2)', // 배경 색상
                        fill: true, // 선 아래 영역 채우기
                        tension: 0.1 // 곡선 정도
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: '날짜'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: '생산량 합계'
                            },
                            beginAtZero: true // Y축을 0부터 시작
                        }
                    }
                }
            });
        })
        .catch(error => console.error('데이터를 가져오는 중 오류 발생:', error));
});		

document.addEventListener("DOMContentLoaded", function () {
		    // 서버로부터 수요량 데이터를 가져와 차트를 그리는 함수
		    fetch('/api/demand-data')  // 수요량 API 엔드포인트
		        .then(response => response.json())
		        .then(data => {
		            console.log("서버로부터 받은 Demand 데이터:", data);

		            // demand_date와 demand 값을 배열로 분리
		            const demandDates = data.map(item => item.demandDate);  // X축: 날짜
		            const demandValues = data.map(item => item.demand);  // Y축: 수요량

		            // 데이터가 비어있는지 확인
		            if (demandDates.length === 0 || demandValues.length === 0) {
		                console.error('수요량 데이터가 없습니다.');
		                return;
		            }

		            // Chart.js로 수요량 차트 그리기
		            const ctxDemand = document.getElementById('demandChart').getContext('2d');
		            new Chart(ctxDemand, {
		                type: 'line',
		                data: {
		                    labels: demandDates,  // X축: 날짜
		                    datasets: [{
		                        label: '날짜별 수요량',  // 차트 제목
		                        data: demandValues,  // Y축: 수요량 데이터
		                        borderColor: '#ff6384',  // 선 색상
		                        backgroundColor: 'rgba(255, 99, 132, 0.2)',  // 배경 색상
		                        fill: true,  // 선 아래 영역 채우기
		                        tension: 0.1  // 곡선 정도
		                    }]
		                },
		                options: {
		                    responsive: true,
		                    scales: {
		                        x: {
		                            title: {
		                                display: true,
		                                text: '날짜'
		                            }
		                        },
		                        y: {
		                            title: {
		                                display: true,
		                                text: '수요량'
		                            },
		                            beginAtZero: true  // Y축을 0부터 시작
		                        }
		                    }
		                }
		            });
		        })
		        .catch(error => console.error('Demand 데이터를 가져오는 중 오류 발생:', error));
		});