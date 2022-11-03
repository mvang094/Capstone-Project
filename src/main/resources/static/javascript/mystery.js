//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const firstPage = document.querySelector(".page-1");
const adventureOne = document.querySelector(".adventure-one");
const adventureTwo = document.querySelector(".adventure-two");
const secondPage = document.querySelector(".page-2");
const titleHeader = document.querySelector(".title-header");
const clickedImage = document.querySelector(".clicked-image");
const clickedDetails = document.querySelector(".clicked-details");

let movieDetails;

const headers = {
    'Content-Type': 'application/json'
}

const baseURL = "http://localhost:8085/api/v1/capstone";

async function getMystery(){
    firstPage.classList.remove('hide');
    secondPage.classList.add('hide');

    await fetch(`${baseURL}/mystery`, {
        method: "GET",
        headers: headers
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        for(let i = 0; i < 5; i++){
            let movieCard1 = `
                <div class = "card">
                    <img class = "card-top" src = "${data[i].image}" width = "200" height = "300">
                    <div class = "card-body text-center">
                        <button class = "btn btn-primary" onclick = "show(${data[i].movie_id})">DETAILS</button>
                    </div>
                </div>`
            adventureOne.innerHTML += movieCard1;
        }
        for(let i = 5; i < data.length; i++){
            let movieCard1 = `
                <div class = "card">
                    <img class = "card-top" src = "${data[i].image}" width = "200" height = "300">
                    <div class = "card-body text-center">
                        <button class = "btn btn-primary" onclick = "show(${data[i].movie_id})">DETAILS</button>
                    </div>
                </div>`
            adventureTwo.innerHTML += movieCard1;
        }
    })
}
async function show(id){

    await fetch(`${baseURL}/${id}`, {
        method: "GET",
        headers: headers
    })
    .then(res => res.json())
    .then(data => {
        console.log(data);
        firstPage.classList.add('hide');
        secondPage.classList.remove('hide');

        clickedImage.innerHTML = '';
        clickedDetails.innerHTML = '';
        titleHeader.innerHTML = '';
        clickedImage.innerHTML = `<img src = "${data.image}" alt = "movie_${data.movie_id}" height = "300">`;
        clickedDetails.innerHTML = `${data.summary}
                                    <br>
                                    <div class = "container buttons">
                                        <button type = "button" class = "btn btn-primary" onclick = "addToWatchList(${data.movie_id})">+ Watched</button>
                                        <button type = "button" class = "btn btn-primary" onclick = "playTrailer(${data.movie_id})">Trailer</button>
                                        <button type = "button" class = "btn btn-primary" onclick = "addToInterestedList(${data.movie_id})">+ Interested</button>
                                        <br>
                                        <a href = "mystery.html">Return</a>
                                    </div>
                                    `;
        titleHeader.innerHTML = `${data.title}`;
    })
    .catch(err => console.error(err.message))
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

getMystery();



