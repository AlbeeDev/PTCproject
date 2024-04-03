document.addEventListener('DOMContentLoaded', function () {
    //showSection('section1');
    //populateRep()

    const editableDivs = document.querySelectorAll('.editable');
    editableDivs.forEach(function(div) {
        div.addEventListener('keydown', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault();
                event.target.blur();
            }
        });
    });

    var carNamesDiv = document.getElementById("carNames");
    var carNamesData = carNamesDiv.textContent.trim();
    var carNames = carNamesData.slice(1, -1).split(",");

    // Remove any leading or trailing whitespace from each car name
    carNames = carNames.map(function(name) {
        return name.trim();
    });

    $('#carName').on('input', function() {
        var inputVal = $(this).val().toLowerCase();
        var filteredCars = carNames.filter(function(car) {
            return car.toLowerCase().includes(inputVal);
        });

        // Clear previous results
        $('#typeaheadResults').empty();

        // Display up to 5 matching results below the input
        for (var i = 0; i < Math.min(filteredCars.length, 5); i++) {
            var resultListItem = $('<li>').addClass('list-group-item list-group-item-action user-select-none').text(filteredCars[i]);
            resultListItem.appendTo('#typeaheadResults');

            // Set click event to select input with the text of the clicked list item
            resultListItem.click(function() {
                $('#carName').val($(this).text());
                $('#typeaheadResults').empty();
            });
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

function elementFromHtml(html){
    const template = document.createElement("template");
    template.innerHTML = html.trim();
    return template.content.firstElementChild;
}


// ? WIP
async function populateRep(){
    const response = await fetch('/api/data')
    const data = await response.json()

    const repTable = document.getElementById('RepTable');
    repTable.innerHTML = '';


    data.forEach(res => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${res.id}</td>
            <td>${res.name}</td>
            <td>${res.votes}</td>
        `;
        repTable.appendChild(row);
    });
}

