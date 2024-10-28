document.addEventListener("DOMContentLoaded", function () {
	    function populateOptionsForImport() {
	        fetch("/api/trade-options")
	            .then(response => response.json())
	            .then(data => {
	                populateSelect("tradeDate_in", data.tradeDates.sort((a, b) => new Date(b) - new Date(a)));
	                populateSelect("rCode_in", data.rCodes);
	                populateSelect("cCode_in", data.cCodes);
	            })
	            .catch(error => console.error("Options loading error:", error));
	    }

	    function populateSelect(elementId, options) {
	        const select = document.getElementById(elementId);
	        select.innerHTML = '<option value="">모두</option>';
	        options.forEach(option => {
	            const opt = document.createElement("option");
	            opt.value = option;
	            opt.textContent = option;
	            select.appendChild(opt);
	        });
	    }

	    function displayFilteredImportData(data, tradeDate, rCode, cCode) {
	        const container = document.getElementById("filteredTradeinData");
	        container.innerHTML = `<h3>필터링된 수입 데이터 - 날짜: ${tradeDate || '모두'}, 지역: ${rCode || '모두'}, 국가: ${cCode || '모두'}</h3>`;
	        
	        if (data.length === 0) {
	            container.innerHTML += "<p>검색 결과가 없습니다.</p>";
	        } else {
	            let table = `<table><thead><tr><th>날짜</th><th>지역 (R Code)</th><th>국가 (C Code)</th><th>수입 값</th></tr></thead><tbody>`;
	            data.forEach(item => {
	                table += `<tr>
	                            <td>${item.tradeDate || ''}</td>
	                            <td>${item.importValue || 0}</td>
	                          </tr>`;
	            });
	            table += "</tbody></table>";
	            container.innerHTML += table;
	        }
	    }

	    function searchFilteredImportData() {
	        const tradeDate = document.getElementById("tradeDate_in").value;
	        const rCode = document.getElementById("rCode_in").value;
	        const cCode = document.getElementById("cCode_in").value;

	        let queryString = `/api/import-filter?`;
	        if (tradeDate) queryString += `tradeDate=${tradeDate}&`;
	        if (rCode) queryString += `rCode=${rCode}&`;
	        if (cCode) queryString += `cCode=${cCode}`;

	        fetch(queryString)
	            .then(response => response.json())
	            .then(data => {
	                data.sort((a, b) => new Date(b.tradeDate) - new Date(a.tradeDate));
	                displayFilteredImportData(data, tradeDate, rCode, cCode);
	            })
	            .catch(error => console.error("Filtering error:", error));
	    }

	    document.getElementById("searchTradeData").addEventListener("click", searchFilteredImportData);
	    document.getElementById("closeTradePopup").addEventListener("click", function () {
	        document.getElementById("tradePopup").style.display = "none";
	    });

	    document.getElementById("viewMoreTradeChart").addEventListener("click", function () {
	        document.getElementById("tradePopup").style.display = "block";
	        populateOptionsForImport();
	    });
	});