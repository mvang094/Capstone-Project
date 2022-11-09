//Cookie
const cookieArr = document.cookie.split("=")
console.log(cookieArr);
const userId = cookieArr[1];

const watchedBox = document.querySelector(".watchedBox");
const review = document.querySelector("#movie-body");
const image = document.querySelector(".image");
const title = document.querySelector("#movie-edit-modal-title");
const updateNoteBtn = document.querySelector("#update-movie-button");
const radioButtons = document.querySelectorAll('input[name="inlineRadioOptions"]');

let rating;

const headers = {
    'Content-Type': 'application/json'
}

const baseURL = "http://localhost:8085/api/v1/capstone/watched";

async function getAllWatched(){

    await fetch(`${baseURL}/${userId}`, {
        method: "GET",
        headers: headers
    })
    .then(response => response.json())
    .then(data =>
            data.forEach(elem =>{
            console.log(data);
                              let movieCard = `
                                  <div class="col">
                                       <div class="card text-center">
                                            <img src="${elem.image}" class="card-img-top" alt="movie_${elem.movie_id}">
                                            <div class="card-body">
                                                 <h4 class="card-title">${elem.title}</h4>
                                                 <div class="card-body text-center">

                                                      <button type = "button" class = "btn btn-primary" onclick = "getMovieById(${elem.movie_id})"
                                                                      data-bs-toggle = "modal" data-bs-target = "#movie-edit-modal"><b>+<b> Review</button>
                                                      <button type = "button" class = "btn btn-primary" onclick = "deleteMovie(${elem.movie_id})">Delete</button>
                                                 </div>
                                            </div>
                                       </div>
                                 </div>
                              `
//                              movieRating(elem.movie_id);
                              watchedBox.innerHTML += movieCard;
    }))
    .catch(error => console.log(error.message));
}

async function movieRating(id){
console.log(id)
    await fetch(`${baseURL}/rating/${userId}/${id}`, {
            method: "GET",
            headers: headers
        })
        .then(res => res.json())
        .then(data => {
            console.log(data)
            rating = `${data.rating}`;
            console.log(rating);
            const stars = document.querySelector("#stars");
            stars.innerHTML = `${rating}`;
        })
        .catch(err => console.log(err.message))
}


async function getMovieById(id){

    await fetch(`${baseURL}/${userId}/${id}`,{
        method: "GET",
        headers: headers
    })
    .then(res => res.json())
    .then(data => populateModal(data))
    .catch(err => console.error(err.message))
}

const populateModal = (obj) =>{
    title.innerHTML = `Add review for ${obj.title}`;
    image.innerHTML = `<img src = "${obj.image}" alt = "movie_${obj.movie_id}">`
    updateNoteBtn.setAttribute('data-movie-id', obj.movie_id)
}

async function addReview(id){

    let bodyObj = {
        rating: rating,
        review: review.value,
    }
    console.log(bodyObj);

    await fetch(`${baseURL}/review/${userId}/${id}`, {
            method: "PUT",
            body: JSON.stringify(bodyObj),
            headers: headers
    })

    review.value = ``;
    for (const radio of radioButtons)
        radio.checked = false;

    return location.reload();
}

async function deleteMovie(id){
    await fetch(`${baseURL}/${userId}/${id}`, {
            method: "DELETE",
            headers: headers
    })
    .catch(err => console.error(err))

    return location.reload();
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

getAllWatched();
getUserName();

updateNoteBtn.addEventListener("click", (e)=>{
    let movieId = e.target.getAttribute('data-movie-id');
    for(const radio of radioButtons)
    {
        if(radio.checked){
            rating = radio.value;
            if (rating === "option1")
                rating = 1;
            else if(rating === "option2")
                rating = 2;
            else if (rating === "option3")
                rating = 3;
            else if (rating === "option4")
                rating = 4;
            else if (rating === "option5")
                rating = 5;
            break;
        }
    }
    addReview(movieId);
})