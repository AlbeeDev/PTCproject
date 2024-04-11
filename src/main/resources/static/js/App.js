document.addEventListener('DOMContentLoaded', function () {

    const editableDivs = document.querySelectorAll('.editable');
    editableDivs.forEach(function(div) {
        div.addEventListener('keydown', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault();
                event.target.blur();
            }
        });
    });

    var lastSectionValue = document.getElementById("lastsection").textContent;
    if(lastSectionValue){
        document.getElementById(lastSectionValue).click();
    }


    var carNamesDiv = document.getElementById("carNames");
    var carNamesData = carNamesDiv.textContent.trim();
    var carNames = carNamesData.slice(1, -1).split(",");

    carNames = carNames.map(function(name) {
        return name.trim();
    });

    $('#carName').on('input', function() {
        var inputVal = $(this).val().toLowerCase();
        var filteredCars = carNames.filter(function(car) {
            return car.toLowerCase().includes(inputVal);
        });

        var typeaheadResults = document.getElementById('typeaheadResults');
        typeaheadResults.innerHTML = '';

        for (var i = 0; i < Math.min(filteredCars.length, 5); i++) {
            var result = filteredCars[i];
            var resultListItem = document.createElement('li');
            resultListItem.textContent = result;
            resultListItem.classList.add('list-group-item', 'list-group-item-action', 'user-select-none');

            resultListItem.addEventListener('click', function() {
                document.getElementById('carName').value = this.textContent;
                typeaheadResults.innerHTML = '';
            });

            typeaheadResults.appendChild(resultListItem);
        }

    });



});
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.classList.add('d-none')
    });

    const selectedSection = document.getElementById(sectionId);
    if (selectedSection) {
        selectedSection.classList.remove('d-none');
    }

    const divwraps = document.querySelectorAll('.section div')
    divwraps.forEach(div => {
        div.style.flex='10'
    })
    //const buttons = document.querySelectorAll('.nav-bar button');
}

function setcardata() {
    return {
        carName: document.getElementById('carName').value,
        stars: document.getElementById('stars').value,
        carRank: document.getElementById('carRank').value
    };
}

function submitForm(url, formData) {
    fetch('/api/' + url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.status === 201) {
                location.reload();
            } else {

            }
        })
        .catch(error =>{

        })
}

