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


});
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });

    const selectedSection = document.getElementById(sectionId);
    if (selectedSection) {
        selectedSection.style.display = 'flex';
        selectedSection.style.flexWrap= 'wrap';
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

