//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

console.log("Connected to interested.js");

const interestedBox = document.querySelector(".interestedBox");

const headers = {
    'Content-Type': 'application/json'
}

const baseURL = "http://localhost:8085/api/v1/capstone/interested";

async function getUserName(){

    await fetch("http://localhost:8085/api/v1/users/" + `${userId}`,{
         method: "GET",
         headers: headers
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        signin.innerHTML = `${data.username}`
    })
    .catch(err => console.log(err.message));
}

async function getAllInterested(){

    await fetch(`${baseURL}/${userId}`, {
        method: "GET",
        headers: headers
    })
    .then(response => response.json())
    .then(data => data.forEach(elem =>{
        console.log(data);
                              let movieCard = `
                                  <div class="col">
                                       <div class="card text-center">
                                            <img src="${elem.image}" class="card-img-top" alt="movie_${elem.movie_id}">
                                            <div class="card-body">
                                                 <h4 class="card-title">${elem.title}</h4>
                                                 <div class="card-body text-center">
                                                      <button type = "button" class = "btn btn-primary" onclick = "deleteMovie(${elem.movie_id})">Delete</button>
                                                      <button type = "button" class = "btn btn-primary" onclick = "addToWatchedList(${elem.movie_id})">Watched</button>
                                                 </div>
                                            </div>
                                       </div>
                                 </div>
                              `
                              interestedBox.innerHTML += movieCard;
    }))
    .catch(error => console.log(error.message));
}

async function deleteMovie(id){
    await fetch(`${baseURL}/${userId}/${id}`, {
            method: "DELETE",
            headers: headers
    })
    .catch(err => console.error(err))

    return location.reload();

}

getAllInterested();