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
const movieReviews = document.querySelector(".movieReviews");

const headers = {
    'Content-Type': 'application/json'
}

const baseURL = "http://localhost:8085/api/v1/capstone";

async function getActionHomePage(){
    firstPage.classList.remove('hide');
    secondPage.classList.add('hide');

    await fetch(`${baseURL}/action`, {
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
                        <button class = "btn btn-primary" onclick = "show(${data[i].movieId})">DETAILS</button>
                    </div>
                </div>`
            adventureOne.innerHTML += movieCard1;
        }
        for(let i = 5; i < data.length; i++){
            let movieCard1 = `
                <div class = "card">
                    <img class = "card-top" src = "${data[i].image}" width = "200" height = "300">
                    <div class = "card-body text-center">
                        <button class = "btn btn-primary" onclick = "show(${data[i].movieId})">DETAILS</button>
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
        firstPage.classList.add('hide');
        secondPage.classList.remove('hide');

        console.log(data);

        clickedImage.innerHTML = '';
        clickedDetails.innerHTML = '';
        titleHeader.innerHTML = '';
        clickedImage.innerHTML = `<img src = "${data.image}" alt = "movie_${data.movie_id}" height = "300">`;
        clickedDetails.innerHTML = `${data.summary}
                                    <br>
                                    <div class = "container buttons">
                                        <button type = "button" class = "btn btn-primary" id = "watchBtn" onclick = "addToWatchedList(${data.movie_id})">+ Watched</button>
                                        <button type = "button" class = "btn btn-primary" onclick = "playTrailer(${data.movie_id})" data-bs-toggle = "modal"
                                                    data-bs-target = "#video-modal">Trailer</button>
                                        <button type = "button" class = "btn btn-primary" id = "interestedBtn" onclick = "addToInterestedList(${data.movie_id})">+ Interested</button>
                                        <br>
                                        <a href = "home.html">Return to Home</a>
                                    </div>
                                    `;
        titleHeader.innerHTML = `${data.title}`;
getReviews(id);
    })
    .catch(err => console.error(err.message))
}

async function getReviews(id){
    await fetch(`${baseURL}/reviews/${id}`,{
        method: "GET",
        headers: headers
    })
    .then(res => res.json())
    .then(data =>
            data.forEach(elem =>{
                let reviewCard = `
                    <div class = "reviewBox">
                        <h4>Rating: ${elem.rating} / 5</h4>
                        <p>"${elem.review}"</p>
                        <hr>
                    </div>
                `
                movieReviews.innerHTML+=reviewCard;
        }))
}

async function playTrailer(id){
    await fetch(`${baseURL}/${id}`, {
            method: "GET",
            headers: headers
        })
        .then(res => res.json())
        .then(data => {
            const box = document.querySelector(".trailer");
            console.log(data.trailer);
            box.innerHTML = `
                <iframe src = "${data.trailer}" width = "100%" height = "500%"></iframe>
            `

        })
        .catch(err => console.log(err.message))
}

async function addToInterestedList(id){
    const interested = document.querySelector("#interestedBtn");
    interested.innerHTML = "Added!";
    interested.disabled = true;

    const response = await fetch(`${baseURL}/interested/${userId}/${id}`, {
        method: "POST",
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return "Successfully Added to interested-List"
    }
}

async function addToWatchedList(id){
    const watchBtn = document.querySelector("#watchBtn");
    watchBtn.innerHTML = "Added!";
    watchBtn.disabled = true;

    const response = await fetch(`${baseURL}/watched/${userId}/${id}`, {
            method: "POST",
            headers: headers
        })
            .catch(err => console.error(err.message))
        if (response.status == 200) {
            return "Successfully Added to watched List"
        }
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

getActionHomePage();

