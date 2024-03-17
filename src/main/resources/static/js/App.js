document.addEventListener('DOMContentLoaded', function () {
    //showSection('section1');
    //populateRep()
});
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });

    const selectedSection = document.getElementById(sectionId);
    if (selectedSection) {
        selectedSection.style.display = 'block';
    }

    const buttons = document.querySelectorAll('.nav-bar button');
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

