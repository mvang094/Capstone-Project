//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const topRow = document.querySelector(".top-row");
const bottomRow = document.querySelector(".bottom-row");
const bottom = document.querySelector(".bottom");
const detailBtn = document.querySelector("#details");

const firstPage = document.querySelector(".page-1");
const secondPage = document.querySelector(".page-2");
const titleHeader = document.querySelector(".title-header");
const clickedImage = document.querySelector(".clicked-image");
const clickedDetails = document.querySelector(".clicked-details");

let movieDetails;

const headers = {
    'Content-Type': 'application/json'
}

const baseURL = 'http://localhost:8082/api/capstone';

async function getHomePage(){
    firstPage.classList.remove('hide');
    secondPage.classList.add('hide');

    await fetch(`${baseURL}/homepage`, {
        method: "GET",
        headers: headers
    })
    .then(response => response.json())
    .then(elem =>{
        let movieObj = elem.data;
        console.log(movieObj[1]);
        for(let i = 0; i < 4; i ++){
            let movieCard1 = `
                <div class = "card">
                    <img class = "card-top" src = "${movieObj[i].movie_image}">
                    <div class = "card-body text-center">
                        <button class = "btn btn-primary" onclick = "show(${movieObj[i]})">DETAILS</button>
                    </div>
                </div>`

            topRow.innerHTML += movieCard1;
        }
        for(let i = 4; i < 8; i ++){
            let movieCard2 = `
                <div class = "card">
                    <img class = "card-top" src = "${movieObj[i].movie_image}">
                    <div class = "card-body text-center">
                        <button type = "button" class = "btn btn-primary" onclick = "show(${movieObj[i]})">DETAILS</button>
                    </div>
                </div>`
            bottomRow.innerHTML += movieCard2;
        }
    })
    .catch(err => console.error(err))
}

function show(bodyObj){
    firstPage.classList.add('hide');
    secondPage.classList.remove('hide');

    clickedImage.innerHTML = '';
    clickedDetails.innerHTML = '';
    titleHeader.innerHTML = '';
    clickedImage.innerHTML = `<img src = "${bodyObj.movie_image}" alt = "movie_${bodyObj.movie_id}">`;
    clickedDetails.innerHTML = `${bodyObj.movie_summary}`;
    titleHeader.innerHTML = `${bodyObj.movie_title}`;
}

function showDetails(){}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}
getHomePage();


